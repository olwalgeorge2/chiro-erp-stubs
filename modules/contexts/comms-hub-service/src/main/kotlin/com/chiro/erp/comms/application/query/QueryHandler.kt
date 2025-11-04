package com.chiro.erp.comms.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
