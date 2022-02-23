package feladat02;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {
    public static void main(String[] args) {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernek = new DataOutputStream(kapcsolat.getOutputStream());

            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.print("Kérem az a oldalt: ");
                int a = scan.nextInt();
                szervernek.writeInt(a);
                szervernek.flush();
                System.out.print("Kérem az b oldalt: ");
                int b = scan.nextInt();
                szervernek.writeInt(b);
                szervernek.flush();

                int menu;
                do{
                    System.out.println("Válasszon az alábbi lehetőségek közül: ");
                    System.out.println("1: Kerület\n2: Terület\n3: Négyzet-e\n4: Átló hossza\n5: Kilépés");
                    menu = scan.nextInt();
                    szervernek.writeInt(menu);
                    szervernek.flush();
                    System.out.println(szervertol.readUTF());
                } while (menu != 5);

            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
