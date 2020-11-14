package com.mir00r.salarydisburse.commons.utils

import com.mir00r.salarydisburse.exceptions.exists.AlreadyExistsException
import com.mir00r.salarydisburse.exceptions.forbidden.ForbiddenException
import com.mir00r.salarydisburse.exceptions.invalid.InvalidException
import com.mir00r.salarydisburse.exceptions.notfound.NotFoundException


/**
 * Created by IntelliJ IDEA.
 * User: razzak
 * Date: 13/11/19
 * Time: 11:12 AM
 */

class ExceptionUtil {
    companion object {
        fun getInvalidException(message: String): InvalidException {
            return InvalidException(message)
        }

        fun getNotFound(name: String, id: Long?): NotFoundException {
            return NotFoundException("Could not find $name with id: $id")
        }

        fun getNotFound(name: String, code: String?): NotFoundException {
            return NotFoundException("Could not find $name with id: $code")
        }

        fun getNotFoundRuntime(name: String, code: String?): RuntimeException {
            return RuntimeException("Could not find $name with id: $code")
        }

        fun getAlreadyExist(name: String): AlreadyExistsException {
            return AlreadyExistsException("$name already exists")
        }

        fun getAlreadyExist(name: String, param: String): AlreadyExistsException {
            return AlreadyExistsException("$name already exists with: $param")
        }

        fun getAlreadyExistMessage(message: String): AlreadyExistsException {
            return AlreadyExistsException(message)
        }

        fun getNotFoundMessage(message: String): NotFoundException {
            return NotFoundException(message)
        }

        fun forbidden(): ForbiddenException {
            return ForbiddenException("Operation not allowed!")
        }

        fun forbidden(s: String): ForbiddenException {
            return ForbiddenException("Operation not allowed!\n $s")
        }
    }
}
