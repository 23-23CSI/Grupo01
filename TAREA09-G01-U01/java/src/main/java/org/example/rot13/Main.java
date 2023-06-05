package org.example.rot13;
import org.example.blowfish.Lectura;

public class Main {
    public static String rot13(String mensaje) {
        
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        String cifrado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLetter(letra)) {
                int indice = alfabeto.indexOf(Character.toLowerCase(letra));
                char nuevaLetra = alfabeto.charAt((indice + 13) % alfabeto.length());
                cifrado += Character.isUpperCase(letra) ? Character.toUpperCase(nuevaLetra) : nuevaLetra;
            } else {
                cifrado += letra;
            }
            System.out.println(i); 
        }
        
        return cifrado;

    }
    
    public static String desrot13(String mensajeCifrado) {
        return rot13(mensajeCifrado);
    }

    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        
        // System.out.print("Ingrese el mensaje: ");
        // String mensaje = scanner.nextLine();
        long inicio = System.currentTimeMillis();
        String mensajeCifrado = rot13(Lectura.leerArchivo("cien"));
        System.out.println("Mensaje cifrado: " + mensajeCifrado);
        
        String mensajeDescifrado = desrot13(mensajeCifrado);
        System.out.println("Mensaje descifrado: " + mensajeDescifrado);
        long fin = System.currentTimeMillis();
        long tiempo = ((fin - inicio));
        System.out.println("tiempo: "+ tiempo);
        
        // scanner.close();
    }
}