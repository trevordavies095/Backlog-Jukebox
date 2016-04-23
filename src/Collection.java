/************************************
 * Program Name   : Backlog Jukebox *
 * Author         : L Trevor Davies *
 * Date           : 04/22/2016      *
 * Version		  : 2.0             *
 ************************************/

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Collections;
import java.util.LinkedList;
import java.io.*;

public class Collection
{
	// Class constants
	
	// Class variables
	private List<Album> albums = new LinkedList<Album>();		// Linked list to store backlog
	private int count = 0;										// Number of albums
	
	public Collection()
	{
		
	}
	
	public void addAlbum(Album album)
	{
		// Add albums to list, increment count
		albums.add(album);
		count++;
	}
	
	public void removeAlbum(String album)
	{
		// Search for album
		int i = searchAlbum(album);
		
		// IF album isn't found, show error
		if(i == -1)
			JOptionPane.showMessageDialog(null, album + " was not found!");
		
		// ELSE remove album
		else
		{
			albums.remove(i);
			count--;
		}
	}
	
	public int searchAlbum(String a)
	{
		Album compare;				// Album to be searched
		
		// For every album in the list
		for(int i = 0; i < count; i++)
		{
			// Get album at index i
			compare = albums.get(i);
			
			// IF there's a match, return index
			if(a.toLowerCase().equals(compare.getAlbum().toLowerCase()))
				return i;
		}
		
		// Return -1 (album not found)
		return -1;
	}
	
	public Album randomAlbum()
	{
		Random rand = new Random();			// Random num gen
		int i = 0;							// Random number
		Album album;						// Random album
		
		// Get random number
		i = rand.nextInt(count);
		
		// Return album at index i
		return album = albums.get(i);
	}
	
	public void save(String fileName) throws IOException
	{
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		// FOR every album in the list, add album to file
		for(Album album:albums)
			pw.println(album.getYear() + "  "  + album.getArtist() + "  " + album.getAlbum());
		
		pw.flush();
		pw.close();
	}
	
	public Collection load(String fileName) throws IOException, ClassNotFoundException
	{
		Collection col = new Collection();
		Scanner fileScan = new Scanner(new File(fileName));
		Scanner stringScan;
		String record = "";
		String artist = "";
		String album = "";
		String year = "";
		
		// While it isn't the end of the file, load albums to list
		while(fileScan.hasNext())
		{
			record = fileScan.nextLine();
			stringScan = new Scanner(record);
			stringScan.useDelimiter("  ");
			year = stringScan.next();
			artist = stringScan.next();
			album = stringScan.next();
			
			col.addAlbum(new Album(artist, album, year));
			count++;
		}
		
		fileScan.close();
		
		// Return new instance of Collection()
		return col;
	}
	
	public boolean isEmpty()
	{
		if(count != 0)
			return false;
		
		return true;
	}
	
	public int getSize()
	{
		return count;
	}
	
	public String toString()
	{
		String result = "";
		Album current;
		
		for(Album album:albums)
		{
			current = album;
			result += "[" + current.getYear() + "] " + current.getArtist() + " - " + current.getAlbum() + "\n";
		}
		
		return result;
	}
}