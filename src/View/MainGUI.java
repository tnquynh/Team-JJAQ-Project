/**
 * TCSS 360 – Winter 2019
 * Test
 */

package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class creates the GUI for the program.
 * 
 * @author Jacob Dickson
 * @version Winter 2019
 */
public class MainGUI {

	private static final Dimension SIZE = new Dimension(450, 450);
	private JFrame myFrame;

	/**
	 * Is the main constructor for the GUI.
	 */
	public MainGUI() {

		myFrame = new JFrame("JJAQ Program");

	}

	/**
	 * Starts the GUI.
	 */
	public void start() {
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
								+ "Version number: \n",
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
