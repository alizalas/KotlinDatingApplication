package demo.application.service

import demo.application.dto.*
import demo.application.repository.UsersRepository
import demo.application.repository.ReactionsRepository
import demo.application.response.UserAddInformationResponse
import demo.application.response.UserRegisterResponse
import demo.application.response.ReactionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class Service(
    val users: UsersRepository,
    val reactions: ReactionsRepository
) {
    private fun generateRandomString(length: Int): String {
        val chars = ('A'..'F') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

    fun register(user: UserRegister) : ResponseEntity<UserRegisterResponse> {
        val token = generateRandomString(10)
        val id = users.register(user, token)
        return ResponseEntity(UserRegisterResponse(id, user.login, token), HttpStatus.OK)
    }

    fun addInformation(questionnaire: UserAddInfotmation, token: String): ResponseEntity<UserAddInformationResponse> {
        val id = users.addInformation(questionnaire, token)
        return ResponseEntity(UserAddInformationResponse(id), HttpStatus.OK)
    }

    fun getList(page: Int, size: Int, sortBy: String, token: String): ResponseEntity<List<Profile>> {
        val user = users.users.find { it.token == token }
        if (user == null) {
            return ResponseEntity(listOf(), HttpStatus.UNAUTHORIZED)
        }
        val profiles = users.getProfiles(page, size, sortBy)
        return ResponseEntity(profiles, HttpStatus.OK)
    }

    fun react(reaction: ReactionRequest, id: Int, token: String) : ResponseEntity<ReactionResponse> {
        val user = users.users.find { it.token == token }
        if (user == null) {
            return ResponseEntity(ReactionResponse(false), HttpStatus.UNAUTHORIZED)
        }
        reactions.react(reaction.reaction, id, user.id)
        return ResponseEntity(ReactionResponse(reaction.reaction), HttpStatus.OK)
    }
}