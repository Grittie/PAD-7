# questions.json

The question logic is written in JSON, this way it is made easy for questions to be added, changed and removed.




## Json structure

The question.json consists of multiple questions. Every question has multiple answers, in this case Yes, No or Maybe.
Every question has multiple scores.
```json
{
  "questions": [
    {
      "question": "Question placeholder",
      "answers": [
        {
          "answer": "Yes",
          "score-back-end": 0,
          "score-front-end": 0,
          "score-robot-ui": 0,
          "score-robot-technical": 0,
          "score-ict-ondernemer": 0
        },
        {
          "answer": "Maybe",
          "score-back-end": 0,
          "score-front-end": 0,
          "score-robot-ui": 0,
          "score-robot-technical": 0,
          "score-ict-ondernemer": 0
        },
        {
          "answer": "No",
          "score-back-end": 0,
          "score-front-end": 0,
          "score-robot-ui": 0,
          "score-robot-technical": 0,
          "score-ict-ondernemer": 0
        }
      ]
    }
  ]
}

```
