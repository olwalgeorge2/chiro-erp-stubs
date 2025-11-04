package com.chiro.erp.bi.infrastructure.mapping
interface BiMapper<S,T> { fun toTarget(s:S): T }
