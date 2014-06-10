/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RSA.Result;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Sabuz
 */

public class ResultSheet {
    
    private int played;
    private int win;
    private int loss;
    private int drawn;
    
    public ResultSheet()
    {
        FileInputStream fis = null;
        
        try
        {
            Properties pros = new Properties();
            File file = new File("Statistics.properties");
            fis = new FileInputStream(file);
            pros.load(fis);
            
            played = Integer.parseInt(pros.getProperty("Total_Game_Played"));
            win = Integer.parseInt(pros.getProperty("Total_Game_Win"));
            loss = Integer.parseInt(pros.getProperty("Total_Game_Loss"));
            drawn = Integer.parseInt(pros.getProperty("Total_Game_Drawn"));
            
        }catch(Exception e)
        {
            
        }
        
    }
    
    public void updateResult(long type)
    {
        if(type == 0)
            win = win + 1;
        else if(type == 2)
            loss = loss + 1;
        else 
            drawn = drawn + 1;
        
        played = played + 1;
        
        
        FileOutputStream fos = null;
        
        try
        {
            Properties pros = new Properties();
            File file = new File("Statistics.properties");
            fos = new FileOutputStream(file);
            
            pros.setProperty("Total_Game_Played", Integer.toString(played));
            pros.setProperty("Total_Game_Win", Integer.toString(win));
            pros.setProperty("Total_Game_Loss", Integer.toString(loss));
            pros.setProperty("Total_Game_Drawn", Integer.toString(drawn));
            
            pros.store(fos, null);
            fos.close();

            
            
        }catch(Exception e)
        {
            
        }
        
        
    }
    
}
