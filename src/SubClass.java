import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author aa49442
 * 
 */
public class SubClass extends SuperClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int b = 20;

	public SubClass() {
		super(5);
		this.b = 15;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubClass [b=" + b + ", a=" + a + "]";
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		SubClass subClass = new SubClass();
		subClass.b = 100;
		subClass.a = 200;
		System.out.println(subClass);
		FileOutputStream fos = new FileOutputStream("fos.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(subClass);

		SubClass subClass2 = (SubClass) new ObjectInputStream(
				new FileInputStream("fos.ser")).readObject();

		System.out.println(subClass2);
	}
}