package com.chiro.erp.inventory.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
