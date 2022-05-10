package com.example.demo.imagen;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.example.demo.arbol.Nodo;
public class Imagen {
    private BufferedImage imagen; 
    private Graphics g;
    private File Fichero;
    private String Formato;
    private int Longitud = 750;
    private int Resto = 250;
    private int Ancho = 0;

    public Imagen(){
        Iniciar();
    }
    public void Iniciar(){
        imagen = new BufferedImage(1000,800,BufferedImage.TYPE_INT_RGB);
        g = imagen.getGraphics();
        Fichero = new File("Estado.jpg");
        Formato = "jpg";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.BOLD,25));
    }
    public int getLongitud(){
        return Longitud;
    }
    public void setLongitud(int n){
        Longitud = n;
    }
    public int getAncho(){
        return Ancho;
    }
    public void setAncho(int A){
        Ancho = A;

    }
    public String getImagen(){
        return Fichero.getAbsolutePath();
    }
    
    public int getResto() {
        return Resto;
    }
    public void setResto(int resto) {
        Resto = resto;
    }
    public void Pintar(){
        try {
            ImageIO.write(imagen,Formato,Fichero);
            DropboxImagen.Cargar();
        } catch (IOException e) {
            System.out.println("ERRO DE ESCRITURA");
        }
    }

    public void CrerRectangulo(Nodo n,int L){
        Longitud = (L==0)?Longitud-Resto:Longitud+Resto;
        Ancho = Ancho + 50;
        Resto = Resto - 80;
        g.drawString(n.getCarta().getCarta(),Longitud,Ancho);
    }
    public void Linea(int x0,int y0,int x2,int y2){
        g.drawLine(x0, y0, x2, y2);
    }

    
}
