package me.daemon.lint.demo

import java.io.Serializable

data class SerializableInner(
    val string: String
)

data class SerializableSuper(
    val inner: SerializableInner
) : Serializable