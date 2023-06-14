package TiketKereta;

import java.util.*;

public class Akun {
    public void infoAkun(UserDatabase user) {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Nama Pengguna  : " + user.getUser(0).get(0));
        System.out.println("Username       : " + user.getUser(0).get(1));
        System.out.println("Password       : " + user.getUser(0).get(2));
        System.out.println("Saldo          : " + Math.round(Double.parseDouble(user.getUser(0).get(3))));
        System.out.println();
        System.out.println("Tekan ENTER untuk kembali ke Menu");
        input.nextLine();
    }

    public void isiSaldo(UserDatabase user) {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("\t[1] Lanjut\n\t[2] Kembali ke Menu");
        System.out.println();
        System.out.print("Pilihan Anda : ");
        String tanya = input.nextLine();
        if (tanya.equals("1")) {
            System.out.print("\tMasukkan nominal uang : Rp. ");
            int saldo = Integer.parseInt(input.nextLine());
            // user.getUser(0).add(String.valueOf(saldo));
            double currentSaldo = Double.parseDouble(user.getUser(0).get(3));
            double saldoToAdd = Double.parseDouble(user.getUser(0).get(3));

            user.getUser(0).set(3, String.valueOf(currentSaldo + saldo));
            // user.getUser(0).remove(4);
            System.out.println();
            System.out.println("\tSaldo telah di isi");
            System.out.println();

        }
        System.out.println("Tekan ENTER untuk kembali ke Menu");
        input.nextLine();
    }

}
