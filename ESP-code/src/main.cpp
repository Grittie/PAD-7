#include <WiFi.h>
#include <PubSubClient.h>
#include <Wire.h>

// Define Wi-Fi credentials.
// const char* WIFI_NAME = "iotroam";
// const char* WIFI_PASSWORD = "qQl6zb6jjq";
const char* WIFI_NAME = "Kothuis";
const char* WIFI_PASSWORD = "luk4s4mu3l";

// Define MQTT server and credentials.
const char* MQTT_SERVER = "mqtt.hva-robots.nl";
const char* MQTT_USERNAME = "kothuil";
const char* MQTT_PASSWORD = "ySDupTfLbgwRssv7xlgs";

// Define pin numbers for buttons.
uint8_t buttons[3] = {7, 15, 16};

// Create instances of Wi-Fi client and PubSubClient.
WiFiClient espClient;
PubSubClient client(espClient);

// Define function to handle incoming MQTT messages.
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

// Define function to initiates the buttons, try's to connect to the wifi network and connects to the MQTT server.
void setup() {
	// Initialize serial communication at a baud rate of 115200.
	Serial.begin(115200);
	// Set button pins as input pins.
	for (int i = 0; i < 3; ++i) {
		pinMode(buttons[i], INPUT);
	}
	delay(10);
	Serial.println();
	Serial.print("Connecting to ");
	Serial.println(WIFI_NAME);
	// Connect to Wi-Fi network and print a "." every second until connected.
	WiFi.begin(WIFI_NAME, WIFI_PASSWORD);
	while (WiFi.status() != WL_CONNECTED) {
		delay(1000);
		Serial.print(".");
	}
	Serial.printf("%s","\nWiFi connected.\nIP address: ");
	// When connected print the ip adress that you have connected to.
	Serial.println(WiFi.localIP());
	delay(10);
	// Set MQTT server and callback function.
	client.setServer(MQTT_SERVER, 1883);
	client.setCallback(callback);
}

// Define function to reconnect to MQTT broker.
void reconnect() {
	// Loop until we're reconnected
	while (!client.connected()) {
		Serial.print("Attempting MQTT connection...");
		// Attempt to connect to MQTT broker with client ID "Testclient".
		if (client.connect("Testclient", MQTT_USERNAME, MQTT_PASSWORD)) {
			Serial.println("connected");
			// Subscribe to topic "kothuil/output". If neccesary change "output".
			client.subscribe("kothuil/output");
		} else {
			// If connection fails, print error message and try again in 5 seconds.
			Serial.print("failed, rc=");
			Serial.print(client.state());
			Serial.println(" try again in 5 seconds");
			delay(5000);
		}
	}
}

// Define function to check which button is pressed and return which button is pressed as a number.
int button(uint8_t buttons[]) {
	for (int i = 0; i < 3; ++i) {
		if (digitalRead(buttons[i])) {
		return i + 1;
		}
	}
	return 0;
}

uint8_t buttonPressed = 1;
void loop() {
	uint8_t buttonState = 0;
	// Check if client is connected to MQTT server and try to reconnect if not.
	if (!client.connected()) {
		reconnect();
	}
	// Check for incoming messages from the MQTT server.
	client.loop();
	// Call function button() which checks if a button is pressed and assign that button as a number to variable: "pressed".
	uint8_t pressed = button(buttons);
	// Variables "buttonState" and "buttonPressed" are there to make sure that when a button is pressed it only processes it once instead of repeating it.
	if (pressed != 0) {
		buttonState = 1;
		}
	// If a button hase been pressed send a message to the MQTT server which includes the number of the button that was pressed.
	if (buttonState && !buttonPressed && pressed) {
		Serial.printf("%s%d\n","Button pressed: ", pressed);
		buttonPressed = 1;
		client.publish("kothuil/pressed", "testing");
	// When button is no longer pressed reset the variable "buttonPressed".
	} else if (!buttonState && buttonPressed){
		buttonPressed = 0;
	}
	delay(50);
}