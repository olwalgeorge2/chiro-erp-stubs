package com.chiro.erp.comms.infrastructure.mapping
interface CommsMapper<S,T> { fun toTarget(s:S): T }
