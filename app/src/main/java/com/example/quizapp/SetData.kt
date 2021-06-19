package com.example.quizapp

object SetData {

    const val name:String = "name"
    const val score:String ="score"

    fun getQuestion():ArrayList<QuestionData> {
        var que:ArrayList<QuestionData> = arrayListOf()
        var q1 = QuestionData(
            1,
            "What is the capital of India",
            "UP",
            "Kerala",
            "New Delhi",
            "None",
            3
        )
        que.add(q1)
        var q2 = QuestionData(
            2,
            "What is the capital of Rajasthan",
            "Kota",
            "Jaipur",
            "Udaipur",
            "None",
            2
        )
        que.add(q2)
        var q3 = QuestionData(
            3,
            "What is the capital of Madhya Pradesh",
            "Bhopal",
            "Gwalior",
            "Ujjain",
            "None",
            1
        )
        que.add(q3)
        var q4 = QuestionData(
            4,
            "What is the capital of Maharashtra",
            "Nagpur",
            "Mumbai",
            "Pune",
            "None",
            2
        )
        que.add(q4)
        var q5 = QuestionData(
            5,
            "What is the capital of Karnataka",
            "Ooty",
            "Chennai",
            "Bengaluru",
            "None",
            3
        )
        que.add(q5)
        return que
    }

}