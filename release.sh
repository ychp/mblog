#!/usr/bin/env bash
set -e
echo 'pull newest code'
git checkout develop
git pull

local_path=`pwd`

echo 'package'
mvn clean package -Dmaven.test.skip -U

echo 'make image'
cp ${local_path}/web/target/blog.jar ${local_path}/docker/test/
cd ${local_path}/docker/test
docker build . -t blog-dev:1.0

echo 'start service'
if [ -f ~/blog-dev.sh ];
then
    sh ~/blog-dev.sh
else
    echo 'if [ -f /var/run/blog-dev-api.pid ];' >> ~/blog-dev.sh
    echo 'then' >> ~/blog-dev.sh
    echo '  pid=`cat /var/run/blog-dev-api.pid`' >> ~/blog-dev.sh
    echo '  docker stop $pid' >> ~/blog-dev.sh
    echo '  docker rm $pid' >> ~/blog-dev.sh
    echo '  rm /var/run/blog-dev-api.pid' >> ~/blog-dev.sh
    echo 'fi;' >> ~/blog-dev.sh
    echo 'pid=`docker run -p 8099:8099 -v /var/log/blog_new_dev:/var/log/blog --link mysql:mysql --link redis:redis --name blog-dev \' >> ~/blog-dev.sh

    echo "请输入数据库名称:"
    read mysql_database

    echo "请输入数据库密码:"
    read mysql_password

    echo '-e MYSQL_HOST=mysql \' >> ~/blog-dev.sh
    echo '-e MYSQL_PORT=3306 \' >> ~/blog-dev.sh
    echo '-e MYSQL_NAME=root \' >> ~/blog-dev.sh
    echo '-e MYSQL_DATABASE='${mysql_database}' \' >> ~/blog-dev.sh
    echo '-e MYSQL_PASSWORD='${mysql_password}' \' >> ~/blog-dev.sh

    echo "请输入redis密码:"
    read redis_password

    echo '-e REDIS_HOST=redis \' >> ~/blog-dev.sh
    echo '-e REDIS_PORT=6379 \' >> ~/blog-dev.sh
    echo '-e REDIS_AUTH='${redis_password}' \' >> ~/blog-dev.sh

    echo "请输入es链接:"
    read es_host

    echo "请输入es端口:"
    read es_port

    echo "请输入es集群名称:"
    read es_cluster_name

    echo '-e ES_HOST='${es_host}' \' >> ~/blog-dev.sh
    echo '-e ES_PORT='${es_port}' \' >> ~/blog-dev.sh
    echo '-e ES_CLUSTER_NAME='${es_cluster_name}' \' >> ~/blog-dev.sh

    echo "请输入对象存储服务类型:"
    read file_type
    echo '-e FILE_TYPE='${file_type}' \' >> ~/blog-dev.sh

    if [ "$file_type" == "cos" ];
    then
        echo "请输入COS secretId:"
        read cos_secret_id
        echo '-e COS_SECRET_ID='${cos_secret_id}' \' >> ~/blog-dev.sh

        echo "请输入COS secretKey:"
        read cos_secret_key
        echo '-e COS_SECRET_KEY='${cos_secret_key}' \' >> ~/blog-dev.sh

        echo "请输入COS appId:"
        read cos_app_id
        echo '-e COS_APP_ID='${cos_app_id}' \' >> ~/blog-dev.sh

        echo "请输入COS bucketName:"
        read cos_bucket_name
        echo '-e COS_BUCKET_NAME='${cos_bucket_name}' \' >> ~/blog-dev.sh

        echo "请输入COS region:"
        read cos_region
        echo '-e COS_REGION='${cos_region}' \' >> ~/blog-dev.sh
    fi;

    echo '-dit blog-dev:1.0`' >> ~/blog-dev.sh
    echo 'echo $pid >> /var/run/blog-dev-api.pid' >> ~/blog-dev.sh
    echo 'tail -f /var/log/blog_new_dev/root.log' >> ~/blog-dev.sh
    sh ~/blog-dev.sh
fi;
