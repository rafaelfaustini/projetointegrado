package projetointegrado.com.conversormedidas;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculadoraDistancia extends AppCompatActivity {
    private Spinner unidade;
    private EditText valor;
    private ConversorDistancia conversor;
    private ListView lista;
    private ArrayList<String> resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.conversor_distancia); // Troca o título ao mudar para a activity de distancia

        // Criação da droplist
        setContentView(R.layout.activity_calculadora_distancia);
        this.unidade = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> MyAdapter = new ArrayAdapter<String>(CalculadoraDistancia.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.medidasDistancia_abreviado));
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
                    CalculadoraDistancia.this.resultado = StringArray;
                    String[] b = getResources().getStringArray(R.array.medidasDistancia_extenso);
                    ArrayList<String> c = new ArrayList<>(Arrays.asList(b));

                    int length = StringArray.size();
                    if (length != c.size()) {
                    }
                    ArrayList<String> array3 = new ArrayList<String>(length);
                    for (int i = 0; i < length; i++) {
                        array3.add(StringArray.get(i) + " " + c.get(i));
                    }

                    MedidasAdapter adapter = new MedidasAdapter(CalculadoraDistancia.this,StringArray, c);

                    CalculadoraDistancia.this.lista.setAdapter(adapter);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CalculadoraDistancia.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CalculadoraDistancia.this, "Texto copiado para area de transferencia",Toast.LENGTH_SHORT).show();
                final android.content.ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Source Text", CalculadoraDistancia.this.resultado.get(position));
                clipboardManager.setPrimaryClip(clipData);
            }
        });

    }
}
