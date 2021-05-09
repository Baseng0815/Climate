#!/usr/bin/env python3

import adafruit_dht
import pymongo
from datetime import datetime

data_pin = 4
sensor = adafruit_dht.DHT22(data_pin)

db_client = pymongo.MongoClient('mongodb://localhost:27017/')
db_collection = db_client.hord.climate

temperature = sensor.temperature
humidity = sensor.humidity
if humidity is not None and temperature is not None:
    print(f"Temperature={temperature} humidity={humidity}")
    db_collection.insert_one({'when': datetime.now().isoformat(),
                              'temperature': temperature, 'humidity': humidity})
