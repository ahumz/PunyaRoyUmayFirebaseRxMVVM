package ch.berco.loginmvvmfirebase.ui.auth

interface AuthListener {
    fun onSuccessAdmin()
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}