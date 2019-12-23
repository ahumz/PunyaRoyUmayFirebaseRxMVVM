package ch.berco.loginmvvmfirebase.Menu.Admin.Schedule

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ch.berco.loginmvvmfirebase.Menu.ModelSchedule
import ch.berco.loginmvvmfirebase.R
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase

class AdapterSchedule (val mCtx: Context, val layoutResId: Int, val list: List<ModelSchedule>)
    : ArrayAdapter<ModelSchedule>(mCtx,layoutResId,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View =layoutInflater.inflate(layoutResId, null)

        val textMateri = view.findViewById<TextView>(R.id.txt_materi)
        val textNamaTrainer = view.findViewById<TextView>(R.id.txt_nama_trainer)
        val textRuang = view.findViewById<TextView>(R.id.txt_ruang)
        val textDurasi = view.findViewById<TextView>(R.id.txt_durasi)
        val btnDelete = view.findViewById<Button>(R.id.btn_delete)
        val viewGambar = view.findViewById<ImageView>(R.id.img_data)

        val menuus = list[position]

        textMateri.text = "Materi  = " + menuus.materi
        textNamaTrainer.text = "Trainer = " +menuus.namaTrainer
        textRuang.text = "Ruang   = "+ menuus.ruangan
        textDurasi.text = "Durasi  = "+menuus.durasi

        if (menuus.fotoTrainer.equals("default")){
            Glide.with(mCtx).load(R.drawable.null1).into(viewGambar)
        }else {
            Glide.with(mCtx).load(menuus.fotoTrainer).into(viewGambar)
        }

        btnDelete.setOnClickListener {
            deleteInfo(menuus)

        }

        return view
    }

    private fun deleteInfo(menuus: ModelSchedule) {
        val progressDialog = ProgressDialog(context,R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Menghapus...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference(menuus.hari)
        mydatabase.child(menuus.id).removeValue()
        Toast.makeText(mCtx, "Data hari "+menuus.hari+" berhasil di hapus, kak :)", Toast.LENGTH_SHORT).show()
        progressDialog.dismiss()

        val habisdelete = Intent(context, ViewSchedule::class.java)
            habisdelete.putExtra("value",menuus.hari)
            context.startActivity(habisdelete)

    }
}