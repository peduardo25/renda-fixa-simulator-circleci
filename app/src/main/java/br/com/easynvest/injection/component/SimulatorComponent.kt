package br.com.easynvest.injection.component

import br.com.easynvest.injection.module.AppModule
import br.com.easynvest.injection.module.SimulatorModule
import br.com.easynvest.injection.scope.ActivityScope
import br.com.easynvest.view.impl.SimulatorFormActivity
import br.com.easynvest.view.impl.SimulatorResultActivity
import dagger.Component

@Component(modules = arrayOf(AppModule::class, SimulatorModule::class), dependencies = arrayOf(AppComponent::class))
@ActivityScope
interface SimulatorComponent {
    fun inject(activity: SimulatorFormActivity)
}