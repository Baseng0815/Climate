#!/usr/bin/env python3

import adafruit_dht
import psycopg2
from datetime import datetime

data_pin = 4
sensor = adafruit_dht.DHT22(data_pin)

db_connection = psycopg2.connect(user='pi', host='127.0.0.1', port='5432', database='pi')
db_cursor = db_connection.cursor()

temperature = sensor.temperature
humidity = sensor.humidity
if humidity is not None and temperature is not None:
    print(f"Temperature={temperature} humidity={humidity}")
    db_cursor.execute(f'INSERT INTO climate VALUES (\'{datetime.now().isoformat()}\', {temperature}, {humidity})')
    db_connection.commit()
