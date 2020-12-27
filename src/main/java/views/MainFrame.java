/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import gauss.Solve;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import utils.FileUtils;

/**
 *
 * @author OS
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    private String fileName;
    private final String SPACES = "  ";
    private double[][] matrix;
    private Container contentPant;

    public MainFrame() {
        initComponents();
        initComponentManually();
        initEvents();
    }

    private void initEvents() {
        btUploadEvent();
        btSolveEvent();
        btClearEvent();
        btFinishEvent();
    }

    private void btFinishEvent() {
        btFinish.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    Thread.sleep(2000);
                    System.exit(0);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void btClearEvent() {
        btClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PnCenter.removeAll();
                repaint();
                revalidate();
                taResult.setText("");
            }
        });
    }

    private void btSolveEvent() {
        btSolve.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String line = "";
                if (matrix == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập input");
                    return;
                }
               
                double[] result = Solve.solveEquations(matrix);
               
                if (result == null) {
                    taResult.setText("Hệ vô nghiệm");
                    return;
                }
                for (int i = 0; i < result.length; i++) {
                    line += "x" + i + "= " + result[i] + SPACES;
                }
                taResult.setText(line);
            }
        });
    }

    private void btUploadEvent() {
        btUpload.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PnCenter.removeAll();
                String path = getClass().getResource("/input").getFile();
                JFileChooser chooser = new JFileChooser(path);
                if (JFileChooser.APPROVE_OPTION == chooser.showDialog(null, "Upload")) {
                    String regex = "[\\w-]+[.]{1}(?i)(?:txt)";
                    File sourceFile = chooser.getSelectedFile();
                    fileName = sourceFile.getName();
                    if (!fileName.matches(regex)) {
                        JOptionPane.showMessageDialog(null, "File không hợp lệ");
                        return;
                    }
                    String inputPath = path + File.separator + fileName;
                    try {
                        matrix = FileUtils.loadData(FileUtils.readFile(inputPath));
                        GridLayout gridLayout = new GridLayout(matrix.length, matrix[0].length);
                        PnCenter.setLayout(gridLayout);
                        for (double[] arr : matrix) {
                            String line = "";
                            for (int i = 0; i < arr.length; i++) {
                                String text = "";
                                if (i == arr.length - 1) {
                                    text = "=" + SPACES + String.valueOf(arr[i]);
                                } else {
                                    text = String.valueOf(arr[i]) + "x" + i;
                                }
                                JLabel lbNumber = new JLabel();
                                lbNumber.setText(text);
                                PnCenter.add(lbNumber);
                            }
                        }
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, "Input không hợp lệ");
                        return;
                    } finally {
                        repaint();
                        revalidate();
                    }

                }
            }
        });
    }

    private void initComponentManually() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPant = getContentPane();
        contentPant.setBackground(Color.WHITE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnTop = new javax.swing.JPanel();
        btUpload = new javax.swing.JButton();
        btSolve = new javax.swing.JButton();
        btClear = new javax.swing.JButton();
        btFinish = new javax.swing.JButton();
        PnCenter = new javax.swing.JPanel();
        PnBottom = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taResult = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btUpload.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/upload.png"))); // NOI18N
        btUpload.setText("UPLOAD");
        btUpload.setFocusPainted(false);
        PnTop.add(btUpload);

        btSolve.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btSolve.setText("SOLVE");
        btSolve.setFocusPainted(false);
        PnTop.add(btSolve);

        btClear.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btClear.setText("CLEAR");
        btClear.setFocusPainted(false);
        PnTop.add(btClear);

        btFinish.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btFinish.setText("FINISH");
        btFinish.setFocusPainted(false);
        PnTop.add(btFinish);

        getContentPane().add(PnTop, java.awt.BorderLayout.PAGE_START);

        PnCenter.setBackground(new java.awt.Color(255, 255, 255));
        PnCenter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giải hệ phương trình", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        PnCenter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout PnCenterLayout = new javax.swing.GroupLayout(PnCenter);
        PnCenter.setLayout(PnCenterLayout);
        PnCenterLayout.setHorizontalGroup(
            PnCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        PnCenterLayout.setVerticalGroup(
            PnCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        getContentPane().add(PnCenter, java.awt.BorderLayout.CENTER);

        PnBottom.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kết quả", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 0, 153))); // NOI18N
        PnBottom.setLayout(new java.awt.BorderLayout());

        taResult.setColumns(20);
        taResult.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        taResult.setLineWrap(true);
        taResult.setRows(5);
        taResult.setWrapStyleWord(true);
        taResult.setFocusable(false);
        jScrollPane2.setViewportView(taResult);

        PnBottom.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(PnBottom, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnBottom;
    private javax.swing.JPanel PnCenter;
    private javax.swing.JPanel PnTop;
    private javax.swing.JButton btClear;
    private javax.swing.JButton btFinish;
    private javax.swing.JButton btSolve;
    private javax.swing.JButton btUpload;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taResult;
    // End of variables declaration//GEN-END:variables
}
