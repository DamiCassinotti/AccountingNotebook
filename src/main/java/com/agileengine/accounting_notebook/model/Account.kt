package com.agileengine.accounting_notebook.model

import com.agileengine.accounting_notebook.enums.TransactionType
import com.agileengine.accounting_notebook.exception.AccountCantBeNegativeException
import com.agileengine.accounting_notebook.extensions.isNegative
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock

class Account {

    var accountValue: BigDecimal = BigDecimal.ZERO
        private set

    var transactions: ArrayList<Transaction> = ArrayList()
        private set

    val rwLock = ReentrantReadWriteLock(true)

    fun credit(creditAmount: BigDecimal): Transaction {
        rwLock.writeLock().lock()
        accountValue = accountValue.add(creditAmount)
        val transaction = Transaction(type = TransactionType.CREDIT, amount = creditAmount)
        transactions.add(transaction)
        rwLock.writeLock().unlock()
        return transaction
    }

    fun debit(debitAmount: BigDecimal): Transaction {
        rwLock.readLock().lock()
        if (accountValue.subtract(debitAmount).isNegative())
            throw AccountCantBeNegativeException("Insufficient funds. You can't debit $debitAmount from total.")
        accountValue = accountValue.subtract(debitAmount)
        val transaction = Transaction(type = TransactionType.DEBIT, amount = debitAmount)
        transactions.add(transaction)
        rwLock.readLock().unlock()
        return transaction
    }
}