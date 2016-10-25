import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.toedter.calendar.JCalendar;

import java.text.DateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

public class CarRental
{
	public static Long dateDiff;
	public static JComboBox custName = new JComboBox();
    public static JComboBox carsAvailable = new JComboBox();
    public static JTextField rentTB = new JTextField(10);
	public static Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carRental","root","");
		}
		catch (Exception ex)
		{
            ex.printStackTrace();
        }
		return con;
		
	}
	public static void customerDetailsInput()
	{
		final JFrame main = new JFrame("Customer Details Input");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gui = new JPanel(new BorderLayout(3,3));
        gui.setBorder(new EmptyBorder(5,5,5,5));
        gui.setPreferredSize(new Dimension(400, 400));
        main.setContentPane(gui);	
		
		
		final JTextField name, id, phone;
        JButton submit, back;
    	
        name = new JTextField(10);
        id = new JTextField(10);
        phone = new JTextField(10);


        JPanel labels = new JPanel(new GridLayout(0,1));
        JPanel controls = new JPanel(new GridLayout(0,1));
        JPanel buttonPane = new JPanel();
        gui.add(labels, BorderLayout.WEST);
        gui.add(controls, BorderLayout.CENTER);

        labels.add(new JLabel("Customer ID: "));
        controls.add(id);
        final Connection conn = connect();
        try
        {
        	Statement stmt = conn.createStatement();
            String sql = "SELECT CustID FROM customer;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
            	Integer s = Integer.parseInt(rs.getString(1));
            	System.out.println(s++);
            	id.setText((s++).toString());
            }
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        id.disable();
        labels.add(new JLabel("Customer Name: "));
        controls.add(name);
        labels.add(new JLabel("Phone Number: "));
        controls.add(phone);
        
        submit = new JButton("Submit");
        buttonPane.add(submit);
        back = new JButton("Back");
        buttonPane.add(back);

        main.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        main.pack();
        main.setVisible(true);
        
        submit.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		try
        		{
        			Statement stmt = conn.createStatement();
        			String sql = "INSERT INTO carrental.customer(CustID, Name, Phone) " +
                            "VALUES ("+ Integer.parseInt(id.getText())+ ", '" + name.getText() + "', '"+ phone.getText()+"');";
            		stmt.executeUpdate(sql);
            		System.out.println("Customer Details inserted successfully!!");
        		}
        		catch(SQLException ex)
        		{
        			ex.printStackTrace();
        		}
        	}
        }  );
        
        back.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		main.setVisible(false);
        		createAndShowGUI();
        	}
        }  );
        
	}
	public static void carDetailsInput()
	{
		final JFrame main = new JFrame("Car Details Input");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gui = new JPanel(new BorderLayout(3,3));
        gui.setBorder(new EmptyBorder(5,5,5,5));
        gui.setPreferredSize(new Dimension(400, 400));
        main.setContentPane(gui);	
		
        String[] cars = { "Compact", "Medium", "Large", "SUV", "Truck", "Van" };
		final JTextField id, model, year, dailyValue, weeklyValue;
        JButton submit, back;
        final JComboBox<String> carTypes = new JComboBox<String>(cars);
    	
        model = new JTextField(10);
        id = new JTextField(10);
        year = new JTextField(10);
        dailyValue = new JTextField(10);
        weeklyValue = new JTextField(10);


        JPanel labels = new JPanel(new GridLayout(0,1));
        JPanel controls = new JPanel(new GridLayout(0,1));
        JPanel buttonPane = new JPanel();
        gui.add(labels, BorderLayout.WEST);
        gui.add(controls, BorderLayout.CENTER);

        labels.add(new JLabel("Vehicle ID: "));
        controls.add(id);
        final Connection conn = connect();
        try
        {
        	Statement stmt = conn.createStatement();
            String sql = "SELECT VehicleID FROM cardetail;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
            	Integer s = Integer.parseInt(rs.getString(1));
            	System.out.println(s++);
            	id.setText((s++).toString());
            }
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        id.disable();
        labels.add(new JLabel("Model: "));
        controls.add(model);
        labels.add(new JLabel("Year: "));
        controls.add(year);
        labels.add(new JLabel("Car Type: "));
        controls.add(carTypes);
        labels.add(new JLabel("Daily Rental Value: "));
        controls.add(dailyValue);
        labels.add(new JLabel("Weekly Rental Value: "));
        controls.add(weeklyValue);
        
        submit = new JButton("Submit");
        buttonPane.add(submit);
        back = new JButton("Back");
        buttonPane.add(back);

        main.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        main.pack();
        main.setVisible(true);
        
        submit.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		try
        		{
        			Statement stmt = conn.createStatement();
        			String sql = "INSERT INTO carrental.cardetail " +
                            "VALUES ("+ Integer.parseInt(id.getText())+ ", '" + model.getText().toString() + "', '" + year.getText().toString() + "', '"+ carTypes.getSelectedItem().toString()+"', "+Integer.parseInt(dailyValue.getText())+", "+Integer.parseInt(weeklyValue.getText())+", 0);";
            		stmt.executeUpdate(sql);
            		System.out.println("Car Details inserted successfully!!");
        		}
        		catch(SQLException ex)
        		{
        			ex.printStackTrace();
        		}
        	}
        }  );
        
        back.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		main.setVisible(false);
        		createAndShowGUI();
        	}
        }  );
	}
	public static void rentalDetails()
	{
		final JFrame main = new JFrame("Rental Details");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel gui = new JPanel(new BorderLayout(3,3));
        gui.setBorder(new EmptyBorder(5,5,5,5));
        main.setContentPane(gui);	
		
        String[] cars = { "Compact", "Medium", "Large", "SUV", "Truck", "Van" };
		JRadioButton dailyRate, weeklyRate;
        JButton submit, back;
        JLabel startDate1, endDate1, startDate2, endDate2;
        
        
        final JPanel leftPane = new JPanel(new GridLayout(0,1));
        final JPanel rightPane = new JPanel(new GridLayout(0,1));
        final JPanel buttonPane = new JPanel();
        gui.add(leftPane, BorderLayout.WEST);
        gui.add(rightPane, BorderLayout.CENTER);
        
        final Connection conn = connect();
        leftPane.add(new JLabel("Customer Name: "));
        rightPane.add(custName);
        try
        {
        	String selectTableSQL = "SELECT Name FROM carrental.customer";
        	Statement statement = conn.createStatement();
        	ResultSet rs = statement.executeQuery(selectTableSQL);
        	while (rs.next())
        	{
        		custName.addItem(rs.getString(1));	
        	}
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        
        leftPane.add(new JLabel("Car Type: "));
        rightPane.add(carsAvailable);
        try
        {
        	String model = "SELECT Model FROM carrental.cardetail";
        	Statement statement1 = conn.createStatement();
        	ResultSet rs1 = statement1.executeQuery(model);
        	
        	String year = "SELECT Year FROM carrental.cardetail";
        	Statement statement2 = conn.createStatement();
        	ResultSet rs2 = statement2.executeQuery(year);
        	
        	String isReserved = "SELECT isReserved FROM carrental.cardetail";
        	Statement statement3 = conn.createStatement();
        	ResultSet rs3 = statement3.executeQuery(isReserved);
        	while ((rs1.next()) && (rs2.next())&& (rs3.next()))
        	{
        		if(rs3.getString(1).equals("0"))
        			carsAvailable.addItem(rs1.getString(1)+ " " +rs2.getString(1));	
        	}
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        
        startDate2 = new JLabel("Choose a Start Date:");
        leftPane.add(startDate2);
        UtilDateModel model2 = new UtilDateModel();
        model2.setSelected(true);
    	Properties p2 = new Properties();
    	p2.put("text.today", "Today");
    	p2.put("text.month", "Month");
    	p2.put("text.year", "Year");
    	JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
    	final JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
    	
    	rightPane.add(datePicker2);
    	
    	endDate2 = new JLabel("Choose an End Date:");
    	leftPane.add(endDate2);
    	UtilDateModel model22 = new UtilDateModel();
    	model22.setSelected(true);
    	Properties p22 = new Properties();
    	p22.put("text.today", "Today");
    	p22.put("text.month", "Month");
    	p22.put("text.year", "Year");
    	JDatePanelImpl datePanel22 = new JDatePanelImpl(model22, p22);
    	final JDatePickerImpl datePicker22 = new JDatePickerImpl(datePanel22, new DateLabelFormatter());
    	rightPane.add(datePicker22);
  	
        submit = new JButton("Submit");
        buttonPane.add(submit);
        back = new JButton("Back");
        buttonPane.add(back);

        main.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        main.pack();
        main.setVisible(true);
        
        submit.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		Date sDate = (Date) datePicker2.getModel().getValue();
        		Date eDate = (Date) datePicker22.getModel().getValue();
        		System.out.println("sdate: "+ sDate);
        		System.out.println("edate: "+ eDate);
        		dateDiff = (eDate.getTime() - sDate.getTime())/(1000 * 60 * 60 * 24);
        		System.out.println(dateDiff);
        		newWindow(carsAvailable.getSelectedItem().toString());
        	}
        }  );
        back.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		main.setVisible(false);
        		createAndShowGUI();
        	}
        }  );
	}
	private static Long calculateRent(String carSelected)
	{
		final Connection conn = connect();
		final ResultSet rs1;
		long rent =0;
		try
		{
			String weeklyValue = "SELECT Model, DailyRentalValue, WeeklyRentalValue FROM carrental.cardetail";
			Statement statement = conn.createStatement();
	    	rs1 = statement.executeQuery(weeklyValue);
	    	while(rs1.next())
	    	{
	    		String model = rs1.getString("Model");
	    		int dailyRentalValue = rs1.getInt("DailyRentalValue");
	    		int weeklyRentalValue = rs1.getInt("WeeklyRentalValue");
	    		if(carSelected.toLowerCase().contains(model.toLowerCase()))
	    		{
	    			if(dateDiff < 7)
	    			{
	    				rent = dateDiff * dailyRentalValue;
	    			}
	    			else
	    			{
	    				if(dateDiff % 7 == 0)
		    			{
		    				rent = (dateDiff/7) * weeklyRentalValue;
		    			}
	    				else
	    				{
	    					rent = ((int)(dateDiff/7)* weeklyRentalValue) + ((dateDiff%7) * dailyRentalValue);
	    				}
	    			}
				}
	    	}
		}
    	catch(SQLException e)
		{
    		e.printStackTrace();
		}
		return rent;
	}
	public static void onClikingYes()
	{
		Connection conn = connect();
		
		try
		{
			Statement stmt1 = conn.createStatement();
	        String custID = "SELECT CustID FROM customer WHERE Name = '"+ custName.getSelectedItem().toString() +"';";
	        ResultSet rs1 = stmt1.executeQuery(custID);
	        Statement stmt2 = conn.createStatement();
	        String[] mod = carsAvailable.getSelectedItem().toString().split("2");
	        String vehID = "SELECT VehicleID FROM carrental.cardetail WHERE Model = '"+ mod[0] +"';";
	        ResultSet rs2 = stmt2.executeQuery(vehID);
	        while(rs1.next() && rs2.next())
	        {
	        	Statement stmt3 = conn.createStatement();
	        	String insertTableSQL = "INSERT INTO carrental.rentalreservation"
	    				+ "(CustID, VehicleID, NoofDays, TotalCost) " + "VALUES"
	    				+ "("+ Integer.parseInt(rs1.getString(1)) +", "+ Integer.parseInt(rs2.getString(1)) +", "+ dateDiff +", "+ Integer.parseInt(rentTB.getText())+");";
	        	stmt3.executeUpdate(insertTableSQL);
	        	
	        	Statement stmt4 = conn.createStatement();
	            String sql = "UPDATE carrental.cardetail " +
	                         "SET isReserved = 1 WHERE Vehicleid = "+Integer.parseInt(rs2.getString(1))+";";
	            stmt4.executeUpdate(sql);
	        }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		
	private static void newWindow(String carSelected)
	{
        final JFrame newWin = new JFrame("Reservation Details");
        newWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gui = new JPanel(new BorderLayout(3,3));
        gui.setBorder(new EmptyBorder(5,5,5,5));
        gui.setPreferredSize(new Dimension(400, 400));
        newWin.setContentPane(gui);
        
        final JPanel leftPane = new JPanel(new GridLayout(0,1));
        final JPanel rightPane = new JPanel(new GridLayout(0,1));
        final JPanel buttonPane = new JPanel();
        newWin.add(leftPane, BorderLayout.WEST);
        newWin.add(rightPane, BorderLayout.CENTER);
        
        JTextField periodReservedTB = new JTextField(10);
        JLabel periodReserved = new JLabel("Period for which the car is reserved:");
        leftPane.add(periodReserved);
        rightPane.add(periodReservedTB);
        periodReservedTB.setText(dateDiff.toString());
        
        JLabel rent = new JLabel("Rent for the period will be:");
        leftPane.add(rent);
        rightPane.add(rentTB);
        Long rentAmount = calculateRent(carSelected);
        rentTB.setText(rentAmount.toString());
        
        JButton submit = new JButton("Submit");
        buttonPane.add(submit);
        JButton back = new JButton("Back");
        buttonPane.add(back);
        
        newWin.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        newWin.pack();
        newWin.setVisible(true);
        
        
        submit.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		MyDialog dialog = new MyDialog(new JFrame(), "Confirm Reservation", "Do you want to confirm your reservation?");
                dialog.setSize(300, 150);
        	}
        }  );
        back.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		newWin.dispose();
        	}
        }  );
	}
	
	public static void retrieveCar(String name)
	{
		final Connection conn = connect();
        try
        {
        	String selectTableSQL = "SELECT CustID FROM carrental.customer WHERE Name = '"+ name +"';";
        	Statement statement = conn.createStatement();
        	ResultSet custId = statement.executeQuery(selectTableSQL);
//        	while (custId.next())
//        	{
//        		custName.addItem(rs.getString(1));	
//        	}
        	String vehId = "SELECT VehicleID FROM carrental.rentalreservation WHERE CustID = "+ custId.getString(0) +";"; 
        	
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        
	}
	
	public static void cancelReservation()
	{
		final JFrame main = new JFrame("Cancel Reservation");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel gui = new JPanel(new BorderLayout(3,3));
        gui.setBorder(new EmptyBorder(5,5,5,5));
        main.setContentPane(gui);	
		
        //String[] cars = { "Compact", "Medium", "Large", "SUV", "Truck", "Van" };
		//JRadioButton dailyRate, weeklyRate;
        JButton submit, back;
//        JLabel startDate1, endDate1, startDate2, endDate2;
        
        
        final JPanel leftPane = new JPanel(new GridLayout(0,1));
        final JPanel rightPane = new JPanel(new GridLayout(0,1));
        final JPanel buttonPane = new JPanel();
        gui.add(leftPane, BorderLayout.WEST);
        gui.add(rightPane, BorderLayout.CENTER);
        
        
        leftPane.add(new JLabel("Customer Name: "));
        rightPane.add(custName);
        final Connection conn = connect();
        try
        {
        	String selectTableSQL = "SELECT Name FROM carrental.customer";
        	Statement statement = conn.createStatement();
        	ResultSet rs = statement.executeQuery(selectTableSQL);
        	while (rs.next())
        	{
        		custName.addItem(rs.getString(1));	
        	}
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        
        submit = new JButton("Submit");
        buttonPane.add(submit);
        back = new JButton("Back");
        buttonPane.add(back);

        main.getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        main.pack();
        main.setVisible(true);
        
        custName.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
            	if(e.getStateChange()==ItemEvent.SELECTED)
            	{
//            		e.getItem();  //Do what ever you want :))
//            		System.out.println("e: "+e.getItem());
            		retrieveCar(e.getItem().toString());
                }
            }
        });
        submit.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
