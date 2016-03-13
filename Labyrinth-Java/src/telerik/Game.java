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
//			while((labyrinth.playersCurrentColumn!=0)&&(labyrinth.playersCurrentColumn!=6)
//					&&(labyrinth.playersCurrentRow!=0)&&(labyrinth.playersCurrentRow!=6)){
//				labyrinth.inputCommand();			
//			}
			solveGame();
//			System.out.println();
//			labyrinth.printMaze();
			finishGame();
//			System.out.println("Congratulations! You escaped in "+labyrinth.playersMovesCount+" moves.");
//			System.out.println();
//			
//			Scanner scanner = new Scanner(System.in);
//			System.out.println("Enter your name : ");
//			String name = scanner.next();
//			Player player = new Player(name,labyrinth.playersMovesCount);
//			if(labyrinth.board.addPlayerToChart(player)==true){
//				System.out.println("Your score is in top 5!");
//				labyrinth.board.printBoard(labyrinth.board.list);
//			}
//			resetGame();
//			labyrinth.isExit = false;
//			labyrinth.playersCurrentColumn = 3;
//			labyrinth.playersCurrentRow = 3;
//			labyrinth.playersMovesCount = 0;
		}		
	}
	public static void solveGame() {
		while((labyrinth.playersCurrentColumn!=0)&&(labyrinth.playersCurrentColumn!=6)
				&&(labyrinth.playersCurrentRow!=0)&&(labyrinth.playersCurrentRow!=6)){
			labyrinth.inputCommand();			
		}
		System.out.println();
		labyrinth.printMaze();
	}
	
	public static void finishGame() {
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
	}
	
//	public static void resetGame() {
//		labyrinth.isExit = false;
//		labyrinth.playersCurrentColumn = 3;
//		labyrinth.playersCurrentRow = 3;
//		labyrinth.playersMovesCount = 0;
//	}
}
