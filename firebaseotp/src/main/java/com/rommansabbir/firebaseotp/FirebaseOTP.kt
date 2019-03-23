package com.rommansabbir.firebaseotp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class FirebaseOTP(private var context: Context?, private var firebaseAuth: FirebaseAuth?, private val phoneNumber: String) : AppCompatActivity() {
    private var verificationCode: String? = null
    private var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private var firebaseOTPCallbackInterface: FirebaseOTPInterface? = null
    private val codeSentMessage = "Code Sent"
    private val successMessage = "OK"
    private val failedMessage = "ERROR"

    init {
        /**
         * Instantiate firebaseOTPCallbackInterface
         */
        firebaseOTPCallbackInterface = context as FirebaseOTPInterface
        handleFirebaseVerification()

        startPhoneNumberVerification(phoneNumber)
    }
    /**
     * Start handling phone number verification
     */
    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                java.util.concurrent.TimeUnit.SECONDS,
                this,
                mCallbacks!!)
    }

    /**
     * Handle firebase verification
     */
    private fun handleFirebaseVerification() {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted: ")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d(TAG, "onVerificationFailed: ")
            }

            override fun onCodeSent(s: String?, forceResendingToken: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(s, forceResendingToken)
                /**
                 * Assign the code to verificationCode and notify the callback interface
                 */
                verificationCode = s
                firebaseOTPCallbackInterface!!.onCodeSent(codeSentMessage)

            }
        }
    }

    /**
     * Verify the OTP code
     */
    fun verifyOTP(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationCode!!, code)
        checkOTPCredential(credential)
    }


    /**
     * Check the OTP credential
     */
    private fun checkOTPCredential(credential: PhoneAuthCredential) {
        firebaseAuth!!.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        firebaseOTPCallbackInterface!!.onVerificationSuccess(successMessage)

                    } else {
                        firebaseOTPCallbackInterface!!.onVerificationFailed(failedMessage)
                    }
                }
    }

    /**
     * Destroy the callback after usages
     */
    fun selfDestroy() {
        firebaseOTPCallbackInterface = null
        mCallbacks = null
        firebaseAuth = null
        context = null
    }

    companion object {
        private val TAG = "FirebaseOTP"
    }
}
