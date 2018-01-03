package br.com.easynvest.view

import br.com.easynvest.model.SimulationResponse
import br.com.easynvest.vo.SimulationParams

interface SimulatorFormView: BaseView {
    fun showLoad()
    fun hideLoad()
    fun resultScreen(response: SimulationResponse)
    fun showRequestError()
    fun setInvestedAmountText(currentText: String)
    fun clearInvestedAmount()
    fun getCDIText(): String
    fun getDateText(): String
    fun getAmountText(): String
    fun enableSimulationBt()
    fun disableSimulationBt()
}