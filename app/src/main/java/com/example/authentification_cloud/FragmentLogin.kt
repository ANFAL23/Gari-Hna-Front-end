package com.example.authentification_cloud

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.authentification_cloud.databinding.FragmentLoginBinding
import com.example.authentification_cloud.databinding.FragmentRegisterBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var SIGN_IN = 100
lateinit var mGoogleSignInClient : GoogleSignInClient
var login = 0
var user = 0


class FragmentLogin : Fragment()  { //, GoogleApiClient.OnConnectionFailedListener

    private lateinit var binding: FragmentLoginBinding

    lateinit var signInButton : SignInButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentLoginBinding.inflate(layoutInflater)

        var gso : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(MainActivityView, gso)
        var account : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(MainActivityView)
        //updateUI(account)
        //googleApiClient = GoogleApiClient.Builder(thisView.context).enableAutoManage(thisView. , this)
        //   .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()
        signInButton = binding.GoogleSignIn
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        /*signInButton.setOnClickListener( View.OnClickListener {
            Toast.makeText(thisView.context, "Sign in !", Toast.LENGTH_SHORT ).show()
            @Override
            fun onClick ( view: View ) {
                var intent : Intent = mGoogleSignInClient.signInIntent
                startActivityForResult(intent, SIGN_IN )
            }
        })*/
        /*signInButton.setOnClickListener( View.OnClickListener {
            fun onClick ( v: View ) {
                var intent : Intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
                startActivityForResult(intent, SIGN_IN )
            }
        })*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.GoogleSignIn.setOnClickListener {
            Toast.makeText(requireActivity(), "Sign in !", Toast.LENGTH_SHORT ).show()
            var intent : Intent = mGoogleSignInClient.signInIntent
            startActivityForResult(intent, SIGN_IN )
        }

        binding.Connectionbutton.setOnClickListener {
            if(TextUtils.isEmpty(binding.password.text.toString()) ||
                TextUtils.isEmpty(binding.Email.text.toString()))
            {

                Toast.makeText(requireActivity(),"Email and passeword are requeired", Toast.LENGTH_LONG).show()
            }

            login(CreateRequest ())
        }
        binding.singin.setOnClickListener {
            //login(binding.ConnectionEmailadd.text.toString(),binding.ConnectionMdpadd.text.toString())
            Toast.makeText(requireActivity(),"Sing in ", Toast.LENGTH_LONG).show()


            view.findNavController().navigate(R.id.action_fragment_login_to_fragment_register)

        }
        // handle backPressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack(R.id.fragment_ListPark , false)
            }

        })


    }
    private fun CreateRequest () : LoginRequest {

        var loginRequest =  LoginRequest ();

        loginRequest.setEmail(binding.Email.text.toString())
        loginRequest.setMdp(binding.password.text.toString())


        return loginRequest


    }

    private fun login(loginRequest : LoginRequest) {

        val userResponseCall = ApiClient.getUserService().LoginUser(loginRequest)
        userResponseCall.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(
                call: retrofit2.Call<UserResponse?>,
                response: Response<UserResponse?>
            ) {
                if (response.isSuccessful) {
                    //UserResponse userreponse =
                    Toast.makeText(requireActivity(),response.message().toString() , Toast.LENGTH_LONG).show()
                    login = 1
                    user= response.body()?.id_user!!
                    reservationList = getReservation()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ( requestCode == SIGN_IN ) {
            var task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
            /*var result : GoogleSignInResult? = data?.let {
                Auth.GoogleSignInApi.getSignInResultFromIntent( it )
            }
            if (result != null) {
                if ( result.isSuccess ){
                    startActivity( Intent( thisView.context , ProfilActivity::class.java ) )
                    //finish()
                }
                else {
                    Toast.makeText(thisView.context, "LoginFailed !", Toast.LENGTH_SHORT ).show()
                }

            }*/
        }
    }

    private fun handleSignInResult ( completedTask: Task<GoogleSignInAccount>  ) {
        //try {
            var account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            //updateUI(account)
            var acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(thisView.context)
            if (acct != null )
            {
                var loginRequest =  LoginRequest ();
                loginRequest.setEmail(acct.email.toString())
                loginRequest.setMdp("")
                val userResponseCall = ApiClient.getUserService().GoogleLoginUser(loginRequest)
                //Toast.makeText(requireActivity()," response: ${userResponseCall.toString()}." , Toast.LENGTH_LONG).show()
                var name = acct.getDisplayName()
                var photo = acct.photoUrl
                Toast.makeText(thisView.context, " User email : "+ acct.email, Toast.LENGTH_SHORT).show()

                userResponseCall.enqueue(object : Callback<UserResponse?> {
                    override fun onResponse(
                        call: retrofit2.Call<UserResponse?>,
                        response: Response<UserResponse?>
                    ) {
                        if (response.isSuccessful) {
                            login=1
                            user= response.body()?.id_user!!
                            Toast.makeText(requireActivity()," welcome: ${user} ", Toast.LENGTH_LONG).show()

                            Toast.makeText(requireActivity()," welcome: ${acct.displayName} ", Toast.LENGTH_LONG).show()
                            //mGoogleSignInClient.signOut()
                            reservationList = getReservation()
                            view?.findNavController()?.navigate(R.id.action_fragment_login_to_fragment_listpark)
                        } else {
                            Toast.makeText(requireActivity(), "email ${acct.email} doesn't exist ", Toast.LENGTH_LONG).show()
                            mGoogleSignInClient.signOut()
                            view?.findNavController()?.navigate(R.id.action_fragment_login_to_fragment_register)
                        }
                    }
                    override fun onFailure(call: retrofit2.Call<UserResponse?>, t: Throwable) {
                        Toast.makeText(requireActivity(), "erreur in saved ", Toast.LENGTH_LONG)
                    }
                })
            }

    }


    fun getReservation(): List<Reservation> {
        Base_Name= "xxxxxxxxxxxxxxxxxxxxxxx"
        db = AppDatabase.buildDatabase(requireActivity())?.getReservationDao()

        reservationList = db?.getReservation() as ArrayList<Reservation>


            var reservations: List<ReservationRequest>? = GetApiReservations()


            if (reservations != null) {
                Log.e("text tete tetette", reservations.toString());
                for (res in reservations) {

                    addReservation(
                        Reservation( res.id_res, res.date,
                            res.entree, res.sortie, res.id_user, res.id_park,
                            res.valider, 1  )  )
                }
            }
      return reservationList as ArrayList<Reservation>
    }

    fun addReservation(reservation: Reservation) {

        db?.addReservations(reservation)

    }

    fun GetApiReservations(): List<ReservationRequest>? {
        var reservations: List<ReservationRequest>? = null
        val reservationResponseCall = ApiClient.getReservationService().reservations
        reservationResponseCall.enqueue(object : Callback<List<ReservationRequest>> {
            override fun onResponse(
                call: Call<List<ReservationRequest>>,
                response: Response<List<ReservationRequest>>
            ) {
                if (response?.body() != null)
                    reservations = response?.body()!!
                var  i = 1
                if (reservations != null) {
                    Log.e("text tete tetette", reservations.toString());

                    for (res in reservations!!) {

                        addReservation(

                            Reservation( res.id_res,res.date,
                                res.entree, res.sortie, res.id_user, res.id_park,
                                res.valider, 1  )  )

                    }
                }
                Log.e("seccuess", response.body().toString())
            }

            override fun onFailure(call: Call<List<ReservationRequest>>, t: Throwable) {
                Log.e("erreur", t.localizedMessage);
            }
        })
        return reservations
    }

}
