import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

public class winTwo {
	

	 static private Object obj = new Object();

	    public static void staticWait() {
	        synchronized (obj) {
	            try {
	                obj.wait();
	            } catch (Exception e) {}
	        }    
	    }

	    public static void staticNotify() {
	        synchronized (obj) {
	            obj.notify();
	        }
	    }

	private JFrame frame;
	public static int[][] grid;
	boolean seedingInProgress = true;
	boolean readyToSimulate = false;
	JRadioButton rdbtnStepByStep;
	JPanel controlPanel;
	int r=0;
	int c=0;
	int cellSize=50;
	
	final int row;
	final int col;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static int delay;

	public winTwo(int[][] _grid) {
		row = _grid.length;
		col = _grid[0].length;
		initialize(_grid);
	}

	public void initialize( int[][] _grid) {
		grid=_grid;
		if(row>12)
			cellSize = 600/row;
		if(col*cellSize>1350)
			cellSize = 1350/col;
			
		
		frame = new JFrame();
		if(col*cellSize<350)
			frame.setBounds(100, 100, 350, row*cellSize+150);
		else
			frame.setBounds(100, 100, col*cellSize+40, row*cellSize+150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel simulationPanel = new JPanel() 
		{
			 public void paintComponent(Graphics g)
			               {
			   
			                       g.setColor(Color.DARK_GRAY);
			                       g.fillRect(0,0,1370,760);
			   
			                       for(int i=0;i<row;i++)
			                       {
			                           for(int j=0;j<col;j++)
			                           {
			                               if(grid[i][j]==1)
			                                   g.setColor(Color.black);
			                               else
			                                   g.setColor(Color.white);
			                               g.fillRect(cellSize*j+2,cellSize*i+2,cellSize-2,cellSize-2);
			                           }
			                       }
			               
			               }
		};
		
		
		simulationPanel.addMouseListener(new MouseAdapter(){
			                  @Override
			                 public void mousePressed(MouseEvent e) {
			                	  System.out.println(e.getX());
			                	  System.out.println(e.getY());
			  
			
			                      if(seedingInProgress)
			                      {
			                          int x= e.getX();
			                          int y= e.getY();
			  
			                          c = x / cellSize;
			                          r = y / cellSize;
			  
			                          
			                              if (grid[r][c]==0)
			                                  grid[r][c]=1;
			                              else
			                                  grid[r][c]=0;
			                          
			                      }
			                 }
			             });
		
		simulationPanel.setBounds(10, 10, col*cellSize+2, row*cellSize+2);
		frame.getContentPane().add(simulationPanel);
		
		controlPanel = new JPanel(); 
		if(col*cellSize<350)
			controlPanel.setBounds(10, row*cellSize+20, 300, 90);
		else
			controlPanel.setBounds(10, row*cellSize+20, col*cellSize, 90);
		frame.getContentPane().add(controlPanel);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {30, 0, 0, 30, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		controlPanel.setLayout(gbl_contentPane);
		
		JButton btnRandom = new JButton("RandomPattern");
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grid = generateRandomGrid(row,col);
			}
		});
		GridBagConstraints gbc_btnRandom = new GridBagConstraints();
		gbc_btnRandom.insets = new Insets(0, 0, 5, 5);
		gbc_btnRandom.gridx = 1;
		gbc_btnRandom.gridy = 1;
		controlPanel.add(btnRandom, gbc_btnRandom);
		
		JButton btnClear = new JButton("    Clear    ");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int l=0; l<row; l++)
		         {
		             for(int m=0; m<col; m++) {
		                 grid[l][m] = 0;
		             }
		         }
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 5);
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 1;
		controlPanel.add(btnClear, gbc_btnClear);
		
		rdbtnStepByStep = new JRadioButton("Show step by step process");
		rdbtnStepByStep.setSelected(true);
		buttonGroup.add(rdbtnStepByStep);
		GridBagConstraints gbc_rdbtnStepByStep = new GridBagConstraints();
		gbc_rdbtnStepByStep.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnStepByStep.gridx = 1;
		gbc_rdbtnStepByStep.gridy = 2;
		controlPanel.add(rdbtnStepByStep, gbc_rdbtnStepByStep);
		
		JRadioButton rdbtnFinalState = new JRadioButton("Show the final State directly");
		buttonGroup.add(rdbtnFinalState);
		GridBagConstraints gbc_rdbtnFinalState = new GridBagConstraints();
		gbc_rdbtnFinalState.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFinalState.gridx = 2;
		gbc_rdbtnFinalState.gridy = 2;
		controlPanel.add(rdbtnFinalState, gbc_rdbtnFinalState);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seedingInProgress=false;
				readyToSimulate = true;
				if(rdbtnStepByStep.isSelected())
					delay = 200;
				else
					delay=0;
				controlPanel.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridwidth = 2;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 3;
		controlPanel.add(btnStart, gbc_btnStart);
		
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
	}

	protected static int[][] generateRandomGrid(int row, int col)
	{
		int[][] grid = new int[row][col];
		 Random rand = new Random();
         for(int l=0; l<row; l++)
         {
             for(int m=0; m<col; m++) {
                 grid[l][m] = rand.nextInt(2);
             }
         }
         return grid;
	}

	public void repaint() {
		frame.repaint();
	}
}
