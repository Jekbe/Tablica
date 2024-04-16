import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static boolean run = true;
    public static void main(String[] args) {
        Thread konsola = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (run) {
                if (scanner.nextLine().equals("exit")) run = false;
                else System.out.println("Nieznana opcja");
            }
        });
        konsola.start();

        try (ServerSocket SocketAPI = new ServerSocket(8003)){
            System.out.println("Serwer działa");
            while (run) {
                Socket socket = SocketAPI.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("Nowy klient");

                String request = in.readLine();
                String response = "status:400";

                out.println(response);
            }
        } catch (IOException e) {
            System.out.println("Błąd: " + e);
        }
    }
}