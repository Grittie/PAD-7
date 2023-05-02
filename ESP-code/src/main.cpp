
/*********
    Rui Santos
  Complete project details at https://randomnerdtutorials.com  
*********/

#include <WiFi.h>
#include <PubSubClient.h>
#include <Wire.h>

// Replace the next variables with your SSID/Password combination
// const char* SSID = "iotroam";
// const char* PASSWORD = "qQl6zb6jjq";

const char* SSID = "Kothuis";
const char* PASSWORD = "luk4s4mu3l";

// Add your MQTT Broker IP address, example:
//const char* MQTT_SERVER = "192.168.1.144";
const char* MQTT_SERVER = "mqtt.hva-robots.nl";

//Add your MQTT username and password here.
const char* MQTT_USERNAME = "kothuil";
const char* MQTT_PASSWORD = "ySDupTfLbgwRssv7xlgs";

WiFiClient espClient;
PubSubClient client(espClient);
uint8_t buttonPressed = 1;
uint8_t buttons[3] = {7, 15, 16};


void setup_wifi() {
	delay(10);
	Serial.println();
	Serial.print("Connecting to ");
	Serial.println(SSID);
	WiFi.begin(SSID, PASSWORD);
	while (WiFi.status() != WL_CONNECTED) {
		delay(1000);
		Serial.print(".");
	}
	Serial.printf("%s","\nWiFi connected.\nIP address: ");
	Serial.println(WiFi.localIP());
}

void callback(char* topic, byte* message, unsigned int length) {
	Serial.print("Message arrived on topic: ");
	Serial.print(topic);
	Serial.print(". Message: ");
	String messageTemp;
	
	for (int i = 0; i < length; i++) {
		Serial.print((char)message[i]);
		messageTemp += (char)message[i];
	}
	Serial.println();
}

void reconnect() {
	// Loop until we're reconnected
	while (!client.connected()) {
		Serial.print("Attempting MQTT connection...");
		// Attempt to connect
		if (client.connect("Testclient", MQTT_USERNAME, MQTT_PASSWORD)) {
			Serial.println("connected");
			client.subscribe("kothuil/output");
		} else {
			Serial.print("failed, rc=");
			Serial.print(client.state());
			Serial.println(" try again in 5 seconds");
			delay(5000);
		}
	}
}

void setup() {
	uint8_t buttons[3] = {7, 15, 16};
	Serial.begin(115200);
	setup_wifi();
	client.setServer(MQTT_SERVER, 1883);
	client.setCallback(callback);
	for (int i = 0; i < 3; ++i) {
		pinMode(buttons[i], INPUT);
	}
}

int button(uint8_t buttons[]) {
	for (int i = 0; i < 3; ++i) {
		if (digitalRead(buttons[i])) {
		return i + 1;
		}
	}
	return 0;
}


void loop() {
	uint8_t buttonState = 0;
	if (!client.connected()) {
		reconnect();
	}
	client.loop();
	uint8_t pressed = button(buttons);
	if (pressed != 0) {
		buttonState = 1;
		}
	if (buttonState && !buttonPressed && pressed) {
		Serial.printf("%s%d\n","Button pressed: ", pressed);
		buttonPressed = 1;
		client.publish("kothuil/pressed", "testing");
	} else if (!buttonState && buttonPressed){
		buttonPressed = 0;
	}
	delay(50);
}