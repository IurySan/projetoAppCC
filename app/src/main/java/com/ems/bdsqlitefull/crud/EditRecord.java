package com.ems.bdsqlitefull.crud;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.MainActivity;
import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Produto;
import com.ems.bdsqlitefull.utils.Message;

import java.util.List;

public class EditRecord extends AppCompatActivity {

    TextView id;
    EditText codBarra, produto, descricao, setor;
    Button btSalvar, btDeletar;

    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setTitle("LISTA DE PRODUTO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.id);
        codBarra = findViewById(R.id.codBarra);
        produto = findViewById(R.id.produto);
        descricao = findViewById(R.id.descricao);
        setor = findViewById(R.id.setor);
        btSalvar = findViewById(R.id.btSalvar);

        btDeletar = findViewById(R.id.btDeletar);

        final Intent itProduto = getIntent();
        final Produto prod = (Produto) itProduto.getExtras().getSerializable("objProduto");
        id.setText(String.valueOf(prod.getId()));
        codBarra.setText(prod.getCodBarra());
        produto.setText(prod.getProduto());
        descricao.setText(prod.getDescricao());
        setor.setText(prod.getSetor());


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put("CodBarra", codBarra.getText().toString());
                values.put("Produto", produto.getText().toString());
                values.put("Descricao", descricao.getText().toString());
                values.put("Setor", setor.getText().toString());

                Produto novosDados = new Produto();
                novosDados.setCodBarra(codBarra.getText().toString());
                novosDados.setProduto(produto.getText().toString());
                novosDados.setDescricao(descricao.getText().toString());
                novosDados.setSetor(setor.getText().toString());

                db = openOrCreateDatabase("db_produto", Context.MODE_PRIVATE, null);
                db.execSQL("UPDATE produto SET " +
                        "codBarra='" + novosDados.getCodBarra() + "'," +
                        "produto='" + novosDados.getProduto() + "'," +
                        "descricao='" + novosDados.getDescricao() + "'," +
                        "setor='" + novosDados.getSetor() + "' " +
                        "WHERE id=" + produto.getId()
                );

                Message message = new Message(EditRecord.this);
                message.show(
                        "Dados Atualizados com Sucesso!",
                        novosDados.getDados(),
                        R.drawable.ic_add);
                ;
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });

        btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Produto novosDados = new Produto();
                novosDados.setCodBarra(codBarra.getText().toString());
                novosDados.setProduto(produto.getText().toString());
                novosDados.setDescricao(descricao.getText().toString());
                novosDados.setSetor(setor.getText().toString());

                db = openOrCreateDatabase("db_produto", Context.MODE_PRIVATE, null);
                db.delete("produto", "id=?",new String[]{String.valueOf(id.getText())});


                Intent list = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(list);


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
}