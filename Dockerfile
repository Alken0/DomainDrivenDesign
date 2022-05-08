FROM gradle:7-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
RUN unzip build/distributions/*.zip -d build/distributions/unzip


FROM openjdk:16
EXPOSE 8080
ENV BARTLIN_HOST="0.0.0.0"
RUN mkdir /app
COPY --from=build /home/gradle/src/build/distributions/unzip/* /app/bartlin
ENTRYPOINT ["/app/bartlin/bin/bartlin"]
