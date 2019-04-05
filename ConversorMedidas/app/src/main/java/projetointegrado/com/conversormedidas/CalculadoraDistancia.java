package projetointegrado.com.conversormedidas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CalculadoraDistancia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.conversor_distancia); // Troca o título ao mudar para a activity de distancia

        // Criação da droplist
        setContentView(R.layout.activity_calculadora_distancia);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> MyAdapter = new ArrayAdapter<String>(CalculadoraDistancia.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.medidasDistancia));
        MyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(MyAdapter);
        // fim-droplist

    }
}
