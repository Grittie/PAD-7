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
          "score-example-one": 0,
          "score-example-two": 0
        },
        {
          "answer": "Maybe",
          "score-example-one": 0,
          "score-example-two": 0
        },
        {
          "answer": "No",
          "score-example-one": 0,
          "score-example-two": 0
        }
      ]
    }
  ]
}

```
