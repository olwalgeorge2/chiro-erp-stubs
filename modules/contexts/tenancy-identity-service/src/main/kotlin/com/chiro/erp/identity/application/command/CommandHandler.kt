package com.chiro.erp.identity.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
