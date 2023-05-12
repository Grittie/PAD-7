# User story: LED


## User story description:
The future student wants the robot's colors/leds to change when choosing a learning path

## Used modules:
These modules come from the NAOqi api:
- Session
- ALLeds


## PlantUML:
<img src="../../assets/plantumlLED.png" width="1000">


## Class diagram:
<img src="../../assets/LED.png" width="350">

## The Java Class:
The switch case ensures that the robot becomes a different color with a certain input. <br>
The alLeds.fadeRGB method is used to specify which LEDs it should apply to and what the intensity of the LEDs is.

<img src="../../assets/ledcode.png" width="1000" >

