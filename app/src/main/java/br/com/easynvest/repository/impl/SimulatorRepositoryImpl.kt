package br.com.easynvest.repository.impl

import br.com.easynvest.model.SimulationResponse
import br.com.easynvest.network.SimulatorService
import br.com.easynvest.repository.SimulatorRepository
import br.com.easynvest.vo.SimulationParams
import retrofit2.Retrofit
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

open class SimulatorRepositoryImpl @Inject constructor(private val retrofit: Retrofit) : SimulatorRepository {

    override fun simulateInvestment(params: SimulationParams): Observable<SimulationResponse> {
        return retrofit.create(SimulatorService::class.java)
                .simulate(params.investedAmount, params.index, params.rate, params.isTaxFree, params.maturityDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}