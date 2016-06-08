package ao.studio.me.meuscontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btnSalvar;
    private EditText nomeEd, telefoneEd, emailEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSalvar = (Button) findViewById(R.id.gravar);
        nomeEd = (EditText) findViewById(R.id.nome);
        telefoneEd = (EditText) findViewById(R.id.telefone);
        emailEd = (EditText) findViewById(R.id.email);



        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactoDAO dao = new ContactoDAO(MainActivity.this);
                Contacto contacto = new Contacto(nomeEd.getText().toString(),
                        telefoneEd.getText().toString(),
                        emailEd.getText().toString());

                dao.salvar(contacto);
                Toast.makeText(MainActivity.this, "Contacto Salvo com sucesso!",Toast.LENGTH_SHORT).show();
                nomeEd.setText("");
                telefoneEd.setText("");
                emailEd.setText("");

            }
        });
    }

    public void listar(View view){
        Intent i = new Intent(MainActivity.this, ListaActivity.class);
        startActivity(i);
    }




}
