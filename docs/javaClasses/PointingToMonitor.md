# User story: PointingToMonitor


## User story description:
The future student wants the robot to point to the monitor/presentation
so that the robot can show what it is talking about.

## Used modules:
- Application
- ALMotion


## PlantUML:
<img src="../../assets/plantUML.png" width="1000">


## Class diagram:
<img src="../../assets/wijzenmonitordiag.png" width="200">

## The Java Class:
It then specifies an array of joint names and an array of joint angles that the robot should move to.<br>The loop iterates 
over each joint in the arrays and sets the joint angles using the "setAngles()" method provided by the ALMotion class.

<img src="../../assets/wijzenmonitor%20class.png" width="1000" >

