/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newapp;
import com.mysql.cj.x.protobuf.MysqlxConnection;
import java.util.Date;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JOptionPane;
//import org.jdesktop.swingx.JXDatePicker;
/**
 *
 * @author Subhransu das
 */
public class Appointment extends javax.swing.JFrame {

    /**
     * Creates new form Appointment
     */
    public Appointment() {
        initComponents();
        Dashboard D2=new Dashboard();
//        JFrame.getFrames(D2.setVisible(true));
           user();

//setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jLabel3.setText("choose shift");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 81, -1, 30));

        jLabel2.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jLabel2.setText("choose date");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        dateChooserCombo1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        dateChooserCombo1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dateChooserCombo1OnSelectionChange(evt);
            }
        });
        jPanel2.add(dateChooserCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));

        jButton1.setText("make appointment");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 570, 240));

        jLabel1.setFont(new java.awt.Font("Bell MT", 1, 18)); // NOI18N
        jLabel1.setText("Form");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int nu;
    public void user()
    {
        userDao d1 = new userDao();
//        Dashboard dd1=new Dashboard();
           
        d1.Connect();
        User u1 = new User();
        
        Connection conn = d1.conn;
       
        try {

            String myquery = "select * from users where status=?";
            PreparedStatement pst = conn.prepareStatement(myquery);

            pst.setString(1, "true");
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                u1.id=rs.getInt(1);
            }
            pst.close();
            conn.close();
            nu=u1.id;
        }
        catch(Exception e)
        {
            System.out.println("error i dont know");
        }
        
    }
    
    int nd;
    public Doctors doctor(int dd)
    {
        userDao d1=new userDao();
        d1.Connect();
      nd=dd;
        Doctors D1=d1.getDoctors(dd);
        String open=D1.opening_time;
       
//        if(open.contains("am"))
        String Fopen="";
        for(int i=0;i<2;i++)
        {
            Fopen+=open.charAt(i);
        }
        int Gopen=Integer.parseInt(Fopen);
        if(Gopen<12)
        {
            
            jComboBox1.addItem("day");
            
            
        }
        else if(Gopen>12)
        {
            
            jComboBox1.addItem("night");
            
            
        }
        else
        {
            System.out.println("something wrong");
        }
        System.out.println(open);
        return D1;
//        jLabel14.setText(D1.dname);
    }
    
    private void dateChooserCombo1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnSelectionChange
        // TODO add your handling code here:
//        Date date1=dateChooserCombo1.getSelectedDate();
//        dateChooserCombo1.getD
//        
//        dateChooserCombo1.setSelectedDate(dateChooserCombo1.getSelectedDate());
    }//GEN-LAST:event_dateChooserCombo1OnSelectionChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        userDao d1=new userDao();
         d1.Connect();
         Connection conn=d1.conn;
         try
        {
            String myquery="select ID,DID from appoint where ID=? and DID=?";
            System.out.println(myquery);
            
            PreparedStatement pst=conn.prepareStatement(myquery);
            pst.setInt(1, nu);
            pst.setInt(2, nd);
//            pst.setString(3,"false");
            
            ResultSet rs=pst.executeQuery();
            
            if(rs.next())
            {
                
                JOptionPane.showMessageDialog(this, "User already appointed with this doctor");
            }
            else
            {
               d1.setAppoint(nu,nd);
            }
            pst.close();
            conn.close();
        }
        catch(Exception c)
        {
            System.out.println(c);
        }
        
            System.out.println(nd);
           
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Appointment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
