package ch.berco.loginmvvmfirebase.Menu.User.Schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import ch.berco.loginmvvmfirebase.Menu.ModelSchedule
import ch.berco.loginmvvmfirebase.R
import com.bumptech.glide.Glide

class AdapterUser (val mCtx: Context, val layoutResId: Int, val list: List<ModelSchedule>)
    : ArrayAdapter<ModelSchedule>(mCtx,layoutResId,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View =layoutInflater.inflate(layoutResId, null)

        val textMateri = view.findViewById<TextView>(R.id.txt_materi)
        val textNamaTrainer = view.findViewById<TextView>(R.id.txt_nama_trainer)
        val textRuang = view.findViewById<TextView>(R.id.txt_ruang)
        val textDurasi = view.findViewById<TextView>(R.id.txt_durasi)
        val viewGambar = view.findViewById<ImageView>(R.id.img_data)

        val menuus = list[position]

        textMateri.text = "Materi  = " + menuus.materi
        textNamaTrainer.text = "Trainer = " +menuus.namaTrainer
        textRuang.text = "Ruang   = " + menuus.ruangan
        textDurasi.text = menuus.durasi

        if (menuus.fotoTrainer.equals("default")){
            Glide.with(mCtx).load(R.drawable.null1).into(viewGambar)
        }else {
            Glide.with(mCtx).load(menuus.fotoTrainer).into(viewGambar)
        }
        return view
    }
}