package edu.ufp.inf.aedlp.project;



public class Viagem {

  public String id;

  public String aeroportoInit;
  
  public String aeroportoDest;
  
  public String aviao;
  
  public int passageiros;
  
  public String dataInit;
  
  public String dataDest;
  
  public String horaInit;
  
  public String horaDest;
  
  public int altitude;
  
  public int distancia;

    public Viagem(String id, String aeroportoInit, String aeroportoDest, String aviao, int passageiros, String dataInit, String dataDest, String horaInit, String horaDest, int altitude, int distancia) {
        this.id = id;
        this.aeroportoInit = aeroportoInit;
        this.aeroportoDest = aeroportoDest;
        this.aviao = aviao;
        this.passageiros = passageiros;
        this.dataInit = dataInit;
        this.dataDest = dataDest;
        this.horaInit = horaInit;
        this.horaDest = horaDest;
        this.altitude = altitude;
        this.distancia = distancia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAeroportoInit() {
        return aeroportoInit;
    }

    public void setAeroportoInit(String aeroportoInit) {
        this.aeroportoInit = aeroportoInit;
    }

    public String getAeroportoDest() {
        return aeroportoDest;
    }

    public void setAeroportoDest(String aeroportoDest) {
        this.aeroportoDest = aeroportoDest;
    }

    public String getAviao() {
        return aviao;
    }

    public void setAviao(String aviao) {
        this.aviao = aviao;
    }
    
    public int getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(int passageiros) {
        this.passageiros = passageiros;
    }

    public String getDataInit() {
        return dataInit;
    }

    public void setDataInit(String dataInit) {
        this.dataInit = dataInit;
    }

    public String getDataDest() {
        return dataDest;
    }

    public void setDataDest(String dataDest) {
        this.dataDest = dataDest;
    }
    
    

    public String getHoraInit() {
        return horaInit;
    }

    public void setHoraInit(String horaInit) {
        this.horaInit = horaInit;
    }

    public String getHoraDest() {
        return horaDest;
    }

    public void setHoraDest(String horaDest) {
        this.horaDest = horaDest;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Viagem{" + "id=" + id + ", aeroportoInit=" + aeroportoInit + ", aeroportoDest=" + aeroportoDest + ", aviao=" + aviao + ", passageiros=" + passageiros + ", dataInit=" + dataInit + ", dataDest=" + dataDest +", horaInit=" + horaInit + ", horaDest=" + horaDest + ", altitude=" + altitude + ", distancia=" + distancia + '}';
    }
    
    


    

    



}