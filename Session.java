package pkgDoman;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Session extends Thread{
	
	private String path;
	private Semaforo s = new Semaforo();
	
	Session(String path){
		this.path = path;
	}
	
	public void run(){
		ArrayList<Integer> orden = new ArrayList<Integer>();
		
		orden = genSession();
		
		for(int i = 0; i < orden.size(); i++){
			try {
				Imagen imagen = new Imagen(path+Integer.toString(orden.get(i))+".png",s);
				imagen.show();
				s.sleep();
				imagen.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private ArrayList<Integer> genSession(){
		
		ArrayList<Integer> orden = new ArrayList<Integer>();
		int numFiles = new File(path).listFiles().length;
		
		
		for(int i = 0; i < numFiles; i++) orden.add(i);
		
		Collections.shuffle(orden);
		
		return orden;
	}
}
