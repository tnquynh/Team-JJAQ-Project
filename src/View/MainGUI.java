/*
 * TCSS 360 – Winter 2019
 */

package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class creates the GUI for the program.
 * 
 * @author Jacob Dickson
 * @version Winter 2019
 */
public class MainGUI {
	private VersionInfo myVersion;
	private static final Dimension SIZE = new Dimension(450, 450);
	private JFrame myFrame;
	private JPanel myMainPanel;
	private JPanel myNewPPanel;


	/**
	 * Is the main constructor for the GUI.
	 */
	public MainGUI() {
		myVersion = new VersionInfo();
		myFrame = new JFrame("JJAQ Program");
		myMainPanel = new JPanel(new GridLayout(0, 3));
		myNewPPanel = new JPanel(new GridLayout(0, 3));
	}

	/**
	 * Starts the GUI.
	 */
	public void start() {
		makeNewProjectPanel();
		makePanel();
		myFrame.setPreferredSize(SIZE);

		myFrame.setJMenuBar(menuBar());

		myFrame.pack();
		myFrame.setMinimumSize(myFrame.getSize());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}

	/**
	 * Makes the panel.
	 */
	private void makePanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(3, 0, 3, 10));
		JButton newP = new JButton("New Project");
		JButton oldP = new JButton("Open Project");
		JButton compareP = new JButton("Compare");
		newP.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    goToNew();
			    myFrame.revalidate();
				myFrame.repaint();

			  } 
			} );
		buttonPanel.add(newP);
		buttonPanel.add(oldP);
		buttonPanel.add(compareP);
		myMainPanel.add(new JPanel());
		myMainPanel.add(new JPanel());
		myMainPanel.add(new JPanel());
		myMainPanel.add(new JPanel());




		myMainPanel.add(buttonPanel);
		myMainPanel.add(new JPanel());

		myMainPanel.add(new JPanel());

		myFrame.add(myMainPanel);
	}

	 private void goToNew() {
		 myFrame.remove(myMainPanel);
		 myFrame.add(myNewPPanel);
		 System.out.println("Test");
	 }
	 
	 private void makeNewProjectPanel() {
			JPanel inputPanel = new JPanel();
			JPanel buttonPanel = new JPanel(new GridLayout(3, 0, 3, 10));
			JButton makeP = new JButton("Make Project");
			JButton cancelP = new JButton("Cancel");
			JTextField newP = new JTextField(5);
			inputPanel.add(new JLabel("New Project:"));
			inputPanel.add(newP);
			buttonPanel.add(makeP);
			buttonPanel.add(cancelP);
			JButton home = new JButton("Home");

			myNewPPanel.add(new JPanel());
			myNewPPanel.add(new JPanel());
			myNewPPanel.add(new JPanel());
			myNewPPanel.add(new JPanel());




			myNewPPanel.add(inputPanel);
			myNewPPanel.add(new JPanel());
			myNewPPanel.add(new JPanel());

			myNewPPanel.add(buttonPanel);

	 }
	
	/**
	 * Creates a menu bar.
	 * 
	 * @return is a created menu bar.
	 */
	private JMenuBar menuBar() {
		final JMenuBar menuBar = new JMenuBar();
		final JMenu optionsMenu = new JMenu("Options");

		final JMenuItem j = new JMenuItem("About...");
		j.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent theEvent) {
				JOptionPane.showMessageDialog(null,
						"Team JJAQ\n" + "Team members: Anthony Trang, Jacob Dickson, "
								+ "James Olmsted, Quynh Trinh\n"
								+ "Version number: " + myVersion.getVersion() + "\n",
						"About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		optionsMenu.add(j);
		menuBar.add(optionsMenu);

		return menuBar;
	}

	/**
	 * Sets the look and feel.
	 */

	private static void setLookAndFeel() {
		try {

			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

		} catch (final UnsupportedLookAndFeelException e) {
			System.out.println("UnsupportedLookAndFeelException");
		} catch (final ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
		} catch (final InstantiationException e) {
			System.out.println("InstantiationException");
		} catch (final IllegalAccessException e) {
			System.out.println("IllegalAccessException");
		}
	}

	/**
	 * The main method.
	 * 
	 * @param theArgs is the command line arguments.
	 */
	public static void main(final String... theArgs) {
		setLookAndFeel();
		new MainGUI().start();
	}

}
