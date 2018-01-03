package br.com.easynvest.model

import android.os.Parcel
import android.os.Parcelable

class SimulationResponse(val grossAmount: Double,
                         val taxesAmount: Double,
                         val netAmount: Double,
                         val grossAmountProfit: Double,
                         val netAmountProfit: Double,
                         val annualGrossRateProfit: Double,
                         val monthlyGrossRateProfit: Double,
                         val dailyGrossRateProfit: Double,
                         val taxesRate: Double,
                         val rateProfit: Double,
                         val annualNetRateProfit: Double,
                         val investmentParameter: InvestmentParameterResponse) : Parcelable {

    constructor(source: Parcel) : this(
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readParcelable<InvestmentParameterResponse>(InvestmentParameterResponse::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(grossAmount)
        writeDouble(taxesAmount)
        writeDouble(netAmount)
        writeDouble(grossAmountProfit)
        writeDouble(netAmountProfit)
        writeDouble(annualGrossRateProfit)
        writeDouble(monthlyGrossRateProfit)
        writeDouble(dailyGrossRateProfit)
        writeDouble(taxesRate)
        writeDouble(rateProfit)
        writeDouble(annualNetRateProfit)
        writeParcelable(investmentParameter, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SimulationResponse> = object : Parcelable.Creator<SimulationResponse> {
            override fun createFromParcel(source: Parcel): SimulationResponse = SimulationResponse(source)
            override fun newArray(size: Int): Array<SimulationResponse?> = arrayOfNulls(size)
        }
    }
}