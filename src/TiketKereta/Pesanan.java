package TiketKereta;

import java.util.*;

public class Pesanan {
    static Date x = new Date();

    private static String[][] ekonomi = {
            { "Senin  ", "05.30", "06.45 ", "Surabaya  ", "5000", "0" },
            { "Selasa ", "05.30", "07.00 ", "Madura    ", "7000", "0" },
            { "Rabu   ", "06.30", "07.30 ", "Mojokerto ", "10000", "0" },
            { "Kamis  ", "05.45", "08.45 ", "Malang    ", "35000", "0" },
            { "Jumat  ", "06.00", "07.15 ", "Jombang   ", "10000", "0" },
            { "Sabtu  ", "05.30", "08.30 ", "Kediri    ", "25000", "10" },
            { "Minggu ", "05.30", "07.00 ", "Sidoarjo  ", "20000", "10" }
    };
    static String[][] bisnis = {
            { "Senin  ", "05.30", "06.45 ", "Surabaya  ", "20000", "0" },
            { "Selasa ", "05.30", "07.00 ", "Madura    ", "25000", "0" },
            { "Rabu   ", "06.30", "07.30 ", "Mojokerto ", "30000", "0" },
            { "Kamis  ", "05.45", "08.45 ", "Malang    ", "65000", "0" },
            { "Jumat  ", "06.00", "07.15 ", "Jombang   ", "20000", "0" },
            { "Sabtu  ", "05.30", "08.30 ", "Kediri    ", "50000", "10" },
            { "Minggu ", "05.30", "07.00 ", "Sidoarjo  ", "55000", "10" }
    };

