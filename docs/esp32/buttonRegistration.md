# User story: Button Registration


## User story description:
As a future student, I want the programm to register button presses

## Used modules:
- WiFi.h
- PubSubClient.h
- Wire.h

## The C Code:
When the ESP32 first gets power it will immideatly try to connect to the saved wifi network and the [MQTT](../javaClasses/MQTT.md) server. <br> If it can't connect to the wifi network it will keep trying forever till it connect. The same applies for the [MQTT](../javaClasses/MQTT.md) server. <br>
When the user presses a button, the coresponding answer (Yes, Maybe, No) is sent to the [MQTT](../javaClasses/MQTT.md) server.

<img src="../../assets/loopbuttoncode.png" width="1000" >