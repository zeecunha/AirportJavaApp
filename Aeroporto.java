package edu.ufp.inf.aedlp.project;

import edu.princeton.cs.algs4.RedBlackBST;


public class Aeroporto {

  private String nome_aeroporto;  
    
  private String codigo_aeroporto;

  private String cidade;

  private String pais;
  
  private String continente;
  
  private String classificacao;
  
  RedBlackBST<String, Aviao> aeroportoAviaoST = new RedBlackBST<>();
  
  RedBlackBST<String, Viagem> aeroportoViagemST = new RedBlackBST<>();

    public Aeroporto(String nome_aeroporto, String codigo_aeroporto, String cidade, String pais, String continente, String classificacao) {
        this.nome_aeroporto = nome_aeroporto;
        this.codigo_aeroporto = codigo_aeroporto;
        this.cidade = cidade;
        this.pais = pais;
        this.continente = continente;
        this.classificacao = classificacao;
    }

  
  
  
    /**
     * @return the nome_aeroporto
     */
    public String getNome_aeroporto() {
        return nome_aeroporto;
    }

    /**
     * @param nome_aeroporto the nome_aeroporto to set
     */
    public void setNome_aeroporto(String nome_aeroporto) {
        this.nome_aeroporto = nome_aeroporto;
    }

    /**
     * @return the codigo_aeroporto
     */
    public String getCodigo_aeroporto() {
        return codigo_aeroporto;
    }

    /**
     * @param codigo_aeroporto the codigo_aeroporto to set
     */
    public void setCodigo_aeroporto(String codigo_aeroporto) {
        this.codigo_aeroporto = codigo_aeroporto;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the continente
     */
    public String getContinente() {
        return continente;
    }

    /**
     * @param continente the continente to set
     */
    public void setContinente(String continente) {
        this.continente = continente;
    }

    /**
     * @return the classificacao
     */
    public String getClassificacao() {
        return classificacao;
    }

    /**
     * @param classificacao the classificacao to set
     */
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
    
    
    public RedBlackBST<String, Aviao> getAeroportoAviaoST() {
        return aeroportoAviaoST;
    }

    public void setAeroportoAviaoST(RedBlackBST<String, Aviao> aeroportoAviaoST) {
        this.aeroportoAviaoST = aeroportoAviaoST;
    }

    public RedBlackBST<String, Viagem> getAeroportoViagemST() {
        return aeroportoViagemST;
    }

    public void setAeroportoViagemST(RedBlackBST<String, Viagem> aeroportoViagemST) {
        this.aeroportoViagemST = aeroportoViagemST;
    }

    @Override
    public String toString() {
        return "Aeroporto{" + "nome_aeroporto=" + nome_aeroporto + ", codigo_aeroporto=" + codigo_aeroporto + ", cidade=" + cidade + ", pais=" + pais + ", continente=" + continente + ", classificacao=" + classificacao + '}';
    }
    
    
    

}