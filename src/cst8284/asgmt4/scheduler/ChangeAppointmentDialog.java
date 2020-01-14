package cst8284.asgmt4.scheduler;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ChangeAppointmentDialog {
	private static final GridBagConstraints textConstants = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1,
			1, 1, // gridx, gridy, gridwidth, gridheight, weightx, weighty
			GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady
	private static final GridBagConstraints labelConstants = new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1,
			1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

	private static Container cp;
	private static final int labelWidth = 35;
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);

	public static void showChangeAppointmentDialog(Scheduler scheduler, Appointment apt) {
		JFrame f = new JFrame("Get, set, change or delete an appointment");
		cp = f.getContentPane();
		cp.setLayout(new GridBagLayout());

		JTextField clientDateInput = setRow("Appointment Date (entered as DDMMYYYY):", 'd');
		JTextField clientTimeInput = setRow("Appointment Time:", 't');

		JButton saveButton = new JButton("Save");
		JButton exitButton = new JButton("Exit");

		cp.add(saveButton);
		cp.add(exitButton);

		saveButton.addActionListener(e -> {
			String date = clientDateInput.getText();
			String time = clientTimeInput.getText();

			Calendar cal = Scheduler.makeCalendarFromUserInput(false, date, time);
			
			apt.setCalendar(cal);
			
			JOptionPane.showMessageDialog(null, "Your appointment has been updated");
			f.dispose();

		});


		exitButton.addActionListener(e -> {
			f.dispose();
		});

		f.pack();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		f.setVisible(true);
	}

	private static JTextField setRow(String label, char keyboardShortcut) {
		JLabel l;
		JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), textConstants);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
		cp.add(t = new JTextField(labelWidth), labelConstants);
		t.setFocusAccelerator(keyboardShortcut);
		return t;
	}
}
