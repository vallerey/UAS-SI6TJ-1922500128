package ac.id.atmaluhur.mhs.uassi6tj1922500128;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private LecturerJsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_lecturer_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3/FAB/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderAPI = retrofit.create(LecturerJsonPlaceHolderAPI.class);
        getPosts();
    }

    public void btn_tambah(View View){
        Intent i = new Intent(MainActivity.this, Electurer.class);
        startActivity(i);
    }

    private void getPosts() {
        Map<String, String> parameters = new HashMap<>();
        Call<List<LecturerPost>> call = jsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<LecturerPost>>() {
            @Override
            public void onResponse(Call<List<LecturerPost>> call, Response<List<LecturerPost>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<LecturerPost> posts = response.body();
                for (LecturerPost post : posts) {
                    String content = "";
                    content += "NIDN: " + post.getNidn() + "\n";
                    content += "Nama Dosen: " + post.getNama_dosen() + "\n";
                    content += "Jabatan: " + post.getJabatan() + "\n";
                    content += "Golongan Pangkat: " + post.getGol_pang() + "\n";
                    content += "Bidang Keahlian: " + post.getKeahlian() + "\n";
                    content += "Program Studi: " + post.getProgram_studi() + "\n\n";
                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<LecturerPost>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}