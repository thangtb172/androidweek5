package ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import data.Account

class ProfileViewModel(fullName:String,email: String, passWord: String): ViewModel() {
    var account: MutableLiveData<Account> = MutableLiveData()
    init{
        account.value = Account(fullName,email,passWord)
    }
}