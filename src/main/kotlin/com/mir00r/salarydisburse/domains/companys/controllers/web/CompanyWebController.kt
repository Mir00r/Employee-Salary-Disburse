package com.mir00r.salarydisburse.domains.companys.controllers.web

import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.common.controller.CrudWebController
import com.mir00r.salarydisburse.domains.companys.models.dtos.CompanyDto
import com.mir00r.salarydisburse.domains.companys.models.mappers.CompanyMapper
import com.mir00r.salarydisburse.domains.companys.services.CompanyService
import com.mir00r.salarydisburse.routes.Route

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
class CompanyWebController @Autowired constructor(
        private val companyService: CompanyService,
        private val companyMapper: CompanyMapper
) : CrudWebController<CompanyDto> {

    @GetMapping(Route.V1.ADMIN_SEARCH_COMPANYS)
    override fun search(@RequestParam("q", defaultValue = "") query: String,
                        @RequestParam("page", defaultValue = "0") page: Int,
                        @RequestParam("size", defaultValue = "10") size: Int,
                        model: Model): String {
        val entities = this.companyService.search(query, page, size)
        model.addAttribute("companys", entities)
        return "companys/fragments/all"
    }

    @GetMapping(Route.V1.ADMIN_FIND_COMPANY)
    override fun find(@PathVariable("id") id: Long,
                      model: Model): String {
        val entity = this.companyService.find(id).orElseThrow { ExceptionUtil.getNotFound("Company", id) }
        model.addAttribute("company", entity)
        return "companys/fragments/details"
    }

    @GetMapping(Route.V1.ADMIN_CREATE_COMPANY_PAGE)
    override fun createPage(model: Model): String {
        return "companys/fragments/create"
    }

    @PostMapping(Route.V1.ADMIN_CREATE_COMPANY)
    override fun create(@Valid @ModelAttribute dto: CompanyDto,
                        redirectAttributes: RedirectAttributes): String {
        val entity = this.companyService.save(this.companyMapper.map(dto, null))
        redirectAttributes.addFlashAttribute("message", "Success!!")
        return "redirect:${Route.V1.ADMIN_FIND_COMPANY.replace("{id}", entity.id.toString())}"
    }

    @GetMapping(Route.V1.ADMIN_UPDATE_COMPANY_PAGE)
    override fun updatePage(@PathVariable("id") id: Long, model: Model): String {
        val entity = this.companyService.find(id).orElseThrow { ExceptionUtil.getNotFound("Company", id) }
        model.addAttribute("company", entity)
        return "companys/fragments/create"
    }

    @PostMapping(Route.V1.ADMIN_UPDATE_COMPANY)
    override fun update(@PathVariable("id") id: Long,
                        @Valid @ModelAttribute dto: CompanyDto,
                        redirectAttributes: RedirectAttributes): String {
        var entity = this.companyService.find(id).orElseThrow { ExceptionUtil.getNotFound("Company", id) }
        entity = this.companyService.save(this.companyMapper.map(dto, entity))
        redirectAttributes.addFlashAttribute("message", "Success!!")
        return "redirect:${Route.V1.ADMIN_FIND_COMPANY.replace("{id}", entity.id.toString())}"
    }

    @PostMapping(Route.V1.ADMIN_DELETE_COMPANY)
    override fun delete(@PathVariable("id") id: Long,
                        redirectAttributes: RedirectAttributes): String {
        this.companyService.delete(id, true)
        redirectAttributes.addFlashAttribute("message", "Deleted!!")
        return "redirect:${Route.V1.ADMIN_SEARCH_COMPANYS}";
    }

}
