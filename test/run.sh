#!/bin/sh
cd $(dirname $0)

cd ../gs-serving-web-content

mvn clean package spring-boot:repackage -DskipTests
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi
mkdir target/dependency
(cd target/dependency; jar -xf ../*.jar)
# Build a Docker Image with Maven
docker build -f Dockerfile -t mgperez/serving-web-content .
rm -rf target

# To push to a Docker registry
docker push mgperez/serving-web-content

cd ../gs-spring-boot-docker

mvn clean package spring-boot:repackage -DskipTests
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi
mkdir target/dependency
(cd target/dependency; jar -xf ../*.jar)
# Build a Docker Image with Maven
docker build -f Dockerfile -t mgperez/gs-spring-boot-docker .
rm -rf target

exit