package ao.studio.me.meuscontactos;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by manuelernesto on 26/05/16.
 */
public class Contacto implements Serializable{
    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Contacto(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Contacto(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContentValues conteudo(){
      ContentValues dados = new ContentValues();
        dados.put("nome",nome);
        dados.put("telefone",telefone);
        dados.put("email",email);
        return dados;
    }

    @Override
    public String toString() {
        return id + "-" + nome;
    }
}
