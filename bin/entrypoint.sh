#!/bin/sh

jdbc_url="$(echo "$JDBC_URL" | sed -e 's/\//\\\//g')"

sed -i  -e  "s/{{JDBC_URL}}/$jdbc_url/g" \
        -e  "s/{{JDBC_USERNAME}}/$JDBC_USERNAME/g" \
        -e  "s/{{JDBC_PASSWORD}}/$JDBC_PASSWORD/g" conf/application-$ENV.yml

cat conf/application-$ENV.yml

java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n \
    -Xms32m \
    -Xmx$JVM_XMX \
    -Dfile.encoding=UTF-8 \
    -Duser.timezone=Asia/Ho_Chi_Minh \
    -Duser.language=en \
    -Duser.country=US \
    -Djava.io.tmpdir=/tmp \
    -Dcom.sun.management.jmxremote.port=9999 \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Dcom.sun.management.jmxremote.rmi.port=9999 \
    -Dspring.profiles.active=$ENV \
    -Dsentry.dsn=$SENTRY_DSN \
    -jar app.jar \
    --spring.config.location=conf/
