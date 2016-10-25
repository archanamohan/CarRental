import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyDialog extends JDialog
{
	public MyDialog(JFrame parent, String title, String message)
	{
		super(parent, title);
		Point p = new Point(400, 400);
		setLocation(p.x, p.y);

		JPanel messagePane = new JPanel();
		messagePane.add(new JLabel(message));
		getContentPane().add(messagePane);
		
		JPanel buttonPane = new JPanel();
		JButton yes = new JButton("Yes");
		buttonPane.add(yes);
		JButton cancel = new JButton("Cancel");
		buttonPane.add(cancel);

		yes.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		CarRental.onClikingYes();
        		dispose();
        	}
        }  );
		
		cancel.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{     
        		dispose();
        	}
        }  );
		getContentPane().add(buttonPane, BorderLayout.PAGE_END);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
