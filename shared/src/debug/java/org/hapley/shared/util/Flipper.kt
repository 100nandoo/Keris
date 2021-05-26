package org.hapley.shared.util

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import javax.inject.Inject

class Flipper @Inject constructor(app: Application) {
    val networkPlugin = NetworkFlipperPlugin()

    init {
        SoLoader.init(app, false)
        AndroidFlipperClient.getInstance(app).apply {
            addPlugin(InspectorFlipperPlugin(app, DescriptorMapping.withDefaults()))
            addPlugin(DatabasesFlipperPlugin(app))
            addPlugin(SharedPreferencesFlipperPlugin(app))
            addPlugin(LeakCanaryFlipperPlugin())
            addPlugin(networkPlugin)
            start()
        }
    }
}
