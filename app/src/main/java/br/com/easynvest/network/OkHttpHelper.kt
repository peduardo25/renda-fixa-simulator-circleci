package br.com.easynvest.network

import okhttp3.OkHttpClient

class OkHttpHelper {

    companion object {
        private var client: OkHttpClient.Builder? = null

        fun getOkHttpInstance(): OkHttpClient.Builder {
            if (client == null) {
                client = OkHttpClient.Builder()
            }

            return client!!
        }

    }
}