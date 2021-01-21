#!/bin/env python3
import adafruit_dht
import board
import mysql.connector;

sensor = adafruit_dht.DHT22(board.D4)

humidity = sensor.humidity
temperature = sensor.temperature

print(f"Using temperature={temperature}*C and humidity={humidity}%")
db = mysql.connector.connect(host="localhost",user="sike",passwd="you thought")
cursor = db.cursor()
sql = "INSERT INTO climate (Date, Temperature, Humidity) VALUES(curtime(), %s, %s)"
val = (temperature, humidity)
cursor.execute("USE logging")
cursor.execute(sql, val)
db.commit()
