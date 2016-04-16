public class Album
{
	// Class constants
	
	// Class variables
	private String artist;
	private String album;
	private String year;
	
	Album(String artist, String album, String year)
	{
		// Constructor constants
		
		// Constructor variables
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getAlbum()
	{
		return album;
	}
	
	public void setAlbum(String album)
	{
		this.album = album;
	}
	
	public String getYear()
	{
		return year;
	}
	
	public void setYear(String year)
	{
		this.year = year;
	}
	
	public String toString()
	{
		return "[" + year + "] " + artist + " - " + album;
	}
}