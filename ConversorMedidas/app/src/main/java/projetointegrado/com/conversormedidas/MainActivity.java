package projetointegrado.com.conversormedidas;

import android.content.Intent;
import static maes.tech.intentanim.CustomIntent.customType;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private CardView cardDistancia;
    private CardView cardVelocidade;
    private CardView cardTemperatura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cardDistancia = (CardView) findViewById(R.id.cardDistancia);
        cardDistancia.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                abrirCalculadoraDistancia();
            }
        });
        cardVelocidade = (CardView) findViewById(R.id.cardVelocidade);
        cardVelocidade.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                abrirCalculadoraVelocidade();
            }
        });

        cardTemperatura = (CardView) findViewById(R.id.cardTemperatura);
        cardTemperatura.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                abrirCalculadoraTemperatura();
            }
        });


    }
    public void abrirCalculadoraDistancia(){
        Intent intent = new Intent(this, CalculadoraDistancia.class);
        startActivity(intent);
        customType(MainActivity.this,"fadein-to-fadeout");
    }
    public void abrirCalculadoraVelocidade(){
        Intent intent = new Intent(this, CalculadoraVelocidade.class);
        startActivity(intent);
        customType(MainActivity.this,"fadein-to-fadeout");
    }
    public void abrirCalculadoraTemperatura(){
        Intent intent = new Intent(this, CalculadoraTemperatura.class);
        startActivity(intent);
        customType(MainActivity.this,"fadein-to-fadeout");
    }
}
