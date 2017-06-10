package edu.ufp.inf.aedlp.project;

import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;



public class Rede extends EdgeWeightedDigraph {

  public String id;
  
  // red-black trees
  RedBlackBST<String, Aviao> aviaoST = new RedBlackBST<>();
  RedBlackBST<String, Viagem> viagemST = new RedBlackBST<>();
        
  //Separate Chaining Hash 
  SeparateChainingHashST<String, Aeroporto> aeroportoST = new SeparateChainingHashST<>();
  
  //Hash map
  HashMap<Integer,String> hmap = new HashMap<>();
  HashMap<String, Integer> hmapInvert = new HashMap<>();
  
  protected static String custoSelecionado = Rede.DISTANCIA;
  public final static String DISTANCIA = "DISTANCIA";
  public final static String DURACAO = "DURACAO";
  public final static String CONSUMO = "CONSUMO";
  
        public Rede(int V) {
        super(V);

    }
        
        
  /*
  *  Validações
   */
    
  /**
     * Validar se o aeroporto existe;
     *
     * @param aeroportoST
     * @param codigo_aeroporto
     * @return
  */
  
    public static boolean codAeroportoValidation(SeparateChainingHashST<String, Aeroporto> aeroportoST, String codigo_aeroporto) {
     for (String key : aeroportoST.keys()) {
            Aeroporto a = aeroportoST.get(key);
            if (a.getCodigo_aeroporto().equals(codigo_aeroporto)) { //compare
                return true;
            }
        }
        return false;
    }
    
    /**
     * Validar se o aviao existe;
     *
     * @param aviaoST
     * @param id_aviao
     * @return
  */
  
    public static boolean idAviaoValidation(RedBlackBST<String, Aviao> aviaoST, String id_aviao) {
        for (String key : aviaoST.keys()) {
            Aviao a = aviaoST.get(key);
            if (a.getId_aviao().equals(id_aviao)) { //compare
                return true;
            }
        }
        return false;
    }
    
       /**
     * Validar se a viagem existe;
     *
     * @param viagemST
     * @param id_viagem
     * @return
  */
  
    public static boolean idViagemValidation(RedBlackBST<String, Viagem> viagemST, String id_viagem) {
        for (String key : viagemST.keys()) {
            Viagem v = viagemST.get(key);
            if (v.getId().equals(id_viagem)) { //compare
                return true;
            }
        }
        return false;
    }
    
     /**
     * Validar se o numero de passageiros cabe no aviao;
     *
     * @param aviaoST
     * @param passageiros
     * @return
  */
  
    public static boolean passageirosValidation(RedBlackBST<String, Aviao> aviaoST, int passageiros) {
        for (String key : aviaoST.keys()) {
            Aviao a = aviaoST.get(key);
            if (a.getCapacidade_de_passageiros() >= passageiros) { //compare
                return true;
            }
        }
        return false;
    }



/**
     * Adicionar um novo aeroporto
     *
     * @param aeroportoST
     */
  public static void addAeroporto(SeparateChainingHashST<String, Aeroporto> aeroportoST, HashMap<Integer,String> hmapInvert){
      
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial
        String nome_aeroporto, codigo_aeroporto, cidade, pais, continente, classificacao;
        Scanner sca = new Scanner(System.in);
        
        System.out.print("-> Insira o nome do aeroporto: ");
        nome_aeroporto = sca.nextLine();
        System.out.print("\n-> Insira o codigo do aeroporto: ");
        codigo_aeroporto = sca.nextLine();
        
        //Verifica se o codigo do aeroporto existe na BD
        while (codAeroportoValidation(aeroportoST, codigo_aeroporto) == true) {
            System.out.println("O aeroporto pretendido já existe na BD!");
            System.out.print("-> Insira o nome do  aeroporto: ");
            nome_aeroporto = sca.nextLine();
            System.out.println("\n-> Insira o codigo do aeroporto: ");
            codigo_aeroporto = sca.nextLine();
        }
        System.out.print("-> Insira a cidade: ");
        cidade = sca.nextLine();
        System.out.print("\n-> Insira o pais: ");
        pais = sca.nextLine();
        System.out.print("-> Insira o continente: ");
        continente = sca.nextLine();
        System.out.print("\n-> Insira a classificacao: ");
        classificacao = sca.nextLine();


        Aeroporto a = new Aeroporto(nome_aeroporto, codigo_aeroporto, cidade, pais, continente, classificacao);
        aeroportoST.put(nome_aeroporto, a);
        
        int id = 0;
        Set<Integer> aux = hmapInvert.keySet();
        for ( Integer naux : aux)
        {
            if(aux != null)
            {
                System.out.println(naux);
            }
            id= naux;
        }
        System.out.println("id " + id);
        hmapInvert.put(id+1, codigo_aeroporto);
        
  
        System.out.println("O aeroporto foi inserido com sucesso");
        //System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
      
  }
  
   /**
     * Remove do ficheiro airports.txt.
     * Escolher o codigo do aeroporto que se pretende eliminar
     *
     * @param aeroportoST
     * @param aviaoST
     */
  public static void apagaAeroporto(SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST, RedBlackBST<String, Viagem> viagemST){
      
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial
        Scanner sca = new Scanner(System.in);
        String delete;
        Main.printAeroportos(aeroportoST);
        System.out.print("\n-> Insira o codigo de aeroporto que pretende eliminar: ");
        delete = sca.nextLine();
        for (String codigo_aeroporto : aeroportoST.keys()) {
            Aeroporto a = (Aeroporto) aeroportoST.get(codigo_aeroporto); //recebe o codigo do aeroporto
            if (a.getCodigo_aeroporto().equals(delete)) {
                aeroportoST.delete(codigo_aeroporto);
        }
     }

        //apagar os avioes do aeroporto a ser eliminado
        for (String codigo_aeroporto : aviaoST.keys()) {
            Aviao av = (Aviao) aviaoST.get(codigo_aeroporto);
            if (av.getCod_aeroporto().equals(delete)) {
                aviaoST.delete(codigo_aeroporto);
            }
        }




        //apagar as viagens de partida do aeroporto a ser eliminado
        for (String codigo_aeroporto : viagemST.keys()) {
            Viagem v = (Viagem) viagemST.get(codigo_aeroporto);
            if (v.getAeroportoInit().equals(delete)) {
                viagemST.delete(codigo_aeroporto);
            }
        }
        
        //apagar as viagens de destino do aeroporto a ser eliminado
        for (String codigo_aeroporto : viagemST.keys()) {
            Viagem v = (Viagem) viagemST.get(codigo_aeroporto);
            if (v.getAeroportoDest().equals(delete)) {
                viagemST.delete(codigo_aeroporto);
            }
        }
 
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }

