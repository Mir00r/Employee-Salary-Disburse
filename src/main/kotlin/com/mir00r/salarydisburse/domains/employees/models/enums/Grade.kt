package com.mir00r.salarydisburse.domains.employees.models.enums

import com.fasterxml.jackson.annotation.JsonProperty
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil

/**
 * @project IntelliJ IDEA
 * @author mir00r on 14/11/20
 */
enum class Grade(
        var id: Byte,
        @JsonProperty("label") var label: String
) {

    ONE(1, "One"),
    TWO(2, "two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    OTHER(7, "Other"),
    UNKNOWN(8, "Unknown");

    companion object {

        @JvmStatic
        fun get(x: Byte): Grade {
            for (g: Grade in values()) {
                if (g.id == x)
                    return g
            }
            return OTHER
        }

        @JvmStatic
        fun get(x: String): Grade {
            for (g: Grade in values()) {
                if (g.name == x || g.label == x)
                    return g
            }
            return UNKNOWN
        }

        @JvmStatic
        fun validate(x: Byte): Byte {
            if (values().any { it.id == x }) return x
            else throw ExceptionUtil.getNotFound(Grade::class.simpleName.toString(), x.toLong())
        }
    }
}
