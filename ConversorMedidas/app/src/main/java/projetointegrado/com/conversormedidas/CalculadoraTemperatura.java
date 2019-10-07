package projetointegrado.com.conversormedidas;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalculadoraTemperatura extends AppCompatActivity{
    private List<Medida> medidas = new ArrayList<>();
    private List<Conversor> conversor = new ArrayList<>();
    private ArrayList<String> resultado = new ArrayList<>();
    private Medida k;
    private Medida c;
    private Medida f;
    private Medida atual;
    private ListView lista ;
    private EditText valor;
    private Spinner spinner;

    private String formatarNumero(Double numero){

        DecimalFormat df = new DecimalFormat(
                "#,###.##",
                new DecimalFormatSymbols(new Locale("pt", "BR")));

        BigDecimal value = new BigDecimal(numero);
        BigDecimal grande = new BigDecimal(1000000000); // Teto de valor para não notação científica
        BigDecimal pequeno = new BigDecimal(0.001); // Chão
        BigDecimal zero = new BigDecimal(0);


        if(numero == 0){
            return String.valueOf(0);
        }
        if(value.compareTo(grande) > 0 || value.compareTo(pequeno) < 0){
            df = new DecimalFormat(
                    "0.00E0",
                    new DecimalFormatSymbols(new Locale("pt", "BR")));
        }
        if(df.format((value.floatValue())).contentEquals("0")){
            value = value.setScale(4, RoundingMode.CEILING);
            df = new DecimalFormat(
                    "#,###.#####",
                    new DecimalFormatSymbols(new Locale("pt", "BR")));
            return df.format(value.floatValue());
        }


        String resultado = df.format(value.floatValue());
        return resultado;
    }

    public void calcular(Double v){
        ArrayList<String> unidades= new ArrayList<>();
        Double referencia=0.0;
        Conversor conversorAtual;
        atual = (Medida) spinner.getSelectedItem();
        Medida ant=atual;
        int temp=0;
        for(int i = 0; i < medidas.size(); i++) {
            conversorAtual = conversor.get(i);
            if (conversorAtual.getOrigem() == atual) {
                referencia = conversorAtual.converterInverso(v);
            }
        }
        Log.i("Referencia", referencia.toString());
        conversorAtual = conversor.get(0);
        atual = conversorAtual.getDestino();
        resultado.clear();
        for(int i = 0; i < medidas.size(); i++) {
            Double r;
            conversorAtual = conversor.get(i);
            if (conversorAtual.getOrigem() == ant) {
                r = conversorAtual.converterInverso(referencia);
            } else{
                r = conversorAtual.converter(referencia);
            }
            Log.i("Atual ", atual.toString());

            resultado.add(formatarNumero(r));
            unidades.add(medidas.get(i).getNome());

        }

        MedidasAdapter adapter = new MedidasAdapter(CalculadoraTemperatura.this, resultado, unidades);
        CalculadoraTemperatura.this.lista.setAdapter(adapter);
    }

    private void iniciaMedidas(){
        this.c = new Medida("Grau Celsius", "", "°C");
        this.medidas.add(c);
        this.k = new Medida("Kelvin", "", "K");
        this.medidas.add(k);
        this.f = new Medida("Grau Fahrenheit", "", "°F");
        this.medidas.add(f);
    }
    private void iniciaConversor(){
        this.conversor.add(new Conversor(c, c, 1));
        this.conversor.add(new Conversor(k, c, 274.15));
        this.conversor.add(new Conversor(f, c, 33.8));
    }
    private void copyText(String texto) {
        final android.content.ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Source Text", texto);
        clipboardManager.setPrimaryClip(clipData);
    }
    private boolean canCalcular(CharSequence value){
        if(!TextUtils.isEmpty(value)){

            try {
                Double.parseDouble(value.toString());
                return true;
            } catch(NumberFormatException e){
                return false;
            }
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_temperatura);
        iniciaMedidas();
        iniciaConversor();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.conversor_temperatura); // Troca o título ao mudar para a activity de temperatura
        valor = findViewById(R.id.valorTemperatura);
        lista = findViewById(R.id.listaTemperatura);
        spinner = findViewById(R.id.unidadesTemperatura);
        ArrayAdapter<Medida> adapter = new ArrayAdapter<Medida>(this, android.R.layout.simple_spinner_item, getMedidas());
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Medida unidade = (Medida) parent.getSelectedItem();
                CalculadoraTemperatura.this.atual = unidade;
                String texto = valor.getText().toString();
                if(canCalcular(texto)){
                    CalculadoraTemperatura.this.calcular(Double.valueOf(texto));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        valor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CalculadoraTemperatura calc = CalculadoraTemperatura.this;
                if(canCalcular(s)){
                    calc.calcular(Double.valueOf(s.toString()));
                } else{
                    lista.setAdapter(null);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        CalculadoraTemperatura.this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CalculadoraTemperatura calc = CalculadoraTemperatura.this;
                Toast.makeText(calc, "Texto copiado para area de transferencia", Toast.LENGTH_SHORT).show();
                calc.copyText(calc.resultado.get(position));
            }
        });


    }

    public List<Medida> getMedidas() {
        return medidas;
    }
}
