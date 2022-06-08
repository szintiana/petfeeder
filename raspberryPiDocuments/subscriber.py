# subscriber.py
import paho.mqtt.client as mqtt
import time
import board
from adafruit_motorkit import MotorKit

def on_connect(client, userdata, flags, rc):
    print(f"Connected with result code {rc}")
    # subscribe, which need to put into on_connect
    # if reconnect after losing the connection with the broker, it will continue to subscribe to the raspberry/topic topic
    client.subscribe("raspberry/topic")

# the callback function, it will be triggered when receiving messages
def on_message(client, userdata, msg):
    print(f"{msg.topic} {msg.payload}")
    kit = MotorKit(i2c=board.I2C())

    for i in range(300):
        kit.stepper1.onestep()
        time.sleep(0.01)

client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message

# set the will message, when the Raspberry Pi is powered off, or the network is interrupted abnormally, it will send the will message to other clients
client.will_set('raspberry/status', b'{"status": "Off"}')

# create connection, the three parameters are broker address, broker port number, and keep-alive time respectively
client.connect("broker.emqx.io", 1883, 60)

