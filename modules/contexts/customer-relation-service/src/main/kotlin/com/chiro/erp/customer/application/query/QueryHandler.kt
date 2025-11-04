package com.chiro.erp.customer.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
