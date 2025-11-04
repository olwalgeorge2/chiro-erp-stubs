package com.chiro.erp.platform.observability
object Tracing { fun trace(name:String, block:()->Unit) { block() } }
