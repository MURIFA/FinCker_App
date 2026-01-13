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
    public static long saldoUtama = 0; 
    
    public static class Transaksi {
        String tipe;      
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
    
    public static ArrayList<Transaksi> riwayat = new ArrayList<>();
    
    public static void catat(String tipe, String ket, long jumlah) {
        String tgl = java.time.LocalDate.now().toString(); 
        riwayat.add(new Transaksi(tipe, ket, jumlah, tgl));
    }
}
