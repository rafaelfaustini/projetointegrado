package projetointegrado.com.conversormedidas;

public class Medida {
    private String id;
    private String nome;
    private String desc;
    private String unidade;

    public Medida(String nome, String desc, String unidade){
        setNome(nome);
        setDesc(desc);
        setUnidade(unidade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString(){
        return unidade;
    }
}
