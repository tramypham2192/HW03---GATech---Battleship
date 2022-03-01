import java.util.Arrays;
import java.util.Comparator;

import javax.swing.plaf.ColorUIResource;

public class Test {
    public static void main(String[] args) {
        // int[][] sortedPlayer = {{1, 2}, {1, 3}, {1, 4}, {4, 1}, {3, 3}};
        

        char[][] player = {{1, 2}, {1, 3}, {1, 4}, {4, 1}, {3, 3}};
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
                System.out.println();
            } else {
                System.out.print(row);
                int num = 0;
                for (int j = 0; j < 5; j++){  
                    if (sortedPlayer[row][0] == row && sortedPlayer[row][1] == j){
                        System.out.print(" @ ");
                        for (int k = 1; row + k < 5; k++){
                            if (sortedPlayer[row + k][0] == row && sortedPlayer[row + k][1] == j+ k){
                                System.out.print(" @ ");
                                j++;
                            }
                    }
                }       
                    else {
                        System.out.print(" - ");
                    }
                }
            }
                // for (int col = 0; col < 5; col++){
                //     if (sortedPlayer[i][0] == row && sortedPlayer[i][1] == col){
                //         System.out.println(" @ ");
                //     }
                // }
                // for (int i = 0; i < 5; i++){
                //     if (sortedPlayer[i][0] == row){
                //         for (int column = 0; column < 5; column++){
                //             if (column == sortedPlayer[i][1]){
                //                 System.out.print(" @ ");
                //             } else {
                //                 System.out.print(" - ");
                //             }
                //             if (column == 4){
                //                 System.out.println();
                //             }
                //         }
                    // }
                    // else {
                    //     System.out.print(" - ");
                        
                    //     }
                    // }
                    // if (sortedPlayer[row][0] == row && sortedPlayer[row][1] == col){
                    //     System.out.print(" @ ");
                    //     for (int j = 0; j + row < 5; j++){
                    //         if (sortedPlayer[row + j][0] == row && sortedPlayer[row][1] == col){
                    //             System.out.print(" @ ");
                    //         }
                    //     }
                    //  } else {
                    //     System.out.print(" - ");
                    // }
                   
                    // for (int[] ship: sortedPlayer){
                    //     for (int col = 0; col < 5; col++){
                    //         if (ship[0] != row){
                    //             System.out.print("");
                    //         } else {
                    //             if (ship[1] == col){
                    //                 System.out.print(" @ ");
                    //             } else {
                    //                 System.out.print(" - ");
                    //             }
                    //         }
                            
                    //     }
                    // }
                    System.out.println();

                }

            }   
            
    
    

    

    public static int[][] convert(char[][] player){
        int[][] sortedPlayer = new int[5][5];
        for (int i = 0; i < 5; i++){
            int a = player[i][0];
            int b = player[i][1];
            sortedPlayer[i][0] = a;
            sortedPlayer[i][1] = b;
        }
        return sortedPlayer;
    }
}
