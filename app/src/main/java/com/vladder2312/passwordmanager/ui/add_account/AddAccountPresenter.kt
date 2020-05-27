package com.vladder2312.passwordmanager.ui.add_account

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.passwordmanager.App
import com.vladder2312.passwordmanager.data.repositories.AccountsRepository
import com.vladder2312.passwordmanager.domain.Account
import javax.inject.Inject

@InjectViewState
class AddAccountPresenter : MvpPresenter<AddAccountView>() {

    @Inject
    lateinit var accountsRepository: AccountsRepository

    init {
        App.appComponent.inject(this)
    }

    fun saveAccount(account: Account){
        accountsRepository.saveAccount(account)
    }
}