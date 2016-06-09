package ao.studio.me.meuscontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
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
        registerForContextMenu(listView);

    }

    public void cadastro(View view){
        Intent i = new Intent(ListaActivity.this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.equals(listView)){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Contacto contacto = (Contacto) listView.getItemAtPosition(info.position);
            criarMenu(menu, contacto);

        }
    }

    public void criarMenu(ContextMenu menu, final Contacto contacto){
        MenuItem menuItem = menu.add("Apagar");
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ContactoDAO dao = new ContactoDAO(ListaActivity.this);
                dao.apagar(contacto);
                Intent i = new Intent(ListaActivity.this,ListaActivity.class);
                startActivity(i);
                return true;
            }
        });
    }
}
