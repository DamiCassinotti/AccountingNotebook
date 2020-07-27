package com.agileengine.accounting_notebook.model

import com.agileengine.accounting_notebook.exception.AccountCantBeNegativeException
import com.agileengine.accounting_notebook.extensions.isNegative
import java.math.BigDecimal

class Account(private var accountValue: BigDecimal = BigDecimal.ZERO) {

    fun credit(creditValue: BigDecimal) {
        accountValue = accountValue.add(creditValue)
    }

    fun debit(debitValue: BigDecimal) {
        if (accountValue.subtract(debitValue).isNegative())
            throw AccountCantBeNegativeException("Insufficient funds. You can't debit $debitValue from total.")
        accountValue = accountValue.subtract(debitValue)
    }
}