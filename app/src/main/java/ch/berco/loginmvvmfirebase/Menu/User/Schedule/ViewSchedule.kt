package ch.berco.loginmvvmfirebase.Menu.User.Schedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ch.berco.loginmvvmfirebase.Menu.ModelSchedule
import ch.berco.loginmvvmfirebase.Menu.User.MenuSchedule
import ch.berco.loginmvvmfirebase.R
import com.google.firebase.database.*
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.view_user_layout.*


class ViewSchedule : AppCompatActivity(){

    internal lateinit var txt_add : TextView
    internal lateinit var btn_menu_view : Button
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<ModelSchedule>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_user_layout)

        txt_add = findViewById<View>(R.id.txt_isi_schedule) as TextView
        btn_menu_view = findViewById<View>(R.id.btn_menu_view) as Button

        var value : String = intent.getStringExtra("value")
        txt_add.text = value
        ref = FirebaseDatabase.getInstance().getReference(value)
        list = mutableListOf()
        listView = findViewById(R.id.lv_trans)

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
                    val adapter = AdapterUser(
                        this@ViewSchedule,
                        R.layout.item_user_layout,
                        list
                    )
                    listView.adapter = adapter

                    txt_null.visibility = View.GONE
                }
            }
        })

        RxView.clicks(btn_menu_view)
            .subscribe {
                val intent = Intent(this, MenuSchedule::class.java)
                startActivity(intent)

                Toast.makeText(this, "kenapa balik lagi kak ??", Toast.LENGTH_SHORT).show()
            }
    }
}