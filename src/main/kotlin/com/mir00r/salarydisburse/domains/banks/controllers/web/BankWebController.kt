package com.mir00r.salarydisburse.domains.banks.controllers.web

import com.mir00r.salarydisburse.domains.banks.models.dtos.BankDto
import com.mir00r.salarydisburse.domains.banks.models.mappers.BankMapper
import com.mir00r.salarydisburse.domains.banks.services.BankService
import com.mir00r.salarydisburse.commons.utils.ExceptionUtil
import com.mir00r.salarydisburse.domains.common.controller.CrudWebController
import com.mir00r.salarydisburse.routes.Route
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
class BankWebController @Autowired constructor(
        private val bankService: BankService,
        private val bankMapper: BankMapper
) : CrudWebController<BankDto> {

    @GetMapping(Route.V1.ADMIN_SEARCH_BANKS)
    override fun search(@RequestParam("q", defaultValue = "") query: String,
                        @RequestParam("page", defaultValue = "0") page: Int,
                        @RequestParam("size", defaultValue = "10") size: Int,
                        model: Model): String {
        val entities = this.bankService.search(query, page, size)
        model.addAttribute("banks", entities)
        return "banks/fragments/all"
    }

    @GetMapping(Route.V1.ADMIN_FIND_BANK)
    override fun find(@PathVariable("id") id: Long,
                      model: Model): String {
        val entity = this.bankService.find(id).orElseThrow { ExceptionUtil.getNotFound("Bank", id) }
        model.addAttribute("bank", entity)
        return "banks/fragments/details"
    }

    @GetMapping(Route.V1.ADMIN_CREATE_BANK_PAGE)
    override fun createPage(model: Model): String {
        return "banks/fragments/create"
    }

    @PostMapping(Route.V1.ADMIN_CREATE_BANK)
    override fun create(@Valid @ModelAttribute dto: BankDto,
                        redirectAttributes: RedirectAttributes): String {
        val entity = this.bankService.save(this.bankMapper.map(dto, null))
        redirectAttributes.addFlashAttribute("message", "Success!!")
        return "redirect:${Route.V1.ADMIN_FIND_BANK.replace("{id}", entity.id.toString())}"
    }

    @GetMapping(Route.V1.ADMIN_UPDATE_BANK_PAGE)
    override fun updatePage(@PathVariable("id") id: Long, model: Model): String {
        val entity = this.bankService.find(id).orElseThrow { ExceptionUtil.getNotFound("Bank", id) }
        model.addAttribute("bank", entity)
        return "banks/fragments/create"
    }

    @PostMapping(Route.V1.ADMIN_UPDATE_BANK)
    override fun update(@PathVariable("id") id: Long,
                        @Valid @ModelAttribute dto: BankDto,
                        redirectAttributes: RedirectAttributes): String {
        var entity = this.bankService.find(id).orElseThrow { ExceptionUtil.getNotFound("Bank", id) }
        entity = this.bankService.save(this.bankMapper.map(dto, entity))
        redirectAttributes.addFlashAttribute("message", "Success!!")
        return "redirect:${Route.V1.ADMIN_FIND_BANK.replace("{id}", entity.id.toString())}"
    }

    @PostMapping(Route.V1.ADMIN_DELETE_BANK)
    override fun delete(@PathVariable("id") id: Long,
                        redirectAttributes: RedirectAttributes): String {
        this.bankService.delete(id, true)
        redirectAttributes.addFlashAttribute("message", "Deleted!!")
        return "redirect:${Route.V1.ADMIN_SEARCH_BANKS}";
    }

}
