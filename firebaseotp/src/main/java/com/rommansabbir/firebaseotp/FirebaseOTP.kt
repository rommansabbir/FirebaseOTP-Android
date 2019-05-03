package com.rommansabbir.firebaseotp

import android.support.v7.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

object FirebaseOTP : AppCompatActivity() {
    private var verificationCode: String? = null
    private var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private lateinit var phoneNumber : String
    private lateinit var firebaseAuth :FirebaseAuth
    private lateinit var callback: FirebaseOTPCallback

    fun verify(firebaseAuth: FirebaseAuth?, phoneNumber: String, callback : FirebaseOTPCallback) {
        /**
         * This method handle firebase one time permission
         * @param firebaseAuth, instantiated firebaseAuth
         * @param phoneNumber, the number you want to send otp code
         * @param callback, provide callback to get notified on otp steps
         */
        this.firebaseAuth = firebaseAuth!!
        this.phoneNumber = phoneNumber
        this.callback= callback
        /**
         * Handle otp stuff
         */
        handleVerification()
        /**
         * Start otp
         */
        startOTP(phoneNumber)
    }
    /**
     * This method handle verification using PhoneAuthProvider
     * @param phoneNumber, the number used for otp
     */
    private fun startOTP(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                java.util.concurrent.TimeUnit.SECONDS,
                this,
                mCallbacks!!)
    }

    /**
     * This method handle otp code sending stuff
     * Notify via callback to get notified
     */
    private fun handleVerification() {
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(e: FirebaseException) {
            }

            override fun onCodeSent(s: String?, forceResendingToken: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(s, forceResendingToken)
                /**
                 * Assign the code to verificationCode and notify the callback interface
                 */
                verificationCode = s
                callback.onCodeSent()

            }
        }
    }

    /**
     * This method handle code verification stuff
     * @param code, the code sent for otp
     */
    fun verifyOTP(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationCode!!, code)
        checkOTPCredential(credential)
    }


    /**
     * This method handle otp credential checking
     * @param credential, the PhoneAuthCredential to check with Firebase Auth
     * Notify on task success or failure
     */
    private fun checkOTPCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onVerificationSuccess()

                    } else {
                        callback.onVerificationFailed()
                    }
                }
    }

}
