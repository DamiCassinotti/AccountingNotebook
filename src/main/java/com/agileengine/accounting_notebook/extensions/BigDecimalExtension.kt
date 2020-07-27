package com.agileengine.accounting_notebook.extensions

import java.math.BigDecimal

fun BigDecimal.isNegative(): Boolean {
    return this.compareTo(BigDecimal.ZERO) < 0
}