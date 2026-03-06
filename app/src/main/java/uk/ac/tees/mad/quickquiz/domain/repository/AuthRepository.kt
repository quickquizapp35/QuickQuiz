package uk.ac.tees.mad.quickquiz.domain.repository

interface AuthRepository {
   suspend fun login(email : String,
              password : String) : Result<Unit>

    suspend fun signup(email: String,
               password: String) : Result<Unit>

   suspend fun logout()
}