package br.com.easynvest.presenter

import br.com.easynvest.vo.SimulationParams

interface SimulatorFormPresenter {
    fun onSimulationClicked(params: SimulationParams)
    fun onInvestedAmountChanged(newText: String)
    fun afterInvestedAmountChanged(newText: String)
    fun onCdiChanged(newText: String)
    fun onMaturityDateChanged(newText: String)
}