  /**
     * Editar um aeroporto
     * Escolher o codigo do aeroporto que se pretende eliminar
     *
     * @param aeroportoST
     * @throws edu.ufp.inf.aedlp.project.ProjectException
     */
  
  public static void updateAeroporto(SeparateChainingHashST<String, Aeroporto> aeroportoST) throws ProjectException {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial  
        Scanner sca = new Scanner(System.in);
        String codigo, opcao, editar;
        Main.printAeroportos(aeroportoST);

        System.out.print("\nInsira o codigo do aeroporto que pretende editar: ");
        codigo = sca.nextLine();
        if (codAeroportoValidation(aeroportoST, codigo) == true) {
            System.out.println(codigo);
           
            Aeroporto a = aeroportoST.get(codigo);
            System.out.println(a.getCodigo_aeroporto());
            if (a.getCodigo_aeroporto().compareTo(codigo) == 0) {
                do {
                    System.out.println("O que pretende editar:\n  1-Nome do aeroporto 2-Cidade 3-Pais 4-Continente 5-Classificação");
                    opcao = sca.nextLine();
                    switch (opcao) {
                        case "1": {
                            System.out.println("-> Insira o novo nome de aeroporto: ");
                            editar = sca.nextLine();
                            a.setNome_aeroporto(editar);
                            return;
                        }
                        case "2": {
                            System.out.println("-> Insira a nova cidadee: ");
			    editar = sca.nextLine();
                            a.setCidade(editar);
                            return;
                        }
                        case "3": {
                            System.out.println("-> Insira o novo pais: ");
			    editar = sca.nextLine();
                            a.setPais(editar);
                            return;
                        }
                        case "4": {
                            System.out.println("-> Insira o novo continente: ");
			    editar = sca.nextLine();
                            a.setContinente(editar);
                            return;
                        }

                        case "5": {
                            System.out.println("-> Insira a nova classificação: ");
			    editar = sca.nextLine();
                            a.setClassificacao(editar);
                            return;
                        }

                    }
                } while (opcao.equals("nome") || opcao.equals("cidade") || opcao.equals("pais") || opcao.equals("continente") || opcao.equals("classificacao"));
            }
        } else {
            throw new ProjectException("O aeroporto selecionado não se encontra na BD! ");
        }
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim

    }
  
    
  /**
     * Grava no ficheiro airports.txt
     *
     * @param aeroportoST
     * @param path
     */
  public static void saveAeroporto(SeparateChainingHashST<String, Aeroporto> aeroportoST, String path){
      Out o = new Out(path);
        for (String codigo_aeroporto : aeroportoST.keys()) {
            Aeroporto a = (Aeroporto) aeroportoST.get(codigo_aeroporto);
            o.println(a.getNome_aeroporto()+ ";" + a.getCodigo_aeroporto()+ ";" + a.getCidade() + ";" + a.getPais() + ";" + a.getContinente()+ 
                    ";" + a.getClassificacao());
        }
  }
  
  /**
     * Grava no ficheiro airplanes.bin
     *
     * @param aeroportoST
     */
  public static void saveAeroportoBin(SeparateChainingHashST<String, Aeroporto> aeroportoST){
      
        BinaryOut bo = new BinaryOut("C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airports.bin");
        
        for (String codigo_aeroporto : aeroportoST.keys()) {
             Aeroporto a = (Aeroporto) aeroportoST.get(codigo_aeroporto);
            bo.write(a.getNome_aeroporto()+ ";" + a.getCodigo_aeroporto()+ ";" + a.getCidade() + ";" + a.getPais() + ";" + a.getContinente()+ 
                    ";" + a.getClassificacao());
            }
      
      
  }
  
  /**
     * Grava no ficheiro airports.txt
     *
     * @param aeroportoST
     * @param path
     */
  /*
  public static void saveLinksTranslation(HashMap<String, Integer> hmapInvert, String path) throws IOException{
      
      
      for(HashMap.Entry<String, Integer> entry : hmapInvert.entrySet()){
          hmapInvert.
      }
      pro.store(new FileOutputStream(path), null);
  }
*/
    /**
     * Imprimir toda	a informação relativa a um determinado aeroporto
     * 
     * @param aeroportoST
     * @param aviaoST
     * @param viagemST
     * @param cod_aeroporto 
     */
  public static void infoAeroporto(SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST, RedBlackBST<String, Viagem> viagemST, String cod_aeroporto) {
      
      long tempoInicio = System.currentTimeMillis();  //Tempo inicial

      System.out.println("Informação do aeroporto: " + cod_aeroporto);
        
        for (String codigo: aeroportoST.keys()) {
            Aeroporto a = aeroportoST.get(codigo);
            
          if(a.getCodigo_aeroporto().contains(cod_aeroporto)) {
                System.out.println("\t Nome aeroporto - " + a.getNome_aeroporto()+ "; "
                        + "Código Aeroporto - " + a.getCodigo_aeroporto() + "; Cidade - " + a.getCidade() + "; "
                        + "Pais - " + a.getPais() + "; Continente - " + a.getContinente() + "; "
                        + "Classificaçao - " + a.getClassificacao()+ "; Numero de Avioes - " + a.getAeroportoAviaoST().size() 
                        + "; Numero de Viagens: " + a.getAeroportoViagemST().size());
            }
        } 
          
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }
  
  /**
     * Imprimir todos os aeroportos de um determinado país
     * 
     * @param aeroportoST
     * @param pais
     */
  
  public static void pesquisaAeroportoPais(SeparateChainingHashST<String, Aeroporto> aeroportoST, String pais) {
      long tempoInicio = System.currentTimeMillis();  //Tempo inicial

      System.out.println("Aeroportos no seguinte pais: " + pais);
        
        for (String codigo: aeroportoST.keys()) {
            Aeroporto a = aeroportoST.get(codigo);
                if(a.getPais().equals(pais)) {
                    System.out.println("\t Nome aeroporto - " + a.getNome_aeroporto()+ "; "
                            + "Código Aeroporto - " + a.getCodigo_aeroporto() + "; "
                            + "Cidade - " + a.getCidade()+"; "
                            + "Classificaçao - " + a.getClassificacao()
                            + "; Numero de Avioes - " + a.getAeroportoAviaoST().size() 
                            + "; Numero de Viagens: " + a.getAeroportoViagemST().size());
          }
        }
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }

