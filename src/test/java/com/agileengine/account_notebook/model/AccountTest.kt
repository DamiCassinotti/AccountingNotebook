package com.agileengine.account_notebook.model

import com.agileengine.accounting_notebook.enums.TransactionType
import com.agileengine.accounting_notebook.exception.AccountCantBeNegativeException
import com.agileengine.accounting_notebook.model.Account
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class AccountTest {

    @Test
    fun `empty account starts with 0 balance and empty transactions`() {
        val account = Account()

        assertEquals(BigDecimal.ZERO, account.accountValue)
        assertTrue(account.transactions.isEmpty())
    }

    @Test
    fun `credit if I add a credit transaction, amount is added and a transaction is created`() {
        val account = Account()

        account.credit(BigDecimal.TEN)

        assertEquals(BigDecimal.TEN, account.accountValue)
        assertEquals(1, account.transactions.size)
        val transaction = account.transactions[0]
        assertEquals(BigDecimal.TEN, transaction.amount)
        assertNotNull(transaction.id)
        assertNotNull(transaction.effectiveDate)
        assertEquals(TransactionType.CREDIT, transaction.type)
    }

    @Test
    fun `debit if I add a debit transaction, amount is substracted and a transaction is created`() {
        val account = Account()
        account.credit(BigDecimal.TEN)

        account.debit(BigDecimal.TEN)

        assertEquals(BigDecimal.ZERO, account.accountValue)
        assertEquals(2, account.transactions.size)
        val transaction = account.transactions[1]
        assertEquals(BigDecimal.TEN, transaction.amount)
        assertNotNull(transaction.id)
        assertNotNull(transaction.effectiveDate)
        assertEquals(TransactionType.DEBIT, transaction.type)
    }

    @Test
    fun `debit if I add a debit transaction but i have no funds, throws exception`() {
        val account = Account()

        assertThrows(AccountCantBeNegativeException::class.java) { account.debit(BigDecimal.ONE) }
    }

}