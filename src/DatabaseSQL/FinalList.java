/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobcandidatedatabse_ia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raghav
 */
public class FinalList extends javax.swing.JFrame {

    /**
     * Creates new form FinalList
     */
    public FinalList() {    
        initComponents();
        Show_Final_table();
    }

    // Removed due to privacy concerns
   String driver = "";
   String url = "";
   String user = "";
   String pass = "";
   
   public ArrayList<User2> userList(){
       ArrayList<User2> userList = new ArrayList<>();
       try {
           Class.forName(driver);
           Connection con = DriverManager.getConnection(url, user, pass);
           String query1 = "SELECT * FROM FINAL_LIST";
           PreparedStatement prepStat = con.prepareStatement(query1);
           ResultSet rs = prepStat.executeQuery();
           User2 user2; 
           
           while(rs.next()){
               user2 = new User2(rs.getInt(
               "Shortlist_ID"), rs.getInt("Job_ID"), rs.getInt("Candidate_ID"));
               userList.add(user2);
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
       return userList;
   }
   
   // following method displays MySQL data into JTable
   public void Show_Final_table(){
       ArrayList<User2> list = userList();
       DefaultTableModel model = (DefaultTableModel) finListTable.getModel();
       Object[] row = new Object[3];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getShortID();
           row[1] = list.get(i).getJobID();
           row[2] = list.get(i).getCandID();
           
           model.addRow((Object[]) row);
       }    
   }
           
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        finListTable = new javax.swing.JTable();
        StoreList = new javax.swing.JButton();
        ExBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setText("Final Shortlist");

        finListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Shortlist ID", "Job ID", "Candidate ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(finListTable);

        StoreList.setBackground(new java.awt.Color(255, 204, 204));
        StoreList.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        StoreList.setText("Store Final Shortlist");
        StoreList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StoreListActionPerformed(evt);
            }
        });

        ExBut.setBackground(new java.awt.Color(255, 204, 204));
        ExBut.setText("Exit");
        ExBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StoreList, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExBut))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel1)
                .addGap(0, 245, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(StoreList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExBut)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExButActionPerformed
        this.setVisible(false);
        AddShortlist addShort = new AddShortlist();
        addShort.setVisible(true);
    }//GEN-LAST:event_ExButActionPerformed

    private void StoreListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StoreListActionPerformed
        // this block of code stores the list of finalised candidates in a remote
        // .txt file
        try {
            File file = new File("/Users/raghav/Desktop/CS IA/JobCandStore.txt ");
        if(!file.exists())
        {
            file.createNewFile();
        } 
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i = 0; i < finListTable.getRowCount(); i++){
           for(int j = 0; j < finListTable.getColumnCount(); j++){
               bw.write(finListTable.getModel().getValueAt(i, j) + " ");
           } 
           bw.write("\n__________\n");                  
        }
        bw.close();
        fw.close(); 
        JOptionPane.showMessageDialog(null, "Records Stored.");
        } catch (Exception e) {
            e.printStackTrace();
        }
   
    }//GEN-LAST:event_StoreListActionPerformed

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
            java.util.logging.Logger.getLogger(FinalList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinalList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinalList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinalList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinalList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExBut;
    private javax.swing.JButton StoreList;
    public javax.swing.JTable finListTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
