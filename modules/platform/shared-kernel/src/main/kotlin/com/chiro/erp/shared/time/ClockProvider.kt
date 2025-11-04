package com.chiro.erp.shared.time
object ClockProvider { fun nowIso() = java.time.Instant.now().toString() }
