/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf.highlight;

/**
 *
 * @author thijs
 */
public class ORFLocation {
    private int start;
    private int end;

    public ORFLocation(){
    }
    
    public ORFLocation(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    
    public int getLength(){
        return end-start;
    }
    
    @Override
    public String toString(){
        return "Start: "+start+", End: "+end;
    }
}
