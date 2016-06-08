package ao.studio.me.meuscontactos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by manuelernesto on 26/05/16.
 */
public class ContactoDAO extends SQLiteOpenHelper {


    public ContactoDAO(Context context) {
        super(context, "MeusContactos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE contacto(" +
                "id INTEGER PRIMARY KEY," +
                " nome TEXT NOT NULL, " +
                "telefone TEXT NOT NULL, " +
                "email TEXT NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvar(Contacto contacto) {
        ContentValues dados = contacto.conteudo();

        SQLiteDatabase database = getWritableDatabase();
        database.insert("contacto", null, dados);
    }

    public List<Contacto> listarTodos() {
        List<Contacto> contactos = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM contacto", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));

            Contacto contacto = new Contacto(id, nome, telefone, email);

            contactos.add(contacto);
        }

        return contactos;
    }

    /*Metodo para actualizar os contactos na base de dados*/
    public void actualizar(Contacto contacto, int id) {
        ContentValues dados = contacto.conteudo();
        String[] parametro = {id + ""};
        SQLiteDatabase database = getWritableDatabase();
        database.update("contacto", dados, "id = ?", parametro);
    }

    public void apagar(Contacto contacto) {
        String[] parametro = {contacto.getId() + ""};
        SQLiteDatabase database = getWritableDatabase();
        database.delete("contacto", "id = ?", parametro);
    }

}
