package com.chiro.erp.procurement.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
