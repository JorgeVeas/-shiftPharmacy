FROM openjdk:8-jdk-alpine

# Default copy (Gradle)
ADD build/libs/shiftPharmacy.0.0-SNAPSHOT.jar app.jar

EXPOSE 8080

#Add JVM heap limits
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom "

ENTRYPOINT [ \
   "java", \
   "-jar", \
   "/app.jar" \
]