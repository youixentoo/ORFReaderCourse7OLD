/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.course6.orf.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thijs
 */
public class ErrorFile {

    /** Literally checks if the "ERRORS.txt" file exists
     *
     */
    public static void checkIfFileExists() {
        try {
            FileReader fileReader = new FileReader(System.getProperty("user.dir") +File.separator+"ERRORS.txt");
        } catch (Exception exc) {
            System.out.println("needs to create file");
            createTheFile();
        }
    }
    
    /*
    Creates the error file if it didn't exist already, adds some random pieces of information
    */
    private static void createTheFile() {
        File file = new File(System.getProperty("user.dir")+File.separator+"ERRORS.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            Properties props = System.getProperties();
            String osName = props.getProperty("os.name");
            String userHome = props.getProperty("user.home");
            String userName = props.getProperty("user.name");
            String jvRuntime = props.getProperty("java.runtime.version");
            String cwd = props.getProperty("user.dir");
            fileWriter.write("OS: "+osName+System.lineSeparator()+"Java Runtime: "+jvRuntime+System.lineSeparator()+"Current working dir: "+cwd+System.lineSeparator()+"Username: "+userName+System.lineSeparator()+"User Home: "+userHome+System.lineSeparator()+System.lineSeparator());
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(ErrorFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**Saves the error to the errors file
     *
     * @param error The error
     */
    public static void savingErrors(String error) {
        try {
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir")+File.separator+"ERRORS.txt",true);
            fileWriter.write(error);
            fileWriter.write(System.lineSeparator()+System.lineSeparator());
            fileWriter.close();
        } catch (IOException ex) {
            createTheFile();
        }
    }
    
    /**Saves a <code>String[]</code> containing errors
     *
     * @param error The <code>String[]</code> containing the errors
     */
    public static void savingErrors(String[] error){
        try{
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir")+File.separator+"ERRORS.txt",true);
            for(String line:error){
                fileWriter.append(line+System.lineSeparator());
            }
            fileWriter.write(System.lineSeparator()+System.lineSeparator());
            fileWriter.close();
        }catch (IOException ex) {
            createTheFile();
        }
    }
    
    /**Saves the <code>StackTraceElement[]</code> to the errors file
     *
     * @param error The <code>StackTraceElement[]</code>, Exception.getStackTrace()
     */
    public static void savingErrors(StackTraceElement[] error){
        try{
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir")+File.separator+"ERRORS.txt",true);
            fileWriter.append("Error:"+System.lineSeparator());
            for(StackTraceElement line:error){
                fileWriter.append(line.toString()+System.lineSeparator());
            }
            fileWriter.write(System.lineSeparator()+System.lineSeparator());
            fileWriter.close();
        }catch (IOException ex) {
            createTheFile();
        }
    }
}
