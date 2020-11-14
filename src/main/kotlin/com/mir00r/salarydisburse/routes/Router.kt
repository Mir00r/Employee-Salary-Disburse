package com.mir00r.salarydisburse.routes

/**
 * @project IntelliJ IDEA
 * @author mir00r on 14/11/20
 */
class Router {
    companion object {
        private const val API = "/api"
        private const val VERSION = "/v6"
        private const val ADMIN = "/admin"

        // Auth Login
        const val AUTH_LOGIN = "$API/auth/login"

        // Get single entity through primary key ID
        const val BY_PK_ID = "/{id}"
    }
}
