package com.ems.bdsqlitefull.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Produto;
import com.ems.bdsqlitefull.utils.Message;

public class Insert extends AppCompatActivity {
    EditText codBarra, produto, descricao, setor;
    Button btInserir;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        db = openOrCreateDatabase("db_produto", Context.MODE_PRIVATE, null);


        db.execSQL("CREATE TABLE IF NOT EXISTS produto(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "codBarra VARCHAR NOT NULL, " +
                "produto VARCHAR NOT NULL, " +
                "descricao VARCHAR NOT NULL, " +
                "setor VARCHAR NOT NULL);");



        getSupportActionBar().setTitle("LISTA DE PRODUTO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        codBarra = findViewById(R.id.editCodBarra);
        produto = findViewById(R.id.editProduto);
        descricao = findViewById(R.id.editDescricao);
        setor = findViewById(R.id.editSetor);
        btInserir = findViewById(R.id.btInserir);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Produto prod = new Produto();
                prod.setCodBarra(codBarra.getText().toString());
                prod.setProduto(produto.getText().toString());
                prod.setDescricao(descricao.getText().toString());
                prod.setSetor(setor.getText().toString());


                ContentValues values = new ContentValues();
                values.put("codBarra", prod.getCodBarra());
                values.put("Produto", prod.getProduto());
                values.put("Descricao", prod.getDescricao());
                values.put("Setor", prod.getSetor());


                db.insert("produto", null, values);


                Message message = new Message(Insert.this);
                message.show(
                        "Dados incluídos com sucesso!",
                        prod.getDados(),
                        R.drawable.ic_add);

                clearText();
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void clearText() {
        codBarra.setText("");
        produto.setText("");
        descricao.setText("");
        setor.setText("");
        codBarra.requestFocus();


        ((InputMethodManager) Insert.this.getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), 0);
    }
}
