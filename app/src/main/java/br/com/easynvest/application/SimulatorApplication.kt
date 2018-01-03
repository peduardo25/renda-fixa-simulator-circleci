package br.com.easynvest.application

import android.app.Application
import br.com.easynvest.injection.component.AppComponent
import br.com.easynvest.injection.component.DaggerAppComponent
import br.com.easynvest.injection.module.AppModule
import br.com.easynvest.injection.module.NetworkModule

class SimulatorApplication : Application() {

    lateinit var appInjection: AppComponent

    override fun onCreate() {
        super.onCreate()

        appInjection = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }
}