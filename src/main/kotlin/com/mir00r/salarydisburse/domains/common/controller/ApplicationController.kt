package com.mir00r.salarydisburse.domains.common.controller

import com.mir00r.salarydisburse.SalarydisburseApplication
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/application")
class ApplicationController {

    @PatchMapping("/context/reload")
    fun reloadApplicationContext() {
        SalarydisburseApplication.restart()
    }

}
