package ch.berco.loginmvvmfirebase.Menu.User

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ch.berco.loginmvvmfirebase.R
import ch.berco.loginmvvmfirebase.ui.auth.LoginActivity
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.menu_layout_user.*

class MenuUser :  AppCompatActivity(){

    internal lateinit var txt_logout : TextView
    internal lateinit var txt_schedule : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout_user)

        txt_logout = findViewById<View>(R.id.txt_logout_user) as TextView
        txt_schedule = findViewById<View>(R.id.txt_view_schedule_user) as TextView

        RxView.clicks(txt_schedule)
            .subscribe {
                val intentLogout = Intent(this, MenuSchedule::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentLogout)

                Toast.makeText(this, "Silahkan Pilih Harinya, kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(txt_logout_user)
            .subscribe {
                val intentLogout = Intent(this, LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentLogout)

                Toast.makeText(this, "Akun Anda Sudah di Logout, kak :)", Toast.LENGTH_SHORT).show()
            }
    }
}