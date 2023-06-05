package org.example.blowfish;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.security.Provider;

public class BlowFish {

    public static void cifrar(String cadena)throws Exception{
        long inicio = System.currentTimeMillis();

        System.out.println("El texto original es: " + cadena);
        // Generar una clave Blowfish
        System.out.println("Intentando coger una clave Blowfish....");
        KeyGenerator generador = KeyGenerator.getInstance("Blowfish");//Propociona las funciones de un generador de claves simétricas

        /*
        Provider
        Esta clase representa un "proveedor" para el API para seguridad de Java, donde un proveedor
        implementa algunas o todas las partes de Java Security.
        lo vamos a utilizar para generar una clave secreta segura
        * */

        Provider provider=generador.getProvider();//obtenemos la clave secreta
        String clavePublica =String.valueOf(provider.getName());
        System.out.println("Clave publica: "+clavePublica);
       // JOptionPane.showMessageDialog(null,"Clave publica " + clavePublica);

        generador.init(200);//ingresamos el tamaño de la clave
        SecretKey clave = generador.generateKey();
        System.out.println("OK");

        // Intentar encriptar texto
        System.out.print("Intentando coger un cifrado y encriptar...");
        Cipher cifrador = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");//modo de cifrado de bloque electrónico (ECB)
        //(PKCS5Padding)esquema para rellenar el texto claro para que sea múltiplo de un bloque de 8 bytes
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        byte[] textoCifrado = cifrador.doFinal(cadena.getBytes("UTF8"));
        System.out.println("Ok");
        System.out.println("Texto cifrado: "+new String(textoCifrado));
      //  JOptionPane.showMessageDialog(null,"El texto cifrado es: " + new String(textoCifrado));

        System.out.println("Test completado con exito");
        //Desencriptar texto
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        byte[] decrypted = cifrador.doFinal(textoCifrado);

        System.out.println("Mensaje descifrado: \n"+new String(decrypted));
        long fin = System.currentTimeMillis();
        long tiempo = ((fin - inicio));

        JOptionPane.showMessageDialog(null,"Tiempo de demora: "+tiempo);
    }
}
