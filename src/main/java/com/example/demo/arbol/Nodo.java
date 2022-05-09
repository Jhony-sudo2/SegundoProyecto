package com.example.demo.arbol;

public class Nodo {
    private Nodo Izquierda;
    private Nodo Derecha;
    private Carta carta;
    private int Dato;
    private int Altura;
    private int Nivel = 1;
    
    public Nodo(int dato){
        Dato = dato;
        Izquierda = null;
        Derecha = null;
        Altura = 0;
    }
    public Nodo(Carta c,int n){
        carta = c;
        Izquierda = null;
        Derecha = null;
        Dato = c.getValor();
        Nivel = n;
    }
    public void setNivel(int n){
        Nivel = n;
    }
    public int getNivel(){
        return Nivel;
    }
    public Carta getCarta(){
        return carta;
    }

    public Nodo getIzquierda() {
        return Izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        Izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return Derecha;
    }

    public void setDerecha(Nodo derecha) {
        Derecha = derecha;
    }

    public int getDato() {
        return Dato;
    }

    public void setDato(int dato) {
        Dato = dato;
    }

    public int getAltura() {
        return Altura;
    }

    public void setAltura(int altura) {
        Altura = altura;
    }

    public void CalAltura(){
        if(Izquierda != null & Derecha != null)
            Altura = (Izquierda.getAltura()>Derecha.getAltura())?Izquierda.getAltura()+1:Derecha.getAltura()+1;
        else if (Izquierda != null)
            Altura = Izquierda.getAltura()+1;
        else if(Derecha !=null)
            Altura = Derecha.getAltura()+1;
        else Altura = 0;
        
    }

    public int getFactorEquilibrio(){
        if(Derecha != null & Izquierda != null)
        return (Derecha.getAltura()+1) - (Izquierda.getAltura()+1);
        else if(Derecha == null & Izquierda == null) return 0;
        else return (Derecha == null)?-(Izquierda.getAltura()+1):Derecha.getAltura()+1;
    }

    @Override
    public String toString(){
        return "Valor: "+ Dato + " Carta: " + carta.getCarta() + " Nivel: " + Nivel;
    }
    

    
}
