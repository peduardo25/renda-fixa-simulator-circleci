package br.com.easynvest

import br.com.easynvest.common.convertDate
import br.com.easynvest.common.localDateFormat
import br.com.easynvest.common.toBrCurrency
import br.com.easynvest.common.unmask
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExtensionTest {

    @Test
    fun shouldUnmaskCurrency() {
        val value = "RS 1.000,00"
        org.junit.Assert.assertEquals("100000", value.unmask())
    }

    @Test
    fun shouldConvertToApiDateFormat() {
        val value = "25/05/2022"
        Assert.assertEquals("2022-05-25", value.convertDate())
    }

    @Test
    fun shouldConvertApiDateToLocalDate() {
        val value = "2022-05-25T00:00:00"
        Assert.assertEquals("25/05/2022", value.localDateFormat())
    }

    @Test
    fun shouldFormatInvestmentValueToBrCurrency() {
        val value = 100000.0
        Assert.assertEquals("R$ 1.000,00", value.toBrCurrency())
    }

}