  /**
     * Imprimir todos os aeroportos de um determinado continente
     * 
     * @param aeroportoST
     * @param continente
     */
  public static void pesquisaAeroportoContinente(SeparateChainingHashST<String, Aeroporto> aeroportoST, String continente) {
      long tempoInicio = System.currentTimeMillis();  //Tempo inicial

      System.out.println("Aeroportos no seguinte continente: " + continente);
        
        for (String codigo: aeroportoST.keys()) {
            Aeroporto a = aeroportoST.get(codigo);
                if(a.getContinente().equals(continente)) {
                    System.out.println("\t Nome aeroporto - " + a.getNome_aeroporto()+ "; "
                            + "Código Aeroporto - " + a.getCodigo_aeroporto() + "; "
                            + "Cidade - " + a.getCidade() + ";"
                            + "Pais - " + a.getPais() + ";"
                            + "Classificaçao - " + a.getClassificacao()
                            + "; Numero de Avioes - " + a.getAeroportoAviaoST().size() 
                            + "; Numero de Viagens: " + a.getAeroportoViagemST().size());
          }
        }
        
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }
/**
     * Adicionar um novo aviao
     *
     * @param aviaoST
     * @param aeroportoST
     */
  public static void addAviao(RedBlackBST<String, Aviao> aviaoST, SeparateChainingHashST<String, Aeroporto> aeroportoST) {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        String id_aviao, modelo, nome, companhia_aerea, velocidade_cruzeiro, distancia_maxima, cod_aeroporto;
        int capacidade_de_passageiros, altitude_cruzeiro, capacidade_do_deposito;
        Scanner sca = new Scanner(System.in);
        System.out.print("-> Insira o id do aviao: ");
        id_aviao = sca.nextLine();
        //Verifica se o id do aviao ja existe na BD
        while (idAviaoValidation(aviaoST, id_aviao) == true) {
            System.out.println("O aviao pretendido já existe na BD!");
            System.out.print("-> Insira o id do aviao: ");
            id_aviao = sca.nextLine();
        }
        System.out.print("\n-> Insira o modelo do aviao: ");
        modelo = sca.nextLine();
        System.out.print("\n-> Insira o nome do aviao: ");
        nome = sca.nextLine();
        System.out.print("-> Insira a companhia aerea: ");
        companhia_aerea = sca.nextLine();
        System.out.print("\n-> Insira velocidade cruzeiro: ");
        velocidade_cruzeiro = sca.nextLine();
        System.out.print("-> Insira a altitude cruzeiro: ");
        altitude_cruzeiro = sca.nextInt();
        sca.nextLine(); //limpar o \n do inteiro
        System.out.print("\n-> Insira a distancia maxima: ");
        distancia_maxima = sca.nextLine();
        System.out.print("\n-> Insira o codigo do aeroporto: ");
        cod_aeroporto = sca.nextLine();
         //Verifica se o codigo do aeroporto existe existe na BD
        while (codAeroportoValidation(aeroportoST, cod_aeroporto) == false) {
            System.out.println("O aeroporto pretendido não existe na BD!");
            System.out.print("-> Insira o codigo do aeroporto: ");
            cod_aeroporto = sca.nextLine();
        }
        System.out.print("-> Insira a capacidade de passageiros: ");
        capacidade_de_passageiros = sca.nextInt();
        System.out.print("\n-> Insira a capacidade do deposito: ");
        capacidade_do_deposito = sca.nextInt();

        Aviao a = new Aviao(id_aviao, modelo, nome, companhia_aerea, velocidade_cruzeiro, altitude_cruzeiro, distancia_maxima, cod_aeroporto, capacidade_de_passageiros, capacidade_do_deposito);
        aviaoST.put(id_aviao, a);
       
        aeroportoST.get(cod_aeroporto).aeroportoAviaoST.put(id_aviao,a);
        
        System.out.println("O aviao foi inserido com sucesso");
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }
  
  /**
     * Editar um aeroporto
     * Escolher o codigo do aeroporto que se pretende eliminar
     *
     * @param aviaoST
     * @param aeroportoST
     * @throws edu.ufp.inf.aedlp.project.ProjectException
     */
  
