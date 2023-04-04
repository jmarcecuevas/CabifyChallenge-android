package com.marcelocuevas.cabify.di

import android.content.Context
import androidx.room.Room
import com.marcelocuevas.cabify.framework.room.CabifyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): CabifyDatabase = Room.databaseBuilder(
        context,
        CabifyDatabase::class.java,
        "cabify.db"
    ).build()
}
