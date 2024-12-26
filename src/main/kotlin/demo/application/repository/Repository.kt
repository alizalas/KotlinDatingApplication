package demo.application.repository

import demo.application.dto.User
import demo.application.dto.UserAddInfotmation
import demo.application.dto.UserRegister
import org.springframework.stereotype.Repository

@Repository
class Repository(
    val repository: MutableList<User>

) {
    fun register(user: UserRegister, token: String): Int {
        repository.add(User(id = repository.size, token = token, login = user.login, password = user.password))
        return repository.size - 1
    }

    fun addInformation(questionnaire: UserAddInfotmation, token: String): Int {
        var id = -1
        repository.forEach { user ->
            if (user.token == token) {
                user.age = questionnaire.age
                user.gender = questionnaire.gender
                user.lastName = questionnaire.lastName
                user.firstName = questionnaire.firstName
                id = user.id
            }
        }
        return id
    }
}