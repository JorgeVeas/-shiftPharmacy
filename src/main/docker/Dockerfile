FROM openjdk:8-jdk-alpine

# Default copy (Gradle)
ADD build/libs/ssiond-flightavl-be-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

#Add JVM heap limits
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom "

ENTRYPOINT [ \
   "java", \
   "-jar", \
   "/app.jar" \
]