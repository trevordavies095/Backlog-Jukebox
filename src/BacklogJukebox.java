/***********************************
 * Program Name   : Backlog Jukebox
 * Author         : L Trevor Davies
 * Date           : 04/16/2016
 ***********************************/

import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BacklogJukebox extends JFrame
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		// Local constants
		final String fileName = "library.txt";				// File name
		
		// Local variables
		Collection library = new Collection();				// The library of albums			
		Album newAlbum;										// Album obj
		
		int choice;											// User's choice parsed
		String choiceString;								// User's as a string
		
		String artist;										// Album artist
		String album;										// Album name
		String year;										// Year released
		
		/************** start main **************/
		
		// Try to load library.txt on load
		try
		{
			library = library.load(fileName);
		}
		
		// Catch if library.txt doesn't exist
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "library.txt load failed (this is normal on first run!)");
		}
		
		
		// Load GUI with menu
		choiceString = JOptionPane.showInputDialog("1. Random Album\n"
												 + "2. Add Album\n"
												 + "3. Remove Album\n"
												 + "4. View backlog\n");
		
		// Parse choice
		choice = Integer.parseInt(choiceString);
		
		while(true)
		{	
			switch(choice)
			{
				// Random album
				case 1:
					if(library.isEmpty())
						JOptionPane.showMessageDialog(null, "Library is empty!");
					else
						JOptionPane.showMessageDialog(null, library.randomAlbum());
					
					break;
				
				// Add album
				case 2: 
					artist = JOptionPane.showInputDialog("Artist: ");
					album = JOptionPane.showInputDialog("Album: ");
					year = JOptionPane.showInputDialog("Year: ");
					
					library.addAlbum(new Album(artist, album, year));
					
					
					break;
				
				// Remove album
				case 3:
					album = JOptionPane.showInputDialog(library.toString() + "Album to remove: ");
					library.removeAlbum(album);
					
					break;
					
				// Show backlog
				case 4: 
					if(library.isEmpty())
						JOptionPane.showMessageDialog(null, "Empty library!");
					else
						JOptionPane.showMessageDialog(null, library.toString());
					
					break;
			
				default:
					JOptionPane.showMessageDialog(null, "Invalid input!");
					break;
			}
			
			// Save
			library.save(fileName);
			
			// Load GUI with menu
			choiceString = JOptionPane.showInputDialog("1. Random Album\n"
													 + "2. Add Album\n"
													 + "3. Remove Album\n"
													 + "4. View backlog\n");
			choice = Integer.parseInt(choiceString);
		}
					
	}
}