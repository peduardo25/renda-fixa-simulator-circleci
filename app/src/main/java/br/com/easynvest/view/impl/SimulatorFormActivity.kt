package br.com.easynvest.view.impl

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import android.widget.Toast
import br.com.easynvest.R
import br.com.easynvest.application.SimulatorApplication
import br.com.easynvest.common.FormMasks.Companion.DATE_MASK
import br.com.easynvest.common.convertDate
import br.com.easynvest.common.unmask
import br.com.easynvest.injection.component.DaggerSimulatorComponent
import br.com.easynvest.injection.module.SimulatorModule
import br.com.easynvest.model.SimulationResponse
import br.com.easynvest.presenter.SimulatorFormPresenter
import br.com.easynvest.view.SimulatorFormView
import br.com.easynvest.view.impl.SimulatorResultActivity.Companion.SIMULATION_RESPONSE_KEY
import br.com.easynvest.vo.SimulationParams
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener
import kotlinx.android.synthetic.main.activity_simulator_form.*
import javax.inject.Inject


class SimulatorFormActivity : AppCompatActivity(), SimulatorFormView {

    @Inject
    lateinit var presenter: SimulatorFormPresenter

    lateinit var investedAmountWatcher: TextWatcher
    lateinit var cdiWatcher: TextWatcher
    lateinit var maturityDateWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator_form)
        setupInjection()
        addCDIChangeListener()
        addTInvestedAmountChangeListeners()
        addDateChangeListener()

        BTSimulate.setOnClickListener({ presenter.onSimulationClicked(createSimulationParams()) })
    }

    override fun setupInjection() {
        DaggerSimulatorComponent.builder()
                .appComponent((application as SimulatorApplication).appInjection)
                .simulatorModule(SimulatorModule(this))
                .build()
                .inject(this)
    }

    private fun addCDIChangeListener() {
        cdiWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onCdiChanged(s.toString())
            }
        }
        ETCDI.addTextChangedListener(cdiWatcher)
    }


    private fun addTInvestedAmountChangeListeners() {
        investedAmountWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.afterInvestedAmountChanged(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onInvestedAmountChanged(s.toString())
            }
        }

        ETInvestmentAmount.addTextChangedListener(investedAmountWatcher)
    }

    private fun addDateChangeListener() {
        maturityDateWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onMaturityDateChanged(s.toString())
            }
        }

        ETMaturityDate.addTextChangedListener(maturityDateWatcher)
        ETMaturityDate.addTextChangedListener(MaskEditTextChangedListener(DATE_MASK, ETMaturityDate))
    }

    override fun setInvestedAmountText(currentText: String) {
        ETInvestmentAmount.removeTextChangedListener(investedAmountWatcher)
        ETInvestmentAmount.setText(currentText)
        ETInvestmentAmount.setSelection(currentText.length)
        ETInvestmentAmount.addTextChangedListener(investedAmountWatcher)
    }

    override fun clearInvestedAmount() {
        ETInvestmentAmount.setText("", TextView.BufferType.EDITABLE)
    }

    override fun showLoad() {
        SimulationLoading.visibility = VISIBLE
    }

    override fun hideLoad() {
        SimulationLoading.visibility = GONE
    }

    override fun resultScreen(response: SimulationResponse) {
        resetFields()
        val intent = Intent(this, SimulatorResultActivity::class.java)
        intent.putExtra(SIMULATION_RESPONSE_KEY, response)
        startActivity(intent)
    }

    override fun showRequestError() {
        Toast.makeText(applicationContext, getString(R.string.request_error), Toast.LENGTH_LONG).show()
    }

    private fun resetFields() {
        ETMaturityDate.setText("", TextView.BufferType.EDITABLE)
        ETInvestmentAmount.setText("", TextView.BufferType.EDITABLE)
        ETCDI.setText("", TextView.BufferType.EDITABLE)
    }

    override fun getCDIText(): String = ETCDI.text.toString()
    override fun getDateText(): String = ETMaturityDate.text.toString()
    override fun getAmountText(): String = ETInvestmentAmount.text.toString()

    private fun createSimulationParams(): SimulationParams {
        return SimulationParams(
                investedAmount = getAmountText().unmask().toDouble(),
                rate = getCDIText().toInt(),
                maturityDate = getDateText().convertDate()
        )
    }

    override fun enableSimulationBt() {
        BTSimulate.isEnabled = true
    }

    override fun disableSimulationBt() {
        BTSimulate.isEnabled = false
    }
}
