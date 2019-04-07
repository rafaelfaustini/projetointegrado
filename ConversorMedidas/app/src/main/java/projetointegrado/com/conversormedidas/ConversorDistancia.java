package projetointegrado.com.conversormedidas;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class ConversorDistancia {

    public ArrayList<String> converter(int pos, String value){
        if(value.isEmpty()) {
            ArrayList<String> medidas = new ArrayList<>();
            medidas.add("");
            return medidas;
        }
            String[] vetor = new String[7];

            String ant = "";
            for (int i = pos; i < 7; i++) { // Converte o que está depois de pos
                if (i == pos) {
                    vetor[pos] = value;
                    ant = value;
                    continue;
                }
                vetor[i] = Double.toString(Double.parseDouble(ant) * 10);
                ant = vetor[i];
            }

            for (int i = pos; i > -1; i--) { // Converte o que está depois de pos
                if (i == pos) {
                    ant = value;
                    continue;
                }
                vetor[i] = Double.toString(Double.parseDouble(ant) / 10);
                ant = vetor[i];
            }

            ArrayList<String> medidas = new ArrayList<>(Arrays.asList(vetor));
            Log.i(medidas.toString(), value);
            Log.i("Size: "+medidas.size()+ " POS: "+pos, value);
            return medidas;

    }
}
