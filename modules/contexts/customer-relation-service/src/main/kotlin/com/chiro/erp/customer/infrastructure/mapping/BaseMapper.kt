package com.chiro.erp.customer.infrastructure.mapping
interface CustomerMapper<S,T> { fun toTarget(s:S): T }
