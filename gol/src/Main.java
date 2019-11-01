public class Main {
	
	
	public static void main(String[] args)
	{
		int a=0;
		winOne g1 = new winOne();
		while(!g1.parametersInitialized)
		{
				winTwo.staticWait();
		}
		
		final int row = g1.row;
		final int col = g1.col;
		int[][] grid = new int[row][col];
		
		winTwo g2  = new winTwo(grid);
		while(!g2.readyToSimulate)
			g2.repaint();
		while(true)
		{
		winTwo.grid = nextGenerationMatrix(winTwo.grid);
		g2.repaint();
		try
        {
            Thread.sleep(winTwo.delay);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
		}
	}


	private static int[][] nextGenerationMatrix(int[][] grid) {
		int value=0;
		final int row = grid.length;
		final int col = grid[0].length;
		int[][] grid1 = new int[row][col];
	    for (int i=0;i<row;i++)
	    {
	        for (int j=0;j<col;j++)
	        {
	            if(i==0&j==0)
	            {
	                value = grid[i][j+1]+grid[i+1][j]+grid[i+1][j+1];
	            }
	            else if(i==0&j==(col-1))
	            {
	                value = grid[i][j-1]+grid[i+1][j]+grid[i+1][j-1];
	            }
	            else if(i==(row-1)&j==0)
	            {
	                value = grid[i][j+1]+grid[i-1][j]+grid[i-1][j+1];
	            }
	            else if(i==(row-1)&j==(col-1))
	            {
	                value = grid[i][j-1]+grid[i-1][j]+grid[i-1][j-1];
	            }
	            else if(i==0)
	            {
	                value = grid[i][j-1]+grid[i][j+1]+grid[i+1][j-1]+grid[i+1][j]+grid[i+1][j+1];
	            }
	            else if(j==0)
	            {
	                value = grid[i-1][j]+grid[i+1][j]+grid[i-1][j+1]+grid[i][j+1]+grid[i+1][j+1];
	            }
	            else if(j==(col-1))
	            {
	                value =grid[i-1][j]+grid[i+1][j]+grid[i-1][j-1]+grid[i][j-1]+grid[i+1][j-1];
	            }
	            else if(i==(row-1))
	            {
	                value = grid[i][j-1]+grid[i][j+1]+grid[i-1][j-1]+grid[i-1][j]+grid[i-1][j+1];
	            }
	            else
	            {
	                value = grid[i-1][j-1]+grid[i-1][j]+grid[i-1][j+1]+grid[i][j-1]+grid[i][j+1]+grid[i+1][j-1]+grid[i+1][j]+grid[i+1][j+1];
	            }

	            if (value<=1)
	                grid1[i][j]=0;
	            else if (value==2)
	                grid1[i][j]=grid[i][j];
	            else if (value==3)
	                grid1[i][j]=1;
	            else if (value>=4)
	                grid1[i][j]=0;

	        }
	    }
		return grid1;
	}
}
