package com.agileengine.accounting_notebook.controller

import com.agileengine.accounting_notebook.model.Transaction
import com.agileengine.accounting_notebook.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController(@Autowired private val accountService: AccountService) {

    @GetMapping("", "/")
    fun getAccountTransactions(): List<Transaction> {
        return accountService.getAccountTransactions()
    }

}