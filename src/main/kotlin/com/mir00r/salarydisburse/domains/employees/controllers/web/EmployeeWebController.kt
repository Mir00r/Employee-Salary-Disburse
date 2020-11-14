package com.mir00r.salarydisburse.domains.employees.controllers.web

import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.common.controller.CrudWebController
import com.mir00r.salarydisburse.domains.employees.models.dtos.EmployeeDto
import com.mir00r.salarydisburse.domains.employees.models.mappers.EmployeeMapper
import com.mir00r.salarydisburse.domains.employees.services.EmployeeService
import com.mir00r.salarydisburse.routes.Route
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
class EmployeeWebController @Autowired constructor(
        private val employeeService: EmployeeService,
        private val employeeMapper: EmployeeMapper
) : CrudWebController<EmployeeDto> {

    @GetMapping(Route.V1.ADMIN_SEARCH_EMPLOYEES)
    override fun search(@RequestParam("q", defaultValue = "") query: String,
                        @RequestParam("page", defaultValue = "0") page: Int,
                        @RequestParam("size", defaultValue = "10") size: Int,
                        model: Model): String {
        val entities = this.employeeService.search(query, page, size)
        model.addAttribute("employees", entities)
        return "employees/fragments/all"
    }

    @GetMapping(Route.V1.ADMIN_FIND_EMPLOYEE)
    override fun find(@PathVariable("id") id: Long,
                      model: Model): String {
        val entity = this.employeeService.find(id).orElseThrow { ExceptionUtil.getNotFound("Employee", id) }
        model.addAttribute("employee", entity)
        return "employees/fragments/details"
    }

    @GetMapping(Route.V1.ADMIN_CREATE_EMPLOYEE_PAGE)
    override fun createPage(model: Model): String {
        return "employees/fragments/create"
    }

    @PostMapping(Route.V1.ADMIN_CREATE_EMPLOYEE)
    override fun create(@Valid @ModelAttribute dto: EmployeeDto,
                        redirectAttributes: RedirectAttributes): String {
        val entity = this.employeeService.save(this.employeeMapper.map(dto, null))
        redirectAttributes.addFlashAttribute("message", "Success!!")
        return "redirect:${Route.V1.ADMIN_FIND_EMPLOYEE.replace("{id}", entity.id.toString())}"
    }

    @GetMapping(Route.V1.ADMIN_UPDATE_EMPLOYEE_PAGE)
    override fun updatePage(@PathVariable("id") id: Long, model: Model): String {
        val entity = this.employeeService.find(id).orElseThrow { ExceptionUtil.getNotFound("Employee", id) }
        model.addAttribute("employee", entity)
        return "employees/fragments/create"
    }

    @PostMapping(Route.V1.ADMIN_UPDATE_EMPLOYEE)
    override fun update(@PathVariable("id") id: Long,
                        @Valid @ModelAttribute dto: EmployeeDto,
                        redirectAttributes: RedirectAttributes): String {
        var entity = this.employeeService.find(id).orElseThrow { ExceptionUtil.getNotFound("Employee", id) }
        entity = this.employeeService.save(this.employeeMapper.map(dto, entity))
        redirectAttributes.addFlashAttribute("message", "Success!!")
        return "redirect:${Route.V1.ADMIN_FIND_EMPLOYEE.replace("{id}", entity.id.toString())}"
    }

    @PostMapping(Route.V1.ADMIN_DELETE_EMPLOYEE)
    override fun delete(@PathVariable("id") id: Long,
                        redirectAttributes: RedirectAttributes): String {
        this.employeeService.delete(id, true)
        redirectAttributes.addFlashAttribute("message", "Deleted!!")
        return "redirect:${Route.V1.ADMIN_SEARCH_EMPLOYEES}";
    }

}
