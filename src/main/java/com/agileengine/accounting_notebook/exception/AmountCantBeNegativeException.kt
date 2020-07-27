package com.agileengine.accounting_notebook.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class AmountCantBeNegativeException(message: String?) : RuntimeException(message)