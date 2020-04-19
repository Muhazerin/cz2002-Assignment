package hrps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileIO {
	private String directory = "";

	public FileIO() {}
	
	// pass object type (e.g. Guest)
	// takes in cls as "g.getClass()" where g is: Guest g = new Guest() for example
	// returns all objects stored in the file (example: all Guests info)
	public Object[] readObject(Class<?> cls) {
		directory = cls + ".txt";
		ArrayList<Object> objs = new ArrayList<>();
		try {
			FileInputStream fi = new FileInputStream(new File(directory));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			boolean read = true;
			while (read) {
				Object obj = oi.readObject();
				
				if (obj == null) {
					read = !read;
				}
				else {
					objs.add(obj);	
				}
			}
			
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			if(e.getMessage() != null) {
				System.out.println("Error initializing stream: " + e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objs.toArray();
	} // end of readObject class
	
	// serialize and writes object into text files
	// pass in array of all the same objects you are storing
	// takes in cls as "g.getClass()" where g is: Guest g = new Guest() for example
	public void writeObject (Object[] objs, Class<?> cls) {
		directory = cls + ".txt";
		try {
			FileOutputStream f = new FileOutputStream(new File(directory));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			for (int i = 0; i < objs.length; i++) {
				o.writeObject(objs[i]);
			}

			o.close();
			f.close();
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	} // end of writeObject class
	
	// write only 1 object
	public void writeObjectSingle (Object obj, Class<?> cls) {
		directory = cls + ".txt";
		Object[] stored = readObject(cls);
		try {
			FileOutputStream f = new FileOutputStream(new File(directory));
			ObjectOutputStream o = new ObjectOutputStream(f);

			
			// Write objects to file 
			for (int i = 0; i < stored.length; i++) {
				o.writeObject(stored[i]); 
			}
			 
			
			o.writeObject(obj);
			o.close();
			f.close();
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}

}
