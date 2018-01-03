package br.com.easynvest.injection.module

import br.com.easynvest.presenter.SimulatorFormPresenter
import br.com.easynvest.presenter.impl.SimulatorFormPresenterImpl
import br.com.easynvest.repository.SimulatorRepository
import br.com.easynvest.repository.impl.SimulatorRepositoryImpl
import br.com.easynvest.view.BaseView
import br.com.easynvest.view.SimulatorFormView
import dagger.Module
import dagger.Provides

@Module
class SimulatorModule(val view: BaseView) {

    @Provides
    internal fun simulatorFormPresenter(impl: SimulatorFormPresenterImpl): SimulatorFormPresenter = impl

    @Provides
    internal fun simulatorRepositoryProvider(impl: SimulatorRepositoryImpl): SimulatorRepository = impl

    @Provides
    internal fun simulatorFormViewProvider(): SimulatorFormView = view as SimulatorFormView
}