package telerik;

import java.util.Scanner;

public class Game {
	static generirane labyrinth;
	//The main class of the project. This is used to run the project.
	public static void main(String[] args){
		//The main method. This method starts when the project is run and then initiates the 
		//other classes and calls the other methods as needed.
		labyrinth = new generirane();
		labyrinth.initializeScoreBoard();
		while(true){
			labyrinth.initializeMaze();
			solveGame();
			finishGame();
//			labyrinth.isExit = false;
//			labyrinth.playersCurrentColumn = 3;
//			labyrinth.playersCurrentRow = 3;
//			labyrinth.playersMovesCount = 0;
		}
	}
	public static void solveGame() {
		//Takes input from user as long as the labyrinth is unsolved
		while((labyrinth.playersCurrentColumn!=0)&&(labyrinth.playersCurrentColumn!=6)
				&&(labyrinth.playersCurrentRow!=0)&&(labyrinth.playersCurrentRow!=6)){
			labyrinth.inputCommand();			
		}
		System.out.println();
		labyrinth.printMaze();
	}
	
	public static void finishGame() {
		//Handles adding to the high score board once the player solves a labyrinth
		System.out.println("Congratulations! You escaped in "+labyrinth.playersMovesCount+" moves.");
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name : ");
		String name = scanner.next();
		Player player = new Player(name,labyrinth.playersMovesCount);
		if(labyrinth.board.addPlayerToChart(player)==true){
			System.out.println("Your score is in top 5!");
			labyrinth.board.printBoard(labyrinth.board.list);
		}
		//scanner.close(); //Closing the scanner breaks the code. Not closing gives a warning.
	}	
}
