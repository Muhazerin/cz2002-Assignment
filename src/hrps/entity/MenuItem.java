package entity;

import java.io.Serializable;

/**
 * @author      Koh Tong Liang <KOHT0029@e.ntu.edu.sg>
 * @version     1.0
 * @since       1.0
 */
public class MenuItem implements Serializable 
{
	
	private static final long serialVersionUID = 1234L;
	
	/**
	 * Unique id for identification. Name and description to describe menuitem
	 */
	private String name, description;
	 
	private int id;
	 
	/**
	 * Price variable for each individual menu items
	 */
	private float price;
	/**
	* Default Constructor
	*/
	public MenuItem () 
	{
	  super();
	} // end of default constructor
	/**
	 * Overloaded Constructor to create an instance of MenuItem object.
	 */
	public MenuItem (int id, String name, String description, float price)
	{
	  this.id = id;
	  this.name = name;
	  this.description = description;
	  this.price = price;
	} // end of constructor
	
	/**
	 * Called by other classes to return name
	 * @return name
	 */
	public String getName ()
	{
	  return name;
	} // end of getName
	/**
	 * Called by other classes to return description
	 * @return description
	 */
	public String getDescription ()
	{
	  return description;
	} // end of getDescription
	
	/**
	 * Called by other classes to return price
	 * @return price
	 */
	public float getPrice ()
	{
	  return price;
	} // end of getPrice
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getID() {
		return this.id;
	}
}