  public static void updateAviao(RedBlackBST<String, Aviao> aviaoST, SeparateChainingHashST<String, Aeroporto> aeroportoST) throws ProjectException {
      long tempoInicio = System.currentTimeMillis();  //Tempo inicial
  
      Scanner sca = new Scanner(System.in);
        String id, opcao, editar;
        int editarc;
        Main.printAvioes(aviaoST);

        System.out.print("\nInsira o id do aviao que pretende editar: ");
        id = sca.nextLine();
        if (idAviaoValidation(aviaoST, id) == true) {
            System.out.println(id);
           
            Aviao a = aviaoST.get(id);
            System.out.println(a.getId_aviao());
            if (a.getId_aviao().equals(id) == true) {
                do {
                    System.out.println("O que pretende editar:\n  1-modelo, 2-nome, 3-companhia_aerea, 4-velocidade_cruzeiro, 5-altitude_cruzeiro, "
                            + "6-distancia_maxima, 7-cod_aeroporto, 8-capacidade_de_passageiros, 9-capacidade_do_deposito");
                    opcao = sca.nextLine();
                    switch (opcao) {
                        case "1": {
                            System.out.println("-> Insira o novo modelo do aviao: ");
                            editar = sca.nextLine();
                            a.setModelo(editar);
                            return;
                        }
                        case "2": {
                            System.out.println("-> Insira a nova nome: ");
			    editar = sca.nextLine();
                            a.setNome(editar);
                            return;
                        }
                        case "3": {
                            System.out.println("-> Insira a nova companhia area: ");
			    editar = sca.nextLine();
                            a.setCompanhia_aerea(editar);
                            return;
                        }
                        case "4": {
                            System.out.println("-> Insira a nova velocidade cruzeiro: ");
			    editar = sca.nextLine();
                            a.setVelocidade_cruzeiro(editar);
                            return;
                        }

                        case "5": {
                            System.out.println("-> Insira a nova altitude cruzeiro: ");
			    editarc = sca.nextInt();
                            a.setAltitude_cruzeiro(editarc);
                            return;
                        }
                        case "6": {
                            System.out.println("-> Insira a nova distancia maxima: ");
			    editar = sca.nextLine();
                            a.setDistancia_maxima(editar);
                            return;
                        }
                        case "7": {
                            System.out.println("-> Insira novo codigo do aeroporto: ");
                            editar = sca.nextLine();

                           while(codAeroportoValidation(aeroportoST, editar) == false) {
                               System.out.println("O aeroporto pretendido não existe na BD!");
                               System.out.print("-> Insira o codigo do aeroporto: ");
                               editar = sca.nextLine();                            
                            }
                            a.setCod_aeroporto(editar);
                            aeroportoST.get(editar).aeroportoAviaoST.put(id,a);
                            return;
                        }
                        case "8": {
                            System.out.println("-> Insira a nova capacidade de passageiros: ");
			    editarc = sca.nextInt();
                            a.setCapacidade_de_passageiros(editarc);
                            return;
                        }
                        case "9": {
                            System.out.println("-> Insira a nova capacidade de deposito: ");
			    editarc = sca.nextInt();
                            a.setCapacidade_de_deposito(editarc);
                            return;
                        }
                        
                    }
                } while (opcao.equals("modelo") || opcao.equals("nome") || opcao.equals("companhia_aerea") || 
                        opcao.equals("velocidade_cruzeiro") || opcao.equals("altitude_cruzeiro")
                        || opcao.equals("distancia_maxima") || opcao.equals("cod_aeroporto")
                        || opcao.equals("capacidade_de_passageiros") || opcao.equals("capacidade_do_deposito"));
            }
            
        } else {
            throw new ProjectException("O aviao selecionado não se encontra na BD! ");
        }
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim

    }
  
  
  /**
     * Remove do ficheiro aiplanes.txt.
     * Escolher o id do aviao pretendido
     *
     * @param aviaoST
     */
  public static void apagaAviao(RedBlackBST<String, Aviao> aviaoST) {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        Scanner sca = new Scanner(System.in);
        String delete;
        Main.printAvioes(aviaoST);
        System.out.print("\n-> Insira o id do aviao que pretende eliminar: ");
        delete = sca.nextLine();
        for (String id_aviao : aviaoST.keys()) {
            Aviao a = (Aviao) aviaoST.get(id_aviao); //recebe o codigo do aviao
            if (a.getId_aviao().equals(delete)) {
                aviaoST.delete(id_aviao);
        } 
    }
        System.out.println("O aviao selecionado foi removido com sucesso");
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }
  
   /**
     * Grava no ficheiro airplanes.txt
     *
     * @param aviaoST
     * @param path
     */
  public static void saveAviao(RedBlackBST<String, Aviao> aviaoST, String path){
      Out o = new Out(path);
        for (String id_aviao : aviaoST.keys()) {
            Aviao a = (Aviao) aviaoST.get(id_aviao);
            o.println(a.getId_aviao()+ ";" + a.getModelo()+ ";" + a.getNome()+ ";" + a.getCompanhia_aerea()+ ";" + a.getVelocidade_cruzeiro()+ ";" + a.getAltitude_cruzeiro() + ";" + a.getDistancia_maxima()+ ";" + a.getCod_aeroporto()+ ";" + a.getCapacidade_de_passageiros()+ ";" + a.getCapacidade_de_deposito());
        }
  }
     /**
     * Grava no ficheiro airplanes.bin
     *
     * @param aviaoST
     */
  public static void saveAviaoBin(RedBlackBST<String, Aviao> aviaoST){

        BinaryOut bo = new BinaryOut("C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airplanes.bin");
        for (String id_aviao : aviaoST.keys()) {
            Aviao a = (Aviao) aviaoST.get(id_aviao);
            bo.write(a.getId_aviao()+ ";" + a.getModelo()+ ";" + a.getNome()+ ";" + a.getCompanhia_aerea()+ ";" + a.getVelocidade_cruzeiro()+ ";" + a.getAltitude_cruzeiro() + ";" + a.getDistancia_maxima()+ ";" + a.getCod_aeroporto()+ ";" + a.getCapacidade_de_passageiros()+ ";" + a.getCapacidade_de_deposito()+ "\n");
            }
  }

      /**
     * Imprimir toda	a informação relativa a	um determinado avião
     * 
     * @param aviaoST
     * @param id
     */
  public static void infoAviao(RedBlackBST<String, Aviao> aviaoST, String id) {
      long tempoInicio = System.currentTimeMillis();  //Tempo inicial
  
      System.out.println("Informação do aviao: " + id);
        
        for (String codigo: aviaoST.keys()) {
            Aviao a = aviaoST.get(codigo);
                if(a.getId_aviao().equals(id)) {
                        System.out.println("\t Id aviao - " + a.getId_aviao()+ "; modelo - " + a.getModelo()+ "; nome - " + a.getNome()
                        + "; Companhia aerea - " + a.getCompanhia_aerea()+ "; velocidade_cruzeiro - " 
                        + a.getVelocidade_cruzeiro()+ "; altitude_cruzeiro - " + a.getAltitude_cruzeiro() 
                        + "; distância_máxima - " + a.getDistancia_maxima()+ "; cod_aeroporto - " + a.getCod_aeroporto()
                        + "; capacidade_de_passageiros - " + a.getCapacidade_de_passageiros()+ "; capacidade_do_depósito - " 
                        + a.getCapacidade_de_deposito() + "; Numero de viagens -" + a.getAviaoViagemST().size());
          }
        }
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }

    /**
     * Imprimir todos os	aviões que estão num determinado aeroporto
     * @param aviaoST
     * @param cod_aeroporto
     */
    public static void avioesIntoAeroporto(RedBlackBST<String, Aviao> aviaoST, String cod_aeroporto){
      long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        System.out.println("Aviões no aeroporto: " + cod_aeroporto);
      
      for ( String codigo: aviaoST.keys()) {
          Aviao a = aviaoST.get(codigo);
            if(a.getCod_aeroporto().equals(cod_aeroporto)) {
                System.out.println("\t Id aviao - " + a.getId_aviao()+ "; modelo - " + a.getModelo()+ "; nome - " + a.getNome()
                        + "; Companhia aerea - " + a.getCompanhia_aerea()+ "; velocidade_cruzeiro - " 
                        + a.getVelocidade_cruzeiro()+ "; altitude_cruzeiro - " + a.getAltitude_cruzeiro() 
                        + "; distância_máxima - " + a.getDistancia_maxima()+ "; cod_aeroporto - " + a.getCod_aeroporto()
                        + "; capacidade_de_passageiros - " + a.getCapacidade_de_passageiros()+ "; capacidade_do_depósito - " 
                        + a.getCapacidade_de_deposito()+ "; Numero de viagens -" + a.getAviaoViagemST().size());
            }     
      }
      System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }
  
