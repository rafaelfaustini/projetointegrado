package projetointegrado.com.conversormedidas;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;
import java.util.Locale;

public class ConversorDistancia {

    private String formatarNumero(Double numero){

        DecimalFormat df = new DecimalFormat(
                "#,###.##",
                new DecimalFormatSymbols(new Locale("pt", "BR")));

        BigDecimal value = new BigDecimal(numero);
        BigDecimal grande = new BigDecimal(1000000000); // Teto de valor para não notação científica
        BigDecimal pequeno = new BigDecimal(0.001); // Chão
        if(value.compareTo(grande) > 0 || value.compareTo(pequeno) < 0){
            df = new DecimalFormat(
                    "0.00E0",
                    new DecimalFormatSymbols(new Locale("pt", "BR")));
        }
        String resultado = df.format(value.floatValue());
        return resultado;
    }

    public ArrayList<String> converter(int pos, String value){
        if(value.isEmpty()) {
            ArrayList<String> medidas = new ArrayList<>();
            medidas.add("");
            return medidas;
        }
            String[] vetor = new String[7];
            String ant = "";
            String d = "";
            double numero;
            for (int i = pos; i < 7; i++) { // Converte o que está depois de pos
                if (i == pos) {
                    numero = Double.parseDouble(value);
                    d = formatarNumero(Double.parseDouble(value));
                    vetor[pos] = d;
                    ant = Double.toString(numero);
                    continue;
                }
                numero = Double.parseDouble(ant) * 10;
                d = formatarNumero(numero);
                vetor[i] = d;
                ant = Double.toString(numero);
            }

            for (int i = pos; i > -1; i--) { // Converte o que está antes de pos
                if (i == pos) {
                    numero = Double.parseDouble(value);
                    d = formatarNumero(Double.parseDouble(value));
                    vetor[pos] = d;
                    ant = Double.toString(numero);
                    continue;
                }
                numero = Double.parseDouble(ant) / 10;
                d = formatarNumero(numero);
                vetor[i] = d;
                ant = Double.toString(numero);
            }

            ArrayList<String> medidas = new ArrayList<>(Arrays.asList(vetor));
            return medidas;

    }
}
