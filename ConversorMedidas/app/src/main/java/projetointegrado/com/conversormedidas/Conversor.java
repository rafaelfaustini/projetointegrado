package projetointegrado.com.conversormedidas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Conversor {
    private Medida origem;
    private Medida destino;
    private double valorConv;

    public Conversor(Medida origem, Medida destino, double valorConv){
        setOrigem(origem);
        setDestino(destino);
        setValorConv(valorConv);
    }
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
    public double converter(double valor){
        valor *= getValorConv();
        return valor;
    }
    public double converterInverso(double valor){
        valor /= getValorConv();
        return valor;
    }

    public Medida getOrigem() {
        return origem;
    }

    public void setOrigem(Medida origem) {
        this.origem = origem;
    }

    public Medida getDestino() {
        return destino;
    }

    public void setDestino(Medida destino) {
        this.destino = destino;
    }

    public double getValorConv() {
        return valorConv;
    }

    public void setValorConv(double valorConv) {
        this.valorConv = valorConv;
    }




}


