package it.saimao.latthtautbaydin.data

data class JsonData (
    val questions: List<Question>,
    val answers: List<Answer>,
    val numberList: List<String>
)

data class Question (val questionNo: Int, val questionName: String)

data class Answer(val questionNo: Int, val answerNo: Int, val answerResult: String)