//        		Date sDate = (Date) datePicker2.getModel().getValue();
//        		Date eDate = (Date) datePicker22.getModel().getValue();
//        		System.out.println("sdate: "+ sDate);
//        		System.out.println("edate: "+ eDate);
//        		dateDiff = (eDate.getTime() - sDate.getTime())/(1000 * 60 * 60 * 24);
//        		System.out.println(dateDiff);
//        		newWindow(carsAvailable.getSelectedItem().toString());
        	}
        }  );
	}
	
    public static void createAndShowGUI()
    {    	
    	final JFrame main = new JFrame("Main Form ");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        JPanel gui = new JPanel(new BorderLayout(3,3));
        JPanel gui = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gui.setBorder(new EmptyBorder(5,5,5,5));
        
       // main.add(gui);

        JButton add_customer, add_car, add_rentals,cancel_reservations;
        JPanel controls = new JPanel(new GridLayout(0,1));
        gui.add(controls, BorderLayout.CENTER);

        
        add_customer = new JButton("Add Customer Details");
        gui.add(add_customer);
        add_car = new JButton("Add Car Details");
        gui.add(add_car);
        add_rentals = new JButton("Add Rentals");
        gui.add(add_rentals);
        cancel_reservations = new JButton("Cancel Reservations");
        gui.add(cancel_reservations);
        main.getContentPane().add(gui);

        main.pack();
        main.setVisible(true);
        
        add_customer.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		main.setVisible(false);
        		customerDetailsInput();
        	}
        }  );
        
        add_car.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		main.setVisible(false);
        		carDetailsInput();
        	}
        }  );
        
        add_rentals.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		main.setVisible(false);
        		rentalDetails();
        	}
        }  );
        
        cancel_reservations.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		main.setVisible(false);
        		cancelReservation();
        	}
        }  );
    }

    public static void main(String[] args)
    {
    	connect();
    	createAndShowGUI();
    }
}