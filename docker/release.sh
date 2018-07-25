#bash
set -e
cd /root/workspace/blog
mvn clean package -Dmaven.test.skip
cp /root/workspace/blog/web/target/blog.jar /root/workspace/blog/docker/
cd /root/workspace/blog/docker
docker build . -t blog:1.0
docker run -p 5201:5201 -v /var/log/blog:/var/log/blog -dit blog:1.0