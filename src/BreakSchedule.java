

import java.util.ArrayList;
import java.util.*;

public class BreakSchedule {
	
	
	// Use this class to implement programs for Tasks 2 & 3. Your file must not change the package name,
	// nor the class name. You must not change the names nor the signatures of the two program stubs
	// in this file. You may augment this file with any other method or variable/data declarations that you
	// might need to implement the dynamic programming strategy requested for Tasks 2&3.
	// Make sure however that all your declarations are public.
	
	
	// Precondition: word is a string and breakList is an array of integers in strictly increasing order
	//               the last element of breakList is no more than the number of characters in word.
	// Postcondition: Return the minimum total cost of breaking the string word into |breakList|+1 pieces, where 
	//                the position of each each break is specified by the integers in breakList. 
	//                Refer to the assignment specification for how a single break contributes to the cost.
	
	int totalCost (String word, ArrayList<Integer> breakList) { // TODO Complete for Task 2
		
		//dp table initialisation
		int size = word.length();
		int[][] dpTable = new int [size][size];
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				if(i>j){
					dpTable[i][j] = 0;
				} else {
					dpTable[i][j] = j-i+1;
				}
			}
		}
		return -1;
	}

	int costHelper(int[][]table,int[] boundaries, ArrayList<Integer> breakList){
		//if there exists a single split point then return the boundary calculation
		if(breakList.size() == 1 && breakList.get(0)<=j && breakList.get(0)>=i){
			return table[boundaries[0]][boundaries[1]];
		}
		//if there exists more than one then we kinda iterate through each one and assume that we pick that one
		//annoying thing is calculating the new boundary points. Two cases really exist, one: the thing that
		//we are trying is in between split points, and two: the point we are trying is on an edge.
		//the boundaries given in this case are the coordinates for the initial value that we add to the
		//minimum choice.
		//
		//when to update table? -> when costHelper has resolved a minimum, update the table coordinates with
		//currentVal + minimum.
		
		for(int i = 0; i<breakList.size(); i++){

		}
	}






	 
	// Precondition: word is a string and breakList is an array of integers in strictly increasing order
	//               the last element of breakList is no more than the number of characters in word.
	// Postcondition: Return the schedule of breaks so that word is broken according to the list of
	// 					breaks specified in breakList which yields the minimum total cost.
	 
	 ArrayList<Integer> breakSchedule (String word, ArrayList<Integer> breakList) { // TODO Complete for Task 3
		 return null;
	 }	 
}
