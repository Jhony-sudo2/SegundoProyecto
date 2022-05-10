package com.example.demo.arbol;
import java.util.ArrayList;

import com.example.demo.imagen.Imagen;

public class Arbol {
    private Nodo Raiz;
    private ArrayList<Nodo> Recorrido = new ArrayList<Nodo>();
    private Imagen pImagen = new Imagen();
    private int Nivel = 0;
    public static int Estado = 1;
    public Arbol(){
        Raiz = null;
    }
    
    public Nodo getRaiz(){
        return Raiz;
    }

    public void Agregar(Carta Dato){

        if(Raiz == null){
            Nivel++;
            Raiz = new Nodo(Dato,Nivel);
        }
        else Agregar(Raiz,Dato);
    }

    public Nodo Agregar(Nodo T,Carta Dato){
        if(T == null){
            return new Nodo(Dato,Nivel);
        }
        else if(Dato.getValor()<T.getDato() || Dato.getValor() == T.getDato()){
            Nivel++;
            T.setIzquierda(Agregar(T.getIzquierda(), Dato));
            Nivel--;
            T.CalAltura();
            if(Math.abs(Comprobar(T)) >= 2){
                if(T == Raiz)
                Raiz = Rotar(Comprobar(Raiz), Raiz);
                else
                T = Rotar(Comprobar(T), T);
            }
            return T;
        }
        else {
            Nivel++;
            T.setDerecha(Agregar(T.getDerecha(), Dato));
            Nivel--;
            T.CalAltura();
            if(Math.abs(Comprobar(T)) >= 2){
                if(T == Raiz)
                Raiz = Rotar(Comprobar(Raiz), Raiz);
                else
                T = Rotar(Comprobar(T), T);
            }
            return T;
        }
    }

    public Nodo ELiminar(Nodo t,Carta Dato){
        if(t == null){
            System.out.println("Numero no encontrado ");
            Estado = 2;
            return null;
        }else{
            
            if(t.getDato() == Dato.getValor() & t.getCarta().getCarta().equals(Dato.getCarta())){
                if(t.getDerecha() == null & t.getIzquierda() == null){
                    t = null;
                    Estado = 1;
                }
                else if(t.getDerecha() != null | t.getIzquierda()!=null){
                 Estado = 3;
                 System.out.println("EL nodod tiene hijos");   
                }
                return t;
            
            } else if(Dato.getValor()>t.getDato()){
                t.setDerecha(ELiminar(t.getDerecha(), Dato));
                t.CalAltura();
                if(Math.abs(Comprobar(t)) >= 2){
                    if(t == Raiz)
                    Raiz = Rotar(Comprobar(Raiz), Raiz);
                    else
                    t = Rotar(Comprobar(t), t);
                }
                return t;
            }
            else {

                t.setIzquierda(ELiminar(t.getIzquierda(), Dato));
                t.CalAltura();
                if(Math.abs(Comprobar(t)) >= 2){
                    if(t == Raiz)
                    Raiz = Rotar(Comprobar(Raiz), Raiz);
                    else
                    t = Rotar(Comprobar(t), t);
                }
                return t;
            }
        }

    }
    public Nodo Mayor(Nodo n){
        if(n.getDerecha() == null)
            return n;
        else {
            return Mayor(n.getDerecha());
        }
    }
    public void Borrar(Nodo x,Nodo t){
        //t == raiz
        //x nodo
        boolean tmp = true;
        do {
            if(t.getDerecha() == x ) {
                t.setDerecha(null);
                tmp = false;
            }else if(t.getIzquierda() == x & x.getIzquierda() == null){
                t.setIzquierda(null);
                tmp = false;
            }else if(t.getIzquierda() ==x & x.getIzquierda() != null){
                t.setIzquierda(x.getIzquierda());
                tmp = false;
            }else if(x.getDato() > t.getDato()) t = t.getDerecha();
            else if(x.getDato() < t.getDato()) t = t.getIzquierda();
        } while (tmp);
    }
    public int Comprobar(Nodo n){
        int Diferencia;
        if(n.getDerecha() == null | n.getIzquierda()==null)
            Diferencia = n.getFactorEquilibrio();
        else   Diferencia =n.getFactorEquilibrio();

        return Diferencia;
    }

    public Nodo Rotar(int Diferencia, Nodo n){
        Nodo nodo = null;
        switch (Diferencia) {
            case -2:
                    if(n.getIzquierda() != null && n.getIzquierda().getFactorEquilibrio() !=1)
                         nodo = RSD(n);
                    else nodo = RDD(n); 
                break;

            case 2:
                    if(n.getDerecha() != null && n.getDerecha().getFactorEquilibrio() != -1)
                        nodo = RSI(n);
                    else nodo = RDI(n);
                break;
            default:
                break;
        }
        return nodo;
    }

