package com.agileengine.accounting_notebook.model

import com.agileengine.accounting_notebook.enums.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Transaction(
        val id: String = UUID.randomUUID().toString(),
        val type: TransactionType,
        val amount: BigDecimal,
        val effectiveDate: LocalDateTime = LocalDateTime.now())