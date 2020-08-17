package com.agileengine.account_notebook.extensions

import com.agileengine.accounting_notebook.extensions.isNegative
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class BigDecimalExtensionTest {

    @Test
    fun `isNegative A positive number is positive`() {
        val value = BigDecimal.ONE

        assertFalse(value.isNegative())
    }

    @Test
    fun `isNegative Zero is not negative`() {
        val value = BigDecimal.ZERO

        assertFalse(value.isNegative())
    }

    @Test
    fun `isNegative -1 is negative`() {
        val value = BigDecimal("-1")

        assertTrue(value.isNegative())
    }

}