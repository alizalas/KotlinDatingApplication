package demo.application.repository

import demo.application.dto.Profile
import demo.application.dto.User
import demo.application.dto.UserAddInfotmation
import demo.application.dto.UserRegister
import org.springframework.stereotype.Repository

@Repository
class UsersRepository(
    val users: MutableList<User>

) {
    fun register(user: UserRegister, token: String): Int {
        users.add(User(id = users.size, token = token, login = user.login, password = user.password))
        return users.size - 1
    }

    fun addInformation(questionnaire: UserAddInfotmation, token: String): Int {
        var id = -1
        users.forEach { user ->
            if (user.token == token) {
                user.age = questionnaire.age
                user.gender = questionnaire.gender
                user.lastName = questionnaire.lastName
                user.firstName = questionnaire.firstName
                user.photo=questionnaire.photo
                id = user.id
            }
        }
        return id
    }

    fun getProfiles(page: Int, size: Int, sortBy: String) : List<Profile> {
        val sortedUsers = users.sortedWith(Comparator { user1, user2 ->
            when (sortBy) {
                "age" -> (user1.age ?: 0).compareTo(user2.age ?: 0)
                "lastName" -> (user1.lastName ?: "").compareTo(user2.lastName ?: "")
                "firstName" -> (user1.firstName ?: "").compareTo(user2.firstName ?: "")
                else -> user1.id.compareTo(user2.id)
            }
        })

        val paginatedUsers = sortedUsers
            .drop((page - 1) * size)
            .take(size)

        return paginatedUsers.map { user ->
            Profile(
                age = user.age ?: 0,
                lastName = user.lastName ?: "",
                firstName = user.firstName ?: "",
                photoUrl = user.photo ?: ""
            )
        }
    }
}