class MyException extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyException(){
    	super("Ein Fehler ist aufgetreten");
    }
    MyException(String message){
    	super(message);
    }
}