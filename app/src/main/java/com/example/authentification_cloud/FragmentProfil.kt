package com.example.authentification_cloud

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.authentification_cloud.databinding.FragmentProfilBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn

class ProfilFragment : Fragment() {
    private lateinit var binding: FragmentProfilBinding

    private lateinit var profilImage: ImageView
    private lateinit var name: TextView
    private lateinit var signOutBtn: Button
    private lateinit var myReservationBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfilBinding.inflate(layoutInflater)

        name = binding.ProfilName
        profilImage = binding.ProfilImage

        signOutBtn = binding.signOut
        myReservationBtn = binding.MesReservation

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilBinding.inflate(layoutInflater)

        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signOut.setOnClickListener {
            Toast.makeText(requireActivity(), "Sign out !", Toast.LENGTH_SHORT ).show()
            mGoogleSignInClient.signOut()
            login = 0
            user = 0
            Toast.makeText(requireActivity(), "Sign out done !", Toast.LENGTH_SHORT ).show()
            //startActivity(Intent( requireActivity() , MainActivity::class.java ))
        }

    }


}