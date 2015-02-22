package pkgDoman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Imagen implements ActionListener{
	
	private String filename;
	private JFrame editorFrame;
	private ImageIcon imageIcon;
	private JLabel jLabel;
	private JButton next;
	private Semaforo s;
	
	Imagen(String filename, Semaforo s){
		this.filename = filename;
		this.s = s;
	}
	
	public void show() throws Exception{
		editorFrame = new JFrame("Imagen");
		editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(filename));
		} catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		
		imageIcon = new ImageIcon(image);
		jLabel = new JLabel();
	    jLabel.setIcon(imageIcon);
	    editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);
	    
	    next = new JButton("Siguiente");
	    next.setPreferredSize(new Dimension(100,50));
	    editorFrame.add(next, BorderLayout.SOUTH);
	    next.addActionListener(this);

	    editorFrame.pack();
	    editorFrame.setLocationRelativeTo(null);
	    editorFrame.setVisible(true);
	}
	
	public void hide(){
		editorFrame.setVisible(false);
	}
	
	public void close(){
		editorFrame.dispose();
	}
	
    public void actionPerformed(ActionEvent e) {
    	try{
    		s.wake();
    	} catch( Exception e2){
    		e2.printStackTrace();
    	}
    }
	
}