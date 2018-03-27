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
import javax.swing.JPanel;

/**
 *
 * @author thijs
 */
public class ORFViewPanel extends JPanel {

    private ORFLocation location;
    private int genomeLength;
    private Dimension dimension;

    public ORFViewPanel(ORFLocation location, int genomeLength, Dimension dimension) {
        this.location = location;
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

        int startLoc = (int) ((location.getStart() / (float) genomeLength) * drawSize);
        int endLoc = (int) ((location.getEnd() / (float) genomeLength) * drawSize);

//        System.out.println(startLoc + "::" + endLoc);

        g.setColor(Color.red);
        g.fillRect(startLoc, 10, endLoc - startLoc, 10);
    }

}
