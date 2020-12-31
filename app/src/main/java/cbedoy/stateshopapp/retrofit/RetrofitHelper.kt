package cbedoy.stateshopapp.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import cbedoy.stateshopapp.BuildConfig
import java.util.concurrent.TimeUnit

fun buildRetrofitInstance(baseUrl: String): Retrofit {
    val httpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    addLoggingInterceptor(httpBuilder)

    val httpClient = httpBuilder
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(httpClient)
        .build()
}

private fun addLoggingInterceptor(httpBuilder: OkHttpClient.Builder) {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    httpBuilder.addInterceptor(logging)
}

/*
private fun addHeaderInterceptor(httpBuilder: OkHttpClient.Builder) {
    val interceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Content-type", "application/json")
            .addHeader("x-api-key", BuildConfig.API_KEY)
            .method(original.method, original.body)

        val request = requestBuilder.build()
        chain.proceed(request)
    }
    httpBuilder.addInterceptor(interceptor)
}*/