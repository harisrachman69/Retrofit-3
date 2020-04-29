package org.aplas.myretrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MahasiswiAdapter extends RecyclerView.Adapter<MahasiswiAdapter.ViewHolder> {
    private List<Post> dataSiswa;
    private Context context;

    public MahasiswiAdapter(List<Post> dataSiswa , Context context) {
        this.dataSiswa = dataSiswa;
        this.context = context;
    }


    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int ViewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.bocil,parent,false);
        return new ViewHolder(view);
    }
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Post ssw = dataSiswa.get(position);

       TextView textView = holder.txtidsiswa;
       TextView textView1 = holder.txtnama;
       TextView textView2 = holder.txtalamat;
       TextView textView3 = holder.txtjeniskelamin;
       TextView textView4 = holder.txtnotelp;

       textView.setText(ssw.getId_siswa());
       textView1.setText(ssw.getNama());
       textView2.setText(ssw.getAlamat());
       textView3.setText(ssw.getJenis_kelamin());
       textView4.setText(ssw.getNo_telp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mengirim data berupa nim yaitu idsiswa, nama, alamat, jenis kelamin, dan no telp
                Bundle bundle = new Bundle();
                bundle.putString("Nim", ssw.getId_siswa());
                bundle.putString("Nama", ssw.getNama());
                bundle.putString("Alamat", ssw.getAlamat());
                bundle.putString("Jenis Kelamin", ssw.getJenis_kelamin());
                bundle.putString("No Telp", ssw.getNo_telp());
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    public int getItemCount() { return dataSiswa.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtidsiswa, txtnama , txtalamat , txtjeniskelamin , txtnotelp;
        public ViewHolder(@NonNull View ItemView) {
            super(ItemView);
            txtidsiswa = (TextView) ItemView.findViewById(R.id.id_siswa);
            txtnama = (TextView) ItemView.findViewById(R.id.nama_siswa);
            txtalamat = (TextView) ItemView.findViewById(R.id.alamat_siswa);
            txtjeniskelamin = (TextView) ItemView.findViewById(R.id.jeniskelamin_siswa);
            txtnotelp = (TextView) ItemView.findViewById(R.id.notelp_siswa);
        }
    }
}
