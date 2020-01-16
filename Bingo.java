import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Bingo
{
	public static void main(String[] args)
	{
		final int ROW = 5, COL = 5;

		Random random = new Random();
		Scanner keyboard = new Scanner(System.in);

		//Greetings to the game
		System.out.println("Welcome to Siana's Bingo game!"
				+ "\nYou will be versing the computer, whoever gets two Bingos first wins the game!");
		//Gets the user name
		System.out.print("Before we start the game, what is your name? ");
		String userName = keyboard.nextLine();
		//!!explain more(how numbers turn zero and stuff)
		System.out.println(userName + ", I love your name! Best of luck to you " + userName + "! \n");

		boolean game = true;
		while(game)
		{
			//25 numbers in a chart
			int a1=0,a2=0,a3=0,a4=0,a5=0,b1=0,b2=0,b3=0,b4=0,b5=0,
					c1=0,c2=0,c3=0,c4=0,c5=0,d1=0,d2=0,d3=0,d4=0,d5=0,
					e1=0,e2=0,e3=0,e4=0,e5=0;

			//subbing in the variables to the array to be able to call them in later on
			int[] bingoArray = {a1,a2,a3,a4,a5,b1,b2,b3,b4,b5,c1,c2,c3,c4,c5
					,d1,d2,d3,d4,d5,e1,e2,e3,e4,e5};

			//Game lets the user to put in numbers into their bingo chart
			System.out.println("\nEnter 25 different numbers, all between the range of 1 and 40:");
			//showing the user how many numbers they have entered until now
			int enterCount = 1;
			for (int i = 0; i < bingoArray.length; i++)
			{
				System.out.print(enterCount + ": ");
				bingoArray[i] = keyboard.nextInt();
				enterCount++;
			}

			//creating 2d array to come up with visual bingo chart
			int[][]bingoChart =
				{
						{a1,a2,a3,a4,a5},
						{b1,b2,b3,b4,b5},
						{c1,c2,c3,c4,c5},
						{d1,d2,d3,d4,d5},
						{e1,e2,e3,e4,e5},
				};

			//subbing in values typed in by user that's been saved in bingoArray to bingoChart
			int arrayCount = 0;
			for (int row = 0; row < ROW; row++)
			{
				for(int col = 0; col < COL; col++)
				{
					bingoChart[row][col] = bingoArray[arrayCount];
					arrayCount++;
				}
			}
			//call method showArray to display the bingoChart to the user
			showArray(bingoChart, ROW, COL);

			//to show how many rounds the user has played so far
			int roundCount = 1;

			//store the eliminated numbers in this array list
			ArrayList <Integer> eliminatedNums = new ArrayList<Integer>();

			//continue the next block of codes under the while loop until bingo is true(user gets a bingo)
			boolean bingo1 = false;
			while(!bingo1)
			{
				//provide the user with a random value between 1 to 40
				//if the number matches with a number on a bingo chart, that number will be eliminated
				//number on the chart turns '0' when eliminated
				int eliminated = 0;
				int bingoNum = random.nextInt(40)+1;
				//check if the number has been called already
				//if it has, create a new number that hasn't been called
				boolean overlap = true;
				for (int i = 0; i < eliminatedNums.size()-1; i++)
				{
					if (bingoNum==eliminatedNums.get(i))
					{
						bingoNum = random.nextInt(40)+1;
					}
				}
				System.out.println("\n\nRound " + (roundCount) + " bingo number: " + bingoNum);
				for (int i = 0; i < ROW; i++)
				{
					for (int i2 = 0; i2 < COL; i2++)
					{
						//eliminated = 0;
						if (bingoChart[i][i2]==bingoNum)
						{
							eliminatedNums.add(bingoNum);
							bingoChart[i][i2] = 0;
							if (bingoChart[i][i2]==0)
							{
								//if the randomly generated number is found in the bingo chart, return value 1 for eliminated
								eliminated = 1;
							}
						}
					}
				}

				//if eliminated equals 1(number is in the chart)
				if(eliminated==1)
				{
					System.out.println("\nYay! " + bingoNum + " has been eliminated from your chart! XD\n");
				}
				//if the number isn't found in the bingo chart, eliminated stays as zero
				else if (eliminated==0)
				{
					System.out.println("\nYou didn't have " + bingoNum + " on your bingo chart! :(\n");
				}
				//show the new chart where the eliminated numbers turned to zeros/ or not if there is no eliminated number
				showArray(bingoChart, ROW, COL);

				//all possibilities of user getting a bingo
				//calling the bingoUser to check for a bingo, while bingo is having all zeros in a horizontal/vertical/diagonal line
				//bingo1 flag value equals to the returned boolean value in bingoChart
				//bingoUser is true when there is a bingo detected(all numbers in the row, col, or diagonal is zero)
				for (int i = 0; i < ROW; i++)
				{//horizontal bingos
					if(bingoUser(bingoChart[i][0],bingoChart[i][1],bingoChart[i][2],bingoChart[i][3],bingoChart[i][4],bingo1)==true)
					{
						bingo1 = true;
						break;
					}
					else
					{
						bingo1 = false;
					}
				}
				if (bingo1==false)
				{
					for (int i = 0; i < ROW; i++)
					{
						if(bingoUser(bingoChart[0][i],bingoChart[1][i],bingoChart[2][i],bingoChart[3][i],bingoChart[4][i],bingo1)==true)
						{//vertical bingos
							bingo1 = true;
							break;
						}
						else
						{
							bingo1 = false;
						}
					}
				}
				if (bingo1==false)
				{
					//diagonal bingos
					if(bingoUser(bingoChart[0][0],bingoChart[1][1],bingoChart[2][2],bingoChart[3][3],bingoChart[4][4],bingo1)==true)
					{//diagonal left top to right bottom
						bingo1 = true;
					}
					else
					{
						bingo1 = false;
					}
				}//diagonal right top to left bottom
				if (bingo1==false)
				{
					if (bingoUser(bingoChart[0][4],bingoChart[1][3],bingoChart[2][2],bingoChart[3][1],bingoChart[4][0],bingo1)==true)
					{
						bingo1 = true;
					}
					else
					{
						bingo1 = false;
					}
				}
				//to help the user see the process of getting the bingo, let the user stop and decide when to continue to the next round
				System.out.print("Press zero to continue to next round: ");
				int continueInput = keyboard.nextInt();
				boolean input = false;
				while(input==false)
				{
					if(continueInput==0)
					{
						input = true;
						//keep track of how many rounds have been played
						roundCount++;
						break;
					}
					else
					{
						input = false;
						System.out.println("Please only press zero to continue to next round.");
						continueInput = keyboard.nextInt();
					}
				}
			}
			if (bingo1==true)
			{
				System.out.println("\nYou got a bingo!");
			}

			//menu: shows which key to press to see certain values that were saved during the game
			System.out.println("\nMenu:"
					+ "\nPress '0' to view how many rounds have you played."
					+ "\nPress '1' to view the eliminated numbers list.");
			System.out.println("Press '2' to skip menu selection.");
			System.out.print("Selection: ");
			int menuSelect = keyboard.nextInt();
			while (!(menuSelect==2))
			{
				switch (menuSelect)
				{
				case 0:
					//show how many rounds have been played
					System.out.println("You have played " + (roundCount-1) + " rounds to get a bingo!");
					//roundCount - 1 because one extra round is added at the end of the loop above
					System.out.print("Press X to exit menu: ");
				break;
				case 1:
					//show the numbers that has been eliminated on the bingo chart
					System.out.println("The numbers you have elimnated to get a bingo are: " + eliminatedNums);
					System.out.print("Press X to exit menu: ");
					break;
				case 2:
					//exit while loop: exit menu
					break;
				default:
					//if none of the above were entered
					System.out.println("Please select from the menu or press X to exit menu.");
					break;
				}
				menuSelect = keyboard.nextInt();
			}
			//asks the user if they want to exit the game or play another game
			//string datatype to take in user input
			String yesNoInput = keyboard.nextLine();
			//char to only look at the first character of the user input
			char yesNo;
			boolean invalid = true;
			System.out.println("\n\nDo you want to play another game of Bingo, " + userName +"? (Enter y for yes, n for no)");
			yesNoInput = keyboard.nextLine();
			yesNo = yesNoInput.charAt(0);
			//keep on going through the loop when the entered input is invalid
			while(invalid==true)
			{
				if(yesNo=='n'||yesNo=='N')
				{
					System.out.println("\nSee you next time, " + userName + "! ");
					game = false;
					invalid = false;
					break;
				}
				else if (yesNo=='y'||yesNo=='Y')
				{
					game = true;
					//keyboard.nextLine();
					invalid = false;
					break;
				}
				//if user enters neither of y or n
				else if (!(yesNo=='y'||yesNo=='Y')&&!(yesNo=='n'||yesNo=='N'))
				{
					invalid = true;
					System.out.println("That is an invalid input. Please Enter either y for yes, or n for no. ");
				}
				yesNoInput = keyboard.nextLine();
				yesNo = yesNoInput.charAt(0);
				//keyboard.nextLine();
			}
		}
	}

	//method: going to be used often for different values in the main method
	//displaying the bingo charts
	public static void showArray(int[][] displayArray, int rowFin, int colFin)
	{
		for(int row = 0; row < rowFin; row++)
		{
			for(int col = 0; col < colFin; col++)
			{
				//formatting so that the numbers are separated from each other
				System.out.printf("%-4d", displayArray[row][col]);
			}
			System.out.println();
		}
	}

	//method: going to be used often for different values in the main method
	//checking if the user has a bingo
	public static boolean bingoUser(int a, int b, int c, int d, int e, boolean bingo)
	{
		bingo = false;//first declaring bingo as false, so it doesn't always return true after getting first bingo
		if(a==0&&b==0&&c==0&&d==0&&e==0)
		{
			//return bingo = true, if all the values that has been subbed in are equal zero
			bingo = true;
		}
		else
		{
			bingo = false;
		}
		return bingo;
	}
}
