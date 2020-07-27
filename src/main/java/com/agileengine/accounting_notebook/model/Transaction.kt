package com.agileengine.accounting_notebook.model

import com.agileengine.accounting_notebook.enums.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Transaction(
        private val id: String = UUID.randomUUID().toString(),
        private val type: TransactionType,
        private val amount: BigDecimal,
        private val effectiveDate: LocalDateTime = LocalDateTime.now())