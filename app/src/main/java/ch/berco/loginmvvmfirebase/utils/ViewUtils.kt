package ch.berco.loginmvvmfirebase.utils

import android.content.Context
import android.content.Intent
import ch.berco.loginmvvmfirebase.Biodata.IsiBiodataActivity
import ch.berco.loginmvvmfirebase.SplashAdmin
import ch.berco.loginmvvmfirebase.SplashUser

fun Context.startIsiBiodataActivity() =
    Intent(this, IsiBiodataActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startAdminActivity() =
    Intent(this, SplashAdmin::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startJurusanActivity() =
    Intent(this, SplashUser::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }