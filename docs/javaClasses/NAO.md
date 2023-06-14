# NAO

In this class we connect to the NAO. After that the NAO can be controlled with methods from other classes. 

@startuml

!theme plain
top to bottom direction
skinparam linetype ortho

## PlantUML diagram

```puml
class NAO {
+ NAO(String, int):
- application: Application
- movementTalking: MovementTalking
- led: LED
+ led(String): void
+ stand(): void
+ music(): void
+ stopmusic(): void
+ say(String): void
+ setName(String): void
+ waitingloop(): void
  }

@enduml
```
