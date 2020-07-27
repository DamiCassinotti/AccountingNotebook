package com.agileengine.accounting_notebook.controller

import com.agileengine.accounting_notebook.exception.AmountCantBeNegativeException
import com.agileengine.accounting_notebook.exception.InvalidIdException
import com.agileengine.accounting_notebook.extensions.isNegative
import com.agileengine.accounting_notebook.model.IncomingTransaction
import com.agileengine.accounting_notebook.model.Transaction
import com.agileengine.accounting_notebook.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/transaction")
class TransactionController(@Autowired private val accountService: AccountService) {

    @GetMapping("", "/")
    fun getAccountTransactions(): List<Transaction> {
        return accountService.getAccountTransactions()
    }

    @PostMapping("", "/")
    fun addNewTransaction(@RequestBody transaction: IncomingTransaction): Transaction {
        if (transaction.amount.isNegative())
            throw AmountCantBeNegativeException("invalid amount")
        return accountService.addNewTransaction(transaction)
    }

    @GetMapping("/{id}")
    fun getTransactionById(@PathVariable id: String): Transaction {
        val uuid = try {
            UUID.fromString(id)
        } catch (e: IllegalArgumentException) {
            throw InvalidIdException("invalid ID supplied")
        }
        return accountService.getTransactionById(uuid)
    }

}