package com.example.demo.services;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.regex.Matcher;
import com.example.demo.arbol.Arbol;
import com.example.demo.arbol.Carta;
import com.example.demo.arbol.Nodo;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private Arbol avl = new Arbol();
    private ArrayList<Carta> Cartas = new ArrayList<Carta>();
    public HttpStatus GuardarCartas(String Entrada){
        int x = 0;
        int y = Entrada.split(",").length;
        Object obj = JSONValue.parse(Entrada);
        JSONObject Json = (JSONObject) obj;
        while(x<y){
            String Imagen = (String)Json.get(x+"");
            StringBuilder P = new StringBuilder(Imagen);
            //Problema de string
            for(int i=0;i<Imagen.length();i++){
                int n = Imagen.charAt(i);
                if(n==65039) {
                    Imagen.replace(String.valueOf(Imagen.charAt(i)),"");
                    P.deleteCharAt(i);
                }   
            }
            Imagen = String.valueOf(P);
            if(Verificar(Imagen) & Repetido(Imagen)){
                System.out.println("IMAGEN: "+Imagen);
                Carta Cartatmp = new Carta(Imagen);
                Cartas.add(Cartatmp);
                avl.Agregar(Cartatmp);
            }
            x++;
            
        }
        return HttpStatus.OK;
    }

    public boolean GuardarCarta(JSONObject Entrada){
        String Imagen = (String)Entrada.get("insert");
        if(Verificar(Imagen) & Repetido(Imagen)){
            Carta tmp = new Carta(Imagen);
            Cartas.add(tmp);
            avl.Agregar(tmp);
            return true;
        }else return false;
    }
    
    public boolean Verificar(String Imagen){
        Pattern pat = Pattern.compile("(10|[2-9]|J|Q|K|A)[♣♥♦♠]");
        Matcher mat = pat.matcher(Imagen);
        if(mat.matches())return true; 
        else ;return false;
    }

    public boolean Repetido(String Imagen){
        if(Cartas.size() == 0)return true;
        else{
            boolean tmp = true;
            for (int i = 0; i < Cartas.size(); i++) {
                if(Imagen.equals(Cartas.get(i).getCarta())) tmp = false;
            }
            return tmp;

        }
    }
    public JSONObject Recorrido(int n){
        JSONObject N = new JSONObject();
        avl.Recorrido(n, avl.getRaiz(),0);
        System.out.println("PAAATH " + avl.getPath());
        ArrayList<Nodo> C = avl.getRecorrido();
        for(int i=0;i<C.size();i++){
            N.put(i,C.get(i).getCarta().getCarta());
        }
        avl.setRecorrido();
        return N;
    }

    public int ELiminar(JSONObject Entrada){
        if(Entrada.size() ==2 | Entrada.size() == 1){
            if(Entrada.size() == 2){
                String Imagen1 = (String)Entrada.get("delete_1");
                String Imagen2 = (String)Entrada.get("delete_2");
                if(Verificar(Imagen1) & Verificar(Imagen2)){
                    Carta carta1 = new Carta(Imagen1);
                    Carta carta2 = new Carta(Imagen2);
                    if(carta1.getValor()+carta2.getValor() == 13){
                        avl.ELiminar(avl.getRaiz(),carta1);
                        avl.ELiminar(avl.getRaiz(),carta2);
                        return avl.Estado;
                    }else return 3;
                }else return 4;

            }else{
                String Imagen1 = (String)Entrada.get("delete_1");
                if(Verificar(Imagen1)){
                    Carta tmp = new Carta(Imagen1);
                    if(tmp.getValor() == 13){
                        avl.ELiminar(avl.getRaiz(),tmp);
                        return avl.Estado;
                    }else return 3;
                }else return 4;
            }
        }else return 4;
    }

    public JSONObject Pruebas(int Nivel){
        JSONObject N = new JSONObject();
        int x =0;
        avl.Recorrido(1, avl.getRaiz(),0);
        ArrayList<Nodo> P  = avl.getRecorrido();
        for(int i=0;i<P.size();i++){
            Nodo c = P.get(i);
            if(c.getNivel() == Nivel){
              N.put(x,c.getCarta().getCarta());
              x++;
            }
        }
        avl.setRecorrido();
        return N;
    }

    public JSONObject getImagen(){
        avl.ReiniciarImagen();
        JSONObject N = new JSONObject();
        avl.Recorrido(1,avl.getRaiz(),0);
        avl.Pintar();
        N.put("path","https://www.dropbox.com/sh/837n94ewjbmqbxf/AABZ84-_p125CIbpVvvp5JSja?dl=0");
        return N;
    }

    
}
