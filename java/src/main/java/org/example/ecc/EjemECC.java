package org.example.ecc;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.swing.*;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class EjemECC {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        try {
            // Generar una clave privada y una clave pública basadas en una curva elíptica
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
            keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Obtener la clave privada y la clave pública
            PrivateKey clavePrivada = keyPair.getPrivate();
            PublicKey clavePublica = keyPair.getPublic();

            // Cifrar un mensaje utilizando la clave pública
            String message = JOptionPane.showInputDialog(null,"Ingrese mesaje a cifrar por ECC: ");
            Cipher cipher = Cipher.getInstance("ECIES", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, clavePublica);
            byte[] encriptData = cipher.doFinal(message.getBytes("UTF8"));

            // Descifrar el mensaje utilizando la clave privada
            cipher.init(Cipher.DECRYPT_MODE, clavePrivada);
            byte[] decryptData = cipher.doFinal(encriptData);

            // Imprimir los resultados
            System.out.println("Mensaje original: " + message);
            JOptionPane.showMessageDialog(null, "Mensaje original: " + message);
            System.out.println("Mensaje cifrado: " + new String(encriptData, "UTF8"));
            JOptionPane.showMessageDialog(null, "Mensaje cifrado: " + new String(encriptData, "UTF8"));
            System.out.println("Mensaje descifrado: " + new String(decryptData, "UTF8"));
            JOptionPane.showMessageDialog(null, "Mensaje descifrado: " + new String(decryptData, "UTF8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}