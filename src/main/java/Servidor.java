import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException {

        Random aleatorio = new Random();

        Path path = Paths.get("arquivos/lista_dispositivos.txt");

        String texto = "";

        BufferedReader buffer;

            buffer = new BufferedReader(new FileReader(String.valueOf(path)));

        ServerSocket servidor = new ServerSocket(8090);
        System.out.println("Porta Aberta");

        String [] comando = new String[3];

        Socket cliente = servidor.accept();

        System.out.println("Nova Conexão com o Cliente " + cliente.getInetAddress().getHostAddress());

        Scanner scannerCliente = new Scanner(cliente.getInputStream());

        Scanner scannerServidor = new Scanner(System.in);

        PrintStream saida = new PrintStream(cliente.getOutputStream());

        String mensagemCliente = "";

        while (scannerCliente.hasNextLine()){

            mensagemCliente = scannerCliente.nextLine();

           // saida.println("Mensagem do Servidor");

            if(mensagemCliente.equals("!sair")){
                System.out.println("O cliente foi desconectado!");
               break;
            }

            if(mensagemCliente.contains("CONECTAR")){
                comando = mensagemCliente.split(" ");
                int encontrado = 0;
                while((texto = buffer.readLine()) != null){
                    if(texto.contains(comando[2])){
                        saida.println(comando[2] + " ATIVADO 30");
                        encontrado = 1;
                        break;
                    }
                }
                if(encontrado == 0) {
                    saida.println(comando[2] + " DESATIVADO");
                }
                while(!mensagemCliente.equals("!sair")){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int temperatura = aleatorio.nextInt(30);
                    saida.println(temperatura);
                }

            }



            System.out.println(mensagemCliente);
        }

        scannerCliente.close();
        cliente.close();
        servidor.close();
    }
}

