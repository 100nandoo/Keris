package org.hapley.keris

import android.app.Application
import org.hapley.keris.util.Flipper

class KerisApp : Application() {
    lateinit var flipper: Flipper
    override fun onCreate() {
        super.onCreate()

        Flipper.init(this)
    }
}