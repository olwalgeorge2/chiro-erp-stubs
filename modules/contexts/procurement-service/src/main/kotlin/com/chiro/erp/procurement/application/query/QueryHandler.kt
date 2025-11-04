package com.chiro.erp.procurement.application.query
interface QueryHandler<Q,R> { fun handle(query:Q): R }
