# User story: Button Registration

[◄ back to index](../index.md)

## User story description:
As a future student, I want the programm to register button presses

## Used modules:
- WiFi.h
- PubSubClient.h
- Wire.h

## The C Code:
When the ESP32 first gets power it will immideatly try to connect to the saved wifi network and the [MQTT](../javaClasses/MQTT.md) server. <br>
If it can't connect to the wifi network it will keep trying forever till it connect. The same applies for the [MQTT](../javaClasses/MQTT.md) server. <br>
When the user presses a button, the coresponding answer (Yes, Maybe, No) is sent to the [MQTT](../javaClasses/MQTT.md) server.

<img src="../../assets/loopbuttoncode.png" width="1000" >

## Leds on and off:
Now when a message received on the "gritla/led" topic is calls the led function to turn all the leds on.
<img src="../../assets/loopbuttoncoderecieve.png" width="1000" >

When the user pressed a button the pressed led will stay on and the others turn of, after a few second the pressed led also turns off.
<img src="../../assets/loopbuttoncodeleds.png" width="1000" >
