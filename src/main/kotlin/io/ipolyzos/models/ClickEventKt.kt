//Generated by the protocol buffer compiler. DO NOT EDIT!
// source: main/proto/event.proto

package io.ipolyzos.models;

@kotlin.jvm.JvmSynthetic
inline fun clickEvent(block: io.ipolyzos.models.ClickEventKt.Dsl.() -> Unit): io.ipolyzos.models.ClickEvent =
  io.ipolyzos.models.ClickEventKt.Dsl._create(io.ipolyzos.models.ClickEvent.newBuilder()).apply { block() }._build()
object ClickEventKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  class Dsl private constructor(
    @kotlin.jvm.JvmField private val _builder: io.ipolyzos.models.ClickEvent.Builder
  ) {
    companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: io.ipolyzos.models.ClickEvent.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): io.ipolyzos.models.ClickEvent = _builder.build()

    /**
     * <code>string eventTime = 1;</code>
     */
    var eventTime: kotlin.String
      @JvmName("getEventTime")
      get() = _builder.getEventTime()
      @JvmName("setEventTime")
      set(value) {
        _builder.setEventTime(value)
      }
    /**
     * <code>string eventTime = 1;</code>
     */
    fun clearEventTime() {
      _builder.clearEventTime()
    }

    /**
     * <code>string eventType = 2;</code>
     */
    var eventType: kotlin.String
      @JvmName("getEventType")
      get() = _builder.getEventType()
      @JvmName("setEventType")
      set(value) {
        _builder.setEventType(value)
      }
    /**
     * <code>string eventType = 2;</code>
     */
    fun clearEventType() {
      _builder.clearEventType()
    }

    /**
     * <code>string productId = 3;</code>
     */
    var productId: kotlin.String
      @JvmName("getProductId")
      get() = _builder.getProductId()
      @JvmName("setProductId")
      set(value) {
        _builder.setProductId(value)
      }
    /**
     * <code>string productId = 3;</code>
     */
    fun clearProductId() {
      _builder.clearProductId()
    }

    /**
     * <code>string categoryId = 4;</code>
     */
    var categoryId: kotlin.String
      @JvmName("getCategoryId")
      get() = _builder.getCategoryId()
      @JvmName("setCategoryId")
      set(value) {
        _builder.setCategoryId(value)
      }
    /**
     * <code>string categoryId = 4;</code>
     */
    fun clearCategoryId() {
      _builder.clearCategoryId()
    }

    /**
     * <code>string categoryCode = 5;</code>
     */
    var categoryCode: kotlin.String
      @JvmName("getCategoryCode")
      get() = _builder.getCategoryCode()
      @JvmName("setCategoryCode")
      set(value) {
        _builder.setCategoryCode(value)
      }
    /**
     * <code>string categoryCode = 5;</code>
     */
    fun clearCategoryCode() {
      _builder.clearCategoryCode()
    }

    /**
     * <code>string brand = 6;</code>
     */
    var brand: kotlin.String
      @JvmName("getBrand")
      get() = _builder.getBrand()
      @JvmName("setBrand")
      set(value) {
        _builder.setBrand(value)
      }
    /**
     * <code>string brand = 6;</code>
     */
    fun clearBrand() {
      _builder.clearBrand()
    }

    /**
     * <code>string price = 7;</code>
     */
    var price: kotlin.String
      @JvmName("getPrice")
      get() = _builder.getPrice()
      @JvmName("setPrice")
      set(value) {
        _builder.setPrice(value)
      }
    /**
     * <code>string price = 7;</code>
     */
    fun clearPrice() {
      _builder.clearPrice()
    }

    /**
     * <code>string userId = 8;</code>
     */
    var userId: kotlin.String
      @JvmName("getUserId")
      get() = _builder.getUserId()
      @JvmName("setUserId")
      set(value) {
        _builder.setUserId(value)
      }
    /**
     * <code>string userId = 8;</code>
     */
    fun clearUserId() {
      _builder.clearUserId()
    }

    /**
     * <code>string userSession = 9;</code>
     */
    var userSession: kotlin.String
      @JvmName("getUserSession")
      get() = _builder.getUserSession()
      @JvmName("setUserSession")
      set(value) {
        _builder.setUserSession(value)
      }
    /**
     * <code>string userSession = 9;</code>
     */
    fun clearUserSession() {
      _builder.clearUserSession()
    }
  }
}
@kotlin.jvm.JvmSynthetic
inline fun io.ipolyzos.models.ClickEvent.copy(block: io.ipolyzos.models.ClickEventKt.Dsl.() -> Unit): io.ipolyzos.models.ClickEvent =
  io.ipolyzos.models.ClickEventKt.Dsl._create(this.toBuilder()).apply { block() }._build()
