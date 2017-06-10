package edu.ufp.inf.aedlp.project;

import edu.princeton.cs.algs4.RedBlackBST;


public class Aviao {

  private String id_aviao;

  private String modelo;

  private String nome;
  
  private String companhia_aerea;
  
  private String velocidade_cruzeiro;
  
  private int altitude_cruzeiro;
  
  private String distancia_maxima;
  
  private String cod_aeroporto;
  
  private int capacidade_de_passageiros;
  
  private int capacidade_de_deposito;
  
  RedBlackBST<String, Viagem> aviaoViagemST = new RedBlackBST<>();

    public Aviao(String id_aviao, String modelo, String nome, String companhia_aerea, String velocidade_cruzeiro, int altitude_cruzeiro, String distancia_maxima, String cod_aeroporto, int capacidade_de_passageiros, int capacidade_de_deposito) {
        this.id_aviao = id_aviao;
        this.modelo = modelo;
        this.nome = nome;
        this.companhia_aerea = companhia_aerea;
        this.velocidade_cruzeiro = velocidade_cruzeiro;
        this.altitude_cruzeiro = altitude_cruzeiro;
        this.distancia_maxima = distancia_maxima;
        this.cod_aeroporto = cod_aeroporto;
        this.capacidade_de_passageiros = capacidade_de_passageiros;
        this.capacidade_de_deposito = capacidade_de_deposito;
    }

    /**
     * @return the id_aviao
     */
    public String getId_aviao() {
        return id_aviao;
    }

    /**
     * @param id_aviao the id_aviao to set
     */
    public void setId_aviao(String id_aviao) {
        this.id_aviao = id_aviao;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the companhia_aerea
     */
    public String getCompanhia_aerea() {
        return companhia_aerea;
    }

    /**
     * @param companhia_aerea the companhia_aerea to set
     */
    public void setCompanhia_aerea(String companhia_aerea) {
        this.companhia_aerea = companhia_aerea;
    }

    /**
     * @return the velocidade_cruzeiro
     */
    public String getVelocidade_cruzeiro() {
        return velocidade_cruzeiro;
    }

    /**
     * @param velocidade_cruzeiro the velocidade_cruzeiro to set
     */
    public void setVelocidade_cruzeiro(String velocidade_cruzeiro) {
        this.velocidade_cruzeiro = velocidade_cruzeiro;
    }

    /**
     * @return the altitude_cruzeiro
     */
    public int getAltitude_cruzeiro() {
        return altitude_cruzeiro;
    }

    /**
     * @param altitude_cruzeiro the altitude_cruzeiro to set
     */
    public void setAltitude_cruzeiro(int altitude_cruzeiro) {
        this.altitude_cruzeiro = altitude_cruzeiro;
    }

    /**
     * @return the distancia_maxima
     */
    public String getDistancia_maxima() {
        return distancia_maxima;
    }

    /**
     * @param distancia_maxima the distancia_maxima to set
     */
    public void setDistancia_maxima(String distancia_maxima) {
        this.distancia_maxima = distancia_maxima;
    }

    /**
     * @return the cod_aeroporto
     */
    public String getCod_aeroporto() {
        return cod_aeroporto;
    }

    /**
     * @param cod_aeroporto the cod_aeroporto to set
     */
    public void setCod_aeroporto(String cod_aeroporto) {
        this.cod_aeroporto = cod_aeroporto;
    }

    /**
     * @return the capacidade_de_passageiros
     */
    public int getCapacidade_de_passageiros() {
        return capacidade_de_passageiros;
    }

    /**
     * @param capacidade_de_passageiros the capacidade_de_passageiros to set
     */
    public void setCapacidade_de_passageiros(int capacidade_de_passageiros) {
        this.capacidade_de_passageiros = capacidade_de_passageiros;
    }

    /**
     * @return the capacidade_de_deposito
     */
    public int getCapacidade_de_deposito() {
        return capacidade_de_deposito;
    }

    /**
     * @param capacidade_de_deposito the capacidade_de_deposito to set
     */
    public void setCapacidade_de_deposito(int capacidade_de_deposito) {
        this.capacidade_de_deposito = capacidade_de_deposito;
    }

    public RedBlackBST<String, Viagem> getAviaoViagemST() {
        return aviaoViagemST;
    }

    public void setAviaoViagemST(RedBlackBST<String, Viagem> aviaoViagemST) {
        this.aviaoViagemST = aviaoViagemST;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Aviao{" + "id_aviao=" + id_aviao + ", modelo=" + modelo + ", nome=" + nome + ", companhia_aerea=" + companhia_aerea + ", velocidade_cruzeiro=" + velocidade_cruzeiro + ", altitude_cruzeiro=" + altitude_cruzeiro + ", distancia_maxima=" + distancia_maxima + ", cod_aeroporto=" + cod_aeroporto + ", capacidade_de_passageiros=" + capacidade_de_passageiros + ", capacidade_de_deposito=" + capacidade_de_deposito + '}';
    }



}