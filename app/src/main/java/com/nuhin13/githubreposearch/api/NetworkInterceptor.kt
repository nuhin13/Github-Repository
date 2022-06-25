package com.nuhin13.githubreposearch.api

import android.content.Context
import android.net.ConnectivityManager
import com.nuhin13.githubreposearch.api.RetrofitService.Companion.BASE_URL
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object NetworkInterceptor {
    fun provideOkHttp(context: Context): OkHttpClient {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)

        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            }

        builder.addInterceptor(provideLoggingInterceptor())
        builder.addInterceptor(provideOnlineInterceptor())
        builder.addNetworkInterceptor(provideOfflineInterceptor(context))
        builder.cache(cache)

        return builder.build()
    }

    private fun provideOnlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response: okhttp3.Response = chain.proceed(chain.request())
            val maxAge = 60
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
    }

    private fun provideOfflineInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            var request: Request = chain.request()
            if (!isNetworkAvailable(context)) {
                val maxStale = 60 * 60 * 24 * 30
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            chain.proceed(request)
        }
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideUnsplashApi(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }
}