    /**
     * Adicionar uma nova viagem
     *
     * @param viagemST
     * @param aviaoST
     * @param aeroportoST
     */
  public static void addViagem(RedBlackBST<String, Viagem> viagemST, SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST) {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        String id, aeroportoInit, aeroportoDest, aviao, dataInit, dataDest, horaInit, horaDest;
        int passageiros, altitude, distancia;
        Scanner sca = new Scanner(System.in);
        
        System.out.println("-> Insira o id da viagem: ");
        id = sca.nextLine();
        //Verifica se o id da viagem ja existe na BD
        while (idViagemValidation(viagemST, id)== true) {
            System.out.println("A viagem pretendida já existe na BD!");
            System.out.print("-> Insira outro id de viagem: ");
            id = sca.nextLine();
        }
        
        System.out.println("-> Insira o codigo do aeroporto de partida: ");
        aeroportoInit = sca.nextLine();
        //Verifica se o codigo do aeroporto ja existe na BD
        while (codAeroportoValidation(aeroportoST, aeroportoInit)== false) {
            System.out.println("O aeroporto pretendido não existe na BD!");
            System.out.print("-> Insira outro aeroporto: ");
            aeroportoInit = sca.nextLine();
        }
        
        System.out.println("-> Insira o codigo do aeroporto de destino: ");
        aeroportoDest= sca.nextLine();
        //Verifica se o codigo do aeroporto ja existe na BD
        while (codAeroportoValidation(aeroportoST, aeroportoDest)== false) {
            System.out.println("O aeroporto pretendido não existe na BD!");
            System.out.print("-> Insira outro aeroporto: ");
            aeroportoDest = sca.nextLine();
        }
        
        System.out.println("-> Insira o id do aviao: ");
        aviao= sca.nextLine();
        //Verifica se o codigo do AVIAO ja existe na BD
        while (idAviaoValidation(aviaoST, aviao)== false) {
            System.out.println("O aviao pretendido não existe na BD!");
            System.out.print("-> Insira outro aviao: ");
            aviao = sca.nextLine();
        }
        
        System.out.println("-> Insira o numero de passageiros: ");
        passageiros= sca.nextInt();  

        //Verifica se o AVIAO SUporta esse n de passsageiros
        while (passageirosValidation(aviaoST, passageiros)== false) {
            System.out.println("O aviao nao suporta tantos passageiros!");
            System.out.print("-> Insira menos passageiros: ");
            passageiros = sca.nextInt();
        }
        sca.nextLine(); //limpar o \n do inteiro
        
        System.out.print("-> Insira a data de inicio no formato yyyy/mm/dd ");
        dataInit = sca.nextLine();
        //System.out.print(dataInit);
        
        System.out.print("-> Insira a data de fim no formato yyyy/mm/dd ");
        dataDest = sca.nextLine();
        //System.out.print(dataDest);
        
        System.out.print("-> Insira a hora de inicio no formato hh:mm:ss ");
        horaInit = sca.nextLine();
        //System.out.print(horaInit);
        
        System.out.print("\n-> Insira a hora de fim no formato hh:mm:ss ");
        horaDest = sca.nextLine();
        
        System.out.print("-> Insira a altitude:");
        altitude = sca.nextInt();
       // System.out.print(altitude);
        
        System.out.print("-> Insira a distancia:");
        distancia = sca.nextInt();
        //System.out.print(distancia);

        Viagem v = new Viagem(id, aeroportoInit, aeroportoDest, aviao, passageiros, dataInit, dataDest, horaInit, horaDest, altitude, distancia);
        viagemST.put(dataInit, v);
        
        aeroportoST.get(aeroportoInit).aeroportoViagemST.put(id, v);
        aeroportoST.get(aeroportoDest).aeroportoViagemST.put(id, v);
        aviaoST.get(aviao).aviaoViagemST.put(id, v);
        
        System.out.println("A viagem foi criada com sucesso");
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }
  
  
  /**
     * Remove do ficheiro viagem.txt.
     * Escolher o id da viagem pretendido
     *
     * @param viagemST
     */
  public static void apagaViagem(RedBlackBST<String, Viagem> viagemST) {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        Scanner sca = new Scanner(System.in);
        String delete;
        Main.printViagem(viagemST);
        System.out.print("\n-> Insira o id da viagem que pretende eliminar: ");
        delete = sca.nextLine();
        for (String id : viagemST.keys()) {
            Viagem v = (Viagem) viagemST.get(id); //recebe o id da viagem
            if (v.getId().equals(delete)) {
                viagemST.delete(id);
        } 
    }
        System.out.println("A viagem selecionada foi removida com sucesso");
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }

  /**
     * Grava no ficheiro viagem.txt
     *
     * @param viagemST
     * @param path
     */
  public static void saveViagem(RedBlackBST<String, Viagem> viagemST, String path){
      Out o = new Out(path);
        for (String id : viagemST.keys()) {
            Viagem v = (Viagem) viagemST.get(id);
            o.println(v.getId()+ ";" + v.getAeroportoInit()+ ";" + v.getAeroportoDest()+ ";" + v.getAviao()+";" + v.getPassageiros() +";" +v.getDataInit()+";" + v.getDataDest()+";"+ v.getHoraInit()+";" + v.getHoraDest() + ";" + v.getAltitude() + ";" + v.getDistancia());
        }
  }
  
  /**
     * Grava no ficheiro airplanes.bin
     *
     * @param viagemST
     */
  public static void saveViagemBin(RedBlackBST<String, Viagem> viagemST){

        BinaryOut bo = new BinaryOut("C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\viagem.bin");
        for (String id : viagemST.keys()) {
            Viagem v = (Viagem) viagemST.get(id);
            bo.write(v.getId()+ ";" + v.getAeroportoInit()+ ";" + v.getAeroportoDest()+ ";" + v.getAviao()+";" + v.getPassageiros() +";" +v.getDataInit()+";" + v.getDataDest()+";"+ v.getHoraInit()+";" + v.getHoraDest() + ";" + v.getAltitude() + ";" + v.getDistancia());
        }
  }
  
