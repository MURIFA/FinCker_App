/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fincker.app;

/**
 *
 * @author USER
 */
public class FinCkerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // --- BAGIAN 1: MEMPERCANTIK TAMPILAN (LOOK AND FEEL) ---
        // Kode ini membuat tombol dan inputan terlihat lebih modern (gaya Nimbus)
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) { // Kamu juga bisa ganti "Windows" jika pakai OS Windows
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinCkerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // --- BAGIAN 2: MENJALANKAN APLIKASI ---
        // Menggunakan invokeLater agar aplikasi GUI berjalan stabil (Thread Safe)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        // Panggil WelcomeView, bukan LoginView
        new welcomeView().setVisible(true);
    }
});
    }
    
}