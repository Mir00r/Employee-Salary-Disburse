package com.example.app.domains.bankaccount.controllers.web

import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.bankaccounts.models.dtos.BankAccountDto
import com.mir00r.salarydisburse.domains.bankaccounts.models.mappers.BankAccountMapper
import com.mir00r.salarydisburse.domains.bankaccounts.services.BankAccountService
import com.mir00r.salarydisburse.domains.common.controller.CrudWebController
import com.mir00r.salarydisburse.routes.Route
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
class BankAccountWebController @Autowired constructor(
        private val bankAccountService: BankAccountService,
        private val bankAccountMapper: BankAccountMapper
) : CrudWebController<BankAccountDto> {

    @GetMapping(Route.V1.ADMIN_SEARCH_BANKACCOUNTS)
    override fun search(@RequestParam("q", defaultValue = "") query: String,
                        @RequestParam("page", defaultValue = "0") page: Int,
                        @RequestParam("size", defaultValue = "10") size: Int,
                        model: Model): String {
        val entities = this.bankAccountService.search(query, page, size)
        model.addAttribute("bankaccounts", entities)
        return "bankaccounts/fragments/all"
    }

    @GetMapping(Route.V1.ADMIN_FIND_BANKACCOUNT)
    override fun find(@PathVariable("id") id: Long,
                      model: Model): String {
        val entity = this.bankAccountService.find(id).orElseThrow { ExceptionUtil.getNotFound("BankAccount", id) }
        model.addAttribute("bankaccount", entity)
        return "bankaccounts/fragments/details"
    }

    @GetMapping(Route.V1.ADMIN_CREATE_BANKACCOUNT_PAGE)
    override fun createPage(model: Model): String {
        return "bankaccounts/fragments/create"
    }

    @PostMapping(Route.V1.ADMIN_CREATE_BANKACCOUNT)
    override fun create(@Valid @ModelAttribute dto: BankAccountDto,
                        redirectAttributes: RedirectAttributes): String {
        val entity = this.bankAccountService.save(this.bankAccountMapper.map(dto, null))
        redirectAttributes.addFlashAttribute("message", "Success!!")
        return "redirect:${Route.V1.ADMIN_FIND_BANKACCOUNT.replace("{id}", entity.id.toString())}"
    }

    @GetMapping(Route.V1.ADMIN_UPDATE_BANKACCOUNT_PAGE)
    override fun updatePage(@PathVariable("id") id: Long, model: Model): String {
        val entity = this.bankAccountService.find(id).orElseThrow { ExceptionUtil.getNotFound("BankAccount", id) }
        model.addAttribute("bankaccount", entity)
        return "bankaccounts/fragments/create"
    }

    @PostMapping(Route.V1.ADMIN_UPDATE_BANKACCOUNT)
    override fun update(@PathVariable("id") id: Long,
                        @Valid @ModelAttribute dto: BankAccountDto,
                        redirectAttributes: RedirectAttributes): String {
        var entity = this.bankAccountService.find(id).orElseThrow { ExceptionUtil.getNotFound("BankAccount", id) }
        entity = this.bankAccountService.save(this.bankAccountMapper.map(dto, entity))
        redirectAttributes.addFlashAttribute("message", "Success!!")
        return "redirect:${Route.V1.ADMIN_FIND_BANKACCOUNT.replace("{id}", entity.id.toString())}"
    }

    @PostMapping(Route.V1.ADMIN_DELETE_BANKACCOUNT)
    override fun delete(@PathVariable("id") id: Long,
                        redirectAttributes: RedirectAttributes): String {
        this.bankAccountService.delete(id, true)
        redirectAttributes.addFlashAttribute("message", "Deleted!!")
        return "redirect:${Route.V1.ADMIN_SEARCH_BANKACCOUNTS}";
    }

}