      /**
     * Imprimir todas as	viagens	com origem/destino num determinado aeroporto
     * @param viagemST
     * @param aeroporto
     */
    public static void viagemIntoAeroporto(RedBlackBST<String, Viagem> viagemST, String aeroporto){
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

      System.out.println("Aeroporto escolhido : " + aeroporto);
      
           System.out.println(aeroporto + " como inicio de viagem:"); 
           
           for (String init: viagemST.keys()) {
           Viagem v = viagemST.get(init);   
           if(v.getAeroportoInit().equals(aeroporto)) {
                System.out.println("\tId viagem - " + v.getId()+ "; Aeroporto Init - " + v.getAeroportoInit()+ "; Aeroporto Dest - " + v.getAeroportoDest()+ "; Aviao - " + v.getAviao()+"; Passageiros - " + v.getPassageiros() +"; Hora inicio - " + v.getHoraInit()+"; hora fim - " + v.getHoraDest()+ "; Altitude - " + v.getAltitude() + "; Distancia - " + v.getDistancia());
            }            
           }
           
          System.out.println(aeroporto + " como destino de viagem:");
          
          for (String dest: viagemST.keys()) {
          Viagem v = viagemST.get(dest);
            if(v.getAeroportoDest().equals(aeroporto)){
                System.out.println("\tId viagem - " + v.getId()+ "; Aeroporto Init - " + v.getAeroportoInit()+ "; Aeroporto Dest - " + v.getAeroportoDest()+ "; Aviao - " + v.getAviao()+"; Passageiros - " + v.getPassageiros() +"; Hora inicio - " + v.getHoraInit()+"; hora fim - " + v.getHoraDest()+ "; Altitude - " + v.getAltitude() + "; Distancia - " + v.getDistancia());
            }
      }
          System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
    }

   /**
     * Imprimir todas as 	viagens	realizadas por um determinado avião
     * 
     * @param viagemST
     * @param aviao
     */
  public static void pesquisaViagemAviao(RedBlackBST<String, Viagem> viagemST, String aviao) {
      long tempoInicio = System.currentTimeMillis();  //Tempo inicial

      System.out.println("Viagens realizadas pelo aviao: " + aviao);
        
        for (String viagem: viagemST.keys()) {
            Viagem v = viagemST.get(viagem);
                if(v.getAviao().equals(aviao)){
                System.out.println("\t Id viagem - " + v.getId()+ "; Aeroporto Init - " + v.getAeroportoInit()+ "; Aeroporto Dest - " + v.getAeroportoDest()+ "; Aviao - " + v.getAviao()+"; Passageiros - " + v.getPassageiros() +"; Hora inicio - " + v.getHoraInit() +"; hora fim - " + v.getHoraDest()+ "; Altitude - " + v.getAltitude() + "; Distancia - " + v.getDistancia());
            }
        }
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
  }

   /**
     * Determinar qual o voo que transportou mais passageiros
     * 
     * @param viagemST
     */
 public static void vooMaisPassageiros(RedBlackBST<String, Viagem> viagemST){
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        int primeiro = 0;
        String id = null;
        for(String viagem: viagemST.keys()){ 
            Viagem v = viagemST.get(viagem);
 
            if(v.getPassageiros() > primeiro){  //se o n de passageiros foi maior do que o primeiro n de passageiros 
                primeiro = v.getPassageiros();  //guarda o maior valor
                id = v.id;  //"carregar" o id da viagem com mais passageiros
            }  
        }
        System.out.println("O id do voo com mais passageiros é o :" + id + " com " + primeiro + " passageiros");
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
        
 }
  
 /**
     * Calcular o custo de uma viagem com determinada distância e numa determinada altitude tendo em conta a velocidade do vento para um determinado avião
     * 
     * @param viagemST
     * @param aviaoST
     */
 public static void calcCusto(RedBlackBST<String, Viagem> viagemST, RedBlackBST<String, Aviao> aviaoST){
     long tempoInicio = System.currentTimeMillis();  //Tempo inicial

     int n = 200;   //Altitude a cima
     int m = 400;   //altitude a baixo
     int dif = 0;
     int newdif = 0;
     int litros = 0;
     
        Scanner sca = new Scanner(System.in);
        String id_voo;
        int av = 0; 
        int alt = 0;
        
        Main.printViagem(viagemST);
        System.out.print("\n-> Insira o id da viagem que pretende calcular o custo: ");
        id_voo = sca.nextLine();    //id da viagem
        for (String id : viagemST.keys()) {
            Viagem v = (Viagem) viagemST.get(id_voo); //recebe o id da viagem
            alt = v.getAltitude();  //altitude da viagem pretendida
            for(String aviao : aviaoST.keys()){
                Aviao a = (Aviao) aviaoST.get(aviao);  //recebe o id do aviao
                if(v.getAviao().equals(a.getId_aviao())){
                    av = a.getAltitude_cruzeiro();  //altitude cruzeiro do aviao
                    int deposito = a.getCapacidade_de_deposito(); //capacidade do deposito
                }
            }
        }
        //se a altitude da viagem for maior do que a altitude "perfeita" do aviao
        if(alt>av){ 
            dif = alt - av; //diferença da altitude da viagem com a altitude cruzeiro
            for ( int i = 1; i<=dif; i++){  //i menor que o dif
 
                if( i%1000 == 0 ){
                    litros = litros + 200;
                }
            }
            System.out.println("O aviao vai consumir mais " + litros + " litros");
        }else{
            //se a altitude da viagem for menor do que a altitude "perfeita" do aviao
            if(alt<av){
                dif = alt - av; //diferença da altitude da viagem com a altitude cruzeiro
                newdif = Math.abs(dif); //se o resultado der negativo, converte em positivo
                for ( int i = 0; i<=newdif; i++){  //i menor que o dif
                    
                if( i%1000 == 0 ){
                    litros = litros + 200;
                }        
            }
                System.out.println("O aviao vai consumir mais " + litros + " litros");
            }
            else{
                System.out.println("O aviao vai na altitude perfeita para um otimo consumo");
            }              
        }  
        System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
   }
 /**
      *Determinar qual o aeroporto com mais tráfego 
     * @param viagemST
     * @param aeroportoST
     */
 public static void aeroportoMaiorTrafego(RedBlackBST<String, Viagem> viagemST, SeparateChainingHashST<String, Aeroporto> aeroportoST){
     long tempoInicio = System.currentTimeMillis();  //Tempo inicial

     int count=0, count2=0;
     String cod = "";
     
     //Percorrer todos os aeroportos
     for(String codigo_aeroporto: aeroportoST.keys()){ 
         Aeroporto a = (Aeroporto) aeroportoST.get(codigo_aeroporto);
         //System.out.println("a.codigo_aeroporto");
         count = 0;
         //percorrer todas as viagens
         for(String dataInit : viagemST.keys()){
             Viagem v = viagemST.get(dataInit);
             //comparar aeroportos do aeroporto txt com aeroportos do viagens.txt (inicio e fim)
             if(a.getCodigo_aeroporto().compareTo(v.aeroportoInit) == 0|| a.getCodigo_aeroporto().compareTo(v.aeroportoDest) == 0){
                 count++;   //cada vez que aparece, incrimenta 1 ao count
         }
     }	 
		 
		 if ( count2 < count){	
                     
		        count2 = count;
			cod = a.getCodigo_aeroporto();  //codigo do aeroporto
		 
		 }
         }
		   System.out.println("O aeroporto com mais trafego é o " + cod + " com " + count2 + " viagens");
         
       System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
    }
 
