package com.totalcross.knowcode;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import com.totalcross.knowcode.parse.XmlContainerFactory;
import com.totalcross.knowcode.parse.XmlContainerLayout;

import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;

public class PrinterTotalCross extends MainWindow {

	public PrinterTotalCross() {
		setUIStyle(Settings.MATERIAL_UI);
	}

	static {
		Settings.applicationId = "TCMT";
		Settings.appVersion = "1.0.0";
		Settings.iosCFBundleIdentifier = "com.totalcross.easytiful";
	}

	public void initUI() {
		XmlContainerLayout xmlCont = (XmlContainerLayout) XmlContainerFactory.create("xml/printer_design.xml");
		swap(xmlCont);
		
		Button copy = (Button) xmlCont.getControlByID("@+id/copy");
		copy.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {	
				try {
					Process process = Runtime.getRuntime().exec("python copy.py");
					JOptionPane.showMessageDialog(null, "COPY");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		Button scan = (Button) xmlCont.getControlByID("@+id/scan");
		scan.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				try {
					Process process = Runtime.getRuntime().exec("python SCAN.py");
					JOptionPane.showMessageDialog(null, "SCAN");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		Button print = (Button) xmlCont.getControlByID("@+id/print");
		print.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				try {
					Process process = Runtime.getRuntime().exec("python print.py");
					JOptionPane.showMessageDialog(null, "PRINT");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		Button setup = (Button) xmlCont.getControlByID("@+id/setup");
		setup.addPressListener(new PressListener() {
			@Override
			public void controlPressed(ControlEvent e) {
				try {
					Process process = Runtime.getRuntime().exec("python setup.py");
					JOptionPane.showMessageDialog(null, "SETUP");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		Label insideTempLabel = (Label) xmlCont.getControlByID("@+id/textView1");
		
		ScheduledExecutorService scheduleService = Executors.newSingleThreadScheduledExecutor();
		scheduleService.scheduleAtFixedRate(new Runnable() {
			
		String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		@Override
		public void run() {
			Calendar hoje = Calendar.getInstance();				
			String pattern = "YYYY HH:mm:ss";
			DateFormat df = new SimpleDateFormat(pattern);
			Date today = Calendar.getInstance().getTime();
			String todayAsString = df.format(today);
			insideTempLabel.setText(hoje.get(Calendar.DAY_OF_MONTH) + " " + months[hoje.get(Calendar.MONTH)] + " " + todayAsString);
		}
	}, 1, 1, TimeUnit.SECONDS);
	}
}