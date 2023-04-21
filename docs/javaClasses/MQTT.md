# MQTT

The MQTT class consist of a MQTT client that has the ability to subscribe to a topic 
and listen to recieved messages. 

After receiving a message, it gets checked to see if it's a call to start another function in the project.

## PlantUML diagram

```puml
@startuml

class src.MQTT {
+ {static} String MQTT_HOST
+ {static} String MQTT_CLIENT_ID
+ {static} String MQTT_USERNAME
+ {static} String MQTT_PASSWORD
- NAO nao
+ void mqttClient()
}
@enduml
```
