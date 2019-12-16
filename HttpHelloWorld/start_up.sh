#!/bin/sh

resources=./src/main/resources

mkdir -p /tmp/data/n1 /tmp/data/n2 /tmp/data/n3

#echo "initialzing replica set..."
###docker rm -f my-service docker run -d --name my-service my_service:v2

docker network create rs0-network

#docker network create rs0-network
docker run --name mongo-node1 -d --net rs0-network mongo --replSet "rs0" --config "$resources/mongo1.conf"
docker run --name mongo-node2 -d --net rs0-network mongo --replSet "rs0" --config "$resources/mongo2.conf"
docker run --name mongo-node3 -d --net rs0-network mongo --replSet "rs0" --config "$resources/mongo3.conf"

#while ( != 3)
instances=$(docker ps -a | grep mongo-node* | grep Up | wc -l)
while(( $instances != 3 ))
do
	echo "Waiting for all mongo instances to start up, checking again in 2 secs..."
	docker ps -a
	sleep 2
	
	if (( $(docker ps -a | grep mongo-node* | grep Exited | wc -l) > 0 ))
	then
		echo "At least one mongo node has failed to start, Removing all exited containers"
		docker rm -f mongo-node1
		docker rm -f mongo-node2
		docker rm -f mongo-node3
		echo "startup now exiting.."
		exit
	fi
done

echo "assigning replica set..."
docker exec -i mongo-node1 mongo < "$resources/init_replica_set.js"

docker build -f Dockerfile -t spring-docker-image .

docker exec spring-docker-image



#docker build -t yourname/mongo-rep-set:latest .
#docker run -d -p 27017:27017 yourname/mongo-rep-set:latest
#docker run -d -p 27017:27017 -e JOURNLING=false yourname/mongo-rep-set:latest
#docker run -d
#  -p 27018:27018 \
  -e MONGO_ROLE="primary" \
  -e MONGO_SECONDARY="hostname or IP of secondary" \
  -e MONGO_ARBITER="hostname or IP of arbiter" \
  -e MONGO_ROOT_USER="myRootUser" \
  -e MONGO_ROOT_PASSWORD="myRootUserPassword" \
  -e MONGO_APP_USER="myAppUser" \
  -e MONGO_APP_PASSWORD="myAppUserPassword" \
  -e MONGO_APP_DATABASE="people" \
  yourname/mongo-rep-set:latest

#docker build -t spring-boot-docker-image
#docker tag spring-boot-docker-image gcr.io/httphelloworld/spring-docker-image:v2
#docker push gcr.io/httphelloworld/spring-docker-image:v2

#######################################################################################################################
#gcr.io/httphelloworld/spring-docker-image
hostname=gcr.io
project_id=httphelloworld
image=spring-docker-image
tag=v3
dest_img="${hostname}/${project_id}/${image}"
dest_img_tag="${dest_img}:${tag}"
cluster_name="${project_id}-cluster"

docker build -f Dockerfile -t $dest_img_path .

docker push $dest_img_tag

kubectl apply -f deployment/rest-service/Deployment.yaml

gcloud container clusters create $cluster_name
kubectl run $cluster_name --image=$dest_img_tag --port=8080
kubectl expose deployment $cluster_name --type="LoadBalancer"
kubectl scale deployment $cluster_name --replicas=3
