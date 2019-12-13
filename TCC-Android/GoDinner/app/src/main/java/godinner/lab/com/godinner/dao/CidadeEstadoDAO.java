package godinner.lab.com.godinner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import godinner.lab.com.godinner.model.Cidade;
import godinner.lab.com.godinner.model.Estado;

public class CidadeEstadoDAO extends SQLiteOpenHelper {

    public CidadeEstadoDAO(Context context) {
        super(context, "db_godinner", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_estado(" +
                "id_estado INTEGER PRIMARY KEY," +
                "estado TEXT NOT NULL," +
                "uf TEXT NOT NULL)";

        db.execSQL(sql);

        String sql2 = "CREATE TABLE IF NOT EXISTS tbl_cidade(" +
                "id_cidade INTEGER PRIMARY KEY," +
                "id_estado INTEGER REFERENCES tbl_estado(id_estado) ON UPDATE CASCADE," +
                "cidade TEXT NOT NULL)";

        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean EstadoAlreadyPopuled() {
        SQLiteDatabase dbRead = getReadableDatabase();

        // Verificando se ja há estados na tabela de estados
        String sql = "SELECT COUNT(*) FROM tbl_estado";
        Cursor mCursor = dbRead.rawQuery(sql, null);
        mCursor.moveToFirst();
        int iCount =  mCursor.getInt(0);

        if(iCount != 0){
            return true;
        }
        return false;
    }

    public boolean CidadeAlreadyPopuled() {
        SQLiteDatabase dbRead = getReadableDatabase();

        // Verificando se ja há estados na tabela de estados
        String sql = "SELECT COUNT(*) FROM tbl_cidade";
        Cursor mCursor = dbRead.rawQuery(sql, null);
        mCursor.moveToFirst();
        int iCount =  mCursor.getInt(0);

        if(iCount != 0){
            return true;
        }
        return false;
    }

    public void addEstados(ArrayList<Estado> estados){
        SQLiteDatabase dbWrite = getWritableDatabase();

        for(Estado e : estados) {
            ContentValues dados = getEstado(e);
            dbWrite.insert("tbl_estado", null, dados);
        }
    }

    public void addCidades(ArrayList<Cidade> cidades){
        SQLiteDatabase dbWrite = getWritableDatabase();

        for(Cidade c : cidades) {
            ContentValues dados = getCidade(c);
            dbWrite.insert("tbl_cidade", null, dados);
        }
    }

    private ContentValues getEstado(Estado estado) {
        ContentValues dados = new ContentValues();
        dados.put("id_estado", estado.getIdEstado());
        dados.put("estado", estado.getEstado());
        dados.put("uf", estado.getUf());

        return dados;
    }

    private ContentValues getCidade(Cidade cidade) {
        ContentValues dados = new ContentValues();
        dados.put("id_cidade", cidade.getIdCidade());
        dados.put("id_estado", cidade.getIdEstado());
        dados.put("cidade", cidade.getCidade());

        return dados;
    }

    public List<Estado> getEstados(){
        SQLiteDatabase dbRead = getReadableDatabase();

        String sql = "SELECT * FROM tbl_estado";
        Cursor c = dbRead.rawQuery(sql, null);
        ArrayList<Estado> estados = new ArrayList<>();

        while(c.moveToNext()){
            Estado e = new Estado();
            e.setIdEstado(c.getInt(c.getColumnIndex("id_estado")));
            e.setEstado(c.getString(c.getColumnIndex("estado")));
            e.setUf(c.getString(c.getColumnIndex("uf")));

            estados.add(e);
        }

        return estados;
    }

    public List<Cidade> getCidadesByEstado(int codEstado){
        SQLiteDatabase dbRead = getReadableDatabase();

        String sql = "SELECT * FROM tbl_cidade WHERE id_estado = "+codEstado;
        Cursor c = dbRead.rawQuery(sql, null);
        ArrayList<Cidade> cidades = new ArrayList<>();

        while(c.moveToNext()){
            Cidade city = new Cidade();
            city.setIdCidade(c.getInt(c.getColumnIndex("id_cidade")));
            city.setIdEstado(c.getInt(c.getColumnIndex("id_estado")));
            city.setCidade(c.getString(c.getColumnIndex("cidade")));

            cidades.add(city);
        }

        return cidades;
    }
}
