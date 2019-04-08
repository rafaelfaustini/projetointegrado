package projetointegrado.com.conversormedidas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculadoraDistancia extends AppCompatActivity {
    private Spinner unidade;
    private EditText valor;
    private ConversorDistancia conversor;
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.conversor_distancia); // Troca o título ao mudar para a activity de distancia

        // Criação da droplist
        setContentView(R.layout.activity_calculadora_distancia);
        this.unidade = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> MyAdapter = new ArrayAdapter<String>(CalculadoraDistancia.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.medidasDistancia));
        MyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unidade.setAdapter(MyAdapter);
        // fim-droplist

        this.conversor = new ConversorDistancia();


        this.valor = (EditText) findViewById(R.id.editText2);
        lista = findViewById(R.id.lista);
        this.valor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null || !s.toString().isEmpty()) {
                    ArrayList<String> StringArray = CalculadoraDistancia.this.conversor.converter(CalculadoraDistancia.this.unidade.getSelectedItemPosition(), s.toString());
                    String[] b = getResources().getStringArray(R.array.medidasDistancia);
                    ArrayList<String> c = new ArrayList<>(Arrays.asList(b));

                    int length = StringArray.size();
                    if (length != c.size()) { // Too many names, or too many numbers
                        // Fail
                    }
                    ArrayList<String> array3 = new ArrayList<String>(length); // Make a new list
                    for (int i = 0; i < length; i++) { // Loop through every name/phone number combo
                        array3.add(StringArray.get(i) + " " + c.get(i)); // Concat the two, and add it
                    }

                    MedidasAdapter adapter = new MedidasAdapter(CalculadoraDistancia.this,StringArray, c);
                   //ArrayAdapter adapter = new ArrayAdapter<String>(CalculadoraDistancia.this, android.R.layout.simple_list_item_1, array3);

                    CalculadoraDistancia.this.lista.setAdapter(adapter);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
