package com.chiro.erp.mfg.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
