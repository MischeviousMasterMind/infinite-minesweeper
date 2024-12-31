package minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.math.*;

public class Frame extends JPanel implements ActionListener, MouseListener
{

	final static int WIDTH = 1000, HEIGHT = 1000;

	public Frame()
	{

		JFrame window = new JFrame("Minesweeper");

		window.setSize(new Dimension(WIDTH, HEIGHT));
		window.setBackground(Color.white);
		window.add(this);
		window.setResizable(false);
		window.addMouseListener(this);

		// set custom cursor eventually

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		Frame frame = new Frame();

		ArrayList<Minefield> fields = new ArrayList<Minefield>();
		
		for(int i = 0; i < 10; i++)
		{
			fields.add(new Minefield());
		}

		for(Minefield field : fields)
		{
			System.out.println(field);
		}
		
		System.out.println("Number of Configurations: " + fields.get(1).numberOfConfigurations());
		System.out.println(ExtendedMath.nPr(3,2));
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

}
