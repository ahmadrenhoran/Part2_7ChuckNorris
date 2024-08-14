package com.ahmadrenhoran.part2_7chucknorris.di

import com.ahmadrenhoran.part2_7chucknorris.data.model.Joke
import com.ahmadrenhoran.part2_7chucknorris.data.remote.ChuckNorrisApiService
import com.ahmadrenhoran.part2_7chucknorris.data.repository.JokeRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/jokes/") // URL base dari BuildConfig
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideJokeApiService(retrofit: Retrofit): ChuckNorrisApiService = retrofit.create(ChuckNorrisApiService::class.java)

    @Provides
    fun provideJokeRepository(
        apiService: ChuckNorrisApiService
    ): JokeRepository = JokeRepository(
        apiService = apiService
    )
}