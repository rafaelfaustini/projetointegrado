package projetointegrado.com.conversormedidas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CalculadoraTemperatura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_temperatura);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.conversor_temperatura); // Troca o título ao mudar para a activity de temperatura
    }
}
