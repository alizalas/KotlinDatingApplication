package demo.application

import demo.application.client.Client
import demo.application.dto.UserAddInfotmation
import demo.application.dto.UserRegister
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatusCode

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private lateinit var client: Client

    @Test
    fun registerUser() {
        val request = UserRegister(login = "LOGIN", password = "PASSWORD")
        val response = client.registerUser(request)
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.statusCode)
    }

    @Test
    fun addUserInformation() {
        val startRequest = UserRegister(login = "login", password = "password")
        val startResponse = client.registerUser(startRequest)
        Assertions.assertEquals(HttpStatusCode.valueOf(200), startResponse.statusCode)

        val endRequest = UserAddInfotmation("female", age = 18, lastName = "Ларина", firstName = "Татьяна")
        val endResponse = client.addUserInformation(endRequest, startResponse.body!!.token)
        Assertions.assertEquals(HttpStatusCode.valueOf(200), endResponse.statusCode)
    }
}