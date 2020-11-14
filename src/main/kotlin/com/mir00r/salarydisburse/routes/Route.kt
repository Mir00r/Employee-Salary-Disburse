package com.mir00r.salarydisburse.routes

/**
 * @project IntelliJ IDEA
 * @author mir00r on 14/11/20
 */
class Route {
    class V1 {
        companion object {
            private const val API = "/api"
            private const val VERSION = "/v1"
            private const val ADMIN = "/admin"

            // Auth Login
            const val AUTH_LOGIN = "$API/auth/login"

            // Get single entity through primary key ID
            const val BY_PK_ID = "/{id}"

            // Banks
            const val SEARCH_BANKS = "$API$VERSION/banks"
            const val CREATE_BANK = "$API$VERSION/banks"
            const val FIND_BANK = "$API$VERSION/banks/{id}"
            const val UPDATE_BANK = "$API$VERSION/banks/{id}"
            const val DELETE_BANK = "$API$VERSION/banks/{id}"

            // Banks (Admin)
            const val ADMIN_SEARCH_BANKS = "$ADMIN/banks"
            const val ADMIN_CREATE_BANK_PAGE = "$ADMIN/banks/create"
            const val ADMIN_CREATE_BANK = "$ADMIN/banks"
            const val ADMIN_FIND_BANK = "$ADMIN/banks/{id}"
            const val ADMIN_UPDATE_BANK_PAGE = "$ADMIN/banks/{id}/update"
            const val ADMIN_UPDATE_BANK = "$ADMIN/banks/{id}"
            const val ADMIN_DELETE_BANK = "$ADMIN/banks/{id}/delete"
        }
    }
}
