package com.agileengine.accounting_notebook.controller

import com.agileengine.accounting_notebook.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/balance")
class BalanceController(@Autowired private val accountService: AccountService) {

    @GetMapping("", "/")
    fun getAccountBalance(): BigDecimal {
        return accountService.getAccountBalance()
    }

}