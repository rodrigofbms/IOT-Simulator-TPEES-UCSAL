import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket(8090);
        System.out.println("Porta Aberta");

        Socket cliente = servidor.accept();

        System.out.println("Nova Conexão com o Cliente " + cliente.getInetAddress().getHostAddress());

        Scanner scanner = new Scanner(cliente.getInputStream());

        String mensagem = "";

        while (scanner.hasNextLine()){

            mensagem = scanner.nextLine();

            if(mensagem.equals("!sair")){
                System.out.println("O cliente foi desconectado!");
               break;
            }

            System.out.println(mensagem);
        }

        scanner.close();
        cliente.close();
        servidor.close();
    }

}
