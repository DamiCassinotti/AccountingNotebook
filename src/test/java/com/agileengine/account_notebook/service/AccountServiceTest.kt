package com.agileengine.account_notebook.service

import com.agileengine.accounting_notebook.enums.TransactionType
import com.agileengine.accounting_notebook.exception.AccountCantBeNegativeException
import com.agileengine.accounting_notebook.exception.TransactionNotFoundException
import com.agileengine.accounting_notebook.model.Account
import com.agileengine.accounting_notebook.model.IncomingTransaction
import com.agileengine.accounting_notebook.service.AccountService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

class AccountServiceTest {

    @Test
    fun `getAccountBalance returns initial account balance`() {
        val accountService = AccountService()

        assertEquals(BigDecimal.ZERO, accountService.getAccountBalance())
    }

    @Test
    fun `getAccountBalance returns transaction list`() {
        val accountService = AccountService()

        val transactions = accountService.getAccountTransactions()

        assertNotNull(transactions)
        assertEquals(0, transactions.size)
    }

    @Test
    fun `addTransaction gets a credit transaction, so it adds amount and a transaction to the list`() {
        val accountService = AccountService()
        val newTransaction = IncomingTransaction(TransactionType.CREDIT, BigDecimal.TEN)

        accountService.addNewTransaction(newTransaction)

        assertEquals(BigDecimal.TEN, accountService.getAccountBalance())
        val transactions = accountService.getAccountTransactions()
        assertNotNull(transactions)
        assertEquals(1, transactions.size)
        val transaction = transactions[0]
        assertEquals(TransactionType.CREDIT, transaction.type)
        assertEquals(BigDecimal.TEN, transaction.amount)
    }

    @Test
    fun `addTransaction gets a debit transaction, so it adds amount and a transaction to the list`() {
        val accountService = AccountService()
        accountService.addNewTransaction(IncomingTransaction(TransactionType.CREDIT, BigDecimal.TEN))
        val newTransaction = IncomingTransaction(TransactionType.DEBIT, BigDecimal.ONE)

        accountService.addNewTransaction(newTransaction)

        assertEquals(BigDecimal("9"), accountService.getAccountBalance())
        val transactions = accountService.getAccountTransactions()
        assertNotNull(transactions)
        assertEquals(2, transactions.size)
        val transaction = transactions[1]
        assertEquals(TransactionType.DEBIT, transaction.type)
        assertEquals(BigDecimal.ONE, transaction.amount)
    }

    @Test
    fun `getTransactionById return the correct transaction`() {
        val accountService = AccountService()
        accountService.addNewTransaction(IncomingTransaction(TransactionType.CREDIT, BigDecimal.TEN))
        val expectedTransaction = accountService.addNewTransaction(IncomingTransaction(TransactionType.DEBIT, BigDecimal.ONE))
        val uuid = accountService.getAccountTransactions()[1].id

        val transaction = accountService.getTransactionById(uuid)

        assertNotNull(transaction)
        assertEquals(expectedTransaction.id, transaction.id)
        assertEquals(expectedTransaction.type, transaction.type)
        assertEquals(expectedTransaction.amount, transaction.amount)
        assertEquals(expectedTransaction.effectiveDate, transaction.effectiveDate)
    }

    @Test
    fun `getTransactionById if I search for a non existing transaction, throws exception`() {
        val accountService = AccountService()

        Assertions.assertThrows(TransactionNotFoundException::class.java) { accountService.getTransactionById(UUID.randomUUID()) }
    }
}