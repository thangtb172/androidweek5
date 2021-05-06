package ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SignupViewModelFactory(val fullName: String, val email: String, val passWord: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SignupViewModel::class.java)){
            return SignupViewModel(fullName,email,passWord) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}