package br.com.easynvest.injection.module

import android.content.Context
import br.com.easynvest.application.SimulatorApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: SimulatorApplication) {

    @Provides
    @Singleton
    internal fun applicationProvider(): SimulatorApplication = application

    @Provides
    internal fun contextProvider(): Context = this.application.applicationContext
}