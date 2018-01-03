package br.com.easynvest.presenter.impl

import br.com.easynvest.common.toBrCurrency
import br.com.easynvest.common.unmask
import br.com.easynvest.presenter.SimulatorFormPresenter
import br.com.easynvest.repository.SimulatorRepository
import br.com.easynvest.view.SimulatorFormView
import br.com.easynvest.vo.SimulationParams
import java.util.regex.Pattern
import javax.inject.Inject


class SimulatorFormPresenterImpl @Inject constructor(private val mView: SimulatorFormView,
                                                     private val repository: SimulatorRepository)
    : SimulatorFormPresenter {

    private var currentText = ""
    private val currencyRegex = Regex("[R$.,]")
    private val dateRegex = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d{2}$"

    override fun onSimulationClicked(params: SimulationParams) {
        mView.showLoad()
        repository.simulateInvestment(params).subscribe({ response ->
            run {
                mView.hideLoad()
                mView.resultScreen(response)
            }
        }, {
            run {
                mView.showRequestError()
                mView.hideLoad()
            }
        })
    }

    private fun isFieldsFilled(): Boolean {
        return !mView.getCDIText().isEmpty() && !mView.getAmountText().isEmpty()
                && !mView.getDateText().isEmpty()
    }

    private fun isValidMaturityDate(date: String): Boolean {
        val matcher = Pattern.compile(dateRegex).matcher(date)
        return matcher.matches()
    }

    override fun onInvestedAmountChanged(newText: String) {
        if (newText != currentText && !newText.isEmpty()) {
            val rawValue = currencyRegex.replace(newText, "")
            val parsed = rawValue.toDouble()
            currentText = parsed.toBrCurrency()
            mView.setInvestedAmountText(currentText)
        }
        verifyValidSimulation()
    }

    override fun afterInvestedAmountChanged(newText: String) {
        if (!newText.isEmpty()) {
            if (newText.unmask().toDouble() == 0.0) {
                mView.clearInvestedAmount()
            }
        }
    }

    override fun onCdiChanged(newText: String) {
        verifyValidSimulation()
    }

    override fun onMaturityDateChanged(newText: String) {
        verifyValidSimulation()
    }

    private fun verifyValidSimulation() {
        if (isFieldsFilled() && isValidMaturityDate(mView.getDateText())) {
            mView.enableSimulationBt()
        } else {
            mView.disableSimulationBt()
        }
    }
}