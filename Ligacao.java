package edu.ufp.inf.aedlp.project;

import edu.princeton.cs.algs4.DirectedEdge;


public class Ligacao extends DirectedEdge{

  private double windWeight;    //vento
  private double altitudeWeight;    //altitude
  
   public Ligacao(int a1, int a2, double distance, double wind, double altitude) {
        super(a1, a2, distance);
        this.windWeight = wind;
        this.altitudeWeight = altitude;
    }
 
  
    public double getDistance() {
        return super.weight();
    }

    public double getWindWeight() {
        return windWeight;
    }

    public void setWindWeight(double windWeight) {
        this.windWeight = windWeight;
    }

    public double getAltitudeWeight() {
        return altitudeWeight;
    }

    public void setAltitudeWeight(double altitudeWeight) {
        this.altitudeWeight = altitudeWeight;
    }

    @Override
    public String toString() {
        return super.from() + "->" + super.to() + 
                " - {Distance: " + super.weight() + " , Vento: " + this.windWeight + " , Altitude: " + this.altitudeWeight +"}";
    }

    @Override
    public double weight() {
        switch(Rede.custoSelecionado){
            case Rede.DURACAO: return this.windWeight;
            case Rede.CONSUMO: return this.altitudeWeight;
            default: return super.weight();
        }
    }
    
    

}