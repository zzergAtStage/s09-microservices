#!/bin/bash

function build_basic() {
  JAR_FILE=$1
  APP_NAME=$2
  JAR_PORT=$3

  docker build -f ./build-scripts/docker/basic/Dockerfile \
    --build-arg JAR_FILE=${JAR_FILE} \
    --build-arg JAR_PORT=${JAR_PORT} \
    -t ${APP_NAME}:latest \
    -t ${APP_NAME}:naive .
}

APP_VERSION=0.0.1-SNAPSHOT

# Building the app
cd ..

echo "Building JAR files"
mvn clean package -DskipTest

echo "Building Docker images"
build_basic ./config-server/target/config-server-${APP_VERSION}.jar application/config-server 8888
build_basic ./discovery-service/target/discovery-service-${APP_VERSION}.jar application/discovery-service 8761
build_basic ./Examinator-core/target/Examinator-core-${APP_VERSION}.jar application/examinator 8093
build_basic ./s09-mathexaminer/target/s09-mathexaminer-${APP_VERSION}.jar application/provider-math 8083
build_basic ./s09-history/target/s09-history-${APP_VERSION}.jar application/provider-history 8085
build_basic ./api-gateway/target/api-gateway-${APP_VERSION}.jar application/api-gateway 8765