    public Nodo RSD(Nodo n){
        System.out.println("Rotacion Simple Derecha ");
        Nodo n1 = n.getIzquierda();
        n.setNivel(n.getNivel()+1);
        n1.getIzquierda().setNivel(n.getNivel());
        n1.setNivel(n.getNivel()-1);
        n.setIzquierda(n1.getDerecha());
        n1.setDerecha(n);
        n.CalAltura();
        n1.CalAltura();
        return n1;
        
    }
    public Nodo RDD(Nodo n){
        System.out.println("Rotacion Doble Derecha");
        Nodo n2 = n.getIzquierda();
        n.setIzquierda(RSI(n2));
        return RSD(n);
    }
    public Nodo RSI(Nodo n){
        System.out.println("Rotacion Simple Izquierda ");
        Nodo n2 = n.getDerecha();
        n.setNivel(n.getNivel()+1);
        n2.getDerecha().setNivel(n2.getNivel());
        n2.setNivel(n.getNivel()-1);
        n.setDerecha(n2.getIzquierda());
        n2.setIzquierda(n);
        n.CalAltura();
        n2.CalAltura();
        return n2;
        
    }
    public Nodo RDI(Nodo n){
        System.out.println("ROTACION DOBLE IZQUIERDA");
        Nodo n2 = n.getDerecha();
        n.setDerecha(RSD(n2));
        return RSI(n);

    }
    

    public void Recorrido(int n,Nodo t,int L){
        System.out.println(t);
        if(t.getDerecha() == null & t.getIzquierda() == null){
            System.out.println(t);
            int x = pImagen.getLongitud();
            int y = pImagen.getAncho();
            Recorrido.add(t);
            pImagen.CrerRectangulo(t, L);
            pImagen.Linea(pImagen.getLongitud(),pImagen.getAncho(), x, y);
        }
        else if(t.getDerecha() != null & t.getIzquierda() != null){
            Tipo(n, t,L);
        } 
        else if(t.getIzquierda() != null){
            if(n==2){
                pImagen.CrerRectangulo(t, L);
                System.out.println(t);
                Recorrido.add(t);
                Recorrido(n, t.getIzquierda(),0);
                
            } else{
                int x = pImagen.getLongitud();
                int y = pImagen.getAncho();
                pImagen.CrerRectangulo(t, L);
                pImagen.Linea(pImagen.getLongitud(),pImagen.getAncho(), x, y);
                Recorrido(n,t.getIzquierda(),0);
                System.out.println(t);
                Recorrido.add(t);
            }  
        }
    
        else if(t.getDerecha() != null){
            if(n==2){
                pImagen.CrerRectangulo(t, L);
                System.out.println(t);
                Recorrido.add(t);
                Recorrido(n, t.getDerecha(),1);
                
            }else{
                Recorrido.add(t);
                int x = pImagen.getLongitud();
                int y = pImagen.getAncho();
                System.out.println(t);
                pImagen.CrerRectangulo(t, L);
                pImagen.Linea(pImagen.getLongitud(),pImagen.getAncho(), x, y);
                Recorrido(n, t.getDerecha(),1);
            }
        }
        
    }

    public void Tipo(int n,Nodo t,int La){
        switch(n){
            case 1: //inorden
                int x = pImagen.getLongitud();
                int y = pImagen.getAncho();
                pImagen.CrerRectangulo(t,La);
                pImagen.Linea(pImagen.getLongitud(),pImagen.getAncho(), x, y);
                int A = pImagen.getAncho();
                int L = pImagen.getLongitud();
                int R = pImagen.getResto();
                Recorrido(n, t.getIzquierda(),0);
                System.out.println(t);
                Recorrido.add(t);    
                pImagen.setLongitud(L);
                pImagen.setAncho(A);
                pImagen.setResto(R);
                Recorrido(n, t.getDerecha(),1);
                

            break;
            case 2: //preorden
                Recorrido.add(t);
                System.out.println(t);
                Recorrido(n, t.getIzquierda(),0);
                Recorrido(n, t.getDerecha(),1);
                
            break;
            case 3: //POSTORDEN
                Recorrido(n, t.getIzquierda(),0);
                Recorrido(n, t.getDerecha(),1);
                System.out.println(t);
                Recorrido.add(t);
            break;
        }

    }

    public ArrayList<Nodo> getRecorrido() {
        return Recorrido;
    }

    public void setRecorrido() {
        Recorrido = new ArrayList<Nodo>();
    }
    public void Pintar(){
        pImagen.Pintar();
    }

    public String getPath(){
        return pImagen.getImagen();
    }

    public ArrayList<Carta> getNoLevel(int n){
        ArrayList<Carta> P = new ArrayList<>();
        for(int i=0;i<Recorrido.size();i++){
            if(P.get(i).getValor() == n) P.add(P.get(i));
        }
        return P;

    }
    
    public void ReiniciarImagen(){
        pImagen = new Imagen();
    }
    
}