    private static ArrayList<ArrayList<String>> laporan_beli = new ArrayList<>();

    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception ex) {
            System.out.println("Error occurred while clearing the screen.");
        }
    }

    public void tampil_ekonomi() {
        clearScreen();
        System.out.println("""
                ===========================================================================================
                |                                   ~~~~ EKONOMI ~~~~~                                    |
                |-----------------------------------------------------------------------------------------|
                |    |          |               Waktu            |             |              |           |
                | No |   Hari   |--------------------------------|   Tujuan    |    Harga     |   Promo   |
                |    |          |  Keberangkatan  |  Kedatangan  |             |              |           |
                |-----------------------------------------------------------------------------------------|
                | 1  | Senin    |      05.45      |     06.45    |  Surabaya   |  Rp. 5.000   |     -     |
                | 2  | Selasa   |      05.45      |     07.00    |  Madura     |  Rp. 7.000   |     -     |
                | 3  | Rabu     |      06.30      |     07.30    |  Mojokerto  |  Rp. 10.000  |     -     |
                | 4  | Kamis    |      05.45      |     08.45    |  Malang     |  Rp. 35.000  |     -     |
                | 5  | Jumat    |      06.00      |     07.15    |  Jombang    |  Rp. 10.000  |     -     |
                | 6  | Sabtu    |      05.30      |     08.30    |  Kediri     |  Rp. 25.000  |    10%    |
                | 7  | Minggu   |      05.30      |     07.00    |  Sidoarjo   |  Rp. 20.000  |    10%    |
                -------------------------------------------------------------------------------------------""");
    }

    public static void tampilBisnis() {
        System.out
                .println("===========================================================================================");
        System.out
                .println("|                                   ~~~~ BISNIS ~~~~~                                     |");
        System.out
                .println("|-----------------------------------------------------------------------------------------|");
        System.out
                .println("|    |          |               Waktu            |             |              |           |");
        System.out
                .println("| No |   Hari   |--------------------------------|   Tujuan    |    Harga     |   Promo   |");
        System.out
                .println("|    |          |  Keberangkatan  |  Kedatangan  |             |              |           |");
        System.out
                .println("|-----------------------------------------------------------------------------------------|");
        System.out
                .println("| 1  | Senin    |      05.45      |     06.45    |  Surabaya   |  Rp. 20.000  |     -     |");
        System.out
                .println("| 2  | Selasa   |      05.45      |     07.00    |  Madura     |  Rp. 25.000  |     -     |");
        System.out
                .println("| 3  | Rabu     |      06.30      |     07.30    |  Mojokerto  |  Rp. 30.000  |     -     |");
        System.out
                .println("| 4  | Kamis    |      05.45      |     08.45    |  Malang     |  Rp. 65.000  |     -     |");
        System.out
                .println("| 5  | Jumat    |      06.00      |     07.15    |  Jombang    |  Rp. 20.000  |     -     |");
        System.out
                .println("| 6  | Sabtu    |      05.30      |     08.30    |  Kediri     |  Rp. 50.000  |    10%    |");
        System.out
                .println("| 7  | Minggu   |      05.30      |     07.00    |  Sidoarjo   |  Rp. 55.000  |    10%    |");
        System.out
                .println("-------------------------------------------------------------------------------------------");
    }

    public void pilih_ekonomi(UserDatabase user) {
        tampil_ekonomi();
        System.out.println();
        Scanner input = new Scanner(System.in);
        System.out.print("\tPilih tiket  : ");
        int pilih_tiket = input.nextInt();
        pilih_tiket -= 1;
        System.out.println();
        System.out.print("\tJumlah pesan tiket : ");
        int jumlah_tiket = input.nextInt();

        String kelas = "Ekonomi";
        String nama = user.getUser(0).get(0);
        String hari = ekonomi[pilih_tiket][0];
        String jam = ekonomi[pilih_tiket][1];
        String jam_2 = ekonomi[pilih_tiket][2];
        String tujuan = ekonomi[pilih_tiket][3];
        int harga = Integer.parseInt(ekonomi[pilih_tiket][4]);
        int promo = Integer.parseInt(ekonomi[pilih_tiket][5]);
        int diskon = (jumlah_tiket * harga * promo) / 100;
        int total = (jumlah_tiket * harga) - diskon;

        struk_beli(kelas, nama, hari, jam, jam_2, tujuan, harga, promo, jumlah_tiket, total);
    }

    public void pilihBisnis(UserDatabase user) {
        Scanner input = new Scanner(System.in);

        tampilBisnis();
        System.out.println();
        System.out.print("\tPilih tiket  : ");
        int pilih_tiket = input.nextInt();
        pilih_tiket--;

        System.out.println();
        System.out.print("\tJumlah pesan tiket : ");
        int jumlah_tiket = input.nextInt();

        String kelas = "Bisnis";

        String nama = user.getUser(0).get(0);
        String hari = bisnis[pilih_tiket][0];
        String jam = bisnis[pilih_tiket][1];
        String jam_2 = bisnis[pilih_tiket][2];
        String tujuan = bisnis[pilih_tiket][3];
        int harga = Integer.parseInt(bisnis[pilih_tiket][4]);
        int promo = Integer.parseInt(bisnis[pilih_tiket][5]);
        int diskon = (jumlah_tiket * harga) * (promo / 100);
        int total = (jumlah_tiket * harga) - diskon;

        struk_beli(kelas, nama, hari, jam, jam_2, tujuan, harga, promo, jumlah_tiket, total);

    }

    public void struk_beli(String kelas, String nama, String hari, String jam, String jam_2, String tujuan,
            int harga, int promo, int jumlah, int total) {

        ArrayList<String> laporan = new ArrayList<>();
        laporan.add(kelas);
        laporan.add(nama);
        laporan.add(hari);
        laporan.add(jam);
        laporan.add(jam_2);
        laporan.add(tujuan);
        laporan.add(String.valueOf(harga));
        laporan.add(String.valueOf(promo));
        laporan.add(String.valueOf(jumlah));
        laporan.add(String.valueOf(total));
        laporan_beli.add(laporan);

        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("          STRUK PEMBELIAN                   ");
        System.out.println("--------------------------------------------");
        System.out.println(" " + hari + "       " + jam + "        " + x);
        System.out.println("--------------------------------------------");
        System.out.println(" Nama Pembeli        : " + nama);
        System.out.println(" Kelas Kereta        : " + kelas);
        System.out.println(" Hari                : " + hari);
        System.out.println(" Waktu Keberangkatan : " + jam);
        System.out.println(" Waktu Kedatangan    : " + jam_2);
        System.out.println(" Tujuan              : " + tujuan);
        System.out.println(" Harga Tiket         : " + harga);
        System.out.println(" Promo Weekend       : " + promo + "%");
        System.out.println(" Jumlah Tiket        : " + jumlah);
        System.out.println(" Total Bayar         : " + Math.round(total));
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("Tekan ENTER untuk kembali ke Menu");
    }

    public void cekout(UserDatabase user) {
        Scanner input = new Scanner(System.in);
        System.out.println();

        if (laporan_beli.size() == 0) {
            System.out.println("\tBELUM ADA PEMESANAN");
            System.out.println();
            System.out.println("Tekan ENTER untuk kembali ke Menu");
            input.nextLine();
        } else {
            int j = 1;
            for (int i = 0; i < laporan_beli.size(); i++) {
                System.out.println();
                System.out.println("                   " + j);
                System.out.println("----------------------------------------------");
                System.out.println("               TIKET KERETA                   ");
                System.out.println(" " + x.getDay() + "       " + x.getHours() + ":" + x.getMinutes() + ":"
                        + x.getSeconds() + "        " + x.getDate());
                System.out.println("----------------------------------------------");
                System.out.println(" Nama Pembeli        : " + laporan_beli.get(i).get(1));
                System.out.println(" Kelas Kereta        : " + laporan_beli.get(i).get(0));
                System.out.println(" Hari                : " + laporan_beli.get(i).get(2));
                System.out.println(" Waktu Keberangkatan : " + laporan_beli.get(i).get(3));
                System.out.println(" Waktu Kedatangan    : " + laporan_beli.get(i).get(4));
                System.out.println(" Tujuan              : " + laporan_beli.get(i).get(5));
                System.out.println(" Harga Tiket         : " + laporan_beli.get(i).get(6));
                System.out.println(" Promo Weekend       : " + laporan_beli.get(i).get(7) + "%");
                System.out.println(" Jumlah Tiket        : " + laporan_beli.get(i).get(8));
                System.out.println(" Total Bayar         : "
                        + Math.round(Double.parseDouble((String) laporan_beli.get(i).get(9))));
                System.out.println("----------------------------------------------");
                j++;
            }

            double total_semua = laporan_beli.stream().mapToDouble(z -> Double.parseDouble((String) z.get(9))).sum();
            System.out.println();
            System.out.println("----------------------------------------------------------------");
            System.out.println("Total harga semua pesanan Anda adalah : Rp." + Math.round(total_semua));
            System.out.println("----------------------------------------------------------------");

            double sisa = 0;
            double sisa1 = 0;
            double saldo_awal = 0;
            saldo_awal += Double.parseDouble((String) user.getUser(0).get(3));

            System.out.println();
            System.out.println("\tSaldo Anda saat ini : Rp." + saldo_awal);
            System.out.println();

            while (true) {
                System.out.println("\n\t[1] Lanjut\n\t[2] Kembali ke Menu");
                System.out.println();
                System.out.print("Pilihan Anda : ");
                String bayar = input.nextLine();

                if (bayar.equals("1")) {
                    if (saldo_awal > total_semua) {
                        sisa = saldo_awal - total_semua;
                        System.out.println();
                        System.out.println("\tKembalian Anda adalah Rp." + Math.round(sisa));
                        sisa1 = sisa;
                        user.getUser(0).set(3, Double.toString(sisa1));
                        while (!laporan_beli.isEmpty()) {
                            laporan_beli.remove(0);
                        }
                        System.out.println();
                        System.out.println("\tTerimakasih!");
                        System.out.println();
                        System.out.println("Tekan ENTER untuk kembali ke Menu");
                        input.nextLine();
                        break;
                    } else {
                        System.out.println();
                        System.out.println("\tMaaf Saldo anda kurang");
                        System.out.println();
                        System.out.println("Tekan ENTER untuk kembali ke Menu");
                        input.nextLine();
                        break;
                    }
                } else if (bayar.equals("2")) {
                    System.out.println();
                    System.out.println("Tekan ENTER untuk kembali ke Menu");
                    input.nextLine();
                    break;
                } else {
                    System.out.println();
                    System.out.println("Input tidak dikenal");
                }
            }
        }

    }
    public void batal(UserDatabase user) {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("            - PESANAN -");
        if (laporan_beli.size() == 0) {
            System.out.println();
            System.out.println("\tBELUM ADA PEMESANAN");
            System.out.println();
            System.out.println("Tekan ENTER untuk kembali ke Menu");
            input.nextLine();

        } else {
            while (true) {
                int j = 1;
                for (int i = 0; i < laporan_beli.size(); i++) {
                    System.out.println("                   " + j);
                    System.out.println("----------------------------------------------");
                    System.out.println("               TIKET KERETA                   ");
                    System.out.println(" " + x.getDay() + "       " + x.getHours() + ":" + x.getMinutes() + ":"
                            + x.getSeconds() + "        " + x.getDate());
                    System.out.println("----------------------------------------------");
                    System.out.println(" Nama Pembeli        : " + laporan_beli.get(i).get(1));
                    System.out.println(" Kelas Kereta        : " + laporan_beli.get(i).get(0));
                    System.out.println(" Hari                : " + laporan_beli.get(i).get(2));
                    System.out.println(" Waktu Keberangkatan : " + laporan_beli.get(i).get(3));
                    System.out.println(" Waktu Kedatangan    : " + laporan_beli.get(i).get(4));
                    System.out.println(" Tujuan              : " + laporan_beli.get(i).get(5));
                    System.out.println(" Harga Tiket         : " + laporan_beli.get(i).get(6));
                    System.out.println(" Promo Weekend       : " + laporan_beli.get(i).get(7) + "%");
                    System.out.println(" Jumlah Tiket        : " + laporan_beli.get(i).get(8));
                    System.out.println(
                            " Total Bayar         : " + Math.round(Double.parseDouble(laporan_beli.get(i).get(9))));
                    System.out.println("----------------------------------------------");
                    j += 1;
                    System.out.println("\t[1] Batalkan Pesanan\n\t[2] Pesanan selanjutnya\n\n[3] Kembali ke Menu");
                    System.out.println();
                    System.out.print("Pilih Menu(nomer) : ");
                    String pilih = input.nextLine();
                    if (pilih.equals("1")) {
                        System.out.print("Ketik nomer pesanan jika ingin membatalkan : ");
                        int pilihHapus = Integer.parseInt(input.nextLine());
                        pilihHapus -= 1;
                        laporan_beli.remove(pilihHapus);
                        System.out.println();
                        System.out.println("\tPesanan telah dibatalkan");
                        System.out.println();
                        System.out.println("Tekan ENTER untuk kembali ke Menu");
                        input.nextLine();
                        return;
                    } else if (pilih.equals("2")) {
                        System.out.println();
                    } else if (pilih.equals("3")) {
                        return;
                    } else {
                        System.out.println();
                        System.out.println("Input Tidak Dikenal!");
                    }
                }
            }
        }
    }


}
