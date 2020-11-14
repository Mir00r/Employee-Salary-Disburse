package com.mir00r.salarydisburse.domains.users.enums

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil

/**
 * @project IntelliJ IDEA
 * @author mir00r on 14/11/20
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class Roles(var id: Byte,
                 @JsonProperty("name_en") var labelEn: String,
                 @JsonProperty("name_bn") var labelBn: String
) {

    ADMIN(1, "Admin", "এডমিন"),
    USER(4, "User", "ইয়ুজার"),
    UNDEFINED(18, "Undefined", "আন্দিফাইন");

    companion object {
        @JvmStatic
        fun get(x: Byte): Roles {
            for (g: Roles in values()) {
                if (g.id == x)
                    return g
            }
            return UNDEFINED
        }

        @JvmStatic
        fun get(x: String): Roles {
            for (g: Roles in values()) {
                if (g.name == x)
                    return g
            }
            return UNDEFINED
        }

        @JvmStatic
        fun validate(x: Byte): Byte {
            if (values().any { it.id == x }) return x
            else throw ExceptionUtil.getNotFound(Roles::class.simpleName.toString(), x.toLong())
        }
    }
}
