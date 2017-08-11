/**
 * 
 */
package arrays;

/**
 * @author apple
 * 
 */
public class Person {
	private String lastName;
	private String firstName;
	private int age;

	/**
	 * @param lastName
	 * @param firstName
	 * @param age
	 */
	public Person(String lastName, String firstName, int age) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [lastName=" + lastName + ", firstName=" + firstName
				+ ", age=" + age + "]";
	}
}
