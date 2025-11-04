package com.chiro.erp.bi.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
