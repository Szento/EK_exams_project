FROM amazoncorretto:26-alpine

RUN apk update && apk add --no-cache curl

COPY target/app.jar /app.jar

EXPOSE 8000

CMD ["java", "-jar", "/app.jar"]