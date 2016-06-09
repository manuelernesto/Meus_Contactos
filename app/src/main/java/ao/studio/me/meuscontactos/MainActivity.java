package ao.studio.me.meuscontactos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


        if (existeContcto()) {
            Contacto contacto = contactoParaActualizar();
            nomeEd.setText(contacto.getNome());
            telefoneEd.setText(contacto.getTelefone());
            emailEd.setText(contacto.getEmail());
            btnSalvar.setText("Actualizar");
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactoDAO dao = new ContactoDAO(MainActivity.this);
                Contacto contacto = new Contacto(nomeEd.getText().toString(),
                        telefoneEd.getText().toString(),
                        emailEd.getText().toString());
                if (existeContcto()) {
                    dao.actualizar(contacto, contactoParaActualizar().getId());
                    Toast.makeText(MainActivity.this, "Contacto Actualizado com sucesso!", Toast.LENGTH_SHORT).show();

                } else {
                    dao.salvar(contacto);
                    Toast.makeText(MainActivity.this, "Contacto Salvo com sucesso!", Toast.LENGTH_SHORT).show();

                }
                nomeEd.setText("");
                telefoneEd.setText("");
                emailEd.setText("");

            }
        });
    }

    public void listar(View view) {
        Intent i = new Intent(MainActivity.this, ListaActivity.class);
        startActivity(i);
    }

    public boolean existeContcto() {
        return getIntent().hasExtra("c");
    }

    public Contacto contactoParaActualizar() {
        Intent intent = getIntent();
        Contacto contacto = (Contacto) intent.getSerializableExtra("c");
        return contacto;
    }


}
