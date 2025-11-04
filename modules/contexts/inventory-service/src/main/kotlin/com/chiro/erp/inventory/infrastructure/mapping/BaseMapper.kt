package com.chiro.erp.inventory.infrastructure.mapping
interface InventoryMapper<S,T> { fun toTarget(s:S): T }
