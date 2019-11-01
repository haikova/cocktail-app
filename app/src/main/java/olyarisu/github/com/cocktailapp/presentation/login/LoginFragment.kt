package olyarisu.github.com.cocktailapp.presentation.login

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_login.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.presentation.base.BaseFragment
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import olyarisu.github.com.cocktailapp.COCKTAIL_APP


class LoginFragment : BaseFragment() {

    private val rcSigIn = 13
    private lateinit var auth: FirebaseAuth

    override val layoutRes: Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        button_login.setOnClickListener {
            sigIn()
        }
    }

    private fun sigIn() {
        activity?.let {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val mGoogleSignInClient = GoogleSignIn.getClient(it, gso)

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, rcSigIn)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == rcSigIn) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.

            Toast.makeText(activity, account?.email, Toast.LENGTH_LONG).show()
            account?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("qwe", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(activity, e.statusCode, Toast.LENGTH_LONG).show()
        }

    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("Login", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        activity?.let {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Login", "signInWithCredential:success")
                        val user = auth.currentUser
                        if (user != null) {
                            checkUserInDB(user)
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Login", "signInWithCredential:failure", task.exception)
                        Toast.makeText(it, "Auth Failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun checkUserInDB(user: FirebaseUser) {
        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(user.uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    Log.d(COCKTAIL_APP, "DocumentSnapshot data: ${document.data}")
                } else {
                    val favourites = hashMapOf(
                        "favourites" to arrayListOf<String>()
                    )
                    db.collection("users")
                        .document(user.uid)
                        .set(favourites)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(COCKTAIL_APP, "get failed with ", exception)
            }


    }
}