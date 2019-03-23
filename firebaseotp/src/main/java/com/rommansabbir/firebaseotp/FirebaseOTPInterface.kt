package com.rommansabbir.firebaseotp

interface FirebaseOTPInterface {
    fun onVerificationSuccess(successMessage : String)
    fun onVerificationFailed(failedMessage : String)
    fun onCodeSent(codeSentMessage : String)
}