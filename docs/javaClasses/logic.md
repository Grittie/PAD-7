# Main Logic

[◄ back to index](../index.md)

The Main Logic of the study check revolves around asking questions, listening for answers.
and storing the values of score for each field.

## PlantUML diagram

```puml
@startuml
class src.Questions {
- {static} Session session
- {static} ALTextToSpeech textToSpeech
- {static} JSONParser parser
- {static} MQTT mqtt
- {static} long[] score
- {static} boolean isPressed
- {static} ArrayList answers
- {static} JSONParser getJsonParser()
- {static} MQTT getMqtt()
+ void remind(int)
+ void listen()
+ void parseJson()
+ void askAllQuestions()
  }
@enduml
```
