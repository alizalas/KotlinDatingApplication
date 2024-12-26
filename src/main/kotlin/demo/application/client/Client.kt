package demo.application.client

import demo.application.dto.UserAddInfotmation
import demo.application.dto.UserRegister
import demo.application.response.UserAddInformationResponse
import demo.application.response.UserRegisterResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@FeignClient(name = "client", url = "http://localhost:8080/")

interface Client {

    @PostMapping(value = ["/users"])
    fun registerUser(@RequestBody user: UserRegister): ResponseEntity<UserRegisterResponse>

    @PutMapping(value = ["/users"])
    fun addUserInformation(@RequestBody questionnaire: UserAddInfotmation, @RequestHeader("Authorization") token: String): ResponseEntity<UserAddInformationResponse>
}
