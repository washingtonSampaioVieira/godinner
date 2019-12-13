package godinner.lab.com.godinner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import godinner.lab.com.godinner.model.ProdutoPedido;
import godinner.lab.com.godinner.model.SacolaPedido;

public class PedidoDAO extends SQLiteOpenHelper {

    public PedidoDAO(Context context) {
        super(context, "db_godinner3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE IF NOT EXISTS tbl_produto(" +
                "id_produto INTEGER PRIMARY KEY," +
                "id_produto2 INTEGER, " +
                "nome TEXT NOT NULL," +
                "quantidade INTEGER NOT NULL," +
                "preco DOUBLE NOT NULL)";

        String sql2 = "CREATE TABLE IF NOT EXISTS tbl_sacola(" +
                "id_sacola INTEGER PRIMARY KEY," +
                "id_restaurante INTEGER NOT NULL," +
                "nome_restaurante TEXT NOT NULL," +
                "tempo_entrega TEXT NOT NULL," +
                "valor_entrega DOUBLE NOT NULL," +
                "valor_total_pedido DOUBLE NOT NULL)";

        String sql3 = "CREATE TABLE IF NOT EXISTS tbl_produto_sacola(" +
                "id_produto_sacola INTEGER PRIMARY KEY," +
                "id_produto INTEGER  REFERENCES tbl_produto(id_produto)ON UPDATE CASCADE," +
                "id_sacola INTEGER  REFERENCES tbl_sacola(id_sacola) ON UPDATE CASCADE)";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);

        ContentValues mDadosSacola = new ContentValues();
        mDadosSacola.put("id_sacola", 1);
        mDadosSacola.put("id_restaurante", 0);
        mDadosSacola.put("nome_restaurante", "");
        mDadosSacola.put("tempo_entrega", "");
        mDadosSacola.put("valor_entrega", 0.0);
        mDadosSacola.put("valor_total_pedido", 0.0);
        db.insert("tbl_sacola", null, mDadosSacola);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ProdutoPedido consultarProdutoById(int idProduto) {
        SQLiteDatabase dbRead = getReadableDatabase();

        ProdutoPedido p = new ProdutoPedido();
        String sql = "SELECT * FROM tbl_produto WHERE id_produto2 = ?";
        Cursor c = dbRead.rawQuery(sql, new String[]{String.valueOf(idProduto)});

        if (c.moveToNext())
            p.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
        else
            p.setQuantidade(0);

        c.close();
        return p;
    }

    public void salvarProduto(ProdutoPedido p, String acao) {
        SQLiteDatabase dbWrite = getWritableDatabase();

        ContentValues dadosProduto = new ContentValues();

        switch (acao) {
            case "novo":
                dadosProduto.put("id_produto2", p.getId());
                dadosProduto.put("nome", p.getNome());
                dadosProduto.put("preco", p.getPreco());
                dadosProduto.put("quantidade", p.getQuantidade());
                dbWrite.insert("tbl_produto", null, dadosProduto);

                ProdutoPedido pedido = new ProdutoPedido();
                pedido.setId(p.getId());

                salvarProdutoPedido(pedido);
                break;
            case "editar":
                String[] args = {p.getId().toString()};

                dadosProduto.put("quantidade", p.getQuantidade());
                dbWrite.update("tbl_produto", dadosProduto, "id_produto2 = ?", args);
                break;
            case "excluir":
                String[] args2 = {p.getId().toString()};

                dbWrite.delete("tbl_produto", "id_produto2 = ?", args2);
                break;
        }
    }

    private void salvarProdutoPedido(ProdutoPedido p) {
        SQLiteDatabase dbWrite = getWritableDatabase();

        ContentValues dadosProdutoSacola = new ContentValues();
        dadosProdutoSacola.put("id_produto", p.getId());
        dadosProdutoSacola.put("id_sacola", 1);

        dbWrite.insert("tbl_produto_sacola", null, dadosProdutoSacola);
    }

    public boolean sacolaIsNull() {
        SQLiteDatabase dbRead = getReadableDatabase();

        String sql = "SELECT nome_restaurante FROM tbl_sacola WHERE id_sacola = 1";
        Cursor c = dbRead.rawQuery(sql, null);

        if (c.moveToNext()) {
            String nomeRestaurante = c.getString(c.getColumnIndex("nome_restaurante"));
            c.close();

            return nomeRestaurante.equals("");
        }
        return false;
    }

    public void atualizarSacola(SacolaPedido s) {
        SQLiteDatabase dbWrite = getWritableDatabase();

        ContentValues dadosSacola = new ContentValues();
        dadosSacola.put("id_restaurante", s.getIdRestaurante());
        dadosSacola.put("nome_restaurante", s.getNomeRestaurante());
        dadosSacola.put("tempo_entrega", s.getTempoEntrega());
        dadosSacola.put("valor_entrega", s.getValorEntrega());
        dadosSacola.put("valor_total_pedido", s.getValorTotalPedido());

        dbWrite.update("tbl_sacola", dadosSacola, "id_sacola = 1", null);
    }

    public SacolaPedido consultarSacola() {
        SQLiteDatabase dbRead = getReadableDatabase();

        SacolaPedido s = new SacolaPedido();

        String sql = "SELECT * FROM tbl_sacola WHERE id_sacola = 1";
        Cursor c = dbRead.rawQuery(sql, null);

        if (c.moveToNext()) {
            s.setIdSacola(c.getInt(c.getColumnIndex("id_sacola")));
            s.setIdRestaurante(c.getInt(c.getColumnIndex("id_restaurante")));
            s.setNomeRestaurante(c.getString(c.getColumnIndex("nome_restaurante")));
            s.setTempoEntrega(c.getString(c.getColumnIndex("tempo_entrega")));
            s.setValorEntrega(c.getDouble(c.getColumnIndex("valor_entrega")));
            s.setValorTotalPedido(c.getDouble(c.getColumnIndex("valor_total_pedido")));
            c.close();
        }

        return s;
    }

    public List<ProdutoPedido> consultarProdutos() {
        SQLiteDatabase dbRead = getReadableDatabase();

        List<ProdutoPedido> listProdutos = new ArrayList<>();
        String sql = "SELECT * FROM tbl_produto";

        Cursor c = dbRead.rawQuery(sql, null);

        while (c.moveToNext()) {
            ProdutoPedido p = new ProdutoPedido();
            p.setId(c.getInt(c.getColumnIndex("id_produto2")));
            p.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
            listProdutos.add(p);
        }
        c.close();

        return listProdutos;
    }

    public List<ProdutoPedido> getProdutos() {
        SQLiteDatabase dbRead = getReadableDatabase();
        List<ProdutoPedido> listProdutos = new ArrayList<>();

        String sql = "SELECT * FROM tbl_produto";

        Cursor c = dbRead.rawQuery(sql, null);
        while (c.moveToNext()) {
            ProdutoPedido p = new ProdutoPedido();
            p.setId(c.getInt(c.getColumnIndex("id_produto")));
            p.setNome(c.getString(c.getColumnIndex("nome")));
            p.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
            p.setPreco(c.getDouble(c.getColumnIndex("preco")));
            listProdutos.add(p);
        }
        c.close();

        return listProdutos;
    }

    public void excluirProduto(int id) {
        SQLiteDatabase dbWrite = getWritableDatabase();
        SQLiteDatabase dbRead = getReadableDatabase();

        String sql = "SELECT COUNT(*) AS qtde FROM tbl_produto";
        Cursor c = dbRead.rawQuery(sql, null);

        if (c.moveToNext()) {
            int qtde = c.getInt(c.getColumnIndex("qtde"));

            if (qtde == 1) {
                esvaziarSacola();
            } else {
                dbWrite.delete("tbl_produto", "id_produto = ?", new String[]{String.valueOf(id)});
            }
        }
    }

    public void esvaziarSacola() {
        SQLiteDatabase dbWrite = getWritableDatabase();

        dbWrite.delete("tbl_produto_sacola", null, null);
        dbWrite.delete("tbl_produto", null, null);

        ContentValues mDadosSacola = new ContentValues();
        mDadosSacola.put("id_restaurante", 0);
        mDadosSacola.put("nome_restaurante", "");
        mDadosSacola.put("tempo_entrega", "");
        mDadosSacola.put("valor_entrega", 0.0);
        mDadosSacola.put("valor_total_pedido", 0.0);

        dbWrite.update("tbl_sacola", mDadosSacola, "id_sacola = 1", null);
    }
}