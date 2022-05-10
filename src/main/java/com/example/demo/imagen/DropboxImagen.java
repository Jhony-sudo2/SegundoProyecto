package com.example.demo.imagen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadBuilder;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;

public class DropboxImagen {
    private static final String ACCES_TOKEN = "sl.BHVwQ55FLLe-kvZ3tgdC3fps0yHTliNAOy6yrteFScJoSBnM61-d3UzAo7Gif3FB-EE3UtndBNxjup52-eDAIIMy83dYo3O0n6EYjwHS2FpltzmRRexTdJdGUWP_rA2nfRf4tGqAlak";
    private static DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/API").build();
    private static DbxClientV2 client = new DbxClientV2(config,ACCES_TOKEN);
    private static File Imagen = new File("/home/jhony/ING/QUINTO/ESTRUCTURA DATOS/ApiPruebas/demo/Estado.jpg");
    public static void Cargar(){
        try {
            InputStream inputStream = new FileInputStream(Imagen);
            UploadBuilder uploadBuilder = client.files().uploadBuilder("/Imagenes/" + Imagen.getName());
            uploadBuilder.withClientModified(new Date(Imagen.lastModified()));
            uploadBuilder.withMode(WriteMode.ADD);
            uploadBuilder.withAutorename(true);
            try {
                uploadBuilder.uploadAndFinish(inputStream);
                inputStream.close();
            } catch (UploadErrorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (DbxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
