/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fincker.app;

import java.util.ArrayList;

/**
 *
 * @author Muham
 */
public class DataKeuangan {
    // Saldo Utama (Sudah ada)
    public static long saldoUtama = 0; 
    public static long totalPemasukan = 0;
    public static long totalPengeluaran = 0;
    
    // --- TAMBAHAN BARU: STRUKTUR DATA TRANSAKSI ---
    
    // Class kecil untuk menampung 1 baris transaksi
    public static class Transaksi {
        String tipe;       // "Masuk", "Keluar", "Wishlist"
        String keterangan; // Contoh: "Uang Jajan", "Beli Bakso", "Nabung iPhone"
        long jumlah;
        String tanggal;    // Kita pakai string sederhana dulu
        
        public Transaksi(String tipe, String keterangan, long jumlah, String tanggal) {
            this.tipe = tipe;
            this.keterangan = keterangan;
            this.jumlah = jumlah;
            this.tanggal = tanggal;
        }
    }
    
    // List untuk menyimpan SEMUA riwayat
    public static ArrayList<Transaksi> riwayat = new ArrayList<>();
    
    // Method Helper untuk Catat Transaksi (Panggil ini setiap ada uang gerak)
    public static void catat(String tipe, String ket, long jumlah) {
        // Ambil tanggal hari ini (Format simpel)
        String tgl = java.time.LocalDate.now().toString(); 
        riwayat.add(new Transaksi(tipe, ket, jumlah, tgl));
    }
}
