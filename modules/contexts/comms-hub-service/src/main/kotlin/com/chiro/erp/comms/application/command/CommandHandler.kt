package com.chiro.erp.comms.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
