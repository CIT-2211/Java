import java.util.*;


public class a {
	public static void main (String[] args){
		
		//issue01();
		//System.out.println("*****************************************************************************\n\n");
		//issue0201();
		//issue0202();
		//System.out.println("*****************************************************************************\n\n");
		//issue03();
		//int a = issue04(3);
		//System.out.println(a);
		issue04(3);
	}
	
	public static void issue01(){
		System.out.println("Welcome to check it out.");
		System.out.println("Start printing the multiplication table............ Please ............ later");
		for(int i = 1; i <= 9 ; i++){
			for(int j = 1; j<=i ; j++){
				System.out.print(j+"*"+i+"="+(i*j)+" ");
			}
			System.out.println();
		}
		System.out.println("At the end of this program, you are welcome to review\n");
	}
	
	public static void issue0201(){
		System.out.println("We use the \"for\" function");
		System.out.println("Odd and even numbers and the system is booting up...... Please ...... later");
		int mun01 = 0;
		int mun02 = 0;
		for(int i = 1; i <= 100; i++){
			if(i%2 !=0){
				mun01 += i;
			}else{
				mun02 += i;
			}	
		}
		System.out.println("Cardinal sum[0~100]: " + mun01);
		System.out.println("Even sum[0~100]: " + mun02);
		System.out.println("At the end of this program, you are welcome to review\n");
	}
	
	public static void issue0202(){
		System.out.println("We use the \"while\" function");
		System.out.println("Odd and even numbers and the system is booting up...... Please ...... later");
		int mun01 = 0;
		int mun02 = 0;
		int i = 100;
		while(i>=1){
			if(i%2 !=0){
				mun01 += i;
			}else{
				mun02 += i;
			}
				i--;
		}
		System.out.println("Cardinal sum[0~100]: " + mun01);
		System.out.println("Even sum[0~100]: " + mun02);
		System.out.println("At the end of this program, you are welcome to review\n");
	}
	
	public static void issue03(){
		System.out.println("=== 88 LOTTERY SYSTEM ===");
		System.out.println(">> SYSTEM INITIALIZING... GET READY! <<");
		System.out.println("\n------- GAME RULES -------");
		System.out.println("* MAX 30,000 DRAWS PER ROUND @ $500/DRAW");
		System.out.println("* NON-STOP ACTION UNTIL LUCKY 88 APPEARS!");
		System.out.println("* JACKPOT: $10,000 FOR 88");
		System.out.println("* BONUS: $250 FOR LUCKY 8");
		System.out.println("--------------------------");
		System.out.println(">> LADIES & GENTLEMEN - LET'S PLAY! <<");
		System.out.println("Rejoice! Gamblers, this is our home turf!");

		
		int NumberDraws;
		int mun = 0;
		int count = 0;
		int per;
		try (Scanner sr = new Scanner(System.in)){
		while (true) {
			System.out.println("Now, the game beginning! Please enter the number of draws (MAXDRAWS<=30,000):");
			if (sr.hasNextInt()) {
				NumberDraws = sr.nextInt();
				if (NumberDraws <= 30000) {
					System.out.println("Okay! Your number of draws is: " + NumberDraws);
					break;
				} else {
					System.out.println("Error! The number of draws cannot be higher than 30,000!");
				}
			} else {
				System.out.println("Warning! Please enter a valid integer.");
				sr.next();
				}
			}
		}

		while(mun < NumberDraws){
			int a = (int) (Math.random()*100)+1;
			System.out.println("Random number is:" + a);
			mun++;
			if (a == 88){
				break;
			}else if (a % 10 == 8){
				count++;
			}
		}
		
		per = 10000-(mun*500)+(count*250);
		System.out.println("Congratulations on winning 88 and winning the jackpot............");
		System.out.println("The number of draws you have drawn is: "+ mun);
		System.out.println("The number of times the number 8 ticket was drawn is: "+ count);
		
		if (per>10000){
			System.out.println("Venerable Chosen Son, your profitability is:"+per+"$");
			System.out.println("We wish you the best of luck every day and win the 88 jackpot like you did today!");
		} else if (per < 10000 && per >5000) {
			System.out.println("Super lucky guy, your winnings are:"+per+"$");
		} else if (per> 1000 && per <5000) {
			System.out.println("Good job good, guys! Your profit is:"+per+"$");
		}else if (per>500 && per<1000){
			System.out.println("Lucky guys, grab your "+per+ "$ and get out of the way");
		} else if (per>0 && per < 500) {
			System.out.println("Funny guy, not bad, you get "+per+"$");
		}else {
			System.out.println("Unlucky guy, I'm sorry to tell you that not only did you not make any gains this time, but you also lost "+per+"$");
			System.out.println("Have a great day today!");
		}
		System.out.println("<<< THANK YOU FOR PLAYING 88 LOTTERY! >>>");
		System.out.println("Your next financial adventure awaits at the 88 System!");
		System.out.println("Good luck and see you soon!");

	}
	public static int issue04(int n){
		
		if (n==1){
			System.out.println("n = " + n);
			System.out.println("1! = 1");
			System.out.println();
			return 1;
		}else{
			int mun = n*issue04(n-1);
			System.out.println("n = "+n);
			System.out.println("mun = "+mun);
			System.out.println();
			return mun; 
		}
	}
}