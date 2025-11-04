package com.chiro.erp.platform.messaging
interface EventPublisher { fun publish(topic:String, key:String?, payload: Any) }
