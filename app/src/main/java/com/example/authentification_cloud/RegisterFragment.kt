package com.example.authentification_cloud

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.authentification_cloud.ApiClient
import com.example.authentification_cloud.R
import com.example.authentification_cloud.databinding.FragmentLoginBinding
import com.example.authentification_cloud.databinding.FragmentRegisterBinding
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment() {
    private lateinit var binding : FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_login, container, false)

        val view = binding.root
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnSubmit.setOnClickListener {
            if(TextUtils.isEmpty(binding.name.text.toString()) ||
                TextUtils.isEmpty(binding.Email.text.toString()) ||
                TextUtils.isEmpty(binding.MobileNumber.text.toString()) ||
                TextUtils.isEmpty(binding.Password.text.toString())||
                TextUtils.isEmpty(binding.ConfirmPassword.text.toString())

               ) {

                Toast.makeText(requireActivity(), "all feilds are requested ", Toast.LENGTH_LONG)
            }
            else {
                if(binding.ConfirmPassword.text.toString()!=binding.Password.text.toString())
                { Toast.makeText(requireActivity(),"Password and confirm password should be same", Toast.LENGTH_LONG).show()
                  Log.e("la valeur recupe from shared","anfellllllllllll")
                }
                else {
                    saveUser(CreateRequest())
                }
            }

        }



    }



private fun CreateRequest () : UserRequest {

    var userRequest =  UserRequest();
    userRequest.setNom(binding.name.text.toString())
    userRequest.setPrenom(binding.name.text.toString())
    userRequest.setEmail(binding.Email.text.toString())
    userRequest.setNum_tel(binding.MobileNumber.text.toString())
    userRequest.setMdp(binding.Password.text.toString())

    return userRequest


}

private fun saveUser (userRequest : UserRequest) {

    val userResponseCall = ApiClient.getUserService().saveUser(userRequest)
    userResponseCall.enqueue(object : Callback<UserResponse?> {
        override fun onResponse(
            call: retrofit2.Call<UserResponse?>,
            response: Response<UserResponse?>
        ) {

            if (response.isSuccessful) {
                Toast.makeText(requireActivity(),"savedSuccesfuli", Toast.LENGTH_LONG).show()
                view?.findNavController()?.navigate(R.id.action_fragment_login_to_fragment_listpark)

            } else {
                Toast.makeText(requireActivity(), "erreur in saved ", Toast.LENGTH_LONG)
            }
        }

        override fun onFailure(call: retrofit2.Call<UserResponse?>, t: Throwable) {
            Toast.makeText(requireActivity(), "erreur in saved ", Toast.LENGTH_LONG)
        }
    })


}
}