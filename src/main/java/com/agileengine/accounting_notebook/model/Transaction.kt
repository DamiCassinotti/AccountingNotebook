package com.agileengine.accounting_notebook.model

import com.agileengine.accounting_notebook.enums.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Transaction(
        val id: UUID = UUID.randomUUID(),
        val type: TransactionType,
        val amount: BigDecimal,
        val effectiveDate: LocalDateTime = LocalDateTime.now())