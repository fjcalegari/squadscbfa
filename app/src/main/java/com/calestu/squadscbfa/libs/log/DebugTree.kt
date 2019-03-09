package com.calestu.squadscbfa.libs.log

import timber.log.Timber

class DebugTree: Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return ":sq:"+String.format("C:%s:%s", super.createStackElementTag(element), element.lineNumber)
    }

}