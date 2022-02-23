package feladat02;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Ugyfelkiszolgalo implements Runnable {
    Socket kapcsolat;

    public Ugyfelkiszolgalo(Socket kapcsolat) {
        this.kapcsolat = kapcsolat;
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());
            while (true) {
                int a = ugyfeltol.readInt();
                int b = ugyfeltol.readInt();

                int menu;
                do {
                    menu = ugyfeltol.readInt();
                    switch (menu) {
                        case 1:
                            ugyfelnek.writeUTF(kerulet(a, b));
                            break;
                        case 2:
                            ugyfelnek.writeUTF(terulet(a, b));
                            break;
                        case 3:
                            ugyfelnek.writeUTF(negyzetE(a, b));
                            break;
                        case 4:
                            ugyfelnek.writeUTF(atloHossza(a, b));
                            break;
                        case 5:
                            ugyfelnek.writeUTF("Na csumi!");
                    }
                } while (menu != 5);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private String atloHossza(int a, int b) {
        return String.format("Az átló hossza: %.2f", Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));
    }

    private String negyzetE(int a, int b) {
        return a == b ? "Négyzet" : "Nem négyzet";
    }

    private String terulet(int a, int b) {
        return "A téglalap területe: " + a * b;
    }

    private String kerulet(int a, int b) {
        return "A téglalap kerülete: " + 2 * (a + b);
    }
}
