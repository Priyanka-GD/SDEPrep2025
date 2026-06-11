import java.util.List;

public class BoatMovements {
    public static boolean canTravelTo(boolean[][] gameMatrix, int fromRow, int fromColumn, int toRow, int toColumn) {
        int rows = gameMatrix.length;
        int cols = gameMatrix[0].length;


        // Edge case
        if(toRow >= rows || toRow < 0 || toColumn >= cols || toColumn < 0){
            return false;
        }

        return canTravelToDestination(gameMatrix, fromRow, fromColumn, toRow, toColumn);

    }

    public static boolean canTravelToDestination(boolean[][] gameMatrix, int fromRow, int fromCol, int toRow, int toCol){

        if(fromRow > toRow){
            for(int row = fromRow; row >= toRow; row--){
                if(!gameMatrix[row][fromCol])
                    return false;
            }
        }
        else if(fromRow < toRow){
            for(int row = fromRow; row <= toRow; row++){
                if(!gameMatrix[row][fromCol])
                    return false;
            }
        }
        else {
            if(fromCol > toCol){
                for(int col = fromCol; col >= toCol; col--){
                    if(!gameMatrix[fromRow][col])
                        return false;
                }
            }else{
                for(int col = fromCol; col <= toCol; col++){
                    if(!gameMatrix[fromRow][col])
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean[][] gameMatrix = {
                {false, true,  true,  false, false, false},
                {true,  true,  true,  false, false, false},
                {true,  true,  true,  true,  true,  true},
                {false, true,  true,  false, true,  true},
                {false, true,  true,  true,  false, true},
                {false, false, false, false, false, false},
        };

        System.out.println(canTravelTo(gameMatrix, 3, 2, 2, 2)); // true, Valid move
        System.out.println(canTravelTo(gameMatrix, 3, 2, 3, 4)); // false, Can't travel through land
        System.out.println(canTravelTo(gameMatrix, 3, 2, 6, 2)); // false, Out of bounds
    }


}