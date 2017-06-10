/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aedlp.project;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author ZeeCunha
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws edu.ufp.inf.aedlp.project.ProjectException
     */
    public static void main(String[] args) throws ProjectException, IOException {

        /*
         * St's Principais 
         */
        // red-black trees
        RedBlackBST<String, Aviao> aviaoST = new RedBlackBST<>();
        RedBlackBST<String, Viagem> viagemST = new RedBlackBST<>();

        //Separate Chaining Hash 
        SeparateChainingHashST<String, Aeroporto> aeroportoST = new SeparateChainingHashST<>();
        
        //Hash map
        HashMap<Integer,String> hmap = new HashMap<>();
        HashMap<String, Integer> hmapInvert = new HashMap<>();

        /*
         *  Inicialização das St's
         */
        loadFromFileAeroporto(aeroportoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airports.txt");
        loadFromFileAeroportoBin(aeroportoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airports.bin");
        loadFromFileAviao(aviaoST, aeroportoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airplanes.txt");
        loadFromFileAviaoBin(aviaoST, aeroportoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airports.bin");
        loadFromFileViagem(viagemST, aeroportoST, aviaoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\viagem.txt");
        loadFromFileViagemBin(viagemST, aeroportoST, aviaoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\viagem.bin");
        loadFromFileLinksTradutor(hmap, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\links(tradutor).txt");
        loadFromFileLinksTradutorInverted(hmapInvert, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\links(tradutor).txt");

        /* 
         *  Menu com as respectivas funcionalidades
         *  Nota: Nem todas as funcoes sao chamadas no menu;
         */

      

        System.out.println("-------- Projeto LP & AED --------\n");
        System.out.println("Escolha uma das seguintes opcoes:\n 1-Imprimir avioes\n 2-Imprimir aeroportos\n3-Imprimir Viagens\n "
                + "4-Adicionar novo aeroporto|gravar txt\n "
                + "5-Remover aeroporto|gravar txt\n 6-Adicionar novo aviao|gravar txt\n 7-Remover aviao|gravar txt\n"
                + "8-Imprimir info Aeroporto dado Codigo\n 9-Imprimir info aviao dado ID\n 10-Imprimir avioes num dado aeroporto\n"
                + "11- Aeroportos num dado pais\n 12- Aeroportos num dado continente\n 13- Criar uma nova viagem|gravar txt\n"
                + "14- Apagar viagem|gravar txt\n15- Ver viagens com origem/destino num dado aeroporto"
                + "\n16-Imprimir viagens realizadas por um aviao\n17-Update aeroporto | gravar txt"
                + "\n18-Update aviao | gravar txt\n19-Imprimir id do voo com + passageiros"
                + "\n20-Custo de uma viagem\n21-aeroporto com maior trafego\n22-Viagens numa data\n23-Novo grafo sem um aeroporto escolhido\n99- Testes aed");
                    String opcao;
                    Scanner sca = new Scanner(System.in);
                    opcao = sca.nextLine();
                    switch (opcao) {
                        case "1": {
                             printAvioes(aviaoST);
                            return;
                        }
                        case "2": {
                             printAeroportos(aeroportoST);
                            return;
                        }
                        case "3": {
                             printViagem(viagemST);
                            return;
                        }
                        case "4": {
                            Rede.addAeroporto(aeroportoST, hmap);
                            //Rede.saveAeroporto(aeroportoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airports.txt");                            
                            Rede.saveAeroportoBin(aeroportoST);
                            //Rede.saveLinksTranslation(hmapInvert, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\links(tradutor).txt");
                            return;
                        }
                        case "5": {
                            Rede.apagaAeroporto(aeroportoST, aviaoST, viagemST);
                            Rede.saveAeroporto(aeroportoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airports.txt");                            
                            return;
                        }
                        case "6": {
                            Rede.addAviao(aviaoST, aeroportoST);
                            Rede.saveAviao(aviaoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airplanes.txt");                            
                            Rede.saveAviaoBin(aviaoST);
                            return;
                        }
                        case "7": {
                            Rede.apagaAviao(aviaoST);
                            Rede.saveAviao(aviaoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airplanes.txt");                            
                            return;
                        }
                         case "8": {
                            Rede.infoAeroporto(aeroportoST,aviaoST, viagemST, "OPO");
                            return;
                        }       
                         case "9": {
                            Rede.infoAviao(aviaoST, "2");
                            return;
                        }
                         case "10": {
                            Rede.avioesIntoAeroporto(aviaoST, "LGW");
                            return;
                        }
                          case "11": {
                            Rede.pesquisaAeroportoPais(aeroportoST, "Portugal");
                            return;
                        }
                          case "12": {
                            Rede.pesquisaAeroportoContinente(aeroportoST, "Europe");
                            return;
                        }
                           case "13": {
                            Rede.addViagem(viagemST, aeroportoST, aviaoST);
                            Rede.saveViagem(viagemST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\viagem.txt");
                            Rede.saveViagemBin(viagemST);
                            return;
                        }
                           case "14": {
                            Rede.apagaViagem(viagemST);
                            Rede.saveViagem(viagemST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\viagem.txt");
                            return;
                        }
                           case "15": {
                            Rede.viagemIntoAeroporto(viagemST, "OPO");
                            return;
                        }
                           case "16": {
                            Rede.pesquisaViagemAviao(viagemST, "10");
                            return;
                        }
                           case "17": {
                            Rede.updateAeroporto(aeroportoST);
                            Rede.saveAeroporto(aeroportoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airports.txt");
                            return;
                        }
                           case "18": {
                            Rede.updateAviao(aviaoST, aeroportoST);
                            Rede.saveAviao(aviaoST, "C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\airplanes.txt");
                            return;
                        }
                           case "19": {
                            Rede.vooMaisPassageiros(viagemST);
                            return;
                        }
                           case "20": {
                            Rede.calcCusto(viagemST, aviaoST);
                            return;
                        }
                           case "21": {
                            Rede.aeroportoMaiorTrafego(viagemST, aeroportoST);
                            return;
                        }
                           case"22":{
                           Rede.viagemNumaData(viagemST, "2017/01/01","2017/03/02");
                           return;
                           }
                              
                           case "99": {
                           teste(viagemST, aeroportoST, aviaoST);
                            return;
                        }
                           case "23": {
                            newGraph(viagemST, hmap, aeroportoST,aviaoST, "LIS");
                           return;
                           }
                           case "24": {
                            newGraphContinente(viagemST, hmap, aeroportoST,aviaoST, "North America");
                           return;
                           }
                    }
       
        Rede r = null;
        In in = new In("C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\links.txt");
        int V = Integer.parseInt(in.readLine());
        //System.out.println("V = " + V);
        r = new Rede(V);
        int E = Integer.parseInt(in.readLine());
        //System.out.println("E = " + E); 
        String line = in.readLine();
        while (line != null) {

            String[] texto = line.split(";");
            int v = Integer.parseInt(texto[0]); //posicao da partida no txt
            int w = Integer.parseInt(texto[1]); //posicao da chegada no txt
            double c1 = Double.parseDouble(texto[2]); //posicao do distancia no txt
            double c2 = Double.parseDouble(texto[3]); //posicao do vento no txt
            double c3 = Double.parseDouble(texto[4]); //posicao do altitude no txt

            r.addEdge(new Ligacao(v, w, c1, c2, c3));

            //System.out.println("carregar ligações: " + hmap.get(v) + " > " + hmap.get(w) + " D: " + c1 + " V: " + c2 + " A: " + c3);
            line = in.readLine();
        }
        //System.out.println(r.toString());
        in.close();


        //Nova ligacao *

        //r.addEdge(new Ligacao(1,27,250,-5.0,21000)); //OPO > GIG com menor distancia (teste)

         //Verificar se a rede é conexa

        //Rede.verificaConexao(r, hmap);    

        int idPartida = 1;  //opo
        int idDestino = 27; //scl
        

         //Distancia

        //System.out.println(r.myDijkstraSP(hmap, viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "DISTANCIA").distTo(idDestino));

         //Tempo
        //System.out.println(r.myDijkstraSP(hmap,viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "DURACAO").distTo(idDestino));

        //Consumo

        //System.out.println(+ r.myDijkstraSP(hmap,viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "CONSUMO").distTo(idDestino));

        //ShortestPath 
        
        //System.out.println(r.shortestPath(hmap, aeroportoST, aviaoST, r, idPartida, idDestino, "DISTANCIA"));    //OPO > SCL
        
        //ver aeroportos com mais que 5 ligações
        
        Rede.conexoes(r, hmap);
                    
                    
    }

    /*
    *   Função para criar um novo graph sem o determinado aeroporto
    *
    */
    
    
        public static void newGraph(RedBlackBST<String, Viagem> viagemST, HashMap<Integer,String> hmap, SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST, String codAeroporto){
         
        String init = "";
        String dest = "";
           
        Rede r = null;
        In in1 = new In("C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\links.txt");
        int V = Integer.parseInt(in1.readLine());
        //System.out.println("V = " + V);
        r = new Rede(V);
        int E = Integer.parseInt(in1.readLine());
        //System.out.println("E = " + E); 
        String line1 = in1.readLine();
        while (line1 != null) {

            String[] texto = line1.split(";");
            int v = Integer.parseInt(texto[0]); //posicao do id no txt
            int w = Integer.parseInt(texto[1]); //posicao do modelo no txt
            init = hmap.get(v);
            dest = hmap.get(w);
            double c1 = Double.parseDouble(texto[2]); //posicao do distancia no txt
            double c2 = Double.parseDouble(texto[3]); //posicao do vento no txt
            double c3 = Double.parseDouble(texto[4]); //posicao do altitude no txt
            
            if(!init.equals(codAeroporto) && !dest.equals(codAeroporto)){
                r.addEdge(new Ligacao(v, w, c1, c2, c3));
                System.out.println(w + " " + v + " " + c1 + " " + c2 + " " + c3);
            }
            line1 = in1.readLine();
        }
             

        //System.out.println(r.toString());
        in1.close();
        
        //Nova ligacao *

        //r.addEdge(new Ligacao(1,27,250,-5.0,21000)); //OPO > GIG com menor distancia (teste)

         //Verificar se a rede é conexa

        //Rede.verificaConexao(r, hmap);    

        int idPartida = 1;  //opo
        int idDestino = 30; //scl
        

         //Distancia

        //System.out.println(r.myDijkstraSP(hmap, viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "DISTANCIA").distTo(idDestino));

         //Tempo
        //System.out.println(r.myDijkstraSP(hmap,viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "DURACAO").distTo(idDestino));

        //Consumo

        //System.out.println(+ r.myDijkstraSP(hmap,viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "CONSUMO").distTo(idDestino));

        //ShortestPath 
        
       // System.out.println(r.shortestPath(hmap, aeroportoST, aviaoST, r, idPartida, idDestino, "DISTANCIA"));    //OPO > SCL
        
        //ver aeroportos com mais que duas ligações
        
        //Rede.conexoes(r, hmap);
            
        }
        
        /*
    *   Função para criar um novo graph sem o determinado aeroporto
    *
    */
    
    
        public static void newGraphContinente(RedBlackBST<String, Viagem> viagemST, HashMap<Integer,String> hmap, SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST, String continente){
         
        String init = "";
        String dest = "";
        String contInit = "";
        String contDest = "";
           
        Rede r = null;
        In in1 = new In("C:\\Users\\ZeeCunha\\Desktop\\UFP\\4final\\2SEMESTRE\\AEDLP\\data\\links.txt");
        int V = Integer.parseInt(in1.readLine());
        //System.out.println("V = " + V);
        r = new Rede(V);
        int E = Integer.parseInt(in1.readLine());
        //System.out.println("E = " + E); 
        String line1 = in1.readLine();
        while (line1 != null) {

            String[] texto = line1.split(";");
            int v = Integer.parseInt(texto[0]); //posicao do id no txt
            int w = Integer.parseInt(texto[1]); //posicao do modelo no txt
            init = hmap.get(v);
            dest = hmap.get(w);
            double c1 = Double.parseDouble(texto[2]); //posicao do distancia no txt
            double c2 = Double.parseDouble(texto[3]); //posicao do vento no txt
            double c3 = Double.parseDouble(texto[4]); //posicao do altitude no txt
            
            for (String codigo: aeroportoST.keys()) {
                Aeroporto a = aeroportoST.get(codigo);
                if(a.getCodigo_aeroporto().equals(init) || a.getCodigo_aeroporto().equals(dest)) {
                    contInit = a.getContinente();
                    System.out.println("init" + contInit);
                    contDest = a.getContinente();
                    System.out.println("dest" + contDest);
                
                    if(!contInit.equals(continente) && !contDest.equals(continente)){
                        r.addEdge(new Ligacao(v, w, c1, c2, c3));
                        System.out.println(w + " " + v + " " + c1 + " " + c2 + " " + c3);
                }
                }
               
            }
            line1 = in1.readLine();
        
             

        //System.out.println(r.toString());
        in1.close();
        
        //Nova ligacao *

        //r.addEdge(new Ligacao(1,27,250,-5.0,21000)); //OPO > GIG com menor distancia (teste)

         //Verificar se a rede é conexa

        //Rede.verificaConexao(r, hmap);    

        int idPartida = 1;  //opo
        int idDestino = 30; //scl
        

         //Distancia

       // System.out.println(r.myDijkstraSP(hmap, viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "DISTANCIA").distTo(idDestino));

         //Tempo
        //System.out.println(r.myDijkstraSP(hmap,viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "DURACAO").distTo(idDestino));

        //Consumo

        //System.out.println(+ r.myDijkstraSP(hmap,viagemST, aeroportoST, aviaoST, r, idPartida, idDestino, "CONSUMO").distTo(idDestino));

        //ShortestPath 
        
        //System.out.println(r.shortestPath(hmap, aeroportoST, aviaoST, r, idPartida, idDestino, "DISTANCIA"));    //OPO > SCL
        
        //ver aeroportos com mais que duas ligações
        
        //Rede.conexoes(r, hmap);
            
        }
        }


    /*
     *   Carregar ficheiros
     */
    /**
     *
     * @param aviaoST
     * @param aeroportoST
     * @param path
     */
    public static void loadFromFileAviao(RedBlackBST<String, Aviao> aviaoST, SeparateChainingHashST<String, Aeroporto> aeroportoST, String path) {
        In in = new In(path);

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String id_aviao = texto[0]; //posicao do id no txt
            String modelo = texto[1]; //posicao do modelo no txt
            String nome = texto[2]; //posicao do nome no txt
            String companhia_aerea = texto[3]; //posicao do companhia no txt
            String velocidade_cruzeiro = texto[4]; //posicao do velocidade no txt
            String altitude_cruzeiro = texto[5]; //posicao do altitude no txt
            String distancia_maxima = texto[6]; //posicao do distância no txt
            String cod_aeroporto = texto[7]; //posicao do cod_aeroporto no txt
            int capPassageiros = Integer.parseInt(texto[8]); //posicao do passageiros no txt
            int capacidade_de_deposito = Integer.parseInt(texto[9]); //posicao do deposito no txt

            Aviao a = new Aviao(id_aviao, modelo, nome, companhia_aerea, velocidade_cruzeiro, Integer.parseInt(altitude_cruzeiro), distancia_maxima, cod_aeroporto, capPassageiros, capacidade_de_deposito);
            aviaoST.put(id_aviao, a);
            Aeroporto airport = aeroportoST.get(cod_aeroporto);
            //System.out.println(airport.getAeroportoAviaoST().size());
            aeroportoST.get(cod_aeroporto).getAeroportoAviaoST().put(id_aviao, a);

        }
    }
    
    /**
     *
     * @param aviaoST
     * @param aeroportoST
     * @param path
     */
    public static void loadFromFileAviaoBin(RedBlackBST<String, Aviao> aviaoST, SeparateChainingHashST<String, Aeroporto> aeroportoST, String path) {
        
        BinaryIn bin = new BinaryIn(path);
            while (!bin.isEmpty()) {
            String[] texto = bin.readString().split(";");
            String id_aviao = texto[0]; //posicao do id no txt
            String modelo = texto[1]; //posicao do modelo no txt
            String nome = texto[2]; //posicao do nome no txt
            String companhia_aerea = texto[3]; //posicao do companhia no txt
            String velocidade_cruzeiro = texto[4]; //posicao do velocidade no txt
            String altitude_cruzeiro = texto[5]; //posicao do altitude no txt
            String distancia_maxima = texto[6]; //posicao do distância no txt
            String cod_aeroporto = texto[7]; //posicao do cod_aeroporto no txt
            int capPassageiros = Integer.parseInt(texto[8]); //posicao do passageiros no txt
            int capacidade_de_deposito = Integer.parseInt(texto[9]); //posicao do deposito no txt

            Aviao a = new Aviao(id_aviao, modelo, nome, companhia_aerea, velocidade_cruzeiro, Integer.parseInt(altitude_cruzeiro), distancia_maxima, cod_aeroporto, capPassageiros, capacidade_de_deposito);
            aviaoST.put(id_aviao, a);
            Aeroporto airport = aeroportoST.get(cod_aeroporto);
            //System.out.println(airport.getAeroportoAviaoST().size());
            aeroportoST.get(cod_aeroporto).getAeroportoAviaoST().put(id_aviao, a);

            }

    }

    /**
     *
     * @param aeroportoST
     * @param path
     */
    public static void loadFromFileAeroporto(SeparateChainingHashST<String, Aeroporto> aeroportoST, String path) {
        In in = new In(path);

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String nome_aeroporto = texto[0]; //posicao do nome no txt
            String codigo_aeroporto = texto[1]; //posicao do codigo no txt
            String cidade = texto[2]; //posicao do cidade no txt
            String pais = texto[3]; //posicao do pais no txt
            String continente = texto[4]; //posicao do continente no txt
            String classificacao = texto[5]; //posicao do classificacao no txt

            Aeroporto a = new Aeroporto(nome_aeroporto, codigo_aeroporto, cidade, pais, continente, classificacao);
            aeroportoST.put(codigo_aeroporto, a);

        }
    }

    /**
     *
     * @param aviaoST
     * @param aeroportoST
     * @param path
     */
    public static void loadFromFileAeroportoBin(SeparateChainingHashST<String, Aeroporto> aeroportoST, String path) {
        
        BinaryIn bin = new BinaryIn(path);
        
            while (!bin.isEmpty()) {
            String[] texto = bin.readString().split(";");
            String nome_aeroporto = texto[0]; //posicao do nome no txt
            String codigo_aeroporto = texto[1]; //posicao do codigo no txt
            String cidade = texto[2]; //posicao do cidade no txt
            String pais = texto[3]; //posicao do pais no txt
            String continente = texto[4]; //posicao do continente no txt
            String classificacao = texto[5]; //posicao do classificacao no txt

            Aeroporto a = new Aeroporto(nome_aeroporto, codigo_aeroporto, cidade, pais, continente, classificacao);
            aeroportoST.put(codigo_aeroporto, a);

        }

    }

    
    /**
     *
     * @param viagemST
     * @param aeroportoST
     * @param aviaoST
     * @param path
     */
    public static void loadFromFileViagem(RedBlackBST<String, Viagem> viagemST, SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST, String path) {
        In in = new In(path);

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String id = texto[0]; //posicao do id no txt
            String aeroportoInit = texto[1]; //posicao do aeroporto inicio no txt
            String aeroportoDest = texto[2]; //posicao do aeroporto destino no txt
            String aviao = texto[3]; //posicao do aviao no txt
            String passageiros = texto[4]; //posicao dos passageiros
            String dataInit = texto[5]; //posicao da hora de inicio de viagem
            String dataDest = texto[6]; //posicao da hora de fim de viagem
            String horaInit = texto[7]; //posicao da hora de inicio de viagem
            String horaDest = texto[8]; //posicao da hora de fim de viagem
            String altitude = texto[9]; //posicao da altitude de viagem 
            String distancia = texto[10]; //posicao da distancia de viagem

            Viagem v;
            v = new Viagem(id, aeroportoInit, aeroportoDest, aviao, Integer.parseInt(passageiros), dataInit, dataDest, horaInit, horaDest, Integer.parseInt(altitude), Integer.parseInt(distancia));
            viagemST.put(id, v);

            aeroportoST.get(aeroportoInit).aeroportoViagemST.put(id, v);
            aeroportoST.get(aeroportoDest).aeroportoViagemST.put(id, v);
            aviaoST.get(aviao).aviaoViagemST.put(id, v);

        }
    }
    
    /**
     *
     * @param viagemST
     * @param aeroportoST
     * @param aviaoST
     * @param path
     */
    public static void loadFromFileViagemBin(RedBlackBST<String, Viagem> viagemST, SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST, String path) {
        BinaryIn bin = new BinaryIn(path);
        
            while (!bin.isEmpty()) {
            String[] texto = bin.readString().split(";");
            String id = texto[0]; //posicao do id no txt
            String aeroportoInit = texto[1]; //posicao do aeroporto inicio no txt
            String aeroportoDest = texto[2]; //posicao do aeroporto destino no txt
            String aviao = texto[3]; //posicao do aviao no txt
            String passageiros = texto[4]; //posicao dos passageiros
            String dataInit = texto[5]; //posicao da hora de inicio de viagem
            String dataDest = texto[6]; //posicao da hora de fim de viagem
            String horaInit = texto[7]; //posicao da hora de inicio de viagem
            String horaDest = texto[8]; //posicao da hora de fim de viagem
            String altitude = texto[9]; //posicao da altitude de viagem 
            String distancia = texto[10]; //posicao da distancia de viagem

            Viagem v;
            v = new Viagem(id, aeroportoInit, aeroportoDest, aviao, Integer.parseInt(passageiros), dataInit, dataDest, horaInit, horaDest, Integer.parseInt(altitude), Integer.parseInt(distancia));
            viagemST.put(id, v);

            aeroportoST.get(aeroportoInit).aeroportoViagemST.put(id, v);
            aeroportoST.get(aeroportoDest).aeroportoViagemST.put(id, v);
            //aviaoST.get(aviao).aviaoViagemST.put(id, v);

        }
    }
    /*
     * @param hmap
     * @param path
    */
    
    public static void loadFromFileLinksTradutor(HashMap<Integer,String> hmap, String path)
    {
        In in = new In(path);
        String line = in.readLine();
        while (line != null) {

            String[] texto = line.split(";");
            String code = texto[0]; //posicao do codigo no txt
            int id = Integer.parseInt(texto[1]); //posicao do id no txt
            hmap.put(id, code);
            line = in.readLine();
        }
        in.close();
    }
    
    /*
     * @param hmap
     * @param path
    */
    
    public static void loadFromFileLinksTradutorInverted(HashMap<String, Integer> hmapInvert, String path)
    {
        In in = new In(path);
        String line = in.readLine();
        while (line != null) {

            String[] texto = line.split(";");
            String code = texto[0]; //posicao do codigo no txt
            int id = Integer.parseInt(texto[1]); //posicao do id no txt
            hmapInvert.put(code, id);
            line = in.readLine();
        }
        in.close();
    }

    /*
     *  Testes e Listagens
     */
    /**
     * Imprimir avioes
     *
     * @param aviaoST
     */
    public static void printAvioes(RedBlackBST<String, Aviao> aviaoST) {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        for (String aux : aviaoST.keys()) {
            Aviao a = aviaoST.get(aux);
            System.out.println(a);
        }
        System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));   //tempo no fim

    }

    /**
     * Imprimir aeroportos
     *
     * @param aeroportoST
     */
    public static void printAeroportos(SeparateChainingHashST<String, Aeroporto> aeroportoST) {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        for (String aux : aeroportoST.keys()) {
            Aeroporto a = aeroportoST.get(aux);
            System.out.println(a);
        }
        System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));   //tempo no fim

    }

    /**
     * Imprimir viagens
     *
     * @param viagemST
     */
    public static void printViagem(RedBlackBST<String, Viagem> viagemST) {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        for (String aux : viagemST.keys()) {
            Viagem v = viagemST.get(aux);
            System.out.println(v);
        }
        System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));   //tempo no fim
    }

    /**
     * Função de testes de aed
     *
     * @param viagemST
     * @param aeroportoST
     * @param aviaoST
     */
    public static void teste(RedBlackBST<String, Viagem> viagemST, SeparateChainingHashST<String, Aeroporto> aeroportoST, RedBlackBST<String, Aviao> aviaoST) {
        long tempoInicio = System.currentTimeMillis();  //Tempo inicial

        System.out.println("Imprimir todos os avioes:\n");
        printAvioes(aviaoST);
        System.out.println("Imprimir todos os aeroportos:\n");
        printAeroportos(aeroportoST);
        System.out.println("Imprimir todas as viagens:\n");
        printViagem(viagemST);
        System.out.println("Imprimir avioes dado um aeroporto:\n");
        Rede.avioesIntoAeroporto(aviaoST, "LGW");
        System.out.println("Imprimir informação dado um aeroporto:\n");
        Rede.infoAeroporto(aeroportoST, aviaoST, viagemST, "OPO");
        System.out.println("Imprimir informação dado um aviao:\n");
        Rede.infoAviao(aviaoST, "3");
        System.out.println("Imprimir aeroportos de um continente:\n");
        Rede.pesquisaAeroportoContinente(aeroportoST, "Europe");
        System.out.println("Imprimir aeroportos dado um pais:\n");
        Rede.pesquisaAeroportoPais(aeroportoST, "Portugal");
        System.out.println("Imprimir todas as viagens de um aviao:\n");
        Rede.pesquisaViagemAviao(viagemST, "10");
        System.out.println("Imprimir todas as viagens de um aeroporto:\n");
        Rede.viagemIntoAeroporto(viagemST, "OPO");
        System.out.println("Imprimir o voo com + passageiros:\n");
        Rede.vooMaisPassageiros(viagemST);
        System.out.println("Imprimir aeroporto com + trafego:\n");
        Rede.aeroportoMaiorTrafego(viagemST, aeroportoST);
        System.out.println("Imprimir viagens dentro de um certo tempo:\n");
        Rede.viagemNumaData(viagemST, "2017/03/01", "2017/03/02");
        System.out.println("Tempo Total do teste: " + (System.currentTimeMillis() - tempoInicio));   //tempo no fim

    }

}
