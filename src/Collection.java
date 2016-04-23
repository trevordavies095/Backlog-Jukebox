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
	private List<Album> albums = new LinkedList<Album>();
	private int count = 0;
	
	public Collection()
	{
		
	}
	
	public void addAlbum(Album album)
	{
		albums.add(album);
		
		count++;
	}
	
	public void removeAlbum(String album)
	{
		int i = searchAlbum(album);
		
		if(i == -1)
			JOptionPane.showMessageDialog(null, album + " was not found!");
		else
		{
			albums.remove(i);
			count--;
		}
	}
	
	public int searchAlbum(String a)
	{
		Album compare;
		
		for(int i = 0; i < count; i++)
		{
			compare = albums.get(i);
			
			if(a.toLowerCase().equals(compare.getAlbum().toLowerCase()))
				return i;
		}
		
		return -1;
	}
	
	public Album randomAlbum()
	{
		Random rand = new Random();
		int i = 0;
		Album album;
		
		i = rand.nextInt(count);
		return album = albums.get(i);
	}
	
	public void save(String fileName) throws IOException
	{
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		
		for(Album album:albums)
		{
			pw.println(album.getYear() + "  "  + album.getArtist() + "  " + album.getAlbum());
		}
		
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