package br.com.easynvest.model

import android.os.Parcel
import android.os.Parcelable

class InvestmentParameterResponse(
        val investedAmount: Double,
        val yearlyInterestRate: Double,
        val maturityTotalDays: Double,
        val maturityBusinessDays: Double,
        val maturityDate: String,
        val rate: Double,
        val isTaxFree: Boolean
) : Parcelable {

    constructor(source: Parcel) : this(
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readDouble(),
            source.readString(),
            source.readDouble(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(investedAmount)
        writeDouble(yearlyInterestRate)
        writeDouble(maturityTotalDays)
        writeDouble(maturityBusinessDays)
        writeString(maturityDate)
        writeDouble(rate)
        writeInt((if (isTaxFree) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<InvestmentParameterResponse> = object : Parcelable.Creator<InvestmentParameterResponse> {
            override fun createFromParcel(source: Parcel): InvestmentParameterResponse = InvestmentParameterResponse(source)
            override fun newArray(size: Int): Array<InvestmentParameterResponse?> = arrayOfNulls(size)
        }
    }
}