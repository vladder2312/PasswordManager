package com.vladder2312.passwordmanager.ui.account

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.passwordmanager.App
import com.vladder2312.passwordmanager.data.repositories.AccountsRepository
import com.vladder2312.passwordmanager.domain.Account
import javax.inject.Inject

@InjectViewState
class AccountPresenter : MvpPresenter<AccountView>() {

    @Inject
    lateinit var accountsRepository: AccountsRepository

    init {
        App.appComponent.inject(this)
    }

    fun updateAccount(account: Account){
        accountsRepository.updateAccount(account)
    }

    fun deleteAccount(id: String){
        accountsRepository.deleteAccount(id)
    }
}