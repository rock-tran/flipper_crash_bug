package com.base.fragments.toolbox


import co.utilities.Utils
import java.util.*

class MainThreadStack : Stack<NavigationState>() {

    @Synchronized override fun isEmpty(): Boolean {
        Utils.ensureOnMainThread()
        return super.isEmpty()
    }

    @Synchronized override fun peek(): NavigationState {
        Utils.ensureOnMainThread()
        return super.peek()
    }

    @Synchronized override fun pop(): NavigationState {
        Utils.ensureOnMainThread()
        return super.pop()
    }

    @Synchronized override fun push(obj: NavigationState): NavigationState {
        Utils.ensureOnMainThread()
        return super.push(obj)
    }

    companion object {
        private val serialVersionUID = 1L
    }
}