  /**
      *Imprimir todas as	viagens	realizadas entre um	determinado período de tempo
     * @param viagemST
     * @param inicio
     * @param fim
     */
 
 public static void viagemNumaData(RedBlackBST<String, Viagem> viagemST, String inicio, String fim) {
        
     long tempoInicio = System.currentTimeMillis();  //Tempo inicial
        for (String aux : viagemST.keys()) {
            Viagem v = viagemST.get(aux);
            if ((v.getDataInit().compareTo(inicio) > -1 || v.getDataInit().compareTo(inicio) == 0) && (v.getDataDest().compareTo(fim) < 1 || v.getDataDest().compareTo(fim) == 0)) {
                System.out.println(v.toString());
            }       
        }
         System.out.println("Tempo Total: "+(System.currentTimeMillis()-tempoInicio));   //tempo no fim
    }

    public RedBlackBST<String, Aviao> getAviaoST() {
        return aviaoST;
    }

    public void setAviaoST(RedBlackBST<String, Aviao> aviaoST) {
        this.aviaoST = aviaoST;
    }

    public RedBlackBST<String, Viagem> getViagemST() {
        return viagemST;
    }

    /**
     * *Segunda parte do projeto
     *
     * @param viagemST
     */
    public void setViagemST(RedBlackBST<String, Viagem> viagemST) {
        this.viagemST = viagemST;
    }

    public SeparateChainingHashST<String, Aeroporto> getAeroportoST() {
        return aeroportoST;
    }

    public void setAeroportoST(SeparateChainingHashST<String, Aeroporto> aeroportoST) {
        this.aeroportoST = aeroportoST;
    }
    

    /**
     * Função para chamar o dijkstra para retornar os consumos, distancias e tempos
     * 
     * @param hmap
     * @param viagemST
     * @param aeroportoST
     * @param aviaoST
     * @param g
     * @param from
     * @param to
     * @param type
     * @return 
     */
    public DijkstraSP myDijkstraSP(HashMap<Integer,String> hmap, RedBlackBST<String, Viagem> viagemST, SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST,EdgeWeightedDigraph g, int from, int to, String type) {
        
        String aviaoID = null;
        int velocidadeAviao = 0;
        int altitudeAviao = 0;
        String id = "0";
        String aeroportoInit = hmap.get(from);
        String aeroportoDest = hmap.get(to);
        int passageiros = 100;
        String dataInit = "2017/05/22";
        String dataDest = "2017/05/23";
        String horaInit = "0";
        String horaDest = "0";
        int altitude = 0;
        int peso = 0;
        int baixo = 0;
        DijkstraSP sp = null ;
        int last = 0;
        int novo = 0;
        
     
        Aeroporto aInit = aeroportoST.get(hmap.get(from));  //de  hmap.get(from) passar de int para string
        
        for (String codigo: aviaoST.keys()) {   //avioes no aeroporto de partida escolhido
          Aviao a = aviaoST.get(codigo);
            if(a.getCod_aeroporto().equals(aInit.getCodigo_aeroporto())) {  // = 
                aviaoID = a.getId_aviao();  //recebe id do aviao
                System.out.println("Para o avião: " + aviaoID + ";");
                sp = new DijkstraSP(g, from, a, type);    //chamar dijkstra
                System.out.println("A rota:" + sp.pathTo(to));
                System.out.println("Total: " + sp.distTo(to) + ";");
                System.out.println("\n");
                peso = (int) sp.distTo(to);
               }

         }
        //Gravar no txt a nova viagem
        
        /*
        switch(type) {
            case DURACAO:
                Viagem vd = new Viagem(id, aeroportoInit, aeroportoDest, aviaoID, passageiros, dataInit, dataDest, horaInit, horaDest, altitude, peso);
                viagemST.put(dataInit, vd);
                saveViagem(viagemST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\viagem.txt");
                break;
            case CONSUMO:
                Viagem vc = new Viagem(id, aeroportoInit, aeroportoDest, aviaoID, passageiros, dataInit, dataDest, horaInit, horaDest, altitude, peso);
                viagemST.put(dataInit, vc);
                saveViagem(viagemST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\viagem.txt");
                break;
            default: 
                Viagem vdis = new Viagem(id, aeroportoInit, aeroportoDest, aviaoID, passageiros, dataInit, dataDest, horaInit, horaDest, altitude, peso);
                viagemST.put(dataInit, vdis);
                saveViagem(viagemST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\viagem.txt");

        
        }
        */

        return sp;
    
    }
    
        
    
    /**
     * Calculates the shortest path between 2 aiports
     * @param hmap
     * @param aeroportoST
     * @param aviaoST
     * @param g
     * @param from
     * @param to
     * @param type
     * @return
     */

