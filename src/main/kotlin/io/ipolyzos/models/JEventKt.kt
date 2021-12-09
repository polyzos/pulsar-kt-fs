package io.ipolyzos.models

import kotlinx.serialization.Serializable

@Serializable
data class JEventKt(val userid: String,
                    val eventTime: Long,
                    val eventType: String,
                    val productId: String,
                    val categoryId: String,
                    val categoryCode: String,
                    val brand: String,
                    val price: Double,
                    val userSession: String)
