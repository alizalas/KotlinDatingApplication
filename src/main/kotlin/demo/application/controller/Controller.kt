package demo.application.controller

import demo.application.dto.UserAddInfotmation
import demo.application.dto.UserRegister
import demo.application.service.Service
import demo.application.dto.ReactionRequest
import org.springframework.web.bind.annotation.*

@RestController
class Controller (
    val service: Service
) {

    @PostMapping("/users")
    fun register(@RequestBody user: UserRegister) = service.register(user)

    @PutMapping("/users")
    fun addInformation(@RequestBody questionnaire: UserAddInfotmation, @RequestHeader("Authorization") token: String) = service.addInformation(questionnaire, token)

    @GetMapping("/users")
    fun getList(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam sortBy: String,
        @RequestHeader("Authorization") token: String
    ) = service.getList(page, size, sortBy, token)

    @PostMapping("/users/{id}/reaction")
    fun react(
        @RequestBody reaction: ReactionRequest,
        @RequestParam id: Int,
        @RequestHeader("Authorization") token: String
    ) = service.react(reaction, id, token)}