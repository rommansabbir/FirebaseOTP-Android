package com.rommansabbir.firebaseotp

interface FirebaseOTPCallback {
    fun onVerificationSuccess()
    fun onVerificationFailed()
    fun onCodeSent()
}