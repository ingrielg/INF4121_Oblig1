package telerik;

import java.util.LinkedList;

public class HighScoreBoard {
	//This class handles the high score board and adding new players 
	//to this board when they get one of the five best scores
	LinkedList<Player> list;  	//This gave a warning. Added a parameter with the type of elements to be stored
	public final int boardSize = 5;
	public HighScoreBoard(){
		list = new LinkedList<Player>();
	}
	
	public boolean addPlayerToChart(Player player){
		//Adds a player to the high score board
		if(list.size()==0){
			list.addFirst(player);
			return true;
		}	
		Player pl = (Player) list.get(list.size()-1);
		if((list.size()>0)&&(list.size()<boardSize)&&(player.movesCount>=pl.movesCount)){	
				list.addLast(player);
				return true;
		}

		if ((list.size()>0)&&(player.movesCount<pl.movesCount)) {
			if((list.size()==boardSize)){
				list.remove(list.size() - 1);
			}
			int index = 0;
			while (index < list.size()) {
				pl = (Player) list.get(index);
				if (player.movesCount <= pl.movesCount) {
					list.add(index, player);
					return true;
				}
				index++;
			}
		}
		return false;
	}
	
	void printBoard(LinkedList<Player> list){
		//Prints the high score board
		System.out.println("Score :");
		for(int i=0;i<list.size();i++){
			Player p = (Player) list.get(i);
			System.out.print((i+1)+". Name - "+p.name+" ");
			System.out.print("Escaped in "+p.movesCount+" moves");
			System.out.println();
		}
	}
}
