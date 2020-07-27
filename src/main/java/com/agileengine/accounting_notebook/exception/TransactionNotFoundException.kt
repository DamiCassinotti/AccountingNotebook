package com.agileengine.accounting_notebook.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class TransactionNotFoundException(message: String): RuntimeException(message)