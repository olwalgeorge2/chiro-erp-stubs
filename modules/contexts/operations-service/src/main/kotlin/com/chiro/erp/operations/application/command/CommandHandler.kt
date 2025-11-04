package com.chiro.erp.operations.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
