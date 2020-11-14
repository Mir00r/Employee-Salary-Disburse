package com.mir00r.salarydisburse.domains.common.controller

import com.mir00r.salarydisburse.domains.common.models.dto.BaseDto
import org.springframework.ui.Model
import org.springframework.web.servlet.mvc.support.RedirectAttributes

interface CrudWebController<T : BaseDto> {
    fun search(query: String, page: Int, size: Int, model: Model): String
    fun find(id: Long, model: Model): String
    fun createPage(model: Model): String
    fun create(dto: T, redirectAttributes: RedirectAttributes): String
    fun updatePage(id: Long, model: Model): String
    fun update(id: Long, dto: T, redirectAttributes: RedirectAttributes): String
    fun delete(id: Long, redirectAttributes: RedirectAttributes): String
}
