package ch.berco.loginmvvmfirebase.Menu.Admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ch.berco.loginmvvmfirebase.R
import ch.berco.loginmvvmfirebase.ui.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.view.RxView

class MenuAdmin :  AppCompatActivity(){

    internal lateinit var txt_logout : TextView
    internal lateinit var txt_regis : TextView
    internal lateinit var txt_schedule : TextView

    var fireAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout_admin)

        val schedule : TextView = findViewById(R.id.txt_view_schedule)
        val logout : TextView = findViewById(R.id.txt_logout_admin)

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_right)

        schedule.startAnimation(animation)
        logout.startAnimation(animation)

        txt_logout = findViewById<View>(R.id.txt_logout_admin) as TextView
        txt_schedule = findViewById<View>(R.id.txt_view_schedule) as TextView

        RxView.clicks(txt_schedule)
            .subscribe {
                val intent = Intent(this, MenuSchedule::class.java)
                startActivity(intent)


                Toast.makeText(this, "Silahkan Pilih Harinya, kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(txt_logout)
            .subscribe {
                val intentLogout = Intent(this, LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentLogout)

                Toast.makeText(this, "Akun Anda Sudah di Logout, kak :)", Toast.LENGTH_SHORT).show()
            }
    }
}