FROM openjdk:8
ADD blog.jar application.jar
EXPOSE 8099
ENTRYPOINT ["sh","-c","java", "-Duser.timezone=Asia/Shanghai", "-jar",, "application.jar", "--spring.profiles.active=test"]