FROM amazoncorretto:17

WORKDIR /backend

COPY . .
RUN chmod +x ./gradlew

EXPOSE 8080
CMD ["./gradlew", "bootRun"]