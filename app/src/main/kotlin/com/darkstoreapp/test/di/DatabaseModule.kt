package com.darkstoreapp.test.di

import android.content.Context
import androidx.room.Room
import com.darkstoreapp.test.data.database.ApplicationDatabase
import com.darkstoreapp.test.data.database.HistoryRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideApplicationDatabase(
        @ApplicationContext context: Context
    ): ApplicationDatabase {
        return Room.databaseBuilder(context, ApplicationDatabase::class.java, "app_database.db")
            .build()
    }


    @Provides
    @Singleton
    fun provideHistoryRecordDao(database: ApplicationDatabase): HistoryRecordDao {
        return database.historyRecordDao()
    }

}