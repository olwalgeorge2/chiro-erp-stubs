package com.chiro.erp.bi.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
