#!/usr/bin/env bash
set -e
local_path=`pwd`
mvn clean package -Dmaven.test.skip
cp ${local_path}/web/target/blog.jar ${local_path}/docker/
cd ${local_path}/docker
docker build . -t blog:1.0
docker run -p 520:520 -v /var/log/blog:/var/log/blog -dit blog:1.0