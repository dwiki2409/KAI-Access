package TiketKereta;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class TiketKereta extends AbstrakTiketKereta {
    private String[] namaPenumpang;
    private String[] tujuan;
    private String[] kelas;
    private int[] jumlahTiket;
    private double[] hargaTiket;
    private double[] totalHarga;

    public TiketKereta() {
        this.namaPenumpang = new String[10];
        this.tujuan = new String[10];
        this.kelas = new String[10];
        this.jumlahTiket = new int[10];
        this.hargaTiket = new double[10];
        this.totalHarga = new double[10];
    }

    @Override
    public void tambahkanTiket() {
        Scanner input = new Scanner(System.in);
        int i = 0;
        // Cari indeks kosong pada array
        while (i < namaPenumpang.length && namaPenumpang[i] != null) {
            i++;
        }

        if (i == namaPenumpang.length) {
            System.out.println("Maaf, pemesanan sudah penuh.");
            return;
        }

        System.out.print("Masukkan nama penumpang: ");
        namaPenumpang[i] = input.nextLine();

        System.out.print("Masukkan tujuan: ");
        tujuan[i] = input.nextLine();

        System.out.print("Masukkan kelas (ekonomi/bisnis/executive): ");
        kelas[i] = input.nextLine();

        System.out.print("Masukkan jumlah tiket: ");
        jumlahTiket[i] = input.nextInt();

        // Harga tiket sesuai kelas dan tujuan
        if (tujuan[i].equalsIgnoreCase("jakarta")) {
            if (kelas[i].equalsIgnoreCase("ekonomi")) {
                hargaTiket[i] = 75000;
            } else if (kelas[i].equalsIgnoreCase("bisnis")) {
                hargaTiket[i] = 150000;
            } else {
                hargaTiket[i] = 250000;
            }
        } else {
            if (kelas[i].equalsIgnoreCase("ekonomi")) {
                hargaTiket[i] = 50000;
            } else if (kelas[i].equalsIgnoreCase("bisnis")) {
                hargaTiket[i] = 100000;
            } else {
                hargaTiket[i] = 200000;
            }
        }
        totalHarga[i] = jumlahTiket[i] * hargaTiket[i];

        System.out.println("Tiket berhasil ditambahkan!");
    }

    @Override
    public void cekPemesanan() {
        System.out.println("==== DAFTAR PEMESANAN ====");
        System.out.println("No.  Nama Penumpang  Tujuan  Kelas  Jumlah Tiket  Harga Tiket  Total Harga");

        for (int i = 0; i < namaPenumpang.length; i++) {
            if (namaPenumpang[i] != null) {
                System.out.printf("%-5d%-16s%-8s%-7s%-13dRp%-11.2fRp%-12.2f\n", i + 1, namaPenumpang[i], tujuan[i],
                        kelas[i], jumlahTiket[i], hargaTiket[i], totalHarga[i]);
            }
        }
    }

    @Override
    public void pembatalan() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nomor pemesanan yang akan dibatalkan: ");
        int nomor = input.nextInt();

        if (namaPenumpang[nomor - 1] == null) {
            System.out.println("Nomor pemesanan tidak valid.");
            return;
        }

        System.out.println("Apakah Anda yakin ingin membatalkan pemesanan ini? (y/n)");
        String konfirmasi = input.next();

        if (konfirmasi.equalsIgnoreCase("y")) {
            namaPenumpang[nomor - 1] = null;
            tujuan[nomor - 1] = null;
            kelas[nomor - 1] = null;
            jumlahTiket[nomor - 1] = 0;
            hargaTiket[nomor - 1] = 0;
            totalHarga[nomor - 1] = 0;
            System.out.println("Pemesanan berhasil dibatalkan.");
        }
    }

    @Override
    public void ubahJadwal() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nomor pemesanan yang akan diubah: ");
        int nomor = input.nextInt();

        if (namaPenumpang[nomor - 1] == null) {
            System.out.println("Nomor pemesanan tidak valid.");
            return;
        }

        System.out.print("Masukkan tujuan baru: ");
        tujuan[nomor - 1] = input.next();

        System.out.print("Masukkan kelas baru (ekonomi/bisnis/executive): ");
        kelas[nomor - 1] = input.next();

        System.out.print("Masukkan jumlah tiket baru: ");
        jumlahTiket[nomor - 1] = input.nextInt();

        // Harga tiket sesuai kelas dan tujuan
        if (tujuan[nomor - 1].equalsIgnoreCase("jakarta")) {
            if (kelas[nomor - 1].equalsIgnoreCase("ekonomi")) {
                hargaTiket[nomor - 1] = 75000;
            } else if (kelas[nomor - 1].equalsIgnoreCase("bisnis")) {
                hargaTiket[nomor - 1] = 150000;
            } else if (kelas[nomor - 1].equalsIgnoreCase("executive")) {
                hargaTiket[nomor - 1] = 250000;
            }
        } else if (tujuan[nomor - 1].equalsIgnoreCase("surabaya")) {
            if (kelas[nomor - 1].equalsIgnoreCase("ekonomi")) {
                hargaTiket[nomor - 1] = 100000;
            } else if (kelas[nomor - 1].equalsIgnoreCase("bisnis")
                    || kelas[nomor - 1].equalsIgnoreCase("executive")) {
                hargaTiket[nomor - 1] = 200000;
            }
        }
        totalHarga[nomor - 1] = jumlahTiket[nomor - 1] * hargaTiket[nomor - 1];

        System.out.println("Jadwal berhasil diubah!");
    }

    @Override
    public void jadwalBerangkat() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out
                .println("===========================================================================================");
        System.out
                .println("|                                   ~~~~ EKONOMI ~~~~~                                    |");
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
                .println("| 1  | Senin    |      05.45      |     06.45    |  Surabaya   |  Rp. 5.000   |     -     |");
        System.out
                .println("| 2  | Selasa   |      05.45      |     07.00    |  Madura     |  Rp. 7.000   |     -     |");
        System.out
                .println("| 3  | Rabu     |      06.30      |     07.30    |  Mojokerto  |  Rp. 10.000  |     -     |");
        System.out
                .println("| 4  | Kamis    |      05.45      |     08.45    |  Malang     |  Rp. 35.000  |     -     |");
        System.out
                .println("| 5  | Jumat    |      06.00      |     07.15    |  Jombang    |  Rp. 10.000  |     -     |");
        System.out
                .println("| 6  | Sabtu    |      05.30      |     08.30    |  Kediri     |  Rp. 25.000  |    10%    |");
        System.out
                .println("| 7  | Minggu   |      05.30      |     07.00    |  Sidoarjo   |  Rp. 20.000  |    10%    |");
        System.out
                .println("-------------------------------------------------------------------------------------------");
        System.out.println();
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
        System.out.println(
                "-------------------------------------------------------------------------------------------\"");
        System.out.println();
        System.out.println("Tekan ENTER untuk kembali ke Menu");// Menunggu pengguna menekan tombol ENTER
        input.nextLine();

    }
}
