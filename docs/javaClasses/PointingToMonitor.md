# User story: PointingToMonitor

[◄ back to index](../index.md)

## User story description:
The future student wants the robot to point to the monitor/presentation
so that the robot can show what it is talking about.

## Used modules:
These modules come from the NAOqi api:
- Application
- ALMotion


## PlantUML:
<img src="../../assets/plantUML.png" width="1000">


## Class diagram:
<img src="../../assets/wijzenmonitordiag.png" width="350">

## The Java Class:
It specifies an array of joint names and an array of joint angles that the robot should move to. <br> The loop iterates 
over each joint in the arrays and sets the joint angles using the "setAngles()" method provided by the ALMotion class.

<img src="../../assets/wijzenmonitor%20class.png" width="1000" >
