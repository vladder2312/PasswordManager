package com.vladder2312.passwordmanager.ui.main

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.passwordmanager.App
import com.vladder2312.passwordmanager.data.repositories.AccountsRepository
import com.vladder2312.passwordmanager.domain.Account
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var accountsRepository : AccountsRepository

    private var model = MainModel();

    init {
        App.appComponent.inject(this)
    }

    fun updateData(){
        val disposable = accountsRepository.getAllAccounts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                model.accounts.clear()
                model.accounts.addAll(it)
                viewState.setAccounts(model.accounts)
            }
    }

    fun siteTextChanged(text : String){
        model.siteText = text
    }

    fun findButtonClicked(){
        val disposable = accountsRepository.getAccountsByName("%"+model.siteText+"%")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                model.accounts.clear()
                model.accounts.addAll(it)
                viewState.setAccounts(model.accounts)
            }
    }
}