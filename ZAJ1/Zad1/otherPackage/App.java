package otherPackage;

import java.io.*;

public class App{
	public static String getString(){
		String text = null;
		try{
			InputStreamReader rd = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(rd);
			
			text = bfr.readLine();
		}catch(IOException e){e.printStackTrace();}
		return text;
	}
	
	public void Start(String[] args){
		System.out.print("Type your name: ");
		String name = App.getString();
		System.out.println("Hello world");
		System.out.println("And hello "+name);
	}
}