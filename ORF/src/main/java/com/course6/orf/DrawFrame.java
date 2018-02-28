/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author thijs
 */
public class DrawFrame extends JFrame {

    private JPanel Panel;

    
    public static void showVisual(List<ORFLocation> locations, int genomeLength) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrawFrame(locations, genomeLength).setVisible(true);
            }
        });

    }

    private DrawFrame(List<ORFLocation> locations, int genomeLength) {
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setTitle("Genes on genome visualisation");
        DrawPanel panel = new DrawPanel(locations, genomeLength);
        super.add(panel);
        super.setSize(panel.getWidth(), 500);
        super.pack();
    }

}

class DrawPanel extends JPanel {

    DrawPanel(List<ORFLocation> locations, int genomeLength) {
        this.locations = locations;
        this.genomeLength = genomeLength;
    }

    @Override
    public Dimension getPreferredSize() {
        int Panel_Height = 200;
        int Panel_Width;
        
        if(genomeLength/1000.0 < 500){
            Panel_Width = 500;
        }else if (genomeLength/1000.0 > 2000){
            Panel_Width = 2000;
        }else{
            Panel_Width = (int) (genomeLength/1000.0);
        }

        return new Dimension(Panel_Width, Panel_Height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.white);
        
        int index = 0;
        int panelWidth = super.getWidth();
        
        g.setColor(Color.black);
        g.fillRect(0, 10, panelWidth, 3);
//        g.setColor(Color.red);
        
        for (ORFLocation location:locations){   
            int startLoc = (int) ((location.getStart()/ (float) genomeLength)*panelWidth);
            int endLoc = (int) ((location.getEnd()/ (float) genomeLength)*panelWidth);
            
            System.out.println(startLoc+"::"+endLoc);
            
            g.setColor(Color.red);
            g.fillRect(startLoc, 10, endLoc-startLoc, 3);
        }

        //System.out.println(super.getWidth());
        //System.out.println(super.getHeight());
    }

    private List<ORFLocation> locations;
    private int genomeLength;

}


//      * @return A JFrame with a hydrophobicity visualisation
