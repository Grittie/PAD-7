// MQTT client credentials
mqttClient = new MQTTClient({
    clientId: 'gritla_1',
    host: 'mqtt.hva-robots.nl',
    port: 443,
    username: 'gritla',
    password: 'D6G9E1b95x8h3LaGFtxA'
}, this.scope);

mqttClient.connect();

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

// Set timeout function
setTimeout(function(){
    // Query selector that adds randomizes no cache to image to prevent the website from loading the cache
    document.querySelector("img").src += "?nocache=" + Math.random();
    window.location.reload(1);
}, 5000);