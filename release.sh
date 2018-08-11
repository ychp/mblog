#!/usr/bin/env bash
set -e
echo 'pull newest code'
git pull

local_path=`pwd`

echo 'package'
mvn clean package -Dmaven.test.skip

echo 'make image'
cp ${local_path}/web/target/blog.jar ${local_path}/docker/
cd ${local_path}/docker
docker build . -t blog:1.0

echo 'start service'
if [ -f ~/blog.sh ];
then
    sh ~/blog.sh
else
    echo 'if [ -f /var/run/blog-api.pid ];' >> ~/blog.sh
    echo 'then' >> ~/blog.sh
    echo '  pid=`cat /var/run/blog-api.pid`' >> ~/blog.sh
    echo '  docker stop $pid' >> ~/blog.sh
    echo '  docker rm $pid' >> ~/blog.sh
    echo '  rm /var/run/blog-api.pid' >> ~/blog.sh
    echo 'fi;' >> ~/blog.sh
    echo 'pid=`docker run -p 8099:8099 -v /var/log/blog_new:/var/log/blog \' >> ~/blog.sh

    echo "请输入数据库连接:"
    read mysql_host

    echo "请输入数据库端口:"
    read mysql_port

    echo "请输入数据库名称:"
    read mysql_database

    echo "请输入数据库密码:"
    read mysql_password

    echo '-e MYSQL_HOST='${mysql_host}' \' >> ~/blog.sh
    echo '-e MYSQL_PORT='${mysql_port}' \' >> ~/blog.sh
    echo '-e MYSQL_DATABASE='${mysql_database}' \' >> ~/blog.sh
    echo '-e MYSQL_PASSWORD='${mysql_password}' \' >> ~/blog.sh

    echo "请输入redis链接:"
    read redis_host

    echo "请输入redis端口:"
    read redis_port

    echo "请输入redis密码:"
    read redis_password

    echo '-e REDIS_HOST='${redis_host}' \' >> ~/blog.sh
    echo '-e REDIS_PORT='${redis_port}' \' >> ~/blog.sh
    echo '-e REDIS_AUTH='${redis_password}' \' >> ~/blog.sh

    echo "请输入对象存储服务类型:"
    read file_type
    echo '-e FILE_TYPE='${file_type}' \' >> ~/blog.sh

    if [ "$file_type" == "cos" ];
    then
        echo "请输入COS secretId:"
        read cos_secret_id
        echo '-e COS_SECRET_ID='${cos_secret_id}' \' >> ~/blog.sh

        echo "请输入COS secretKey:"
        read cos_secret_key
        echo '-e COS_SECRET_KEY='${cos_secret_key}' \' >> ~/blog.sh

        echo "请输入COS appId:"
        read cos_app_id
        echo '-e COS_APP_ID='${cos_app_id}' \' >> ~/blog.sh

        echo "请输入COS bucketName:"
        read cos_bucket_name
        echo '-e COS_BUCKET_NAME='${cos_bucket_name}' \' >> ~/blog.sh

        echo "请输入COS region:"
        read cos_region
        echo '-e COS_REGION='${cos_region}' \' >> ~/blog.sh
    fi;

    echo '-dit blog:1.0`' >> ~/blog.sh
    echo 'echo $pid >> /var/run/blog-api.pid' >> ~/blog.sh
    firewall-cmd --permanent --add-port=8099/tcp
    firewall-cmd --reload
    sh ~/blog.sh
fi;
