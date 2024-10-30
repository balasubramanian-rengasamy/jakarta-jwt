package com.prime

import jakarta.ws.rs.NameBinding
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.CLASS

@NameBinding
@Retention(RUNTIME)
@Target(FUNCTION, CLASS)
annotation class Authenticated
