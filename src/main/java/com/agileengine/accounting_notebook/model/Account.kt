package com.agileengine.accounting_notebook.model

import com.agileengine.accounting_notebook.enums.TransactionType
import com.agileengine.accounting_notebook.exception.AccountCantBeNegativeException
import com.agileengine.accounting_notebook.extensions.isNegative
import java.math.BigDecimal
import java.util.*

class Account(
        private var accountValue: BigDecimal = BigDecimal.ZERO,
        private var transactions: ArrayList<Any?> = ArrayList()) {

    fun credit(creditAmount: BigDecimal) {
        accountValue = accountValue.add(creditAmount)
        val transaction = Transaction(type = TransactionType.CREDIT, amount = creditAmount)
        transactions.add(transaction)

    }

    fun debit(debitAmount: BigDecimal) {
        if (accountValue.subtract(debitAmount).isNegative())
            throw AccountCantBeNegativeException("Insufficient funds. You can't debit $debitAmount from total.")
        accountValue = accountValue.subtract(debitAmount)
        val transaction = Transaction(type = TransactionType.DEBIT, amount = debitAmount)
        transactions.add(transaction)
    }
}