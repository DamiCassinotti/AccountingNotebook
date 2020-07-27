package com.agileengine.accounting_notebook.model

import com.agileengine.accounting_notebook.enums.TransactionType
import java.math.BigDecimal

class IncomingTransaction(
        val type: TransactionType,
        val amount: BigDecimal)