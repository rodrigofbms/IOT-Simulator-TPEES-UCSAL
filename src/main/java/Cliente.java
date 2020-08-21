import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException, UnknownHostException {

        Socket cliente = new Socket("192.168.1.71", 6868);

        System.out.println("O cliente Foi Conectado!");

        Scanner teclado = new Scanner(System.in);

        PrintStream saida = new PrintStream(cliente.getOutputStream());

        while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
        }

        cliente.close();
        saida.close();
        teclado.close();
    }

}
