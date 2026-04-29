package com.toko;

import com.toko.data.Barang;
import com.toko.data.Manager;
import com.toko.data.kategoriBarang;
import java.util.*;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int pilihan = 0;

        while (pilihan != 6) {
            System.out.println("\n=== Sistem Manajemen Stok Toko Elektronik ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Tampilkan Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Update Barang");
            System.out.println("5. Cari Barang");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    addMenu();
                    break;

                case 2:
                    Manager.cetakBaris(Manager.load(Manager.filePath));
                    break;

                case 3:
                    System.out.print("Masukkan nama barang yang akan dihapus: ");
                    String key = input.nextLine();
                    Manager.delete(key);
                    break;

                case 4:
                    System.out.print("Masukkan ID barang yang akan diupdate: ");
                    int key2 = input.nextInt();
                    input.nextLine();
                    Manager.edit(key2);
                    break;

                case 5:
                    menuCari();
                    break;

                case 6:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    static void addMenu() throws Exception {
        List<Barang> list = Manager.load(Manager.filePath);

        System.out.print("Masukkan nama barang: ");
        String nama = input.nextLine();

        System.out.print("Masukkan stok barang: ");
        int stok = Integer.parseInt(input.nextLine());

        System.out.println("Masukkan kategori barang:");
        kategoriBarang.showKategori();

        System.out.print("Pilihan kategori: ");
        int pil = Integer.parseInt(input.nextLine());

        kategoriBarang kategori = kategoriBarang.dariNomor(pil);

        System.out.print("Apakah barang tersedia? (true/false): ");
        boolean status = Boolean.parseBoolean(input.nextLine());

        list.add(new Barang(Manager.getNextId(list), nama, stok, kategori, status));

        Manager.save(list);
        Manager.cetakBaris(list);
    }

    static void menuCari() throws Exception {
        int pilihCari;

        System.out.println("\n=== MENU SEARCH ===");
        System.out.println("1. Cari berdasarkan Nama");
        System.out.println("2. Cari berdasarkan ID");
        System.out.println("3. Cari berdasarkan Kategori");
        System.out.println("4. Cari berdasarkan Status");
        System.out.print("Pilih pencarian (1-4): ");

        pilihCari = input.nextInt();
        input.nextLine();

        switch (pilihCari) {
            case 1:
                cariNama();
                break;
            case 2:
                cariId();
                break;
            case 3:
                cariKategori();
                break;
            case 4:
                cariStatus();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    static void cariNama() throws Exception {
        List<Barang> list = Manager.load(Manager.filePath);

        System.out.print("Masukkan nama barang: ");
        String keyword = input.nextLine();

        boolean ditemukan = false;

        for (Barang b : list) {
            if (b.nama.toLowerCase().contains(keyword.toLowerCase())) {
                tampilData(b);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    static void cariId() throws Exception {
        List<Barang> list = Manager.load(Manager.filePath);

        System.out.print("Masukkan ID barang: ");
        int idCari = input.nextInt();
        input.nextLine();

        boolean ditemukan = false;

        for (Barang b : list) {
            if (b.id == idCari) {
                tampilData(b);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    static void cariKategori() throws Exception {
        List<Barang> list = Manager.load(Manager.filePath);

        System.out.print("Masukkan kategori barang: ");
        String kategoriCari = input.nextLine();

        boolean ditemukan = false;

        for (Barang b : list) {
            if (b.kategori.toString().equalsIgnoreCase(kategoriCari)) {
                tampilData(b);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    static void cariStatus() throws Exception {
        List<Barang> list = Manager.load(Manager.filePath);

        System.out.print("Masukkan status (true/false): ");
        boolean statusCari = Boolean.parseBoolean(input.nextLine());

        boolean ditemukan = false;

        for (Barang b : list) {
            if (b.status == statusCari) {
                tampilData(b);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    static void tampilData(Barang b) {
        System.out.println("----------------------------------");
        System.out.println("ID       : " + b.id);
        System.out.println("Nama     : " + b.nama);
        System.out.println("Stok     : " + b.stok);
        System.out.println("Kategori : " + b.kategori);
        System.out.println("Status   : " + b.status);
    }
}