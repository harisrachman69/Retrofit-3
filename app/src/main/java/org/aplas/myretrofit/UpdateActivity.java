package org.aplas.myretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {
    private TextView ptnim , ptnama , ptalamat , ptjk , ptnotelp;
    private EditText upnim , upnama , upalamat , upjk , upnotelp;
    private Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ptnim = (TextView) findViewById(R.id.pt_idsiswa);
        ptnama = (TextView) findViewById(R.id.pt_namasiswa);
        ptalamat = (TextView) findViewById(R.id.pt_alamatsiswa);
        ptjk = (TextView) findViewById(R.id.pt_jeniskelaminsiswa);
        ptnotelp = (TextView) findViewById(R.id.pt_no_telpsiswa);

        upnim = (EditText) findViewById(R.id.up_idsiswa);
        upnama = (EditText) findViewById(R.id.up_namasiswa);
        upalamat = (EditText) findViewById(R.id.up_alamatsiswa);
        upjk = (EditText) findViewById(R.id.up_jeniskelaminsiswa);
        upnotelp = (EditText) findViewById(R.id.up_no_telpsiswa);

        btnupdate = (Button) findViewById(R.id.terserah);

        Bundle bundle = getIntent().getExtras();
        upnim.setText(bundle.getString("Nim"));
        upnama.setText(bundle.getString("Nama"));
        upalamat.setText(bundle.getString("Alamat"));
        upjk.setText(bundle.getString("Jenis Kelamin"));
        upnotelp.setText(bundle.getString("No Telp"));


            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateData();
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                public void updateData(){
                    String id_siswa = upnim.getText().toString();
                    String nama = upnama.getText().toString();
                    String alamat = upalamat.getText().toString();
                    String jenis_kelamin = upjk.getText().toString();
                    String no_telp = upnotelp.getText().toString();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.100.4/rest-api/wpu-rest-server/CodeIgniter-3.1.10/Api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Post post = new Post(id_siswa, nama , alamat , jenis_kelamin , no_telp);

                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                    Call<Post> call = jsonPlaceHolderApi.updatePost(post);

                    call.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(UpdateActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                                startActivity(i);
                            }

                            }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Toast.makeText(UpdateActivity.this,"Gagal UPDATE BOSS", Toast.LENGTH_SHORT).show();

                        }
                    });


                }



        });


    }







}
