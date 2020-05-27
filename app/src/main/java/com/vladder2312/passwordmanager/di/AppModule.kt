package com.vladder2312.passwordmanager.di

import android.content.Context
import androidx.room.Room
import com.vladder2312.passwordmanager.data.db.AccountDao
import com.vladder2312.passwordmanager.data.db.AccountDatabase
import com.vladder2312.passwordmanager.data.repositories.AccountsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext() : Context {
        return context;
    }

    @Provides
    fun provideDatabase() : AccountDatabase {
        return Room.databaseBuilder(context, AccountDatabase::class.java, "Database")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideAccountDao() : AccountDao {
        return provideDatabase().accountDao
    }

    @Provides
    fun provideAccountsRepository() : AccountsRepository {
        return AccountsRepository(provideAccountDao())
    }
}