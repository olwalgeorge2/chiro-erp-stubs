package com.chiro.erp.customer.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
