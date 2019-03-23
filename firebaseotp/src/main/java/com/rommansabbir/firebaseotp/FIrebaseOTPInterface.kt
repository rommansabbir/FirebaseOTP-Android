package com.rommansabbir.firebaseotp

interface FIrebaseOTPInterface {
    fun onVerificationSuccess(successMessage : String)
    fun onVerificationFailed(failedMessage : String)
    fun onCodeSent(codeSentMessage : String)
}