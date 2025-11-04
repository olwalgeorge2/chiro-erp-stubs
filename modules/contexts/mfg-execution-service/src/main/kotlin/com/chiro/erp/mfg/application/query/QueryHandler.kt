package com.chiro.erp.mfg.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
