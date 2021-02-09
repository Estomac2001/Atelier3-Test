package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class View extends JFrame {
    Random rand = new Random();

    int nbAleatoire = rand.nextInt(99) + 1;
    int nbJoueur;
    String nbTexteJoueur;
    int nbEssai = 0;
    int nbMin = 0;
    int nbMax = 100;

    JFrame frame;

    JPanel panNord;
    JPanel panCentre;
    JPanel panSud;

    JLabel lab;

    JButton btnQuitter;

    public View(){
        String nomBtn;

        frame = new JFrame("Nombre mystère");
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panNord = new JPanel();
        panNord.setLayout(new FlowLayout());
        lab = new JLabel("0/10");
        panNord.add(lab);

        panSud = new JPanel();
        panSud.setLayout(new GridLayout(1,1));
        btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(e -> btnQuitterAction());
        panSud.add(btnQuitter);

        panCentre = new JPanel();
        panCentre.setLayout(new GridLayout(10,10));

        for (int i=1; i<101; i++)
            panCentre.add(new JButton(i+""));
        for (Component comp: panCentre.getComponents())
            if (comp instanceof JButton)
                ((JButton)comp).addActionListener(e -> btnAction(e));

        frame.add(panNord, BorderLayout.NORTH);
        frame.add(panSud, BorderLayout.SOUTH);
        frame.add(panCentre,BorderLayout.CENTER);

    }

    public void btnAction(ActionEvent e){
        nbEssai++;
        JButton btnRef = (JButton)e.getSource();
        nbTexteJoueur = btnRef.getText();
        nbJoueur = Integer.parseInt(nbTexteJoueur);

        if ((nbEssai == 10) && (nbJoueur != nbAleatoire)) {
            JOptionPane.showMessageDialog(frame, "Vous avez perdu! Le nombre était " + nbAleatoire + ".", "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        else if (nbJoueur == nbAleatoire) {
            JOptionPane.showMessageDialog(frame, "Vous avez gagné! Le nombre était " + nbAleatoire + ".", "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        else if (nbJoueur < nbAleatoire){
            nbMin = nbJoueur;
            lab.setText("(" + nbEssai + "/10) Le nombre est plus grand que " + nbMin + " et plus petit que " + nbMax);
        }

        else if (nbJoueur > nbAleatoire){
            nbMax = nbJoueur;
            lab.setText("(" + nbEssai + "/10) Le nombre est plus grand que " + nbMin + " et plus petit que " + nbMax);
        }
    }

    private void btnQuitterAction(){
        System.exit(0);
    }

    public static void main(String[] args) {
        View maVue = new View();
    }
}
