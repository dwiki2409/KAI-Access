package TiketKereta;

import java.util.*;

public class MainProgram {

    void menu(Pesanan pesan, UserDatabase user, Akun akun) {
        TiketKereta tKereta = new TiketKereta();

        // JadwalBerangkat jberangkat = new JadwalBerangkat();
        Scanner input = new Scanner(System.in);
        int pilihan = 0;

        System.out.println("===== SELAMAT DATANG DI KERETA ACCESS =====");

        do {
            System.out.println();
            System.out.println("\t[1] Jadwal dan Harga Tiket");
            System.out.println("\t[2] Pesan Tiket           ");
            System.out.println("\t[3] Cekout                ");
            System.out.println("\t[4] Batalkan pesanan      ");
            System.out.println("\t[5] Info Akun             ");
            System.out.println("\t[6] Isi Saldo             ");
            System.out.println("\t[7] Keluar    ");

            System.out.print("Masukkan pilihan: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    tKereta.jadwalBerangkat();
                    break;
                case 2:
                    System.out.println("------------------------------------------------------");
                    System.out.println("\t[1] Ekonomi\n\t[2] Bisnis\n\n[ENTER] Kembali ke Menu");
                    System.out.println();
                    System.out.print("Pilih Kelas Tiket : ");
                    String pilihKelas = input.next(); // Menggunakan next() untuk membaca input
                    if (pilihKelas.equalsIgnoreCase("1")) {
                        pesan.pilih_ekonomi(user);
                    } else if (pilihKelas.equalsIgnoreCase("2")) {
                        pesan.pilihBisnis(user);
                    } else {
                        System.out.println("Tekan ENTER untuk kembali ke Menu");
                        input.nextLine(); // Menambahkan input.nextLine() untuk membersihkan newline
                    }
                    break;
                case 3:
                    pesan.cekout(user);
                    break;
                case 4:
                    pesan.batal(user);
                    break;
                case 5:
                    akun.infoAkun(user);
                    break;
                case 6:
                    akun.isiSaldo(user);
                    break;
                case 7:
                    System.out.println("Terima kasih sudah menggunakan jasa tiket kereta.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } while (pilihan != 7);
    }

    public static boolean cekLogin(UserDatabase user, String username, String password) {
        boolean cekAkun = false;
        for (int i = 0; i < user.getUserCount(); i++) {
            if (user.getUser(i).get(1).equals(username) && user.getUser(i).get(2).equals(password)) {
                cekAkun = true;
                break;
            }
        }
        return cekAkun;
    }

    public static void main(String[] args) {
        MainProgram main = new MainProgram();
        UserDatabase user = new UserDatabase();
        Pesanan pesan = new Pesanan();
        Scanner input = new Scanner(System.in);
        Akun akun = new Akun();
        while (true) {
            System.out.println();
            System.out.println("====================================================================");
            System.out.println("|                            TIKET.KU                              |");
            System.out.println("|               Aplikasi Pemesanan Tiket Kereta Api                |");
            System.out.println("|                                                                  |");

            java.util.Date date = new java.util.Date();
            System.out.printf("|  %s                      %tT               %tF   |%n",
                    date.toString(), date, date);

            System.out.println("--------------------------------------------------------------------");
            System.out.println();
            System.out.println("\t[1] Masuk\n\t[2] Daftar\n\n\t[3] Keluar Aplikasi");
            System.out.println();
            System.out.print("Silahkan masukkan pilihan Anda: ");
            String pilih1 = input.nextLine();
            if (pilih1.equals("1")) {
                System.out.println();
                System.out.print("\tUsername : ");
                String username = input.nextLine();
                System.out.print("\tPassword : ");
                String password = input.nextLine();
                System.out.println();
                if (cekLogin(user, username, password)) {
                    main.menu(pesan, user, akun);
                } else {
                    System.out.println("\tUsername atau Password tidak valid!");
                    System.out.println();
                    System.out.println("\tTekan ENTER untuk kembali");
                    input.nextLine();
                }
            } else if (pilih1.equals("2")) {
                System.out.println();
                System.out.print("\tMasukkan Nama Lengkap : ");
                String nama = input.nextLine();
                System.out.print("\tBuat Username         : ");
                String username = input.nextLine();
                System.out.print("\tBuat Password         : ");
                String password = input.nextLine();
                String saldoMula = "0";

                user.addUser(nama, username, password, saldoMula);
                System.out.println();
                System.out.println("\tPendaftaran Sukses!");
                System.out.println();
                System.out.println("Tekan ENTER untuk kembali ke Menu");
                input.nextLine();
                continue;
            } else if (pilih1.equals("3")) {
                System.out.println("--------------------------------------------------------------------");
                System.out.println("                             Terimahkasih                           ");
                System.out.println("--------------------------------------------------------------------");
                break;
            } else {
                System.out.println();
                System.out.println("Input Tidak Dikenal");
                System.out.println("Tekan ENTER untuk kembali ke Menu");
                input.nextLine();
            }
        }
    }

}
