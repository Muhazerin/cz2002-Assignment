package hrps;

/**
 * @author      Koh Tong Liang <KOHT0029@e.ntu.edu.sg>
 * @version     1.0
 * @since       1.0
 */
class MenuItem 
{
  /**
 * Unique id for identification. Name and description to describe menuitem
 */
  private String id, name, description;

  /**
 * Price variable for each individual menu items
 */
  private float price;

  /**
  * Default Constructor
  */
  MenuItem () 
  {
    id = "";
    name = "";
    description = "";
    price = 0;
  } // end of default constructor

  /**
  * Overloaded Constructor to create an instance of MenuItem object.
  */
  MenuItem (String id, String name, String description, float price)
  {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
  } // end of constructor

  /**
  * Called by other classes to return id
  * @return id
  */
  public String getId ()
  {
    return id;
  } // end of getId

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
}
