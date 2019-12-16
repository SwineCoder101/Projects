FROM java:8
ADD target/spring-docker-hello.jar spring-docker-hello.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENV SPRING_PROFILE="default"
ENV MONGO_PASSWORD=""

ENTRYPOINT exec java $JAVA_OPTS \
 -javaagent:newrelic/newrelic.jar \
 -Djava.security.egd=file:/dev/./urandom \
 -Dspring.profiles.active=$SPRING_PROFILE \
 -Dmongo.password=$MONGO_PASSWORD \
 -jar app.jar

ENTRYPOINT ["java","-jar","spring-docker-hello.jar"]