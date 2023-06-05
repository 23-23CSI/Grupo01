package org.example.ecc;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;

import java.io.FileReader;

import java.io.BufferedReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class EccNuevo {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        long inicio = System.currentTimeMillis();

        try {
            // Generar una clave privada y una clave pública basadas en una curva elíptica
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
            keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Obtener la clave privada y la clave pública
            PrivateKey clavePrivada = keyPair.getPrivate();
            PublicKey clavePublica = keyPair.getPublic();

            // Imprimir las claves pública y privada
            System.out.println("Clave pública: " + clavePublica.toString());
            System.out.println("Clave privada: " + clavePrivada.toString());

            // Cifrar el archivo de texto utilizando la clave pública
            String inputFile = leerArchivo("diez");
            byte[] encryptedData = encryptData(inputFile, clavePublica);

            System.out.println("Mensaje cifrado:\n" + new String(encryptedData, StandardCharsets.UTF_8));

            // Descifrar el archivo cifrado utilizando la clave privada
            String decryptedData = decryptData(encryptedData, clavePrivada);

            System.out.println("Archivo descifrado:\n" + decryptedData);

            System.out.println("Cifrado y descifrado completados con éxito.");

            long fin = System.currentTimeMillis();
            long tiempo = ((fin - inicio));
            System.out.println(tiempo + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] encryptData(String inputFile, PublicKey publicKey) throws Exception {
        // Leer el contenido del archivo de texto
        byte[] fileData = inputFile.getBytes(StandardCharsets.UTF_8);

        // Cifrar el contenido del archivo utilizando la clave pública
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(fileData);
    }

    private static String decryptData(byte[] encryptedData, PrivateKey privateKey) throws Exception {
        // Descifrar el contenido del archivo utilizando la clave privada
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal(encryptedData);

        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    private static String leerArchivo(String mensaje) {
        String texto = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mensaje + ".txt"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                texto += linea + "\n";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto;
    }

}
