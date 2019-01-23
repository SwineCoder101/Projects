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
  
#trigger deployment  
#for_each instance  
#  send shutdown signal
#  remove from loadbalancer
#  wait for shutdown
#  remove old version
#  spin up new version
#  wait for startup
#  add to loadbalancer
#done