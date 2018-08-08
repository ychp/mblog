FROM openjdk:8
ADD blog.jar application.jar

ENV MYSQL_HOST="127.0.0.1"
ENV MYSQL_PORT="3306"
ENV MYSQL_DATABASE="blog"
ENV MYSQL_PASSWORD="anywhere"
ENV REDIS_HOST="127.0.0.1"
ENV REDIS_PORT="6379"
ENV REDIS_AUTH="anywhere"

EXPOSE 8099

ENTRYPOINT ["sh","-c", "java", "-Duser.timezone=Asia/Shanghai",
  "-DMYSQL_HOST=$MYSQL_HOST",
  "-DMYSQL_PORT=$MYSQL_PORT",
  "-DMYSQL_DATABASE=$MYSQL_DATABASE",
  "-DMYSQL_PASSWORD=$MYSQL_PASSWORD",
  "-DREDIS_HOST=$REDIS_HOST",
  "-DREDIS_PORT=$REDIS_PORT",
  "-DREDIS_AUTH=$REDIS_AUTH",
  "-jar", "application.jar", "--spring.profiles.active=test"]