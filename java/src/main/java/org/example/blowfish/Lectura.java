package org.example.blowfish;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Lectura {

    public static String leerArchivo(String nombreTxt) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String texto = new String();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //"C:\\Users\\cdavi\\OneDrive\\Escritorio\\"+
            archivo = new File(nombreTxt + ".txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero

            String linea;
            while ((linea = br.readLine()) != null) {
               // System.out.println(linea);
                texto += " "+linea+"\n";
            }
           // System.out.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return texto;
    }


}
