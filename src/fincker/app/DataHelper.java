/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fincker.app;

import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author Muham
 */
public class DataHelper {
    
    private static final String FILE_TRANSAKSI = "db_transaksi.txt";
    private static final String FILE_USER = "db_user.txt";

    public static void simpanTransaksi(String tanggal, String tipe, String kategori, String jumlah, String ket) {
        try {
            FileWriter fw = new FileWriter(FILE_TRANSAKSI, true); 
            BufferedWriter bw = new BufferedWriter(fw);
            String data = tanggal + "##" + tipe + "##" + kategori + "##" + jumlah + "##" + ket;
            bw.write(data);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error simpan: " + e.getMessage());
        }
    }

    public static int[] hitungRingkasan() {
        int totalMasuk = 0;
        int totalKeluar = 0;
        
        File f = new File(FILE_TRANSAKSI);
        if (!f.exists()) return new int[]{0,0,0}; 

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_TRANSAKSI));
            String baris;
            while ((baris = br.readLine()) != null) {
                String[] data = baris.split("##");
                if (data.length >= 4) {
                    int nilai = Integer.parseInt(data[3]); 
                    if (data[1].equalsIgnoreCase("Pemasukan")) {
                        totalMasuk += nilai;
                    } else if (data[1].equalsIgnoreCase("Pengeluaran")) {
                        totalKeluar += nilai;
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[]{totalMasuk, totalKeluar, (totalMasuk - totalKeluar)};
    }

    public static void registerUser(String username, String password) {
        try {
            FileWriter fw = new FileWriter(FILE_USER, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(username + "##" + password);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error register: " + e.getMessage());
        }
    }

    public static boolean cekLogin(String username, String password) {
        File f = new File(FILE_USER);
        if (!f.exists()) return false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_USER));
            String baris;
            while ((baris = br.readLine()) != null) {
                String[] data = baris.split("##");
               
                if (data[0].equals(username) && data[1].equals(password)) {
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
