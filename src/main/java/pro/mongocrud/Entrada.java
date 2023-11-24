package pro.mongocrud;
public class Entrada {
    private int id;
    private String nome;
    private String profissao;
    private boolean esta_trabalhando;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getProfissao() {
        return profissao;
    }
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }   
    public boolean isEsta_trabalhando() {
        return esta_trabalhando;
    }
    public void setEsta_trabalhando(boolean esta_trabalhando) {
        this.esta_trabalhando = esta_trabalhando;
    }    
}