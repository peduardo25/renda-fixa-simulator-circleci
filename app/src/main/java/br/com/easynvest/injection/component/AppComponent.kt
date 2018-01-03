package br.com.easynvest.injection.component

import br.com.easynvest.injection.module.AppModule
import br.com.easynvest.injection.module.NetworkModule
import br.com.easynvest.injection.module.SimulatorModule
import br.com.easynvest.view.impl.SimulatorFormActivity
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {
    fun retrofit(): Retrofit
}