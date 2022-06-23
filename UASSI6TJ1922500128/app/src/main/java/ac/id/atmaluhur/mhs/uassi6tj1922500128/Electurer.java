package ac.id.atmaluhur.mhs.uassi6tj1922500128;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class Electurer extends AppCompatActivity {
    private EditText txtNidn, txtNama_dosen, txtJabtan, txtGol_pang, txtKeahlian, txtProgram_studi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electurer);

        txtNidn = (EditText) findViewById(R.id.et_Nidn);
        txtNama_dosen = (EditText) findViewById(R.id.et_Nama_dosen);
        txtJabtan = (EditText) findViewById(R.id.et_Jabatan);
        txtGol_pang = (EditText) findViewById(R.id.et_Gol_pang);
        txtKeahlian = (EditText) findViewById(R.id.et_Keahlian);
        txtProgram_studi = (EditText) findViewById(R.id.et_Program_studi);
    }

    public void kembalidashbord(View View){
        Intent i = new Intent(Electurer.this, MainActivity.class);
        startActivity(i);
    }

    public void simpandata(View View) {
        final String Nidn=txtNidn.getText().toString().trim();
        final String Nama_dosen=txtNama_dosen.getText().toString().trim();
        final String Jabatan=txtJabtan.getText().toString().trim();
        final String Gol_pang=txtGol_pang.getText().toString().trim();
        final String Keahlian=txtKeahlian.getText().toString().trim();
        final String Program_studi= txtProgram_studi.getText().toString().trim();

        class simpandata extends AsyncTask<Void, Void,String> {
            ProgressDialog load;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                load = ProgressDialog.show(
                        Electurer.this, "Add ...", "Wait..",
                        false, false );
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("nidn", Nidn);
                params.put("nama_dosen", Nama_dosen);
                params.put("jabatan", Jabatan);
                params.put("gol_pang", Gol_pang);
                params.put("keahlian", Keahlian);
                params.put("program_studi", Program_studi);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest( "http://172.20.10.3/FAB/tambah_lecturer.php", params);
                return res;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                load.dismiss();
                Toast.makeText(Electurer.this, s, Toast.LENGTH_LONG).show();
            }
        }

        simpandata tb = new simpandata();
        tb.execute();
    }
}