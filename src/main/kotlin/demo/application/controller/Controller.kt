package demo.application.controller

import demo.application.dto.UserAddInfotmation
import demo.application.dto.UserRegister
import demo.application.service.Service
import org.springframework.web.bind.annotation.*

@RestController
class Controller (
    val service: Service
) {

    @PostMapping("/users")
    fun register(@RequestBody user: UserRegister) = service.register(user)

    @PutMapping("/users")
    fun addInformation(@RequestBody questionnaire: UserAddInfotmation, @RequestHeader("Authorization") token: String) = service.addInformation(questionnaire, token)
}