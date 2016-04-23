/************************************
 * Program Name   : Backlog Jukebox *
 * Author         : L Trevor Davies *
 * Date           : 04/22/2016      *
 * Version		  : 2.0             *
 ************************************/

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class BacklogJukeboxGUI 
{
	// Class constants
	private static String fileName = "backlog.txt";					// Universal file name
	
	// Class variables
	private JFrame frmBacklogJukebox;
	private static Collection library = new Collection();		
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		// Local constants
		
		// Local variables
		
		
		/************** start main **************/
		
		// Try to load backlog.txt on load
		try
		{
			library = library.load(fileName);
		}
				
		// Catch if backlog.txt doesn't exist
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, fileName + " load failed (this is normal on first run!)");
		}
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					BacklogJukeboxGUI window = new BacklogJukeboxGUI();
					window.frmBacklogJukebox.setVisible(true);
				}
				
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public BacklogJukeboxGUI() throws ClassNotFoundException, IOException
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException, ClassNotFoundException
	{
		
		// Local constants
		
		// Local variables
		
		/************** start initialize **************/
		
		frmBacklogJukebox = new JFrame();
		frmBacklogJukebox.setResizable(false);
		frmBacklogJukebox.setTitle("Backlog Jukebox 2.0");
		frmBacklogJukebox.setBounds(100, 100, 361, 324);
		frmBacklogJukebox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBacklogJukebox.getContentPane().setLayout(null);
		
		JTextPane albums = new JTextPane();
		albums.setEditable(false);
		albums.setBounds(10, 36, 335, 214);
		frmBacklogJukebox.getContentPane().add(albums);
		albums.setText(library.toString());
		
		JButton btnRandom = new JButton("Random");
		btnRandom.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// Local constants
				
				// Local variables
				
				/************** start btnRandom **************/
				
				// Output error if library is empty
				if(library.isEmpty())
					JOptionPane.showInternalMessageDialog(null, "Backlog is empty!");
				
				// Show random album
				else
					JOptionPane.showMessageDialog(null, library.randomAlbum());
			}
		});
		btnRandom.setBounds(58, 261, 89, 23);
		frmBacklogJukebox.getContentPane().add(btnRandom);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Local constants
				
				// Local variables
				String album;
				
				/************** start btnRemove **************/
				
				// Get album to be removed
				album = JOptionPane.showInputDialog("Album to remove: ");
				library.removeAlbum(album);
				
				// Update textbox
				albums.setText(library.toString());
		
				// Try to save library
				try
				{
					library.save(fileName);
				}
				
				// Output error message. This shouldn't happen
				catch (IOException e1) 
				{
					JOptionPane.showMessageDialog(null, "Failed to save " + fileName + "\n\n" + e1.toString());
				}
			}
		});
		btnRemove.setBounds(157, 261, 89, 23);
		frmBacklogJukebox.getContentPane().add(btnRemove);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Local constants
				
				// Local variables
				String artist;					// Album artist
				String album;					// Album name
				String year;					// Year released
				
				/************** start btnAdd **************/
				
				// Get the album info
				artist = JOptionPane.showInputDialog("Artist: ");
				album = JOptionPane.showInputDialog("Album: ");
				year = JOptionPane.showInputDialog("Year: ");
				
				// Add the album to the library
				library.addAlbum(new Album(artist, album, year));
				
				// Update the textbox
				albums.setText(library.toString());
		
				// Try to save library
				try
				{
					library.save(fileName);
				}
				
				// Output error message. This shouldn't happen
				catch (IOException e1) 
				{
					JOptionPane.showMessageDialog(null, "Failed to save " + fileName + "\n\n" + e1.toString());
				}
				
			}
		});
		btnAdd.setBounds(256, 261, 89, 23);
		frmBacklogJukebox.getContentPane().add(btnAdd);
		
		JLabel lblNewLabel = new JLabel("Backlog:");
		lblNewLabel.setBounds(10, 11, 65, 14);
		frmBacklogJukebox.getContentPane().add(lblNewLabel);
	}
}
