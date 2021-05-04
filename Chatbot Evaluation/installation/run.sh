#!/bin/sh

cd ./xatkit/xatkit-examples/MdeBot
mvn clean compile
mvn exec:java -Dexec.mainClass="com.xatkit.example.MdeBot"