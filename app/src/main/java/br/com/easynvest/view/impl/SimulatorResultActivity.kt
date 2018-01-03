package br.com.easynvest.view.impl

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import br.com.easynvest.R
import br.com.easynvest.common.localDateFormat
import br.com.easynvest.common.toBrCurrency
import br.com.easynvest.model.SimulationResponse
import kotlinx.android.synthetic.main.activity_simulator_result.*

class SimulatorResultActivity : AppCompatActivity() {

    companion object {
        val SIMULATION_RESPONSE_KEY = "simulation_response_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulator_result)
        fillHeaderView()
        fillProfitView()
        fillInfoView()

        Simulate.setOnClickListener({ finish() })
    }

    private fun getSimulationResult(): SimulationResponse {
        return intent.getParcelableExtra(SIMULATION_RESPONSE_KEY)
    }

    private fun fillHeaderView() {
        val result = getSimulationResult()

        val headerView = findViewById<View>(R.id.header)
        val simulationValue = headerView.findViewById<TextView>(R.id.SimulationResultValue)
        val grossAmountProfit = headerView.findViewById<TextView>(R.id.GrossAmountProfit)

        simulationValue.text = result.grossAmount.toBrCurrency()

        grossAmountProfit.setText(changeColor(getString(R.string.gross_amount_profit),
                result.grossAmountProfit.toBrCurrency()), TextView.BufferType.SPANNABLE)
    }

    private fun fillProfitView() {
        val result = getSimulationResult()

        val profitView = findViewById<View>(R.id.profit)
        val investedAmount = profitView.findViewById<TextView>(R.id.InvestedAmount)
        val rateProfit = profitView.findViewById<TextView>(R.id.RateProfit)
        val investmentProfit = profitView.findViewById<TextView>(R.id.InvestmentProfit)
        val iRValue = profitView.findViewById<TextView>(R.id.IRValue)
        val netAmount = profitView.findViewById<TextView>(R.id.NetAmount)

        investedAmount.text = result.investmentParameter.investedAmount.toBrCurrency()
        rateProfit.text = result.grossAmount.toBrCurrency()
        investmentProfit.text = result.grossAmountProfit.toBrCurrency()
        iRValue.text = getString(R.string.ir_value, result.taxesAmount.toBrCurrency(), result.taxesRate.toString())
        netAmount.text = result.netAmount.toBrCurrency()
    }

    private fun fillInfoView() {
        val result = getSimulationResult()

        val infoView = findViewById<View>(R.id.info)
        val maturityDate = infoView.findViewById<TextView>(R.id.MaturityDate)
        val maturityTotalDays = infoView.findViewById<TextView>(R.id.MaturityTotalDays)
        val monthlyGrossRateProfit = infoView.findViewById<TextView>(R.id.MonthlyGrossRateProfit)
        val rate = infoView.findViewById<TextView>(R.id.Rate)
        val annualGrossRateProfit = infoView.findViewById<TextView>(R.id.AnnualGrossRateProfit)
        val periodRateProfit = infoView.findViewById<TextView>(R.id.PeriodRateProfit)

        maturityDate.text = result.investmentParameter.maturityDate.localDateFormat()
        maturityTotalDays.text = getString(R.string.maturity_total_days, result.investmentParameter.maturityTotalDays)
        monthlyGrossRateProfit.text = getString(R.string.monthly_gross_rate, result.monthlyGrossRateProfit.toString())
        rate.text = getString(R.string.rate, result.investmentParameter.rate)
        annualGrossRateProfit.text = getString(R.string.annual_gross_rate, result.investmentParameter.yearlyInterestRate)
        periodRateProfit.text = getString(R.string.rate_profit, result.annualGrossRateProfit.toString())
    }

    private fun changeColor(labelText: String, valueText: String): Spannable {
        val outputText = "$labelText $valueText"
        val spannable = SpannableString(outputText)

        spannable.setSpan(
                ForegroundColorSpan(
                        ContextCompat.getColor(this, R.color.simulate_result_bt)
                ), labelText.length, outputText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }
}
