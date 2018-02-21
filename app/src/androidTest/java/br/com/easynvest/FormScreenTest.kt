//package br.com.easynvest
//
//import android.support.test.InstrumentationRegistry
//import android.support.test.espresso.Espresso.closeSoftKeyboard
//import android.support.test.espresso.Espresso.onView
//import android.support.test.espresso.IdlingRegistry
//import android.support.test.espresso.action.ViewActions.*
//import android.support.test.espresso.assertion.ViewAssertions.matches
//import android.support.test.espresso.matcher.ViewMatchers.*
//import android.support.test.rule.ActivityTestRule
//import android.support.test.runner.AndroidJUnit4
//import br.com.easynvest.SimulatorMatchers.Companion.isEditTextValueEqualTo
//import br.com.easynvest.network.OkHttpHelper
//import br.com.easynvest.view.impl.SimulatorFormActivity
//import com.jakewharton.espresso.OkHttp3IdlingResource
//import org.hamcrest.CoreMatchers.not
//import org.junit.Assert.assertEquals
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//
//@RunWith(AndroidJUnit4::class)
//open class FormScreenTest {
//
//    @get:Rule
//    var activityRule: ActivityTestRule<SimulatorFormActivity> = ActivityTestRule(SimulatorFormActivity::class.java, false, true)
//
//    @Test
//    @Throws(Exception::class)
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getTargetContext()
//
//        assertEquals("br.com.easynvest", appContext.packageName)
//    }
//
//    @Test
//    fun checkIfTextIsDisplayed() {
//        onView(withId(R.id.TVInvestmentAmount)).check(matches(isDisplayed()))
//        onView(withId(R.id.TVMaturityDate)).check(matches(isDisplayed()))
//        onView(withId(R.id.TVCDI)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun checkIfTextIsAccordingToGuideline() {
//        onView(withText("Quanto você gostaria de aplicar? *")).check(matches(isDisplayed()))
//        onView(withText("Qual a data de vencimento do investimento? *")).check(matches(isDisplayed()))
//        onView(withText("Qual o percentual do CDI do investimento? *")).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun checkIfSimulateBtInitializeDisabled() {
//        onView(withId(R.id.BTSimulate)).check(matches(not(isEnabled())))
//    }
//
//    @Test
//    fun checkIfSimulateBtBecomesEnabledWhenAllFieldsAreFilled() {
//        onView(withId(R.id.BTSimulate)).check(matches(not(isEnabled())))
//        fillAllFields()
//        onView(withId(R.id.BTSimulate)).check(matches(isEnabled()))
//    }
//
//    @Test
//    fun checkIfSimulateBtBecomesDisabledWhenAmountFieldIsEmpty(){
//        onView(withId(R.id.BTSimulate)).check(matches(not(isEnabled())))
//        fillAllFields()
//        onView(withId(R.id.BTSimulate)).check(matches(isEnabled()))
//        onView(withId(R.id.ETInvestmentAmount)).perform(clearText())
//        onView(withId(R.id.BTSimulate)).check(matches(not(isEnabled())))
//    }
//
//    @Test
//    fun checkIfSimulateBtBecomesDisabledWhenDateFieldIsEmpty(){
//        onView(withId(R.id.BTSimulate)).check(matches(not(isEnabled())))
//        fillAllFields()
//        onView(withId(R.id.BTSimulate)).check(matches(isEnabled()))
//        onView(withId(R.id.ETMaturityDate)).perform(clearText())
//        onView(withId(R.id.BTSimulate)).check(matches(not(isEnabled())))
//    }
//
//    @Test
//    fun checkIfSimulateBtBecomesDisabledWhenCDIFieldIsEmpty(){
//        onView(withId(R.id.BTSimulate)).check(matches(not(isEnabled())))
//        fillAllFields()
//        onView(withId(R.id.BTSimulate)).check(matches(isEnabled()))
//        onView(withId(R.id.ETCDI)).perform(clearText())
//        onView(withId(R.id.BTSimulate)).check(matches(not(isEnabled())))
//    }
//
//    @Test
//    fun checkIfAmountMaskIsApplied() {
//        onView(withId(R.id.ETInvestmentAmount)).perform(typeText("100000"))
//        closeSoftKeyboard()
//        onView(withId(R.id.ETInvestmentAmount)).check(matches(isEditTextValueEqualTo("R$1.000,00")))
//    }
//
//    @Test
//    fun checkIfDateMaskIsApplied() {
//        onView(withId(R.id.ETMaturityDate)).perform(typeText("25052022"))
//        closeSoftKeyboard()
//        onView(withId(R.id.ETMaturityDate)).check(matches(isEditTextValueEqualTo("25/05/2022")))
//    }
//
//    @Test
//    fun checkIfSimulationResultAfterSimulationClick() {
//        val idlingResource = OkHttp3IdlingResource.create("okhttp", OkHttpHelper.getOkHttpInstance().build())
//        IdlingRegistry.getInstance().register(idlingResource)
//        fillAllFields()
//        onView(withId(R.id.BTSimulate)).perform(click())
//        onView(withId(R.id.SimulationResultLabel)).check(matches(isDisplayed()))
//
//        IdlingRegistry.getInstance().unregister(idlingResource)
//
//    }
//
//    private fun fillAllFields() {
//        onView(withId(R.id.ETInvestmentAmount)).perform(typeText("100000"))
//        closeSoftKeyboard()
//        onView(withId(R.id.ETMaturityDate)).perform(typeText("25052022"))
//        closeSoftKeyboard()
//        onView(withId(R.id.ETCDI)).perform(typeText("100"))
//        closeSoftKeyboard()
//    }
//}
