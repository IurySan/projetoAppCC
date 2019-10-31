package com.ems.bdsqlitefull.crud;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Produto;

import java.util.ArrayList;

public class ListAll extends AppCompatActivity {
    ListView listViewProduto;
    ArrayList<Produto> produto = new ArrayList<>();
    ArrayAdapter<Produto> adaptador;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setTitle("LISTA DE PRODUTO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        db = openOrCreateDatabase("db_produto", Context.MODE_PRIVATE, null);

        listViewProduto = findViewById(R.id.listagem);

        produto.clear();
        Cursor c = db.rawQuery("SELECT * FROM produto ORDER BY produto ASC", null);
        while (c.moveToNext()) {
            produto.add(new Produto(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4)));
        }
        adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                produto);


        listViewProduto.setAdapter(adaptador);

        listViewProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = (Produto) listViewProduto.getItemAtPosition(position);
                Intent itProduto = new Intent(getApplicationContext(), Details.class);
                itProduto.putExtra("objProduto", produto);
                startActivity(itProduto);
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