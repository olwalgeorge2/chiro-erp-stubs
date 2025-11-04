package com.chiro.erp.operations.infrastructure.mapping
interface OperationsMapper<S,T> { fun toTarget(s:S): T }
