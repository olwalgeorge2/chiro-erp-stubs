package com.chiro.erp.mfg.infrastructure.mapping
interface MfgMapper<S,T> { fun toTarget(s:S): T }
