package com.example.demo.arbol;

public class Carta {
    private String Carta;
    private int Valor;

    public Carta(String carta){
        Carta = carta;
        AsignarValor();
    }

    public void AsignarValor(){
        if(Carta.length() == 3){
            Valor = 10;
        }else{
            if(Character.isDigit(Carta.charAt(0))) 
                Valor = Character.getNumericValue(Carta.charAt(0));
            else{
                switch (Carta.charAt(0)) {
                    case 'J':
                        Valor = 11;
                        break;
                    
                    case 'Q':
                        Valor = 12;
                        break;
                    
                    case 'K':
                        Valor = 13;
                        break;
                
                    case 'A':
                        Valor = 1;
                        break;
                
                    default:
                        break;
                }
            }

        }
    }

    public String getCarta(){
        return Carta;
    }
    public void setCarta(String carta){
        Carta = carta;
    }
    public int getValor() {
        return Valor;
    }
    public void setValor(int valor) {
        Valor = valor;
    }
    
   

}
