package pkgDoman;

public class Semaforo {
	
	public synchronized void sleep() throws Exception{
		wait();
	}
	
	public synchronized void wake() throws Exception{
		notify();
	}

}
