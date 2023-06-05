# Questions

[◄ back to index](../index.md)

This is the class that asks the user questions.
First it reads `config/questions.json` for the questions.
Then it asks the question and waits for input via mqtt.
It reminds the student to answer every 10 seconds.
It does this by repeating the question.
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
