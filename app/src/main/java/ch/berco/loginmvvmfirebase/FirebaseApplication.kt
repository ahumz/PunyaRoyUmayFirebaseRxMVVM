package ch.berco.loginmvvmfirebase

import android.app.Application
import ch.berco.loginmvvmfirebase.data.firebase.FirebaseSource
import ch.berco.loginmvvmfirebase.data.repositories.UserRepository
import ch.berco.loginmvvmfirebase.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class FirebaseApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FirebaseApplication))

        bind() from singleton { FirebaseSource() }
        bind() from singleton { UserRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
//        bind() from provider { HomeViewModelFactory(instance()) }
    }
}