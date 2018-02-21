//package br.com.easynvest
//
//import android.content.Intent
//import android.support.test.espresso.Espresso.onView
//import android.support.test.espresso.assertion.ViewAssertions
//import android.support.test.espresso.matcher.ViewMatchers
//import android.support.test.rule.ActivityTestRule
//import android.support.test.runner.AndroidJUnit4
//import br.com.easynvest.ResponseUtils.Companion.API_RESPONSE
//import br.com.easynvest.model.SimulationResponse
//import br.com.easynvest.view.impl.SimulatorResultActivity
//import com.google.gson.GsonBuilder
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class ResultScreenTest {
//
//    @get:Rule
//    private var activityRule: ActivityTestRule<SimulatorResultActivity> =
//            ActivityTestRule(SimulatorResultActivity::class.java, false, false)
//
//
//    private lateinit var response: SimulationResponse
//
//    @Before
//    fun setup() {
//        response = GsonBuilder().create().fromJson(API_RESPONSE, SimulationResponse::class.java)
//        val intent = Intent()
//        intent.putExtra(SimulatorResultActivity.SIMULATION_RESPONSE_KEY, response)
//        activityRule.launchActivity(intent)
//    }
//
//    @Test
//    fun checkIfAllResultHeaderViewsAreDisplayed() {
//        onView(ViewMatchers.withId(R.id.SimulationResultLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.SimulationResultValue)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.GrossAmountProfit)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//    }
//
//    @Test
//    fun checkIfAllResultProfitViewsAreDisplayed() {
//        onView(ViewMatchers.withId(R.id.InvestedAmountLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.InvestedAmount)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.RateProfitLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.RateProfit)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.InvestmentProfitLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.InvestmentProfit)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.IRLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.IRValue)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.NetAmountLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.NetAmount)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//    }
//
//    @Test
//    fun checkIfAllResultInfoViewsAreDisplayed() {
//        onView(ViewMatchers.withId(R.id.MaturityDateLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.MaturityDate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.MaturityTotalDaysLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.MaturityTotalDays)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.MonthlyGrossRateProfitLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.MonthlyGrossRateProfit)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.RateLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.Rate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.AnnualGrossRateProfitLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.AnnualGrossRateProfit)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//
//        onView(ViewMatchers.withId(R.id.PeriodRateProfitLabel)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(ViewMatchers.withId(R.id.PeriodRateProfit)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//
//        onView(ViewMatchers.withId(R.id.Simulate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//    }
//}