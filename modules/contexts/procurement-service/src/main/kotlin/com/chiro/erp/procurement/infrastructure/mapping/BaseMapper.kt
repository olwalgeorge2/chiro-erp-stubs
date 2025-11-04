package com.chiro.erp.procurement.infrastructure.mapping
interface ProcurementMapper<S,T> { fun toTarget(s:S): T }
