package org.hapley.keris

import android.app.Application
import com.facebook.soloader.SoLoader
import org.hapley.keris.util.Flipper

class KerisApp : Application() {
    lateinit var flipper: Flipper
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        Flipper.init(this)
    }
}