FROM adoptopenjdk/openjdk11
VOLUME /tmp
COPY SERVICE_NAME-boot/build/libs/SERVICE_NAME-boot-SERVICE_VERSION.jar app.jar
ENTRYPOINT ["java", "-Duser.timezone='Asia/Seoul'", "-Djava.security.egd=file:/dev/./urandom", "-Xmx256m", "-jar","/app.jar"]
