package com.mir00r.salarydisburse.commons

/**
 * @project IntelliJ IDEA
 * @author mir00r on 14/11/20
 */
class Constants {

    companion object {
        /**
         * SWAGGER CONSTANTS
         */
        const val REST_API = "REST API"
        const val API = "API"
        const val BEARER = "Bearer "

        const val BASIC_APIS = "Basic Api's"
        const val GLOBAL_API = "Global Api's (Super Admin)"
        const val CORE_STATS_API = "Core Statistics Api's (Super Admin)"
        const val USERS_API = "Users"
        const val USERS_ADMIN = "Users (Super Admin)"
        const val ROLES_ADMIN = "Roles (Super Admin)"
        const val ROLES = "Role"
        const val USERS = "User"
        const val USER_PRIVILEGES = "User Privilege"
        const val PASSWORD = "Password"
        const val PRIVILEGES = "Privileges"
        const val PRIVILEGES_ADMIN = "Privileges (Super Admin)"
        const val UPLOADS = "Uploads"

        // API operation messages
        const val POST_MSG = "Create a new "
        const val GET_USER_MEASUREMENTS = "Fetch list of measurements for a agent "
        const val POST_BULK_MSG = "Bulk API to create "
        const val PATCH_MSG = "Update an existing "
        const val UPDATE_PATCH_MSG = "Bulk Update API an existing "
        const val GET_ALL_MSG = "Get all list of "
        const val SEARCH_ALL_MSG = "Search all list of "
        const val GENERATE_ALL_MSG = "Generate all "
        const val GET_BY_MESSAGE = "Get all by "

        const val PROFILES = "Profile"
        const val EMPLOYEE = "Employee"
    }
}
