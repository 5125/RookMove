
public class Main {

	/*
	 * As name suggests following variable controls start & target indexes.
	 */

	static int startElemRowIndex = 7;
	static int startElemColIndex = 0;

	static int targetRowIndex = 0;
	static int targetColIndex = 2;

	public static void main(String[] args) {

		/*
		 * 
		 * algorithm used is modified version of flood fill. main working is
		 * backtracking technique. objective was to keep track of shifts / state change.
		 * which are defined in function itself.
		 */

		// 0's for valid path, 1's for taken places.
		// edit these chess metric / array for our own setup.

		int[][] chess = { 
				{ 0, 1, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 1, 0, 0, 0 }, 
				{ 0, 0, 1, 0, 0, 0, 0, 0 }, 
			  	{ 1, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, };

		int initPointReturnCode = checkForValidityOfMovement(chess);
		if (initPointReturnCode != -1) {
			findMinTurnsTookToReachAtDestinationCell(chess, startElemRowIndex, startElemColIndex, chess.length,
					chess[0].length, 0, initPointReturnCode);

			// Considering number of steps = number of turns required + 1.
			// because moving rook itself is a move. not turn.
			if (minTurns == Integer.MAX_VALUE)
				System.out.println("Blocked..path does not exist..Sorry");

			else {
				System.out.println("Turns required: " + minTurns);
                System.out.println("Moves performed: " + (minTurns+1));
            }
		} else {
            	System.exit(0);
		}
	}

	public static int checkForValidityOfMovement(int[][] tempArray) {
		
			if(startElemColIndex+1 <= 7){
				if (tempArray[startElemRowIndex ][startElemColIndex + 1] == 0)
			return 0;
			}
		
		
			if(startElemRowIndex + 1 <= 7 ){
				if (tempArray[startElemRowIndex +1][startElemColIndex] == 0)
			return 1;
			}


			if(startElemColIndex - 1 >= 0){
				if (tempArray[startElemRowIndex] [startElemColIndex -1] == 0)
			return 2;
			}

			
			if(startElemRowIndex - 1 >= 0){
				if (tempArray[startElemRowIndex - 1][startElemColIndex] == 0)
			return 3;
			}

			return -1;
		}
	
	

	static int minTurns = Integer.MAX_VALUE;

	private static void findMinTurnsTookToReachAtDestinationCell(int[][] chess, int tempRow, int tempCol, int M, int N,
			int currTurns, int currState) {

		// Modified flood fill learned during codechef long competition.
		// extra part is just maintaining switch states.

		if (currTurns >= minTurns)
			return;

		if (tempRow == targetRowIndex && tempCol == targetColIndex) {
			if (currTurns < minTurns)
				minTurns = currTurns;
			return;
		}

		chess[tempRow][tempCol] = currTurns;

		// move to right
		if (tempCol + 1 < N && chess[tempRow][tempCol + 1] == 0) {
			// State vaiable for right is 0.
			boolean isRookStateChanged = currState != 0;
			int updatedState = currState;
			int updatedTurns = currTurns;
			if (isRookStateChanged) {
				updatedState = 0;
				updatedTurns++;
			}
			// in loop we checked for if tempCol +1
			// that is index is valid & can move if condition fulfils for movement.
			findMinTurnsTookToReachAtDestinationCell(chess, tempRow, tempCol + 1, M, N, updatedTurns, updatedState);
		}

		// move to btm
		if (tempRow + 1 < M && chess[tempRow + 1][tempCol] == 0) {
			// State vaiable for btm is 1.
			boolean isRookStateChanged = currState != 1;
			int updatedState = currState;
			int updatedTurns = currTurns;
			if (isRookStateChanged) {
				updatedState = 1;
				updatedTurns++;
			}
			findMinTurnsTookToReachAtDestinationCell(chess, tempRow + 1, tempCol, M, N, updatedTurns, updatedState);
		}

		// move left
		if (tempCol - 1 >= 0 && chess[tempRow][tempCol - 1] == 0) {
			// state variable for left is 2.
			boolean isRookStateChanged = currState != 2;
			int updatedState = currState;
			int updatedTurns = currTurns;
			if (isRookStateChanged) {
				updatedState = 2;
				updatedTurns++;
			}
			// in loop we checked for if tempCol -1
			// that is index is valid & can move if condition fulfils for movement.
			findMinTurnsTookToReachAtDestinationCell(chess, tempRow, tempCol - 1, M, N, updatedTurns, updatedState);
		}

		// move top
		if (tempRow - 1 >= 0 && chess[tempRow - 1][tempCol] == 0) {
			// State variable for top is 3.
			boolean isRookStateChanged = currState != 3;
			int updatedState = currState;
			int updatedTurns = currTurns;
			if (isRookStateChanged) {
				updatedState = 3;
				updatedTurns++;
			}
			findMinTurnsTookToReachAtDestinationCell(chess, tempRow - 1, tempCol, M, N, updatedTurns, updatedState);
		}

		chess[tempRow][tempCol] = 0;

	}

}
