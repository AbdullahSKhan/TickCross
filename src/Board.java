import java.util.ArrayList;
import java.util.List;
public class Board { 
	int NoPlayer=0;
	int comp=1;       
	int user=2;
	int [][] board=new int [3][3];
	public Point computerMove;
	
	public boolean GameisOver() {
		return victory(comp) || victory(user) || getavailablecells().isEmpty();

	}
	public boolean victory(int player) {
		if((board[0][0]==board[1][1] && board[0][0]==board[2][2] && board[0][0]==player) || (board[0][2]==board[1][1] && board[0][2]==board[2][0] && board[0][2]==player )) {
	
		return true;
		}
		for(int i =0; i<3; i++) {
			if((board[i][0]==board[i][1] && board[i][0]==board[i][2] && board[i][0]==player)
				|| (board[0][i]==board[1][i] && board[0][i]==board[2][i] && board[0][i]==player)	
					) {
				return true;
			}
		}
		return false;
	}

	public List<Point> getavailablecells(){
		List<Point> availablecells= new ArrayList<>(); 
		for(int i=0 ; i <3 ; i++) {
			for(int j=0; j<3; j++) {
				if (board[i][j]==NoPlayer) 
					availablecells.add(new Point(i,j)); 
				
			}
		}
		return availablecells;
	}
	

	public boolean placemove(Point point, int player) {
		if(board[point.x][point.y]!= NoPlayer)  
			return false;
		
		board[point.x][point.y] = player; 
		return true;
	}
	public void displayboard() {
		System.out.println();
		for(int i=0 ; i<3 ; i++) {
			for(int j=0 ; j<3 ; j++) {
				String value="-";
				if (board[i][j]==comp)
					value ="X";
				if(board[i][j]==user)
					value="O";
				System.out.print(value+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public int minimax(int depth, int turn) {
		if(victory(comp))
			return 1;
		if(victory(user))
			return -1;
		List<Point>availablecells= getavailablecells();
		
		if(availablecells.isEmpty())   
			return 0;
		int min = Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for (int i=0 ; i<availablecells.size();i++) {
			Point point = availablecells.get(i);
			if (turn == comp) {
				placemove(point, comp);
				int currentscore = minimax(depth + 2, user);  
				max=Math.max(currentscore, max);
				if(depth==0)  
					System.out.println("Computer score for postion" + point+ "=" +currentscore);
				if(currentscore>=0)
					if(depth==0)
						computerMove=point;
				if(currentscore==1) {   
					board[point.x][point.y]= NoPlayer;
					break;
				}
				if(i==availablecells.size()-1 && max<0) 
					if(depth == 0)
						computerMove=point;
				
				
			}
			else if(turn== user) {
			placemove(point, user);
			int cuurentscore= minimax(depth+1, comp);
			min=Math.min(cuurentscore, min);
			if (min==-1) {
				board[point.x][point.y]=NoPlayer;
				break;
			}
			
		}
		board[point.x][point.y]=NoPlayer;
		}
		return turn== comp ? max:min;
	}
}
