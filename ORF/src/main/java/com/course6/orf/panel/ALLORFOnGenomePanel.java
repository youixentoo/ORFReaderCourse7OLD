/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf.panel;

import com.course6.orf.highlight.ORFLocation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author thijs
 */
public class ALLORFOnGenomePanel extends JPanel {

    private List<ORFLocation> locations;
    private ORFLocation selectedORF;
    private int genomeLength;
    private Dimension dimension;

    public ALLORFOnGenomePanel(List<ORFLocation> orfLocations, ORFLocation selectedORF, int genomeLength, Dimension dimension) {
        this.locations = orfLocations;
        this.selectedORF = selectedORF;
        this.genomeLength = genomeLength;
        this.dimension = dimension;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.white);

        int drawSize = this.getSize().width;
        g.setColor(Color.black);
        g.fillRect(0, 10, drawSize, 10);

        for (ORFLocation location : locations) {
            int startLoc = (int) ((location.getStart() / (float) genomeLength) * drawSize);
            int endLoc = (int) ((location.getEnd() / (float) genomeLength) * drawSize);

            g.setColor(Color.CYAN);
            g.fillRect(startLoc, 10, endLoc - startLoc, 10);
        }
        
        int startLoc = (int) ((selectedORF.getStart() / (float) genomeLength) * drawSize);
        int endLoc = (int) ((selectedORF.getEnd() / (float) genomeLength) * drawSize);
        
        g.setColor(Color.red);
        g.fillRect(startLoc, 10, endLoc - startLoc, 10);
        

//        System.out.println(startLoc + "::" + endLoc);
    }

}
