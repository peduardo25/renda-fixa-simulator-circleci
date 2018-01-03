package br.com.easynvest.vo

import android.os.Parcel
import android.os.Parcelable

class SimulationParams(val investedAmount: Double,
                       val index: String = "CDI",
                       val rate: Int,
                       val isTaxFree: Boolean = false,
                       val maturityDate: String) : Parcelable {

    constructor(source: Parcel) : this(
            source.readDouble(),
            source.readString(),
            source.readInt(),
            1 == source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(investedAmount)
        writeString(index)
        writeInt(rate)
        writeInt((if (isTaxFree) 1 else 0))
        writeString(maturityDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SimulationParams> = object : Parcelable.Creator<SimulationParams> {
            override fun createFromParcel(source: Parcel): SimulationParams = SimulationParams(source)
            override fun newArray(size: Int): Array<SimulationParams?> = arrayOfNulls(size)
        }
    }
}