package projetointegrado.com.conversormedidas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CalculadoraVelocidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_velocidade);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.conversor_velocidade); // Troca o título ao mudar para a activity de distancia
    }
}
