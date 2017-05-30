/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package CSE360;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.*;
import java.awt.*;
import java.nio.charset.Charset;
import java.security.Security;

import javax.swing.JFrame;
import javax.swing.*;


import org.json.*;


public class Descrete {
	
	
	public static String readThis(Reader rd) throws IOException{
		
		StringBuilder sb = new StringBuilder();
		int cp;
		
		while((cp = rd.read()) != -1){
			sb.append((char) cp);
			
		}
		
		return sb.toString();
	}
	
	
	public static JSONObject readURL(String url) throws IOException, JSONException{
	
		 InputStream in =  new URL(url).openStream();
		 try{
			 BufferedReader read = new BufferedReader(
					 new InputStreamReader(in, Charset.forName("UTF-8")));
			 String jsonText = readThis(read);
			 JSONObject jsn = new JSONObject(jsonText);
			 
			 return jsn;
			 
		 } finally {
			 
			 in.close();
		 }
		
	}
	     

	 
	public static void main(String[] args) throws IOException, JSONException {
   
		JFrame test = new JFrame();
		JSONObject jon = readURL("https://api.darksky.net/forecast/4f02d91363f259f5ca95263c5c032dfc/37.8267,-122.4233");
		String weather = jon.getJSONObject("currently").getString("summary");
                int temperature = jon.getJSONObject("currently").getInt("temperature");
                int time = jon.getJSONObject("currently").getInt("time");
		
		System.out.println(weather);
                System.out.println(temperature);
                System.out.println(time);
		
		
		
		try {
			
		String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=Tempe,CA&zoom=10&size=400x400";
		String destinationFile = "image.jpg";
	        URL url = new URL(imageURL);
		java.io.InputStream in = url.openStream();
		OutputStream out = new FileOutputStream(destinationFile);
		
		byte b[] = new byte[2048];
		int lenght;
		
		while((lenght = in.read(b)) != -1){
			out.write(b,0,lenght); //change
		}
                
                in.close();
	        out.close();
		
	} catch(Exception e){
		
		System.exit(1);
	}
                JPanel main = new JPanel();
                main.setPreferredSize(new Dimension(250,250));
                main.setVisible(true);
                
		JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(150,150));
                panel.setVisible(true);
                
                JPanel panel2 = new JPanel();
                panel2.setPreferredSize(new Dimension(50,50));
                panel2.setVisible(true);
                
                JLabel label = new JLabel(" Weather: " + weather);
                JLabel label2 = new JLabel("\n Temperature: " + Integer.toString(temperature) + " degrees\n");
               // JLabel label3 = new JLabel(Integer.toString(time));
                
                
                panel2.add(label);
                panel2.add(label2);
               // panel2.add(label3);
                
		panel.add(new JLabel( new ImageIcon(( new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600, java.awt.Image.SCALE_SMOOTH))));
                main.setLayout(new GridLayout(2,1,20,20));
                main.add(panel);
                main.add(panel2);
                
                test.add(main);
                test.setPreferredSize(new Dimension(650,600));
                //test.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
                
                
		test.setVisible(true);
                test.pack();
		test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//panel.setLayout();
		
	}	 
}
