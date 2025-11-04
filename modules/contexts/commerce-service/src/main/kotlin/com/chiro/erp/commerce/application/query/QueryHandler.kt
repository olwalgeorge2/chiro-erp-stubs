package com.chiro.erp.commerce.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
