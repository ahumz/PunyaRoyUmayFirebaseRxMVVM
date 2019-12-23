package ch.berco.loginmvvmfirebase.Menu.Admin.Schedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil.setContentView
import ch.berco.loginmvvmfirebase.Menu.Admin.MenuSchedule
import ch.berco.loginmvvmfirebase.Menu.ModelSchedule
import ch.berco.loginmvvmfirebase.R
import com.google.firebase.database.*
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.view_user_layout.*


class ViewSchedule :  AppCompatActivity(){

    internal lateinit var txt_add : TextView
    internal lateinit var btn_menu_view : Button
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<ModelSchedule>
    lateinit var listView: ListView

    override fun onBackPressed() {
        super.onBackPressed()
            val intent = Intent(this, MenuSchedule::class.java)
            startActivity(intent)
            Toast.makeText(this, "Kenapa Balik Lagi kak ??", Toast.LENGTH_SHORT).show()
            finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_layout)

        var value2 : String = intent.getStringExtra("value")

        txt_add = findViewById<View>(R.id.txt_isi_schedule) as TextView
        btn_menu_view = findViewById<View>(R.id.btn_menu_view) as Button

        txt_add.text = "Add Schedule " + value2
        ref = FirebaseDatabase.getInstance().getReference(value2)
        list = mutableListOf()
        listView = findViewById(R.id.lv_trans)

        RxView.clicks(txt_add)
            .subscribe {
                val intent = Intent(this, AddSchedule::class.java)
                intent.putExtra("value2",value2)
                startActivity(intent)


                Toast.makeText(this, "Silahkan Isi Jadwalnya, kak :)", Toast.LENGTH_SHORT).show()
            }

        RxView.clicks(btn_menu_view)
            .subscribe {
                val intentLogout = Intent(this, MenuSchedule::class.java)
                startActivity(intentLogout)


                Toast.makeText(this, "Kenapa Balik Lagi kak ??", Toast.LENGTH_SHORT).show()
            }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    list.clear()
                    for (h in p0.children){
                        val user = h.getValue(ModelSchedule::class.java)
                        list.add(user!!)
                    }
                    val adapter = AdapterSchedule(
                        this@ViewSchedule,
                        R.layout.item_layout,
                        list
                    )
                    listView.adapter = adapter

                    txt_null.visibility = View.GONE
                }
            }
        })
    }
}