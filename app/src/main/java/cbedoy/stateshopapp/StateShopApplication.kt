package cbedoy.stateshopapp

import android.app.Application
import cbedoy.stateshopapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import zendesk.chat.Chat

class StateShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Chat.INSTANCE.init(this, "", "276842947848196097")

        startKoin {
            androidContext(this@StateShopApplication)
            modules(appModule)
        }


    }
}