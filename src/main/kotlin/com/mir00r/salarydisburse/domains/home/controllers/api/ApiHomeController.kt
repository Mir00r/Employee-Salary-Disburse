package com.mir00r.salarydisburse.domains.home.controllers.api


import com.mir00r.salarydisburse.config.security.SecurityConfig
import com.mir00r.salarydisburse.config.security.TokenService
import com.mir00r.salarydisburse.domains.users.models.annotations.CurrentUser
import com.mir00r.salarydisburse.domains.users.models.dtos.UserRequest
import com.mir00r.salarydisburse.domains.users.models.entities.User
import com.mir00r.salarydisburse.domains.users.models.mappers.UserMapper
import com.mir00r.salarydisburse.domains.users.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1")
class ApiHomeController @Autowired constructor(
        val userService: UserService,
        val tokenService: TokenService,
        val userMapper: UserMapper
) {
    @Value("\${baseUrl}")
    val baseUrl: String? = null

    @Value("\${token.validity}")
    val tokenValidity: String? = null

    @PostMapping("/register/verify")
    fun verifyIdentity(@RequestParam("identity") phoneOrEmail: String): ResponseEntity<Any> {

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis() + Integer.parseInt(this.tokenValidity)
        val sent = this.userService.requireAccountValidationByOTP(phoneOrEmail, calendar.time)

        return if (!sent) ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build()
        else ResponseEntity.ok("OTP sent!")
    }

    @PostMapping("/register")
    fun register(@RequestParam("token") token: String,
                 @RequestBody userDto: UserRequest): ResponseEntity<Any> {

        val user = this.userService.register(token, this.userMapper.map(userDto, null))

        SecurityConfig.updateAuthentication(user)
        return ResponseEntity.ok(tokenService.createAccessToken(user))
    }


    @PostMapping("/change_password")
    fun changePassword(@RequestParam("current_password") currentPassword: String,
                       @RequestParam("new_password") newPassword: String,
                       @CurrentUser currentUser: User): ResponseEntity<Any> {
        this.userService.changePassword(currentUser.id, currentPassword, newPassword)
        return ResponseEntity.ok().build()
    }

    // Password reset
    @GetMapping("/reset_password")
    @ResponseStatus(HttpStatus.OK)
    fun requestResetPassword(@RequestParam("username") username: String) {
        this.userService.handlePasswordResetRequest(username)
    }

    @PostMapping("/reset_password")
    @ResponseStatus(HttpStatus.OK)
    fun resetPassword(@RequestParam("username") username: String,
                      @RequestParam("token") token: String,
                      @RequestParam("password") password: String) {
        this.userService.resetPassword(username, token, password)
    }

}
