FROM openjdk:8
ADD blog.jar application.jar
EXPOSE 520
CMD ["java","-Duser.timezone=Asia/Shanghai", "-jar", "application.jar", "--spring.profiles.active=test"]