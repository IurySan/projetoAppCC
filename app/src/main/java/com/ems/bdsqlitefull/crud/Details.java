package com.ems.bdsqlitefull.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Produto;

public class Details extends AppCompatActivity {
    Button btEditar;
    TextView id, codBarra, produto, descricao, setor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setTitle("LISTA DE PRODUTO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.id);
        codBarra = findViewById(R.id.codBarra);
        produto = findViewById(R.id.produto);
        descricao = findViewById(R.id.descricao);
        setor = findViewById(R.id.setor);
        btEditar = findViewById(R.id.btSalvar);

        Intent itProduto = getIntent();
        final Produto prod = (Produto) itProduto.getExtras().getSerializable("objProduto");
        id.setText(String.valueOf(prod.getId()));
        codBarra.setText(prod.getCodBarra());
        produto.setText(prod.getProduto());
        descricao.setText(prod.getDescricao());
        setor.setText(prod.getSetor());

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editar = new Intent(getApplicationContext(), EditRecord.class);
                editar.putExtra("objProduto", prod);
                startActivity(editar);
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