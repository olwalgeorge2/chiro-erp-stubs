package com.chiro.erp.inventory.application.command
interface CommandHandler<C,R> { fun handle(cmd:C): R }
