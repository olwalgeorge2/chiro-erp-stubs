package com.chiro.erp.commerce.infrastructure.mapping
interface CommerceMapper<S,T> { fun toTarget(s:S): T }
