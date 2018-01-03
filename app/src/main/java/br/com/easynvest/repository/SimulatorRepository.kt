package br.com.easynvest.repository

import br.com.easynvest.model.SimulationResponse
import br.com.easynvest.vo.SimulationParams
import rx.Observable

interface SimulatorRepository {
    fun simulateInvestment(params: SimulationParams): Observable<SimulationResponse>
}