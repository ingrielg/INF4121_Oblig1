package telerik;

import java.util.Random;
import java.util.Scanner;

public class generirane {
	//This class generates the labyrinth and handles the input and movement of the player
	public boolean isVisited[][] = new boolean[7][7];
	public char maze[][] = new char[7][7];
	public int playersCurrentRow;
	public int playersCurrentColumn;
	public String command;
	public boolean isExit = false;
	public int playersMovesCount = 0;
	HighScoreBoard board;
	

	void initializeMaze(){
		Random randomgenerator = new Random();	
		// Generates a new maze until at least one solution is found
		do{
			for(int row=0;row<7;row++){
				for(int column=0;column<7;column++){
					isVisited[row][column]=false;
					if(randomgenerator.nextInt(2)==1){
						maze[row][column] = 'X';
					}
					else {
						maze[row][column] = '-';
					}
				}
			}
		} while(isSolvable(3, 3)==false);
		resetToStart();
				
		maze[playersCurrentRow][playersCurrentColumn] = '*';
		printMaze();
	}
	
	public void resetToStart() {
		//Resets values to the starting values. Need to be done when generating a new labyrinth
		playersCurrentRow = 3;
		playersCurrentColumn = 3;
		isExit = false;
		playersMovesCount = 0;
	}
	
	
	public void initializeScoreBoard(){
		//Creates the High Score Board
		board = new HighScoreBoard();
	}
	
	public boolean isSolvable(int row, int col){
		//Checks if the randomly generated labyrinth is solvable. If not, a new one is generated until one that can be solved is found.
		if((row==6)||(col==6)||(row==0)||(col==0)){
			isExit = true;
			return isExit;
		}
		int checkLen = 4;
		int check[][] = new int[checkLen][2];
		check[0][0] = row-1;
		check[0][1] = col;
		check[1][0] = row+1;
		check[1][1] = col;
		check[2][0] = row;
		check[2][1] = col-1;
		check[3][0] = row;
		check[3][1] = col+1;
		
		for (int i=0;i<checkLen;i++) {
			if((maze[check[i][0]][check[i][1]]=='-')&&(isVisited[check[i][0]][check[i][1]]==false)) {
				isVisited[row][col] = true;
				isSolvable(check[i][0], check[i][1]);
			}
		}
		return isExit;
	}
	
	void printMaze(){
		//Prints the maze for the user
		for(int row=0;row<7;row++){
			for(int column=0;column<7;column++){
				System.out.print(maze[row][column]+" ");
			}
			System.out.println();
		}
	}
	
	public void inputCommand(){
		//Handles the input from the user
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your next move : L(left), " +
				"R(right), U(up), D(down) ");
		command = scanner.next();
		int size = command.length();
		String validMoves = "LlRrUuDd";
		char inputMove = command.charAt(0);
		if(command.equals("exit") || command.equals("Exit")) {
			System.out.println("Good bye!");
			System.exit(0);
		} else if(command.equals("restart")){
                isExit = false;
                initializeMaze();
        } else if(command.equals("top")){
        	if(board.list.size()>0){
        		board.printBoard(board.list);
        	}
        	else{
        		System.out.println("The High score board is empty!");
        	}
        } else if(size>1){
        	System.out.println("Invalid command!");
        } else if (validMoves.indexOf(inputMove)>=0){
        	movePlayer(inputMove);
        } else {
        	System.out.println("Invalid command!");
        }
		//scanner.close(); //Closed the scanner, as it gives a warning without this statement. Unfortunately, including this statement crashes the code.
	}
	
	public  void movePlayer(char firstLetter){
		//Moves the player in the correct direction if a legal move is given
		int playersMoveRow = playersCurrentRow;
		int playersMoveColumn = playersCurrentColumn;
		if ((firstLetter == 'L')||(firstLetter == 'l')) {
			playersMoveColumn = playersCurrentColumn - 1;
		} else if ((firstLetter == 'R')||(firstLetter == 'r')) {
			playersMoveColumn = playersCurrentColumn + 1;
		} else if ((firstLetter == 'U')||(firstLetter == 'u')) {
			playersMoveRow = playersCurrentRow - 1;
		} else if ((firstLetter == 'D')||(firstLetter == 'd')) {
			playersMoveRow = playersCurrentRow + 1;
		}
		if (maze[playersMoveRow][playersMoveColumn] != 'X') {
			swapCells(playersCurrentRow, playersMoveRow, playersCurrentColumn, playersMoveColumn);
			playersCurrentRow = playersMoveRow;
			playersCurrentColumn = playersMoveColumn;
			playersMovesCount++;
		} else {
			System.out.println("Invalid move!");
			printMaze();
		}
	}
			
	void swapCells(int currentRow, int newRow, int currentColumn, int newColumn){
		//Swaps the value of two cells
		char previousCell = maze[currentRow][currentColumn];
		maze[currentRow][currentColumn] = maze[newRow][newColumn];
		maze[newRow][newColumn] = previousCell;
		System.out.println();
		printMaze();
	}	
}