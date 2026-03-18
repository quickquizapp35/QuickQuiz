package uk.ac.tees.mad.quickquiz.data.mapper

import uk.ac.tees.mad.quickquiz.data.remote.model.TriviaCategoryDto
import uk.ac.tees.mad.quickquiz.data.remote.model.QuizCategoryResponse
import uk.ac.tees.mad.quickquiz.domain.model.QuizCategory


fun QuizCategoryResponse.toDomain(): List<QuizCategory> {
  return triviaCategories.map { it.toDomain() }
}


fun TriviaCategoryDto.toDomain() = QuizCategory(
  id = id,
  name = name
)