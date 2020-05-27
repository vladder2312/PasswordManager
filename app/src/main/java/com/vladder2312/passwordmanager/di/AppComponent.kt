package com.vladder2312.passwordmanager.di

import android.content.Context
import com.vladder2312.passwordmanager.data.db.AccountDao
import com.vladder2312.passwordmanager.data.db.AccountDatabase
import com.vladder2312.passwordmanager.data.repositories.AccountsRepository
import com.vladder2312.passwordmanager.ui.account.AccountPresenter
import com.vladder2312.passwordmanager.ui.add_account.AddAccountPresenter
import com.vladder2312.passwordmanager.ui.main.MainActivity
import com.vladder2312.passwordmanager.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun provideContext() : Context
    fun provideAccountDatabase() : AccountDatabase
    fun provideAccountDao() : AccountDao
    fun provideAccountsRepository() : AccountsRepository

    fun inject(mainPresenter: MainPresenter)
    fun inject(accountPresenter: AccountPresenter)
    fun inject(addAccountPresenter: AddAccountPresenter)
}