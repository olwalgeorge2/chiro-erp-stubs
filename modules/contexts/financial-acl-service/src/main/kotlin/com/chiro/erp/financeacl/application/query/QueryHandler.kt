package com.chiro.erp.financeacl.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
