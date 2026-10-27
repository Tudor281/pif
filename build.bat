@echo off

cd pif

call mvn clean compile assembly:single

copy target\pif-1.0-SNAPSHOT-jar-with-dependencies.jar C:\Programs\pif

copy ai.txt C:\Programs\pif

pause