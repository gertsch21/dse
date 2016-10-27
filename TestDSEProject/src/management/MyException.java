package management;
public class MyException extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MyException(){
    	super("Ein Fehler ist aufgetreten");
    }
    public MyException(String message){
    	super(message);
    }
}