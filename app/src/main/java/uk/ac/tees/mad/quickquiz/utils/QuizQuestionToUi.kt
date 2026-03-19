package uk.ac.tees.mad.quickquiz.utils

import uk.ac.tees.mad.quickquiz.domain.model.QuizQuestion
import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizOption
import uk.ac.tees.mad.quickquiz.ui.quiz.component.model.QuizQuestionUi

fun QuizQuestion.toUi(): QuizQuestionUi {
//converting the correct and incorrect answer into one list
    //modifying the options to true and false and shuffling it
    val allAnswers = buildList {
        add(correctAnswer to true)
        incorrectAnswers.forEach { add(it to false) }
    }.shuffled()

    val labeledOptions = allAnswers.mapIndexed { index, pair ->
        QuizOption(
            id = index.toString(),
            label = ('A' + index).toString(),
            text = pair.first,
            isCorrect = pair.second
        )
    }

    return QuizQuestionUi(
        question = question,
        options = labeledOptions
    )
}


//this will return list of question with correct option and incorrect option