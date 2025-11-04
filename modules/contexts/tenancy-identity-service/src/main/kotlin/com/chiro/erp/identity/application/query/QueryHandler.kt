package com.chiro.erp.identity.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