    public String shortestPath(HashMap<Integer,String> hmap, SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST, EdgeWeightedDigraph g, int from, int to, String type) {
        
        
         Aviao a = null;  
         String aviaoID = null;
         int velocidadeAviao = 0;
         int altitudeAviao = 0;
     
        Aeroporto aInit = aeroportoST.get(hmap.get(from));  //de  hmap.get(from) passar de int para string
        
        for ( String codigo: aviaoST.keys()) {  //avioes no aeroporto de partida
            a = aviaoST.get(codigo);
            if(a.getCod_aeroporto().equals(aInit.getCodigo_aeroporto())) {  //=
                aviaoID = a.getId_aviao();  //id do aviao
                velocidadeAviao = Integer.parseInt(a.getVelocidade_cruzeiro()); //velocidade cruzeiro do aviao
                altitudeAviao = a.getAltitude_cruzeiro();   //altitude cruzeiro do aviao
            }
            //System.out.println("aviao id = " + aviaoID);
        }
        StringBuilder sb = new StringBuilder();
        
        DijkstraSP sp = new DijkstraSP(g, from, a, type);    //g = distancia //from destino //distancia 
        

        Aeroporto aDest = aeroportoST.get(hmap.get(to));    //para  hmap.get(to) passar de int para string

        // print shortest path
        if (sp.hasPathTo(to)) { //se tiver caminho 
            sb.append(aInit.getCodigo_aeroporto()).append(" to ").append(aDest.getCodigo_aeroporto()).append(":\n\n"); //imprimir partida / destino : 
            if (sp.hasPathTo(to)) {
                for (DirectedEdge c : sp.pathTo(to)) {  //carregar directedEdge
                    int ifrom = c.from();   //receber o from em int
                    int ito = c.to();   //receber o to em int
                    Aeroporto a1 = aeroportoST.get(hmap.get(ifrom));    //passar de int para string e carregar o codigo nos avioes
                    Aeroporto a2 = aeroportoST.get(hmap.get(ito));  //passar de int para string e carregar o codigo nos avioes
                    sb.append(a1.getCodigo_aeroporto()).append(" > ").append(a2.getCodigo_aeroporto()).append("\n");    //print dos partida e destinos que vão aparecendo 

                }
            }
            sb.append("\nDistancia total - ").append(String.format("%.2f",sp.distTo(to))).append("km ");    //print da distancia total
        }
        else {
            System.out.println("não há caminho entre os dois aeroportos");
        }
        return sb.toString();
    }
      /**
     * Calculates the time between 2 aiports
     * @param e
     * @return
     */

    public static long calcTempo(Aviao a, Ligacao e) {
        

        double flightDuration = 0.0;
        
        //System.out.println("aviao" + idA);
        //System.out.println("distance " + e.getDistance());
        //System.out.println("velocidae aviao " + velocidadeAviao);
        //System.out.println("wind " + e.getWindWeight());
        //duracao = (distancia / (velocidade cruzeiro do aviao + ( 0.3 * vento da ligacao))
        int velAviao = Integer.parseInt(a.getVelocidade_cruzeiro());
        //flightDuration = (e.getDistance() / velocidadeAviao );
        flightDuration = (e.getDistance() / velAviao );


        return Math.round(flightDuration*100)/100;
    }

    
    
     /**
     * Calculates the consumo between 2 aiports
     * @param a
     * @param e
     * @return
     */

    public static long calcConsumo(Aviao a, Ligacao e) {
        
        double flightConsumption = 0.0;
        int lphopt = 400;
        double dif = 0.0;
        double newdif = 0.0;
        double lphextra = 0.0;
        int altAviao = a.getAltitude_cruzeiro();
        
        //calculo do consumo extra
              if(e.getAltitudeWeight()>altAviao){ 
            dif = (e.getAltitudeWeight()) - altAviao; //diferença da altitude da viagem com a altitude cruzeiro
            for ( int i = 1; i<=dif; i++){  //i menor que o dif
 
                if( i%1000 == 0 ){
                    lphextra = lphextra + 200;
                }
            }
            //System.out.println("O aviao vai consumir mais " + lphextra + " litros");
        }else{
            //se a altitude da viagem for menor do que a altitude "perfeita" do aviao
            if(e.getAltitudeWeight()<altAviao){
                dif = e.getAltitudeWeight() - altAviao; //diferença da altitude da viagem com a altitude cruzeiro
                newdif = Math.abs(dif); //se o resultado der negativo, converte em positivo
                for ( int i = 0; i<=newdif; i++){  //i menor que o dif
                    
                if( i%1000 == 0 ){
                    lphextra = lphextra + 200;
                }        
            }
               // System.out.println("O aviao vai consumir mais " + lphextra + " litros");
            }
            else{
                //System.out.println("O aviao vai na altitude perfeita para um otimo consumo");
            }              
        } 
        
			double altitude = Math.abs(altAviao - e.getAltitudeWeight()); //altitude final = altitude do aviao + altitude viagem
			double altitudef = altitude / 1000;
            flightConsumption = calcTempo(a, e) * (lphopt + altitudef * lphextra + e.getWindWeight() * 20);


        return Math.round(flightConsumption*100)/100;
    }
    
    
    /**
     * Verificar se a rede é conexa
     * 
     * @param g 
     * @param hmap 
     */


    public static void verificaConexao(EdgeWeightedDigraph g, HashMap<Integer,String> hmap) {
        
        
        In in = new In("C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\links.txt");
        int from = (Integer.parseInt(in.readLine())) - 1 ;
        int flag = 0;
        String type = "";
        Aviao a = null;
        int t;
        
       //primeiro "for()" para percorrer todos os aeroportos
        for(int i = 1 ; i <= from; i++){
        DijkstraSP sp = new DijkstraSP(g, i, a, type);//chamar o diskstra
        //ver se tem caminho para todos os aeroportos
        for (t = 1; t < g.V(); t++) {
            if (!sp.hasPathTo(t)) {
                flag = 1;
            }
        }
        String cod = hmap.get(i);
        
        if (flag == 0) {
            System.out.println(cod + " > A rede é conexa");
        } else {
            System.out.println(cod + " > A rede não é conexa");
        }
        }
    }
   
    /**
     * Aeroportos com mais que cinco conexoes
     * 
     * @param g 
     * @param hmap 
     */

    public static void conexoes(EdgeWeightedDigraph g, HashMap<Integer,String> hmap){
        
      int nAeroportos = 33;
        
        for(int i = 1; i< nAeroportos; i++){
            if(g.outdegree(i) >= 5)
           System.out.println("O Aeroporto " + hmap.get(i) + " tem " + g.outdegree(i) + " ligações!"); 
        }
        
        
    }
   
}

   
 

 


 