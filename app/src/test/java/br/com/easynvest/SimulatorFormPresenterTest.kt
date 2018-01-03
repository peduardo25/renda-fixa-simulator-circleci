package br.com.easynvest

import br.com.easynvest.TestUtils.Companion.API_RESPONSE
import br.com.easynvest.model.SimulationResponse
import br.com.easynvest.presenter.impl.SimulatorFormPresenterImpl
import br.com.easynvest.repository.SimulatorRepository
import br.com.easynvest.view.SimulatorFormView
import br.com.easynvest.vo.SimulationParams
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import rx.Observable

@RunWith(MockitoJUnitRunner::class)
class SimulatorFormPresenterTest {

    @Mock
    private lateinit var repository: SimulatorRepository

    @Mock
    private lateinit var mView: SimulatorFormView

    @InjectMocks
    private lateinit var subject: SimulatorFormPresenterImpl

    private lateinit var params: SimulationParams

    private lateinit var result: SimulationResponse

    @Before
    fun setup() {
        params = SimulationParams(
                investedAmount = 2000.0,
                rate = 100,
                maturityDate = "2022-02-02"
        )

        result = GsonBuilder().create().fromJson(API_RESPONSE, SimulationResponse::class.java)
    }

    @Test
    fun shouldCallResultScreenWhenFetchSimulationResultData() {

        Mockito.`when`(repository.simulateInvestment(params)).thenReturn(Observable.just(result))

        subject.onSimulationClicked(params)

        Mockito.verify(mView).resultScreen(result)
    }

    @Test
    fun shouldHideLoadWhenFetchSimulationResultData() {
        Mockito.`when`(repository.simulateInvestment(params)).thenReturn(Observable.just(result))

        subject.onSimulationClicked(params)

        Mockito.verify(mView).hideLoad()
    }

    @Test
    fun shouldShowLoadWhenSimulatorButtonIsClicked() {
        Mockito.`when`(repository.simulateInvestment(params)).thenReturn(Observable.just(result))

        subject.onSimulationClicked(params)

        Mockito.verify(mView).showLoad()
    }

    @Test
    fun shouldShowRequestErrorMessageWhenFetchDataFails() {

        Mockito.`when`(repository.simulateInvestment(params))
                .thenReturn(Observable.unsafeCreate<SimulationResponse> { subscriber -> subscriber.onError(RuntimeException()) })

        subject.onSimulationClicked(params)

        Mockito.verify(mView).showRequestError()
    }

    @Test
    fun shouldHideLoadWhenFetchDataFails() {

        Mockito.`when`(repository.simulateInvestment(params))
                .thenReturn(Observable.unsafeCreate<SimulationResponse> { subscriber -> subscriber.onError(RuntimeException()) })

        subject.onSimulationClicked(params)

        Mockito.verify(mView).hideLoad()
    }

    @Test
    fun shouldEnableSimulateButtonWhenAllFieldsAreValidAndCDIChanges() {
        Mockito.`when`(mView.getAmountText()).thenReturn("test")
        Mockito.`when`(mView.getCDIText()).thenReturn("100")
        Mockito.`when`(mView.getDateText()).thenReturn("02/02/2022")

        subject.onCdiChanged("100")

        Mockito.verify(mView).enableSimulationBt()
    }

    @Test
    fun shouldEnableSimulateButtonWhenAllFieldsAreValidAndInvestedAmountChanges() {
        Mockito.`when`(mView.getAmountText()).thenReturn("test")
        Mockito.`when`(mView.getCDIText()).thenReturn("100")
        Mockito.`when`(mView.getDateText()).thenReturn("02/02/2022")

        subject.onInvestedAmountChanged("100")

        Mockito.verify(mView).enableSimulationBt()
    }

    @Test
    fun shouldEnableSimulateButtonWhenAllFieldsAreValidAndMaturityDateChanges() {
        Mockito.`when`(mView.getAmountText()).thenReturn("test")
        Mockito.`when`(mView.getCDIText()).thenReturn("100")
        Mockito.`when`(mView.getDateText()).thenReturn("02/02/2022")

        subject.onMaturityDateChanged("100")

        Mockito.verify(mView).enableSimulationBt()
    }

    @Test
    fun shouldDisableSimulateButtonWhenAllFieldsAreEmptyAndMaturityDateChanges() {
        Mockito.`when`(mView.getAmountText()).thenReturn("")
        Mockito.`when`(mView.getCDIText()).thenReturn("")
        Mockito.`when`(mView.getDateText()).thenReturn("")

        subject.onMaturityDateChanged("100")

        Mockito.verify(mView).disableSimulationBt()
    }

    @Test
    fun shouldDisableSimulateButtonWhenAllFieldsAreEmptyAndCDIChanges() {
        Mockito.`when`(mView.getAmountText()).thenReturn("")
        Mockito.`when`(mView.getCDIText()).thenReturn("")
        Mockito.`when`(mView.getDateText()).thenReturn("")

        subject.onCdiChanged("100")

        Mockito.verify(mView).disableSimulationBt()
    }

    @Test
    fun shouldDisableSimulateButtonWhenAllFieldsAreEmptyAndAmountChanges() {
        Mockito.`when`(mView.getAmountText()).thenReturn("")
        Mockito.`when`(mView.getCDIText()).thenReturn("")
        Mockito.`when`(mView.getDateText()).thenReturn("")

        subject.onInvestedAmountChanged("100")

        Mockito.verify(mView).disableSimulationBt()
    }

    @Test
    fun shouldDisableSimulateButtonWhenDateIsInvalid() {
        Mockito.`when`(mView.getAmountText()).thenReturn("100")
        Mockito.`when`(mView.getCDIText()).thenReturn("100")
        Mockito.`when`(mView.getDateText()).thenReturn("22/24/0222")

        subject.onInvestedAmountChanged("100")

        Mockito.verify(mView).disableSimulationBt()
    }

    @Test
    fun shouldFormatInvestedAmountUsingBrazilianCurrency() {
        Mockito.`when`(mView.getAmountText()).thenReturn("100")
        Mockito.`when`(mView.getCDIText()).thenReturn("100")
        Mockito.`when`(mView.getDateText()).thenReturn("22/24/0222")

        subject.onInvestedAmountChanged("100000")
        Mockito.verify(mView).setInvestedAmountText("R$ 1.000,00")
    }

    @Test
    fun shouldClearInvestedAmountWhenValueIsZero() {
        subject.afterInvestedAmountChanged("0.0")
        Mockito.verify(mView).clearInvestedAmount()
    }
}