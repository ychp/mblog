#!/usr/bin/env bash
set -e
local_path=`pwd`
mvn clean package -Dmaven.test.skip
cp ${local_path}/web/target/blog.jar ${local_path}/docker/
cd ${local_path}/docker
docker build . -t blog:1.0

if [ -f ~/blog.sh ];
then
    sh ~/blog.sh
else
    echo "请输入数据库连接:"
    read mysql_host

    echo "请输入数据库端口:"
    read mysql_port

    echo "请输入数据库名称:"
    read mysql_database

    echo "请输入数据库密码:"
    read mysql_password

    echo "请输入redis链接:"
    read redis_host

    echo "请输入redis端口:"
    read redis_port

    echo "请输入redis密码:"
    read redis_password

    echo 'docker run -p 8099:8099
        -v /var/log/blog:/var/log/blog
        -e MYSQL_HOST='${mysql_host}'
        -e MYSQL_PORT='${mysql_port}'
        -e MYSQL_DATABASE='${mysql_database}'
        -e MYSQL_PASSWORD='${mysql_password}'
        -e REDIS_HOST='${redis_host}'
        -e REDIS_PORT='${redis_port}'
        -e REDIS_AUTH='${redis_password}'
        -dit blog:1.0' >> ~/blog.sh
    firewall-cmd --permanent --add-port=8099/tcp
    firewall-cmd --reload
    sh ~/blog.sh
fi;