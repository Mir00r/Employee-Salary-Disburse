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

            // BankAccounts
            const val SEARCH_BANKACCOUNTS = "$API$VERSION/bankaccounts"
            const val CREATE_BANKACCOUNT = "$API$VERSION/bankaccounts"
            const val FIND_BANKACCOUNT = "$API$VERSION/bankaccounts/{id}"
            const val UPDATE_BANKACCOUNT = "$API$VERSION/bankaccounts/{id}"
            const val DELETE_BANKACCOUNT = "$API$VERSION/bankaccounts/{id}"

            // BankAccounts (Admin)
            const val ADMIN_SEARCH_BANKACCOUNTS = "$ADMIN/bankaccounts"
            const val ADMIN_CREATE_BANKACCOUNT_PAGE = "$ADMIN/bankaccounts/create"
            const val ADMIN_CREATE_BANKACCOUNT = "$ADMIN/bankaccounts"
            const val ADMIN_FIND_BANKACCOUNT = "$ADMIN/bankaccounts/{id}"
            const val ADMIN_UPDATE_BANKACCOUNT_PAGE = "$ADMIN/bankaccounts/{id}/update"
            const val ADMIN_UPDATE_BANKACCOUNT = "$ADMIN/bankaccounts/{id}"
            const val ADMIN_DELETE_BANKACCOUNT = "$ADMIN/bankaccounts/{id}/delete"
        }
    }
}
