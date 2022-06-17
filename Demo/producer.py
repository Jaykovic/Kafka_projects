from kafka import KafkaProducer
import json
import time
from test import get_registered_user


def json_serializer(test):
    return json.dumps(test).encode("utf-8")


producer = KafkaProducer(bootstrap_servers=['192.168.22.150:9092'],
                         value_serializer=json_serializer)

if __name__ == "__main__":
    while 1 == 1:
        registeredUser = get_registered_user
        print(registeredUser)
        producer.send("registered_user", registeredUser)
        time.sleep(5)
