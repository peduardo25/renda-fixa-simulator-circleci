package br.com.easynvest.network

import br.com.easynvest.model.SimulationResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface SimulatorService {

    @GET("/calculator/simulate")
    fun simulate(@Query("investedAmount") investedAmount: Double,
                 @Query("index") index: String,
                 @Query("rate") rate: Int,
                 @Query("isTaxFree") isTaxFree: Boolean,
                 @Query("maturityDate") maturityDate: String): Observable<SimulationResponse>
}