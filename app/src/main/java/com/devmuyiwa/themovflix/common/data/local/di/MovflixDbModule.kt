package com.devmuyiwa.themovflix.common.data.local.di

import android.content.Context
import androidx.room.Room
import com.devmuyiwa.themovflix.common.data.local.MovflixDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovflixDbModule {
    @Provides
    @Singleton
    fun provideMovflixDatabase(@ApplicationContext context: Context): MovflixDatabase =
        Room.databaseBuilder(
            context,
            MovflixDatabase::class.java,
            "movflix"
        ).build()
}