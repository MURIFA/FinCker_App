/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 * Author : Rahmania
*/
package fincker.app;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class wishlistView extends javax.swing.JFrame {

    // --- 1. MODEL & FORMATTER (PERBAIKAN UTAMA) ---
    // Menggunakan WishlistItem, BUKAN String
    private static DefaultListModel<WishlistItem> listModel; 
    
    // Definisi Format Rupiah (Wajib ada biar tidak error)
    java.text.NumberFormat kursIDR = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("id", "ID"));

    private void updateInfoSaldo() {
        // Ambil data dari Pusat
        lblSaldo.setText(kursIDR.format(DataKeuangan.saldoUtama));
    }

    private void tampilkanDetailItem() {
        Object selected = lstWishlist.getSelectedValue();
        
        if (selected instanceof WishlistItem) {
            WishlistItem item = (WishlistItem) selected;
            
            // 1. Hitung Persen
            int persen = item.getProgress();
            
            // 2. Update Progress Bar
            progresTabungan.setValue(persen);
            progresTabungan.setStringPainted(true);
            
            // 3. Update Text Status
            txtStatus.setText("Terkumpul: " + kursIDR.format(item.terkumpul) + 
                              " / " + kursIDR.format(item.targetUang));
            
            // 4. Update Detail Kecil
            lblProgressValue.setText(kursIDR.format(item.terkumpul));
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String tgl = (item.tanggalTarget != null) ? sdf.format(item.tanggalTarget) : "-";
            lblStatusValue.setText("Target: " + tgl);
            
            // Warna Bar
            if (persen >= 100) {
                progresTabungan.setForeground(new java.awt.Color(46, 204, 113)); // Hijau
            } else {
                progresTabungan.setForeground(new java.awt.Color(51, 153, 255)); // Biru
            }
            
        } else {
            resetDetail();
        }
    }

    private void resetDetail() {
        progresTabungan.setValue(0);
        if (lblProgresPersen != null) lblProgresPersen.setText(""); 
        txtStatus.setText("Status: -");
        lblProgressValue.setText("-");
        lblStatusValue.setText("-");
    }

    private void updateTampilan() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void initTanggal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void refreshUI() {
        // 1. UPDATE LIST (PENTING: Pakai setElementAt biar teksnya berubah)
        int index = lstWishlist.getSelectedIndex();
        if (index != -1) {
            listModel.setElementAt(listModel.getElementAt(index), index);
        } else {
            lstWishlist.repaint();
        }
        
        // 2. Update Tampilan Bawah (Bar & Persen)
        tampilkanDetailItem();      
        
        // 3. Update Saldo Atas (PENTING: Biar saldo berkurang saat nabung)
        updateInfoSaldo();
    }

    // --- 2. CLASS DATA BARANG (Inner Class) ---
    // Hapus 'static' agar bisa akses kursIDR

    private static class WishlistItem {
        String nama;
        long targetUang;  // Pakai LONG agar muat Triliunan
        long terkumpul;   // Pakai LONG
        Date tanggalTarget; // Pakai Date biar enak diolah

        public WishlistItem(String nama, long targetUang, Date tanggalTarget) {
            this.nama = nama;
            this.targetUang = targetUang;
            this.tanggalTarget = tanggalTarget;
            this.terkumpul = 0;
        }
 
 @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            String tglStr = (tanggalTarget != null) ? sdf.format(tanggalTarget) : "-";
            return nama + " (Target: " + tglStr + ")";
        }
        
        public int getProgress() {
            if (targetUang == 0) return 0;
            return (int) (((double) terkumpul / targetUang) * 100);
        }
    }
 
    public wishlistView() {
        initComponents();
        
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        
        // --- LOGIKA AGAR DATA TIDAK HILANG (PERSISTENCE) ---
        // Cek apakah listModel sudah ada di memori static?
        if (listModel == null) {
            // Jika belum ada (aplikasi baru buka), buat baru
            listModel = new DefaultListModel();
        }
        
        // Pasangkan model ke List
        // Kita pakai casting (javax.swing.ListModel) biar tidak eror merah
        lstWishlist.setModel((javax.swing.ListModel) listModel);

        // Update Saldo Awal (Ambil dari DataKeuangan)
        updateInfoSaldo();

        // Listener: Deteksi Klik pada List
        lstWishlist.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                tampilkanDetailItem();
            }
        });
        
        // --- RESET TAMPILAN AWAL ---
        if (progresTabungan != null) progresTabungan.setValue(0);
        if (txtStatus != null) txtStatus.setText("Status: -");
        
        // SESUAI REQUEST: Label persen dikosongkan (bukan "0%") biar bersih
        if (lblProgresPersen != null) lblProgresPersen.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel() {
            // 1. Muat Gambar (Ganti nama file sesuai punyamu)
            java.awt.Image backgroundImage = new javax.swing.ImageIcon(getClass().getResource("/fincker/app/gambar/bg-wishlist.gif")).getImage();

            // 2. Timpa cara menggambar Panel
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g); // Gambar panel dasar dulu

                // Gambar background melar memenuhi panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        pnlNavbar = new javax.swing.JPanel();
        btnBeranda = new javax.swing.JButton();
        btnTentang = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        btnTabungan = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        pnlTabungan = new javax.swing.JPanel();
        pnlWishlist = new javax.swing.JPanel();
        scpWishlist = new javax.swing.JScrollPane();
        lstWishlist = new javax.swing.JList<>();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        pnlProgres = new javax.swing.JPanel();
        txtProgres = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        lblProgresPersen = new javax.swing.JLabel();
        progresTabungan = new javax.swing.JProgressBar();
        btnAdd = new javax.swing.JButton();
        btnMin = new javax.swing.JButton();
        pnlInput = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblNamaBarang = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        txtBarang = new javax.swing.JTextField();
        lblProgressValue = new javax.swing.JLabel();
        lblStatusValue = new javax.swing.JLabel();
        lblHargaBarang = new javax.swing.JLabel();
        txtBarang1 = new javax.swing.JTextField();
        dcTarget = new com.toedter.calendar.JDateChooser();
        lblDaftarWishlist = new javax.swing.JLabel();
        lblHeader = new javax.swing.JLabel();
        lblSaldoku = new javax.swing.JPanel();
        txtSaldo = new javax.swing.JTextField();
        pnlSaldo = new javax.swing.JPanel();
        lblSaldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.gray));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlNavbar.setBackground(new java.awt.Color(153, 255, 255));
        pnlNavbar.setOpaque(false);
        pnlNavbar.setPreferredSize(new java.awt.Dimension(1200, 70));

        btnBeranda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBeranda.setText("Beranda");
        btnBeranda.setBorderPainted(false);
        btnBeranda.setContentAreaFilled(false);
        btnBeranda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBeranda.setFocusPainted(false);
        btnBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBerandaActionPerformed(evt);
            }
        });

        btnTentang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTentang.setText("Tentang");
        btnTentang.setBorderPainted(false);
        btnTentang.setContentAreaFilled(false);
        btnTentang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTentang.setFocusPainted(false);
        btnTentang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTentangActionPerformed(evt);
            }
        });

        btnLaporan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLaporan.setText("Laporan");
        btnLaporan.setBorderPainted(false);
        btnLaporan.setContentAreaFilled(false);
        btnLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaporan.setFocusPainted(false);
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });

        btnTabungan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTabungan.setText("Tabungan");
        btnTabungan.setBorderPainted(false);
        btnTabungan.setContentAreaFilled(false);
        btnTabungan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTabungan.setFocusPainted(false);
        btnTabungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabunganActionPerformed(evt);
            }
        });

        btnProfile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnProfile.setText("Akun");
        btnProfile.setBorderPainted(false);
        btnProfile.setContentAreaFilled(false);
        btnProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfile.setFocusPainted(false);
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNavbarLayout = new javax.swing.GroupLayout(pnlNavbar);
        pnlNavbar.setLayout(pnlNavbarLayout);
        pnlNavbarLayout.setHorizontalGroup(
            pnlNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavbarLayout.createSequentialGroup()
                .addContainerGap(326, Short.MAX_VALUE)
                .addComponent(btnBeranda)
                .addGap(97, 97, 97)
                .addComponent(btnTentang, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(btnLaporan)
                .addGap(110, 110, 110)
                .addComponent(btnTabungan)
                .addGap(91, 91, 91)
                .addComponent(btnProfile)
                .addGap(81, 81, 81))
        );
        pnlNavbarLayout.setVerticalGroup(
            pnlNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavbarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlNavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBeranda, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTentang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLaporan)
                    .addComponent(btnTabungan)
                    .addComponent(btnProfile))
                .addGap(32, 32, 32))
        );

        jPanel1.add(pnlNavbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 80));

        pnlTabungan.setBackground(new java.awt.Color(255, 255, 255));
        pnlTabungan.setOpaque(false);
        pnlTabungan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlWishlist.setBackground(new java.awt.Color(0, 102, 102));
        pnlWishlist.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, java.awt.Color.lightGray, java.awt.Color.darkGray));
        pnlWishlist.setLayout(new java.awt.GridBagLayout());

        lstWishlist.setBackground(new java.awt.Color(204, 255, 204));
        lstWishlist.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstWishlist.setModel(new javax.swing.AbstractListModel<Object>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scpWishlist.setViewportView(lstWishlist);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 374;
        gridBagConstraints.ipady = 244;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        pnlWishlist.add(scpWishlist, gridBagConstraints);

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 37, 16, 0);
        pnlWishlist.add(btnEdit, gridBagConstraints);

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 215, 16, 0);
        pnlWishlist.add(btnHapus, gridBagConstraints);

        pnlTabungan.add(pnlWishlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 540, 400));

        pnlProgres.setBackground(new java.awt.Color(0, 102, 102));
        pnlProgres.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 1, 2, new java.awt.Color(204, 204, 204)));

        txtProgres.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtProgres.setForeground(new java.awt.Color(255, 255, 255));
        txtProgres.setText("Progres Mu");

        txtStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtStatus.setForeground(new java.awt.Color(255, 255, 255));
        txtStatus.setText("Status :");

        lblProgresPersen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblProgresPersen.setForeground(new java.awt.Color(255, 255, 255));
        lblProgresPersen.setText("0%");

        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnMin.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnMin.setText("-");
        btnMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProgresLayout = new javax.swing.GroupLayout(pnlProgres);
        pnlProgres.setLayout(pnlProgresLayout);
        pnlProgresLayout.setHorizontalGroup(
            pnlProgresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProgresLayout.createSequentialGroup()
                .addGroup(pnlProgresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProgresLayout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(txtProgres))
                    .addGroup(pnlProgresLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(txtStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProgresPersen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlProgresLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(progresTabungan, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(pnlProgresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMin, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        pnlProgresLayout.setVerticalGroup(
            pnlProgresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProgresLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlProgresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProgresLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnMin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(pnlProgresLayout.createSequentialGroup()
                        .addComponent(txtProgres)
                        .addGap(18, 18, 18)
                        .addComponent(progresTabungan, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlProgresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStatus)
                            .addComponent(lblProgresPersen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );

        pnlTabungan.add(pnlProgres, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 550, 120));

        pnlInput.setBackground(new java.awt.Color(153, 204, 255));
        pnlInput.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.darkGray, java.awt.Color.lightGray, java.awt.Color.darkGray));
        pnlInput.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        pnlInput.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 60, -1));

        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset.setText("Hapus");
        btnReset.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        pnlInput.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 70, -1));

        lblNamaBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNamaBarang.setText("Nama Barang  ");
        pnlInput.add(lblNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        lblTanggal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTanggal.setText("Target Terkumpul");
        pnlInput.add(lblTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        txtBarang.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        pnlInput.add(txtBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 320, 30));
        pnlInput.add(lblProgressValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 160, -1));
        pnlInput.add(lblStatusValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 220, -1));

        lblHargaBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHargaBarang.setText("Harga");
        pnlInput.add(lblHargaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        txtBarang1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        pnlInput.add(txtBarang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 320, 30));
        pnlInput.add(dcTarget, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 320, 30));

        pnlTabungan.add(pnlInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 550, 240));

        lblDaftarWishlist.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDaftarWishlist.setForeground(new java.awt.Color(255, 255, 255));
        lblDaftarWishlist.setText("Daftar Wishlist");
        pnlTabungan.add(lblDaftarWishlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, -1, -1));

        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setText("Mau Nabung Apa ?");
        pnlTabungan.add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 170, 30));

        lblSaldoku.setBackground(new java.awt.Color(255, 255, 255));
        lblSaldoku.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSaldo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSaldo.setText("SALDO");
        txtSaldo.setBorder(null);
        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });

        pnlSaldo.setBackground(new java.awt.Color(255, 255, 255));
        pnlSaldo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pnlSaldo.setLayout(new java.awt.GridBagLayout());

        lblSaldo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblSaldo.setText("0");
        pnlSaldo.add(lblSaldo, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout lblSaldokuLayout = new javax.swing.GroupLayout(lblSaldoku);
        lblSaldoku.setLayout(lblSaldokuLayout);
        lblSaldokuLayout.setHorizontalGroup(
            lblSaldokuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblSaldokuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        lblSaldokuLayout.setVerticalGroup(
            lblSaldokuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblSaldokuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lblSaldokuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pnlTabungan.add(lblSaldoku, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 550, 40));

        jPanel1.add(pnlTabungan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1280, 640));

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
    try {
            String nama = txtBarang.getText().trim();
            Date tgl = dcTarget.getDate(); // Ambil dari JDateChooser
            
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama barang harus diisi!");
                return;
            }
            if (tgl == null) {
                JOptionPane.showMessageDialog(this, "Pilih target tanggal dulu!");
                return;
            }
            
            // Ambil Harga (Pakai LONG)
            long harga = Long.parseLong(txtBarang1.getText().trim());
            
            if (harga <= 0) {
                JOptionPane.showMessageDialog(this, "Harga harus lebih dari 0!");
                return;
            }

            // Simpan Data
            WishlistItem itemBaru = new WishlistItem(nama, harga, tgl);
            listModel.addElement(itemBaru);
            
            // Reset Form
            btnResetActionPerformed(null);
            
            JOptionPane.showMessageDialog(this, "Berhasil menambahkan " + nama);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Kolom Harga harus angka (Tanpa titik/koma)!");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
     // --- LOGIKA RESET FORM INPUT ---
        
        txtBarang.setText("");   
        txtBarang1.setText("");  
        dcTarget.setDate(null); // Kosongkan Tanggal
        txtBarang.requestFocus();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
 
        // --- LOGIKA EDIT DATA ---
        
        Object selected = lstWishlist.getSelectedValue();
        if (selected == null || !(selected instanceof WishlistItem)) {
            JOptionPane.showMessageDialog(this, "Pilih barang dulu!");
            return;
        }
        WishlistItem item = (WishlistItem) selected;

        // 1. Edit Nama
        String namaBaru = JOptionPane.showInputDialog(this, "Ubah Nama:", item.nama);
        if (namaBaru != null && !namaBaru.isEmpty()) item.nama = namaBaru;

        // 2. Edit Harga (LONG)
        String hargaStr = JOptionPane.showInputDialog(this, "Ubah Harga (Rp):", item.targetUang);
        if (hargaStr != null && !hargaStr.isEmpty()) {
            try {
                long hargaBaru = Long.parseLong(hargaStr);
                if (hargaBaru < item.terkumpul) {
                     JOptionPane.showMessageDialog(this, "Gagal! Harga baru lebih kecil dari uang terkumpul!");
                } else {
                    item.targetUang = hargaBaru;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Harga harus angka!");
            }
        }
        // 3. Refresh
        refreshUI();
        JOptionPane.showMessageDialog(this, "Data diperbarui!");
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
 // --- LOGIKA HAPUS DATA ---
        
       int index = lstWishlist.getSelectedIndex();
        Object selected = lstWishlist.getSelectedValue();
        
        if (index != -1 && selected instanceof WishlistItem) {
            WishlistItem item = (WishlistItem) selected;
            if (item.terkumpul > 0) {
                int confirm = JOptionPane.showConfirmDialog(this, 
                        "Masih ada saldo " + kursIDR.format(item.terkumpul) + ".\nHapus akan menghanguskan uang ini. Lanjut?", 
                        "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;
            }
            listModel.remove(index);
            resetDetail();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih item dulu.");
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBerandaActionPerformed
        // TODO add your handling code here:
        // 1. Panggil method loadData() yang sudah kita buat sebelumnya
        // Karena kita sudah ada di Beranda, cukup refresh data saja
        new dashboardView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBerandaActionPerformed

    private void btnTentangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTentangActionPerformed
        // TODO add your handling code here:
        // 1. Buka View Tujuan
        new tentangView().setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btnTentangActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        // TODO add your handling code here:
        new laporanView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void btnTabunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabunganActionPerformed
        // TODO add your handling code here:
        updateTampilan();
        initTanggal();
        javax.swing.JOptionPane.showMessageDialog(this, "Tampilan berhasil diperbarui!");
    }//GEN-LAST:event_btnTabunganActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
        new profileView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinActionPerformed
        // TODO add your handling code here:
        // 1. Cek User pilih barang mana
        Object selected = lstWishlist.getSelectedValue();
        if (selected == null || !(selected instanceof WishlistItem)) {
            JOptionPane.showMessageDialog(this, "Pilih barang dulu!");
            return;
        }
        WishlistItem item = (WishlistItem) selected;
        
        // 2. Input Dialog
        String input = JOptionPane.showInputDialog(this, "Tarik kembali berapa dari " + item.nama + "?");
        if (input != null && !input.isEmpty()) {
            try {
                long nominal = Long.parseLong(input);
                
                // 3. Cek Uang di Barang Cukup Gak?
                if (item.terkumpul < nominal) {
                    JOptionPane.showMessageDialog(this, "Uang di tabungan barang ini kurang!");
                    return;
                }
                
                // 4. Transaksi
                item.terkumpul -= nominal;          // Kurangi dari Barang
                DataKeuangan.saldoUtama += nominal; // Balikin ke Saldo Pusat
                
                // --- TAMBAHAN: CATAT KE LAPORAN ---
                DataKeuangan.catat("Pemasukan", "Tarik Tabungan " + item.nama, nominal);
                
                // 5. Refresh UI
                lstWishlist.repaint();
                tampilkanDetailItem();
                updateInfoSaldo();
                
                JOptionPane.showMessageDialog(this, "Berhasil menarik " + kursIDR.format(nominal));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan angka saja!");
            }
        }
    }//GEN-LAST:event_btnMinActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        // 1. Cek User pilih barang mana
        Object selected = lstWishlist.getSelectedValue();
        if (selected == null || !(selected instanceof WishlistItem)) {
            JOptionPane.showMessageDialog(this, "Pilih barang di list kanan dulu!");
            return;
        }
        WishlistItem item = (WishlistItem) selected;
        
        // --- LOGIKA BARU 1: CEK APAKAH SUDAH LUNAS? ---
        // Kalau sudah penuh, langsung tolak. Jangan tanya mau nabung berapa.
        if (item.terkumpul >= item.targetUang) {
            JOptionPane.showMessageDialog(this, 
                "Eits! Barang ini sudah LUNAS.\nTidak perlu menabung lagi ya! 😊");
            return; // STOP DI SINI
        }
        
        // 2. Input Dialog
        String input = JOptionPane.showInputDialog(this, "Nabung berapa untuk " + item.nama + "?");
        
        if (input != null && !input.isEmpty()) {
            try {
                input = input.replaceAll("[^0-9]", ""); 
                long nominal = Long.parseLong(input);
                
                // --- LOGIKA BARU 2: CEK KELEBIHAN BAYAR ---
                long sisaKekurangan = item.targetUang - item.terkumpul;
                
                if (nominal > sisaKekurangan) {
                    JOptionPane.showMessageDialog(this, 
                        "Ups, kebanyakan! Kamu cuma butuh " + kursIDR.format(sisaKekurangan) + " lagi.\n" +
                        "Silakan masukkan nominal yang pas atau kurang dari itu.");
                    return; // STOP, Jangan kurangi saldo
                }
                
                // 3. Cek Saldo Utama (Cukup gak?)
                if (DataKeuangan.saldoUtama < nominal) {
                    JOptionPane.showMessageDialog(this, "Saldo Utama tidak cukup!");
                    return;
                }
                
                // 4. TRANSAKSI BERHASIL
                DataKeuangan.saldoUtama -= nominal; 
                item.terkumpul += nominal;   
                
                // --- TAMBAHAN: CATAT KE LAPORAN ---
                DataKeuangan.catat("Wishlist", "Nabung: " + item.nama, nominal);
                
                // Cek jika pas lunas (Tanpa logika kembalian lagi karena sudah dicek di atas)
                if (item.terkumpul == item.targetUang) {
                     JOptionPane.showMessageDialog(this, "🎉 Selamat! Target " + item.nama + " Tercapai!");
                }

                // 5. Update Tampilan
                refreshUI(); 
                lblSaldo.setText(kursIDR.format(DataKeuangan.saldoUtama));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!");
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActionPerformed

            /**
             * @param args the command line arguments
             */
            public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(wishlistView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new wishlistView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnMin;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTabungan;
    private javax.swing.JButton btnTentang;
    private com.toedter.calendar.JDateChooser dcTarget;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDaftarWishlist;
    private javax.swing.JLabel lblHargaBarang;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblNamaBarang;
    private javax.swing.JLabel lblProgresPersen;
    private javax.swing.JLabel lblProgressValue;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JPanel lblSaldoku;
    private javax.swing.JLabel lblStatusValue;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JList<Object> lstWishlist;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlNavbar;
    private javax.swing.JPanel pnlProgres;
    private javax.swing.JPanel pnlSaldo;
    private javax.swing.JPanel pnlTabungan;
    private javax.swing.JPanel pnlWishlist;
    private javax.swing.JProgressBar progresTabungan;
    private javax.swing.JScrollPane scpWishlist;
    private javax.swing.JTextField txtBarang;
    private javax.swing.JTextField txtBarang1;
    private javax.swing.JLabel txtProgres;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables

}

