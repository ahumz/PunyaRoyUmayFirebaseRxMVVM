package ch.berco.loginmvvmfirebase.Menu.Admin.Schedule

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ch.berco.loginmvvmfirebase.Menu.Admin.MenuSchedule
import ch.berco.loginmvvmfirebase.Menu.ModelSchedule
import ch.berco.loginmvvmfirebase.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.add_schedule_layout.*
import org.jetbrains.anko.toast


class AddSchedule :  AppCompatActivity(), View.OnClickListener {

    private var imgPath: Uri? = null
    lateinit var ref: DatabaseReference
    internal lateinit var btn_menu: Button

    override fun onClick(v: View?) {
        var value3 : String = intent.getStringExtra("value2")

        val progressDialog = ProgressDialog(this)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Di Catet Dulu, Pulsanya sekalian kak ?")
        progressDialog.show()

        ref = FirebaseDatabase.getInstance().getReference("images")
        when (v) {
            btn_submit -> {
                val materi = edt_materi.text.toString()
                val namaTrainer = edt_nama_trainer.text.toString()
                val ruangan = edt_ruang.text.toString()
                val durasi = edt_durasi.text.toString()

                val id = ref.push().key.toString()

                val storageRef = FirebaseStorage
                    .getInstance()
                    .getReference(value3)
                val databaseRef = FirebaseDatabase
                    .getInstance()
                    .getReference(value3)


                if (imgPath != null) {
                    storageRef.putFile(imgPath!!)
                        .addOnSuccessListener {
                            storageRef.downloadUrl.addOnSuccessListener {

                                val url = it!!.toString()

                                val isiMenu = ModelSchedule(
                                    id,
                                    namaTrainer,
                                    materi,
                                    ruangan,
                                    durasi,
                                    url,
                                    value3
                                )
                                databaseRef.child(id).setValue(isiMenu)

                                toast("Data hari "+value3+" berhasil di tambah, kak :)")

                                progressDialog.hide()
                                finish()
                            }
                        }
                        .addOnFailureListener {
                            println("Info File : ${it.message}")
                            progressDialog.hide()
                        }
                } else {

                    val url = "default"
                    val isiMenu = ModelSchedule(
                        id,
                        namaTrainer,
                        materi,
                        ruangan,
                        durasi,
                        url,
                        value3
                    )
                    databaseRef.child(id).setValue(isiMenu)

                    toast("Data hari "+value3+" berhasil di tambah, kak :)")

                    progressDialog.hide()
                    finish()


                }
            }
            btn_image -> {
                val iImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(iImg, 0)
                progressDialog.hide()

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_schedule_layout)

        var value3 : String = intent.getStringExtra("value2")

        btn_menu = findViewById<View>(R.id.btn_menu) as Button
        RxView.clicks(btn_menu)
            .subscribe {
                val intent = Intent(this, ViewSchedule::class.java)
                intent.putExtra("value",value3)
                startActivity(intent)

                Toast.makeText(this, "kenapa balik lagi kak ??", Toast.LENGTH_SHORT).show()
            }

        btn_submit.setOnClickListener(this)
        btn_image.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            imgPath = data?.data
        }
    }
}