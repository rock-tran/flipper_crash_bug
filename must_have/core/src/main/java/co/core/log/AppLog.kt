package co.core.log

import org.slf4j.Logger

/**
 * Created by freesky1102 on 7/10/15.
 */


private lateinit var mLogger: Logger

val AppLogger
    get() = mLogger

fun setupAppLogger(logger: Logger) {
    mLogger = logger
}