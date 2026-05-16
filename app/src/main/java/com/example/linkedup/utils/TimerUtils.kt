package com.example.linkedup.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun eventTimer(startTime: Long): Flow<Long> = flow {

    while (true) {
        emit(System.currentTimeMillis() - startTime)
        kotlinx.coroutines.delay(1000)
    }
}