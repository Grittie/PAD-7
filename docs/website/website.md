# Website

We developed a website to demonstrate multiple functions for our project. 
The website was created using HTML and JavaScript. We opted for HTML and JavaScript rather than more robust options like PHP with its various frameworks or using JavaScript frameworks,
due to the low requirements for this website.

Nevertheless, we leveraged [Materialize CSS](https://materializecss.com/), a CSS framework, to make the website visually appealing and easier to navigate.

## Functions
Using MQTT we made the website a MQTT client that connected to our broker, the website would subscribe to a topic but also be able to send messages to the broker for another client to use.

In this example we made a container holding various buttons that would send a message to a broker which would trigger the NAO to give a presentation.
### HTML
```html
<!--Buttons for presentation-->
<div class="container">
    <div class="section" id="route-buttons">
        <br>
        <a class="red waves-effect waves-light btn-large" onclick="mqttPublish('gritla/leerroute','SE')"><i class="material-icons left">laptop</i>SE</a>
        <a class="orange waves-effect waves-light btn-large" onclick="mqttPublish('gritla/leerroute','TI')"><i class="material-icons left">adb</i>TI</a>
        <a class="green waves-effect waves-light btn-large" onclick="mqttPublish('gritla/leerroute','CS')"><i class="material-icons left">lock</i>CS</a>
        <a class="blue waves-effect waves-light btn-large" onclick="mqttPublish('gritla/leerroute','GD')"><i class="material-icons left">gamepad</i>GD</a>
        <a class="purple waves-effect waves-light btn-large" onclick="mqttPublish('gritla/leerroute','BIM')"><i class="material-icons left">monetization_on</i>BIM</a>
        <br>
    </div>
    <br><br>
</div>
```
### Javascript
```javascript
mqttClient.on('connected', function () {
    document.getElementById("logo-container").innerHTML = "Connected!";
    console.log('connected');
});

mqttClient.on('connectionLost', function () {
    document.getElementById("logo-container").innerHTML = "Not connected!";
    console.log('ConnectionLost');
});

function mqttPublish(topic, message){
    mqttClient.publish(topic, message);
    console.log('published');
}
```

Another big function of the website is the presenting of our generated [QR codes](../website/QRcode.md) which can be viewed here.