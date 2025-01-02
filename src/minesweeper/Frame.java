package minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{

	static File myFile = new File("random-numbers.csv");
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
		
		Scanner keyboard = new Scanner(System.in);
		
//		Frame frame = new Frame();

		Game myGame = new Game(16, 16, 32);
		
		System.out.println("Game is starting...");
		myGame.start();
		
		myGame.printBoard();
		System.out.println();
		
		while(true)
		{
			System.out.println("Do you want to flag a mine? (y/n) ");
			String answer = keyboard.nextLine();
			
			if(answer.contains("n"))
			{
				System.out.println("What tile do you want to sweep next?");
				
				System.out.print("X Coordinate: ");
				int x = keyboard.nextInt();
				
				System.out.print("Y Coordinate: ");
				int y = keyboard.nextInt();
				
				System.out.println(myGame.sweep(y, x));
				System.out.println();
				myGame.printBoard();
				System.out.println();
			}
			else
			{
				System.out.println("What tile do you want to flag?");
				
				System.out.print("X Coordinate: ");
				int x = keyboard.nextInt();
				
				System.out.print("Y Coordinate: ");
				int y = keyboard.nextInt();
				
				System.out.println(myGame.flag(y, x));
				System.out.println();
				myGame.printBoard();
				System.out.println();
			}
			
			keyboard.nextLine();
		}
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

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
