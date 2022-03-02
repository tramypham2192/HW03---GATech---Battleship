import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Battleship {
    
	public static void main(String[] args) {   
        Scanner sc = new Scanner(System.in);
        int countPlayer1HitShips = 0;
        int countPlayer2HitShips = 0;

        System.out.println("Welcome to Battleship");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES."); 

        //Player 1 enter ships' coordinates
        int[][] player1 = player1InputCoordinates(sc);
        char[][] player1coordinate = convertToCharArray(player1);
        for (int i = 0; i < 5; i++){
            System.out.println(player1[i][0] + " " + player1[i][1]);
        }
        printBattleShip(player1coordinate);

        //player 2 enter ships' coordinates
        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
        int[][] player2 = player2InputCoordinates(sc, player1);
        //convert int array to char array for player 2
        char[][] player2coordinate = convertToCharArray(player2);
        printBattleShip(player2coordinate);

        //hit the ships
        int win = 0;
        while (win == 0){
            boolean player1Fire = player1fire(sc, player2);
            if (player1Fire == true){
                countPlayer2HitShips++;
            }
            boolean player2Fire = player2fire(sc, player1);
            if (player2Fire == true){
                countPlayer1HitShips++;
            }
            if (countPlayer1HitShips == 4){
                player2Fire = player2fire(sc, player1);
                if (player2Fire ==  true){
                    System.out.println("Player 2 hit all 5 ships of Player 1. layer 2 win!");
                    win = -1;
                }
            }
            if (countPlayer2HitShips == 4){
                player1Fire = player1fire(sc, player2);
                if (player1Fire ==  true){
                    System.out.println("Player 1 hit all 5 ships of Player 2. Player 1 win!");
                    win = -1;
                }
            }
        }
        
    }
    //input ship coordinate method foor player 1
    private static int[][] player1InputCoordinates(Scanner sc) {
        int[][] coordinates = new int[5][5];

        for (int i = 0; i < 5; i++){
            System.out.println("Enter ship " + i + " location:");
            int trucX = sc.nextInt();
            int trucY = sc.nextInt();
            sc.nextLine();
            if (i == 0){
                if ((trucX < 0 || trucX > 4)|| (trucY < 0 || trucY > 4)){
                    throw new InputException("Please enter between 0 and 4");
                } else {
                    coordinates[i][0] = trucX;
                    coordinates[i][1] = trucY;
                }
            } else if (i > 0){
                for (int j = 0; j < i; j++){
                    while (coordinates[j][0] == trucX && coordinates[j][1] == trucY){
                        System.out.println("Overlap coordinates. Choose X coordinates different from " + coordinates[i][0] + 
                        " and Y coordinate different from " + coordinates[i][1]);
                        trucX = sc.nextInt();
                        trucY = sc.nextInt();
                        sc.nextLine();
                        if ((trucX < 0 || trucX > 4)|| (trucY < 0 || trucY > 4)){
                            throw new InputException("Please enter between 0 and 4");
                        } else {
                            if (!(coordinates[j][0] == trucX && coordinates[j][1] == trucY)){
                                coordinates[i][0] = trucX;
                                coordinates[i][1] = trucY;
                            } else {
                                System.out.println("Overlap coordinates. Choose X coordinates different from " + coordinates[i][0] + 
                                " and Y coordinate different from " + coordinates[i][1]);
                                trucX = sc.nextInt();
                                trucY = sc.nextInt();
                                sc.nextLine();
                            }
                        }
                        break;
                    } 
                    if ((trucX < 0 || trucX > 4)|| (trucY < 0 || trucY > 4)){
                        throw new InputException("Please enter between 0 and 4");
                    } else {
                        coordinates[i][0] = trucX;
                        coordinates[i][1] = trucY; 
                    }
                }
            }
            // for (int a = 0; a <= i; a++){
            //     System.out.print(coordinates[a][0] + " " + coordinates[a][1]);
            //     System.out.println();
            }  
            System.out.println();              
        return coordinates;
    }

    //input ship coordinate method for player 2
    private static int[][] player2InputCoordinates(Scanner sc, int[][] arr){
        int[][] player2 = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}};
        for (int i = 0; i < 5; i++){
            System.out.println("Enter coordinates for ship " + i + ":");
            int a = sc.nextInt();
            int b = sc.nextInt();
            while (!(checkWithPlayer1(a, b, arr) == false && checkWithPlayer2(a, b, player2) == false)){
                System.out.println("Coordinates exists in previois ships. Please enter another coordinates: ");
                a = sc.nextInt();
                b = sc.nextInt();
                sc.nextLine();
            }
            player2[i][0] = a;
            player2[i][1] = b;
        }
        return player2;
    }

    //player 1 fire, pass scanner object from main method to this
    //method as parameter
    public static boolean player1fire(Scanner sc, int[][] arr){
        boolean result = false;
        System.out.println("Player 1, enter hit row/column: ");
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        for (int i = 0; i < arr.length; i++){
            if (arr[i][0] == x2 && arr[i][1] == y2){
                arr[i][0] = -1;
                arr[i][1] = -1;
                System.out.println("Player 1 hit player 2's ship!");
                result = true;            } 
        }
        
        return result;
    }

     //player 2 fire, pass scanner object from main method to this
    //method as parameter
    public static boolean player2fire(Scanner sc, int[][] arr){
        boolean result = false;
        System.out.println("Player 2, enter hit row/column: ");
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < arr.length; i++){
            if (arr[i][0] == x1 && arr[i][1] == y1){
                arr[i][0] = -1;
                arr[i][1] = -1;
                System.out.println("Player 2 hit player 1's ship!");
                result = true;
            }
        }
        return result;
    }

	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
        //convert char array into int array
        int[][] sortedPlayer = convert(player);
        for (int i = 0; i < 5; i++){
            System.out.println(sortedPlayer[i][0] + " " + sortedPlayer[i][1]);
        }

        System.out.println();
        Arrays.sort(sortedPlayer, Comparator.comparingInt(o -> o[0])); 

        System.out.println("After sorting: ");
        for (int i = 0; i < 5; i++){
            System.out.println(sortedPlayer[i][0] + " " + sortedPlayer[i][1]);
        }

        for (int row = -1; row < 5; row++){
            if (row == -1){
                System.out.print("  ");
                for (int column = 0; column < 5; column++){
                    System.out.print(column + "  ");
                } 
            } else {
                System.out.print(row);
                int[] result = checkEachRow(row, sortedPlayer);
                System.out.print(printEachRow(result));
                } 
                System.out.println();
        }
    }

    //private method to check coordinates with existing ships of player 2
    private static boolean checkWithPlayer2(int a, int b, int[][] player2){
        boolean result = false;
        for (int i = 0; i < player2.length; i++){
            if (player2[i][0] == a && player2[i][1] == b){
                result = true;
            } 
        }
        return result;
    }

    //private method to check coordinates with ships of player 1
    private static boolean checkWithPlayer1(int a, int b, int[][] player1){
        boolean result = false;
        for (int i = 0; i < player1.length; i++){
            if (player1[i][0] == a && player1[i][1] == b){
                result = true;
            }
        }
        return result;
    }

    //private method support for print battle ship method
    private static int[] checkEachRow(int row, int[][] player){
        int[] arr1 = {-1, -1, -1, -1, -1};
        for (int i = 0; i < 5; i++){
            if (player[i][0] == row){
                arr1[player[i][1]] = player[i][1];
            }
        }
        return arr1;
    }

    //private method support for print battle ship method
    private static String printEachRow(int[] result){
        String str = "";
        for (int i = 0; i < 5; i++){
            if (i == result[i]){
                str += " @ ";
            } else {
                str += " - ";
            }
        }
        return str;
    }

    //private method to convert char array into int array, to print battleship display
    private static int[][] convert(char[][] player){
        int[][] sortedPlayer = new int[5][5];
        for (int i = 0; i < 5; i++){
            int a = player[i][0];
            int b = player[i][1];
            sortedPlayer[i][0] = a;
            sortedPlayer[i][1] = b;
        }
        return sortedPlayer;
    }

    //method to convert int array to char array
    private static char[][] convertToCharArray(int[][] arr){
        char[][] charArr = new char[5][5];
        for (int i = 0; i < 5; i++){
            char a = (char)(arr[i][0]);
            char b = (char)(arr[i][1]);
            charArr[i][0] = a;
            charArr[i][1] = b;
        }
        return charArr;
    }
}



