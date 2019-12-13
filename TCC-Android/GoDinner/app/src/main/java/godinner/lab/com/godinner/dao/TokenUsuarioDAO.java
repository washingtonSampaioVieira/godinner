package godinner.lab.com.godinner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TokenUsuarioDAO extends SQLiteOpenHelper {

    public TokenUsuarioDAO(Context context) {
        super(context, "db_godinner1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_token(" +
                "id_token INTEGER PRIMARY KEY," +
                "token TEXT NOT NULL)";
        db.execSQL(sql);

        ContentValues dados = new ContentValues();
        dados.put("id_token", 1);
        dados.put("token", "");
        db.insert("tbl_token", null, dados);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void salvarToken(String token) {
        SQLiteDatabase dbWrite = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("token", token);

        dbWrite.update("tbl_token", dados, "id_token = 1", null);
    }

    public String consultarToken() {
        SQLiteDatabase dbRead = getReadableDatabase();

        String token = "";
        String sql = "SELECT token FROM tbl_token WHERE id_token = 1";
        Cursor c = dbRead.rawQuery(sql, null);

        if (c.moveToNext()) {
            token = c.getString(c.getColumnIndex("token"));
        }

        return token;
    }

    public void limparToken() {
        SQLiteDatabase dbWrite = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("token", "");

        dbWrite.update("tbl_token", dados, "id_token = 1", null);
    }
}
