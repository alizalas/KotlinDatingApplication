package demo.application.service

import demo.application.dto.UserAddInfotmation
import demo.application.dto.UserRegister
import demo.application.repository.Repository
import demo.application.response.UserAddInformationResponse
import demo.application.response.UserRegisterResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class Service(
    val repository: Repository
) {
    private fun generateRandomString(length: Int): String {
        val chars = ('A'..'F') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

    fun register(user: UserRegister) : ResponseEntity<UserRegisterResponse> {
        val token = generateRandomString(10)
        val id = repository.register(user, token)
        return ResponseEntity(UserRegisterResponse(id, user.login, token), HttpStatus.OK)
    }

    fun addInformation(questionnaire: UserAddInfotmation, token: String): ResponseEntity<UserAddInformationResponse> {
        val id = repository.addInformation(questionnaire, token)
        return ResponseEntity(UserAddInformationResponse(id), HttpStatus.OK)
    }
}