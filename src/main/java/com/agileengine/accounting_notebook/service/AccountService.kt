package com.agileengine.accounting_notebook.service

import com.agileengine.accounting_notebook.enums.TransactionType
import com.agileengine.accounting_notebook.exception.TransactionNotFoundException
import com.agileengine.accounting_notebook.model.Account
import com.agileengine.accounting_notebook.model.IncomingTransaction
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

    fun addNewTransaction(transaction: IncomingTransaction): Transaction {
        return if (transaction.type == TransactionType.CREDIT) {
            account.credit(transaction.amount)
        } else {
            account.debit(transaction.amount)
        }
    }

    fun getTransactionById(uuid: UUID): Transaction {
        val optTransaction = account.transactions.stream().filter { it.id == uuid }.findAny()
        if (optTransaction.isEmpty)
            throw TransactionNotFoundException("transaction not found")
        return account.transactions[0]
    }

}