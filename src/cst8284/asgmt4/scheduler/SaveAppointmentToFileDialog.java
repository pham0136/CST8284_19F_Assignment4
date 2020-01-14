package cst8284.asgmt4.scheduler;


import javax.swing.JOptionPane;


public class SaveAppointmentToFileDialog {


	public static void showSaveAppointmentToFileDialog(Scheduler scheduler, String file) {

		// Scheduler.saveAppointmentsToFile(null, null);

		int saveFile = JOptionPane.showConfirmDialog(null, "Would you like to save this appointment to file?",
				"Please confirm", JOptionPane.YES_NO_OPTION);
		if (saveFile == JOptionPane.YES_OPTION) {
			Scheduler.saveAppointmentsToFile(scheduler.getAppointments(), file);
			JOptionPane.showMessageDialog(null, "Appointment saved to file");
		}
	}
	
	public static void showLoadAppointmentToFileDialog(Scheduler scheduler, String file) {

		// Scheduler.saveAppointmentsToFile(null, null);

		int loadFile = JOptionPane.showConfirmDialog(null, "Would you like to load this appointment from file?",
				"Please confirm", JOptionPane.YES_NO_OPTION);
		if (loadFile == JOptionPane.YES_OPTION) {
			scheduler.loadAppointmentsFromFile(file, scheduler.getAppointments());
			JOptionPane.showMessageDialog(null, "Appointment loaded from file");
		}
	}
}
