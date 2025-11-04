package com.chiro.erp.identity.infrastructure.mapping
interface IdentityMapper<S,T> { fun toTarget(s:S): T }
