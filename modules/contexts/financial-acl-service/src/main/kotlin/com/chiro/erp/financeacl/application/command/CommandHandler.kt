package com.chiro.erp.financeacl.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
