FROM amazoncorretto:17

WORKDIR /backend

COPY . .
RUN chmod u+x ./gradlew

CMD ["./gradlew", "bootRun"]