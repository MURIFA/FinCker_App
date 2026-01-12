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
    // 1. Saldo Utama (Sudah ada)
    public static long saldoUtama = 0; 
    
    // 2. STRUKTUR DATA TRANSAKSI (Tambahan Baru)
    public static class Transaksi {
        String tipe;       // "Pemasukan", "Pengeluaran", "Wishlist"
        String keterangan; 
        long jumlah;
        String tanggal;    
        
        public Transaksi(String tipe, String keterangan, long jumlah, String tanggal) {
            this.tipe = tipe;
            this.keterangan = keterangan;
            this.jumlah = jumlah;
            this.tanggal = tanggal;
        }
    }
    
    // 3. LIST PENYIMPAN RIWAYAT (Global)
    public static ArrayList<Transaksi> riwayat = new ArrayList<>();
    
    // 4. METHOD PENCATAT (Panggil ini dari Dashboard & Wishlist)
    public static void catat(String tipe, String ket, long jumlah) {
        String tgl = java.time.LocalDate.now().toString(); // Ambil tanggal hari ini
        riwayat.add(new Transaksi(tipe, ket, jumlah, tgl));
    }
}
