FROM openjdk:8-jre-alpine

# Set the location of the verticles
ENV APP_HOME /usr/app

ENV JVM_XMX 128m

# Copy your fat jar to the container
COPY target/*.jar $APP_HOME/app.jar

COPY src/main/resources/*.yml $APP_HOME/conf/

COPY bin/entrypoint.sh $APP_HOME/bin/entrypoint.sh
RUN chmod a+x $APP_HOME/bin/entrypoint.sh
#add data for timezone if not we can not get correct date time
RUN apk add --no-cache tzdata curl
ENV TZ Asia/Ho_Chi_Minh

# Launch the verticle
WORKDIR $APP_HOME

ENTRYPOINT ["bin/entrypoint.sh"]
