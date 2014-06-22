#!/bin/bash

cd /root/bugtracking
echo "Update data from GitHub"
git pull
echo "Rebuild project"
mvn package
echo "Build finished"

echo "Remove /usr/share/tomcat7/webapps/ROOT{.war}"
rm -rf /usr/share/tomcat7/webapps/ROOT.war
rm -rf /usr/share/tomcat7/webapps/ROOT
echo "Copy new /root/bugtracking/target/bugtracking.war to the /usr/share/tomcat7/webapps/ROOT.war"
cp -f /root/bugtracking/target/bugtracking.war /usr/share/tomcat7/webapps/ROOT.war
sudo service tomcat7 restart
echo "Deployment finished"