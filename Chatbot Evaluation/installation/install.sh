#!/bin/sh

sudo apt update
sudo apt install openjdk-8-jdk openjdk-8-jre
sudo update-java-alternatives --jre-headless --jre --set java-1.8.0-openjdk-amd64

sudo apt install maven

git clone https://github.com/xatkit-bot-platform/xatkit.git
cd xatkit
git submodule update --init --recursive

mvn clean install -DskipTests

cd ..

git clone https://github.com/smishy05/BotSC.git
cp -r ./BotSC/MdeBot ./xatkit/xatkit-examples
cp ./BotSC/admin.html ./xatkit/platforms/xatkit-react-platform/target/classes/admin
cp ./BotSC/admin.html ./xatkit/platforms/xatkit-react-platform/src/main/resources/admin

cd xatkit
mvn clean install -DskipTests