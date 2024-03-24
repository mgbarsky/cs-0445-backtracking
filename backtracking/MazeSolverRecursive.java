package backtracking;


/**
 *  Simple demo program for solving mazes using backtracking 
 *  @author M. Barsky
 *  */

public class MazeSolverRecursive {
	// types of maze cells
	public static final int SPACE = 0, WALL = 1, START = 2, EXIT = 3, VISITED = 4, ONPATH = 5;		
	
	private static String cellChar (int type) {
		if (type == SPACE) return "o";
		if (type == WALL) return "-";
		if (type == START) return "S";
		if (type == EXIT) return "X";
		if (type == VISITED) return "v";
		if (type == ONPATH) return "#";
		return null;
	}
	
	// prints the cells of the maze
	public static void printMaze(int [][] maze) {
		int N = maze.length;     // rows
		int M = maze[0].length;  // cols
		
		for (int i = 0; i < N; i++) {
			for (int j=0; j < M; j++) {
				System.out.print(cellChar(maze[i][j]) + " ");				
			}
			System.out.println();
		}
	}	
	
	// we can move into this cell if:
	// 1. cell is inside the maze
	// 2. cell is passable
	// 3. cell was not visited before
	private static boolean legalCell (int [][] maze, int row, int col) {
		 return (row >= 0 && row < maze.length && col >= 0
			               && col < maze[0].length 
			               && (maze[row][col] == START ||
			               maze[row][col] == SPACE || 
			               maze[row][col] == EXIT));
			   
	}	
	
	// Solves maze by exhaustive search
	// Starting with the START cell tires to move into one direction
	// until either finds the exit or get stack
	// 
	// When hits a dead end - retreats to the previous solution
	// It does not need to do anything special for this:
	// 		the recursion frame is unloaded and the program returns to the the calling point
	// 		It can look around to see if there are more cells to explore from this current place
	public static void solveMaze(int [][] maze) {
		int N = maze.length;     // rows
		int M = maze[0].length;  // cols
		
		// find the start cell - to start exploring
		for (int i = 0; i < N; i++) {
			for (int j=0; j < M; j++) {
				if (maze[i][j] == START) {
					boolean success = findExit (maze, i, j);
					if (success) {
						System.out.println("\nPath found: ");
						printMaze (maze);						
					}
					else {
						System.out.println("No solution");
					}
					return;	
				}
			}			
		}
		System.out.println("Could not find a start cell??");
	}	
	
	// recursively continues from the cell: maze [row][col]
	
	// the order in which the neighbors are explored is: 
	// North (12 o'clock), and then clockwise: NESW (Up-Right-Down-Left)
	private static boolean findExit (int [][] maze, int row, int col) {
		if (!legalCell(maze, row,col)) { //ended up outside the grid or hit the wall or dead end
			return false;
		}
		
		if (maze[row][col] == EXIT) {
			return true;
		}
		
		// first thing: mark as visited not to go here again
		maze[row][col] = VISITED;
		boolean success = false;
		
		// try north (UP)
		success = findExit(maze, row-1, col);
		if (success) {
			maze[row][col] = ONPATH;
			return true;
		}
		
		//try east
		success = findExit(maze, row, col+1);
		if (success) {
			maze[row][col] = ONPATH;
			return true;
		}
		
		//try south
		success = findExit(maze, row+1, col);
		if (success) {
			maze[row][col] = ONPATH;
			return true;
		}
		
		//try west
		success = findExit(maze, row, col-1);
		if (success) {
			maze[row][col] = ONPATH;
			return true;
		}
		
		// if here - there were no path from this cell
		// backtrack
		return false;
	}	

   public static void main(String args[])  {
	   System.out.println("Maze 1:");	  
	   int maze1[][] = { 
			   {2, 0, 0},
			   {0, 0, 0},
			   {0, 0, 3}
	   };
	   
	   printMaze(maze1);
	   solveMaze(maze1);
	   
	   System.out.println("\nMaze 2:");
	   int maze2[][] = {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			   {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
			   {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
			   {0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0}, 
			   {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
			   {0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0},
			   {0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 1, 3, 0}};
	   printMaze(maze2);
	   solveMaze(maze2);
	   
	   System.out.println("\nMaze 3:");
       int maze3[][] = { { 2, 1, 1, 1 },
                        { 0, 0, 1, 0 },
                        { 1, 0, 1, 1 },
                        { 0, 0, 0, 3 } };
       printMaze(maze3);
	   solveMaze(maze3); 
   }
}
