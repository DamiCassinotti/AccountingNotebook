package com.agileengine.accounting_notebook.service

import com.agileengine.accounting_notebook.model.Account
import com.agileengine.accounting_notebook.model.Transaction
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class AccountService(private val account: Account = Account()) {

    fun getAccountBalance(): BigDecimal {
        return account.accountValue
    }

    fun getAccountTransactions(): ArrayList<Transaction> {
        return account.transactions
    }

}