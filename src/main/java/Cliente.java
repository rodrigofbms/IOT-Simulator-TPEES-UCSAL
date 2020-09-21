import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {

        Servidor servidor = new Servidor();

        Socket cliente = new Socket("3.13.191.225",  13729);

        System.out.println("O cliente Foi Conectado!");

        Scanner teclado = new Scanner(System.in);

        Scanner scannerServidor = new Scanner(cliente.getInputStream());

        PrintStream saida = new PrintStream(cliente.getOutputStream());


        saida.println(teclado.nextLine());

        while (true) {

            System.out.println(scannerServidor.nextLine());

                if(scannerServidor.nextLine().contains("ATIVADO")) {
                    while (scannerServidor.hasNextLine()) {

                       System.out.println(scannerServidor.nextLine());

                    }
                }
        }

        //cliente.close();
       // saida.close();
        //teclado.close();
    }

}
