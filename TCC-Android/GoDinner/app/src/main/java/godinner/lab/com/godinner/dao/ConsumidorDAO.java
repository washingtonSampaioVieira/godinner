package godinner.lab.com.godinner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ContextThemeWrapper;

import godinner.lab.com.godinner.model.Consumidor;
import godinner.lab.com.godinner.model.Endereco;

public class ConsumidorDAO extends SQLiteOpenHelper {

    public ConsumidorDAO(Context context) {
        super(context, "db_godinner2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_endereco(" +
                "id_endereco INTEGER PRIMARY KEY," +
                "cep TEXT NOT NULL," +
                "numero TEXT NOT NULL," +
                "logradouro TEXT NOT NULL," +
                "bairro TEXT NOT NULL," +
                "complemento TEXT NOT NULL," +
                "referencia TEXT NOT NULL," +
                "id_cidade INTEGER NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS tbl_consumidor(" +
                "id_consumidor INTEGER PRIMARY KEY," +
                "id_servidor INTEGER NOT NULL," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "cpf TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "foto_perfil NOT NULL,"+
                "id_endereco INTEGER REFERENCES tbl_endereco(id_endereco) ON UPDATE CASCADE)";
        db.execSQL(sql);

        ContentValues dados = new ContentValues();
        dados.put("id_endereco", 1);
        dados.put("cep", "");
        dados.put("numero", "");
        dados.put("logradouro", "");
        dados.put("bairro", "");
        dados.put("complemento", "");
        dados.put("referencia", "");
        dados.put("id_cidade", 0);
        db.insert("tbl_endereco", null, dados);

        dados = new ContentValues();
        dados.put("id_consumidor", 1);
        dados.put("id_servidor", 0);
        dados.put("nome", "");
        dados.put("email", "");
        dados.put("cpf", "");
        dados.put("telefone", "");
        dados.put("foto_perfil", "");
        dados.put("id_endereco", 1);
        db.insert("tbl_consumidor", null, dados);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbl_consumidor";
        db.execSQL(sql);
        onCreate(db);
    }

    public void salvarConsumidorLogado(Consumidor c){
        SQLiteDatabase dbWrite = getWritableDatabase();

        ContentValues dadosEndereco = new ContentValues();
        dadosEndereco.put("cep", c.getEndereco().getCep());
        dadosEndereco.put("numero", c.getEndereco().getNumero());
        dadosEndereco.put("logradouro", c.getEndereco().getLogradouro());
        dadosEndereco.put("bairro", c.getEndereco().getBairro());
        dadosEndereco.put("complemento", c.getEndereco().getComplemento());
        dadosEndereco.put("referencia", c.getEndereco().getReferencia());
        dadosEndereco.put("id_cidade", c.getEndereco().getIdCidade());

        String[] params1 = {"1"};
        dbWrite.update("tbl_endereco", dadosEndereco,  "id_endereco = ?", params1);

        ContentValues dadosConsumidor = new ContentValues();
        dadosConsumidor.put("id_servidor", c.getIdServidor());
        dadosConsumidor.put("nome", c.getNome());
        dadosConsumidor.put("email", c.getEmail());
        dadosConsumidor.put("cpf", c.getCpf());
        dadosConsumidor.put("telefone", c.getTelefone());
        dadosConsumidor.put("foto_perfil", c.getFotoPerfil());
        dadosConsumidor.put("id_endereco", 1);

        String[] params2 = {"1"};
        dbWrite.update("tbl_consumidor", dadosConsumidor,  "id_consumidor = ?", params2);
    }

    public Consumidor consultarConsumidor(){
        Consumidor c = new Consumidor();
        SQLiteDatabase dbRead = getReadableDatabase();
        String sql = "SELECT c.*, " +
                "e.numero, " +
                "e.logradouro, " +
                "e.bairro, " +
                "e.complemento, " +
                "e.referencia, " +
                "e.id_cidade " +
                "FROM tbl_consumidor AS c, tbl_endereco AS e WHERE c.id_consumidor = 1 AND e.id_endereco = 1";

        Cursor c1 = dbRead.rawQuery(sql, null);
        if(c1.moveToNext()){
            c.setIdServidor(c1.getInt(c1.getColumnIndex("id_servidor")));
            c.setIdConsumidor(c1.getInt(c1.getColumnIndex("id_consumidor")));
            c.setNome(c1.getString(c1.getColumnIndex("nome")));
            c.setEmail(c1.getString(c1.getColumnIndex("email")));
            c.setCpf(c1.getString(c1.getColumnIndex("cpf")));
            c.setTelefone(c1.getString(c1.getColumnIndex("telefone")));
            c.setFotoPerfil(c1.getString(c1.getColumnIndex("foto_perfil")));

            Endereco e = new Endereco();
            e.setIdEndereco(c1.getInt(c1.getColumnIndex("id_endereco")));
            e.setNumero(c1.getString(c1.getColumnIndex("numero")));
            e.setLogradouro(c1.getString(c1.getColumnIndex("logradouro")));
            e.setBairro(c1.getString(c1.getColumnIndex("bairro")));
            e.setComplemento(c1.getString(c1.getColumnIndex("complemento")));
            e.setReferencia(c1.getString(c1.getColumnIndex("referencia")));
            e.setIdCidade(c1.getInt(c1.getColumnIndex("id_cidade")));

            c.setEndereco(e);
        }
        c1.close();
        return c;
    }
}