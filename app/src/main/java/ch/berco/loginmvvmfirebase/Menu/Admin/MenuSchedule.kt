package ch.berco.loginmvvmfirebase.Menu.Admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import ch.berco.loginmvvmfirebase.Menu.Admin.Schedule.ViewSchedule
import ch.berco.loginmvvmfirebase.R
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.schedule_menu.*

class MenuSchedule :  AppCompatActivity(){

    internal lateinit var cv_senin : CardView
    internal lateinit var cv_selasa : CardView
    internal lateinit var cv_rabu : CardView
    internal lateinit var cv_kamis : CardView
    internal lateinit var cv_jumat : CardView
    internal lateinit var cv_sabtu : CardView
    internal lateinit var cv_event : CardView
    internal lateinit var cv_back_menu : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_menu)

        cv_senin = findViewById<View>(R.id.cv_senin) as CardView
        cv_selasa = findViewById<View>(R.id.cv_selasa) as CardView
        cv_rabu = findViewById<View>(R.id.cv_rabu) as CardView
        cv_kamis = findViewById<View>(R.id.cv_kamis) as CardView
        cv_jumat = findViewById<View>(R.id.cv_jumat) as CardView
        cv_sabtu = findViewById<View>(R.id.cv_sabtu) as CardView
        cv_event = findViewById<View>(R.id.cv_event) as CardView
        cv_back_menu = findViewById<View>(R.id.img_back_menu) as ImageView

        RxView.clicks(cv_senin)
            .subscribe {
                val intent = Intent(this, ViewSchedule::class.java)
                intent.putExtra("value","Monday")
                startActivity(intent)

                Toast.makeText(this, "Ini jadwal senin kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(cv_selasa)
            .subscribe {
                val intent = Intent(this, ViewSchedule::class.java)
                intent.putExtra("value","Tuesday")
                startActivity(intent)

                Toast.makeText(this, "Ini jadwal selasa kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(cv_rabu)
            .subscribe {
                val intent = Intent(this, ViewSchedule::class.java)
                intent.putExtra("value","Wednesday")
                startActivity(intent)

                Toast.makeText(this, "Ini jadwal rabu kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(cv_kamis)
            .subscribe {
                val intent = Intent(this, ViewSchedule::class.java)
                intent.putExtra("value","Thursday")
                startActivity(intent)

                Toast.makeText(this, "Ini jadwal kamis kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(cv_jumat)
            .subscribe {
                val intent = Intent(this, ViewSchedule::class.java)
                intent.putExtra("value","Friday")
                startActivity(intent)

                Toast.makeText(this, "Ini jadwal jumat kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(cv_sabtu)
            .subscribe {
                val intent = Intent(this, ViewSchedule::class.java)
                intent.putExtra("value","Saturday")
                startActivity(intent)

                Toast.makeText(this, "Ini jadwal sabtu kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(cv_event)
            .subscribe {
                val intent = Intent(this, ViewSchedule::class.java)
                intent.putExtra("value","Event")
                startActivity(intent)

                Toast.makeText(this, "Ini jadwal Event kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(cv_back_menu)
            .subscribe {
                val intentLogout = Intent(this, MenuAdmin::class.java)
                startActivity(intentLogout)

                Toast.makeText(this, "Kenapa balik lagi kak ??", Toast.LENGTH_SHORT).show()
            }

    }
}