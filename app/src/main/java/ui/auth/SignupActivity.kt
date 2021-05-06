package ui.auth

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.week3.R
import com.example.week3.databinding.ActivitySignupBinding
import data.AccountDataStore
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var signupViewModel: SignupViewModel
    private lateinit var signupViewModelFactory: SignupViewModelFactory
    private lateinit var accountDataStore: AccountDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        signupViewModelFactory = SignupViewModelFactory(fullName = "", email = "", passWord = "")
        signupViewModel = ViewModelProvider(this, signupViewModelFactory).get(SignupViewModel::class.java)
        accountDataStore = AccountDataStore(this)
        binding.apply {

            edtEmail.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString().trim()).matches())
                        binding.btnSignup.isEnabled = true
                    else {
                        binding.btnSignup.isEnabled = false
                        binding.edtEmail.setError("Invalid Email")
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
            })

            edtPassword.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val pattern: Pattern
                    val matcher: Matcher
                    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"

                    pattern = Pattern.compile(PASSWORD_PATTERN)
                    matcher = pattern.matcher(edtPassword.text.toString().trim())
                    if (matcher.matches())
                        binding.btnSignup.isEnabled = true
                    else {
                        binding.btnSignup.isEnabled = false
                        binding.edtPassword.setError("Invalid Password")
                    }


                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
            })
            btnSignup.setOnClickListener{
                var fullName = edtFullName.text.toString().trim()
                var email = edtEmail.text.toString().trim()
                var passWord = edtPassword.text.toString().trim()
                //Stores the values
//                if (fullName == null || email == null || passWord == null){
//                    To
//                }
                    lifecycleScope.launch {
                    accountDataStore.storeData(fullName, email, passWord)
                }
                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(intent)

            }
        }

    }
}