/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenparcial2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sergio
 */
public class ExamenParcial2 {

    private JFrame frame;
    private JButton btnTransferencia;
    private JButton btnCrear;
    private ArrayList<CompteBancari> llista;
    private JTextField txtNom, txtDNI, txtCompte, txtSaldo, txtOrigen, txtDesti, txtQuantitat;
    private JLabel lbNom, lbDNI, lbCompte, lbSaldo, lbOrigen, lbDesti, lbQuantitat;
    private JPanel panel1, panel2, panel3, panel4;

    public ExamenParcial2() {
        llista = new ArrayList<CompteBancari>();

        frame = new JFrame("Examen Parcial 2");

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        btnTransferencia = new JButton("Transferencia");
        btnTransferencia.addActionListener(new Btn_listener_2());
        btnCrear = new JButton("Crear");
        btnCrear.addActionListener(new Btn_listener_1());

        txtNom = new JTextField(15);
        lbNom = new JLabel("Nom");
        txtDNI = new JTextField(15);
        lbDNI = new JLabel("DNI");
        txtCompte = new JTextField(15);
        lbCompte = new JLabel("Num compte");
        txtSaldo = new JTextField(15);
        lbSaldo = new JLabel("Saldo");

        txtOrigen = new JTextField(15);
        lbOrigen = new JLabel("Compte origen");
        txtDesti = new JTextField(15);
        lbDesti = new JLabel("Compte desti");
        txtDesti = new JTextField(15);
        txtQuantitat = new JTextField(15);
        lbQuantitat = new JLabel("Import");
    }

    public void go() {
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(lbNom);
        panel1.add(txtNom);
        panel1.add(lbDNI);
        panel1.add(txtDNI);
        panel1.add(lbCompte);
        panel1.add(txtCompte);
        panel1.add(lbSaldo);
        panel1.add(txtSaldo);
        panel1.add(btnCrear);

        panel2.add(lbOrigen);
        panel2.add(txtOrigen);
        panel2.add(lbDesti);
        panel2.add(txtDesti);

        panel3.add(lbQuantitat);
        panel3.add(txtQuantitat);
        panel3.add(btnTransferencia);

        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.add(panel2);
        panel4.add(panel3);

        frame.getContentPane().add(BorderLayout.NORTH, panel1);
        frame.getContentPane().add(BorderLayout.SOUTH, panel4);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ExamenParcial2 gui = new ExamenParcial2();
        gui.go();
    }

    public class Btn_listener_1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            CompteBancari a = new CompteBancari(txtNom.getText(), txtDNI.getText(), Integer.parseInt(txtCompte.getText()), Float.parseFloat(txtSaldo.getText()));
            llista.add(a);
            JOptionPane.showMessageDialog(frame, "Compte creat", "Compte", JOptionPane.OK_OPTION);
        }

    }

    public class Btn_listener_2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            realizarTransfer();
        }

        public void realizarTransfer() {
            int desti = Integer.parseInt(txtDesti.getText());
            int origen = Integer.parseInt(txtOrigen.getText());
            float quantitat = Float.parseFloat(txtQuantitat.getText());
            CompteBancari d = existeCuenta(desti);
            CompteBancari o = existeCuenta(origen);
            if (d != null && o != null && d != o) {
                try {
                    o.transferencia(quantitat, d);
                    JOptionPane.showMessageDialog(frame, "transferencia feta", "Transferencia", JOptionPane.OK_OPTION);
                } catch (ExamenParcialExcepcio ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Transferencia", JOptionPane.OK_OPTION);

                }

            } else {
                JOptionPane.showMessageDialog(frame, "Revisa les dades", "Transferencia", JOptionPane.OK_OPTION);

            }
        }

        public CompteBancari existeCuenta(int compte) {
            Iterator i = llista.iterator();
            while (i.hasNext()) {
                CompteBancari a = (CompteBancari) i.next();
                if (compte == a.getNumCompte()) {
                    return a;
                }

            }
            return null;
        }
    }
}
