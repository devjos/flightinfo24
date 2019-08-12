#!/bin/sh

sqlite3 flights.sqlite "CREATE TABLE flights (time INT,latitude REAL,longitude REAL,speed INT,altitude INT,flightnumber TEXT,airline TEXT,aircraft TEXT,callsign TEXT);" ".mode csv" ".import logs/flights.log flights" "SELECT aircraft, MIN(altitude), ROUND(AVG(altitude),1), MAX(altitude), COUNT(*) FROM flights GROUP BY aircraft ORDER BY AVG(altitude) DESC;" ".exit"

rm flights.sqlite
