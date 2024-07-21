FROM maven:3.9.7-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /target/telegram-cat-bot-1.0-SNAPSHOT.jar /app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]