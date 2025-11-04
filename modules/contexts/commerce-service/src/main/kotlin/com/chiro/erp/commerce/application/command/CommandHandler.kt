package com.chiro.erp.commerce.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
