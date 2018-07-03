/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SOUMIK
 */
import com.sun.glass.events.KeyEvent;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
public class client extends javax.swing.JFrame {

    /**
     * Creates new form client
     */
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;
    static int i=0;
    static String ip;
    static int port=-1;
    static String name;
    static String msg;
    static String msg2;
    static String msg3;
    static boolean conn_state=false;
    static boolean dis_state=false;
    static String str="";
    static String p;
    static String q;
    static String q2;
    static String str2;
    static int key=14;
    static int keylength=10;
    public client() {
        initComponents();
        setLocationRelativeTo(null);
    }
    public class clientcomm implements Runnable
    {
        int j;
        clientcomm()
        {
            j=i;
            i++;
        }
        @Override
        public void run()
        {
            if(this.j==0)
                chat();
            else if(this.j==1)
                writename();
            else if(this.j==2)
                read();
            else
                write();
        }
        public void chat()
        {
            try
            {
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                        jTextArea2.append("Attempting to connect to chat server with IP Address :-"+ip+":"+port+".....\n");
                    }
                });
                s=new Socket(ip,port);
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    jTextArea2.append("Successfully connected with server.\n");
                    }
                });
                conn_state=true;
                dos=new DataOutputStream(s.getOutputStream());
                clientcomm objf = new clientcomm();
                Thread tf = new Thread(objf);
                tf.start();
                clientcomm obj2 = new clientcomm();
                Thread t2 = new Thread(obj2);
                t2.start();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                error1();
            }
        }
        public void writename()
        {
            try
            {
                dos.writeUTF(name);
                dos.flush();   
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
        public void read()
        {
            try
            {
                dis=new DataInputStream(s.getInputStream());
                dis_state=true;
                while(true)
                {
                    //System.out.println("Reding");
                    if(dis_state!=false)
                    str=dis.readUTF();
                    if(str.equalsIgnoreCase(""))
                        ;
                    else
                    {
                        int pos=str.indexOf(": ");
                        p=str.substring(0,pos+2);
                        q=str.substring(pos+2);
                        System.out.println(str);
                        boolean start,end;
                        start = str.startsWith("Client with IP Address :- ");
                        end = str.endsWith(" has left the chat.");
                        System.out.println(start+";"+end);
                        if(!(start&&end))
                        {
                            decrypt();                        
                            str2=p+q2;
                        }
                        else
                            str2=""+str;
                        SwingUtilities.invokeAndWait(new Runnable() {
                            public void run() {
                                jTextArea2.append(str2+"\n");
                            }
                        });
                        System.out.println(str2);
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                error2();
            }
        }
        public void write()
        {
            try
            {
                msg2=msg;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        jTextArea2.append(name+" : "+msg2+"\n");
                    }
                });
                encrypt();
                dos.writeUTF(msg3);
                dos.flush();
                if(msg.equalsIgnoreCase("exit"))
                {
                    dos.close();
                    dis.close();
                    conn_state=false;
                    dis_state=false;
                    s.close();
                    System.exit(0);
                }
                //dos.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        public void encrypt()
        {
            int i;
            msg3="";
            for(i=0;i<msg.length();i++)
            {
                int ch=msg.charAt(i);
                ch=ch^key;
                msg3=msg3+(char)ch;
            }
        }
        public void decrypt()
        {
            int i;
            q2="";
            for(i=0;i<q.length();i++)
            {
                int ch=q.charAt(i);
                ch=ch^key;
                q2=q2+(char)ch;
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        conn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField4 = new javax.swing.JTextField();
        send = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat Client");

        jLabel1.setText("IP Address");

        jLabel2.setText("Port");

        jLabel3.setText("Username");

        conn.setText("Connect");
        conn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connActionPerformed(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField4)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                .addComponent(jTextField1))
                            .addGap(42, 42, 42)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField2))
                                .addComponent(conn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(send)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void error1()
    {
        JOptionPane.showMessageDialog(this,"Could not connect with the server. The server may be currently down. Otherwise, please ensure that the IP Address and Port fields are correct.","Error",JOptionPane.ERROR_MESSAGE);
        i=0;
    }
    public void error2()
    {
        JOptionPane.showMessageDialog(this,"Your connection to the server was interrupted. Click on \"OK\" to terminate.","Error",JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
    private void connActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connActionPerformed
        // TODO add your handling code here:
        if(conn_state==true)
            return;
        try
        {
            ip=jTextField1.getText();
            port=Integer.parseInt(jTextField2.getText());
            name=jTextField3.getText();
            if(ip==null)
            {
                JOptionPane.showMessageDialog(this,"IP Address field cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(port==-1)
            {
                JOptionPane.showMessageDialog(this,"Port field cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(name==null)
            {
                JOptionPane.showMessageDialog(this,"Username field cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
            }
            clientcomm obj1=new clientcomm();
            Thread t1=new Thread(obj1);
            t1.start();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Invalid IP Address or Port No.","Error",JOptionPane.ERROR_MESSAGE);
            i=0;
        }
    }//GEN-LAST:event_connActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
        if(conn_state==false)
            JOptionPane.showMessageDialog(this,"You need to be connected with the server before sending any message.","Error",JOptionPane.ERROR_MESSAGE);
        else
        {
            msg=jTextField4.getText();
            if(msg.equalsIgnoreCase(""))
                return;
            if(msg.trim().length()==0)
                return;
            clientcomm obj3 = new clientcomm();
            Thread t3 = new Thread(obj3);
            t3.start();
        }
        jTextField4.setText("");
    }//GEN-LAST:event_sendActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(conn_state==false)
                JOptionPane.showMessageDialog(this,"You need to be connected with the server before sending any message.","Error",JOptionPane.ERROR_MESSAGE);
            else
            {
                msg=jTextField4.getText();
                if(msg.equalsIgnoreCase(""))
                    return;
                if(msg.trim().length()==0)
                    return;
                clientcomm obj3 = new clientcomm();
                Thread t3 = new Thread(obj3);
                t3.start();
            }
            jTextField4.setText("");
        }
    }//GEN-LAST:event_jTextField4KeyReleased
    
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
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton conn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton send;
    // End of variables declaration//GEN-END:variables
}
