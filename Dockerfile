FROM openjdk:8-alpine

COPY target/uberjar/did-homepage.jar /did-homepage/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/did-homepage/app.jar"]
