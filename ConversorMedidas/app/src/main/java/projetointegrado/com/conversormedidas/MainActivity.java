package projetointegrado.com.conversormedidas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private CardView cardDistancia;
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
    }
    public void abrirCalculadoraDistancia(){
        Intent intent = new Intent(this, CalculadoraDistancia.class);
        startActivity(intent);
    }
}
