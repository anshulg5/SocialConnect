#!/bin/sh

APP=elpis
echo "Running post deploy script"
chown -R $APP:$APP /opt/$APP
mkdir -p /logs/$APP
chown -R $APP:$APP /logs/$APP
sudo chmod +x /etc/init.d/$APP
sudo service $APP restart
echo "Post deploy script done"