package ao.studio.me.meuscontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listView= (ListView) findViewById(R.id.lista);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacto contacto = (Contacto) parent.getItemAtPosition(position);
                Intent i = new Intent(ListaActivity.this, MainActivity.class);
                i.putExtra("c",contacto);
                    startActivity(i);
            }
        });


        ContactoDAO dao = new ContactoDAO(this);
        List<Contacto> contactos = dao.listarTodos();
        ArrayAdapter<Contacto>  adapter =
                new ArrayAdapter<Contacto>
                        (this,
                                android.R.layout.simple_list_item_1,
                                contactos);

        listView.setAdapter(adapter);

    }

    public void cadastro(View view){
        Intent i = new Intent(ListaActivity.this,MainActivity.class);
        startActivity(i);
    }
}
