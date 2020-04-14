FROM openjdk:8-jdk-alpine
RUN apk --no-cache --update add busybox-suid bash curl unzip sudo openssh-client shadow wget
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]

# https://spring.io/guides/topicals/spring-boot-docker/

# execute
#./mvnw package -DskipTests && java -jar target/*.jar

#
#The application is then available on http://localhost:8081/springmvc-resttemplate-test

# Build a Docker Image with Maven
#./mvnw clean package spring-boot:repackage -DskipTests
#mkdir target/dependency
#(cd target/dependency; jar -xf ../*.jar)
#$ ls target/*jar
#docker build -f Dockerfile -t mgperez/gs-spring-boot-docker .


# To push to a Docker registry
#% docker login
#% docker push mgperez/gs-spring-boot-docker

#
# docker run -i --rm -p 8081:8081 -d --name back mgperez/gs-spring-boot-docker
# docker exec -ti back bash
#
#The application is then available on http://localhost:8081/springmvc-resttemplate-test

# Stop and remove all containers
# docker container stop $(docker container ls -aq)
# docker container rm $(docker container ls -aq)

#docker rmi -f $(docker images | grep "mgperez" | awk '/ / { print $3 }')
#docker rmi $(docker images --filter "dangling=true" -q --no-trunc)