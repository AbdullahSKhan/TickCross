import java.util.Random;
import java.util.Scanner;
public class TicCross {
	public static Random random= new Random();
	public static void main(String[] args) {
		Board b = new Board();
		Scanner scanner=new Scanner(System.in);
		b.displayboard();
		System.out.println("Select turn:\n 1. Computer(X)/ 2. User(O) "); 
		int choice= scanner.nextInt();
		if(choice==b.comp) {
			Point p = new Point(random.nextInt(3),random.nextInt(3));
			b.placemove(p, b.comp);
			b.displayboard();
		}
		while(!b.GameisOver()) {
			boolean moveok= true;
			do {
				if(!moveok) {				
					System.out.println("Cell already filled !");
				}
					System.out.println("Your Move: ");
					Point usermove=new Point(scanner.nextInt(),scanner.nextInt());
					moveok=b.placemove(usermove, b.user);
				}
			while (!moveok);	
			b.displayboard();
			if(b.GameisOver())
				break;
			b.minimax(0, b.comp);
			System.out.println("computer choose postion: "+ b.computerMove);
			b.placemove(b.computerMove, b.comp);
			b.displayboard();
			}
		if(b.victory(b.comp))
			System.out.println("You Lost! ");
		else if(b.victory(b.user))
			System.out.println("You win! ");
		else
			System.out.println("Draw!");
		}
	}

