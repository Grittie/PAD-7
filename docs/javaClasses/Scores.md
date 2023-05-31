# Scores

This is the class that asks the user questions.
First it reads `config/scores.json` for the questions.
Then it asks the question and waits for input via mqtt.
It reminds the student to answer after 10 seconds.
After all the questions have been answered it tells another class to generate an image with the results.

## PlantUML diagram

```puml
@startuml

class Questions {
  + Questions(NAO):
  - mqtt: MQTT
  - isPressed: boolean
  - session: Session
  - score: long[]
  - nao: NAO
  - parser: JSONParser
  - answers: ArrayList
  + askAllQuestions(): void
  - getMqtt(): MQTT
  + listen(): void
  - getJsonParser(): JSONParser
  + parseJson(): void
}

class Reminder {
  ~ Reminder(int):
  - time: int
  + run(): void
}

Questions -[#820000]- Reminder
@enduml
```
