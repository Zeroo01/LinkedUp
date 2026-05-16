package com.example.linkedup.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun eventTimer(startTime: Long): Flow<Long> = flow {

    while (true) {
        emit(System.currentTimeMillis() - startTime)
        delay(1000)
    }
}