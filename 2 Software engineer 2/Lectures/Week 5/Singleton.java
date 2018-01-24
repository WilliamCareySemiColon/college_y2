// Singleton Desaign pattern Implemented in Java

package designPatterns.singleton;

public class Singleton {
	private static Singleton uniqueInstance = null;
    static int count = 0; int me;
    
    private Singleton() { me = ++count;}
    
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
    
    public void doSomething(){
        System.out.println("\nGreetings from the unique, one and only ME! Numero " + me);
    }
 }   
 
 