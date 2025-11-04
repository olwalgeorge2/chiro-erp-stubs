package com.chiro.erp.operations.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
