#!/bin/sh

cd /opt/frule/current
ENV=`cat env`
echo "performing db migrations in $ENV"

/usr/java/default/bin/java -classpath 'classes/:libs/*' -DENV=$ENV com.flock.frule.main.LiquibaseHelper
rc=$?
if [[ $rc != 0 ]]; then
    exit $rc
fi
echo "db migrations done"