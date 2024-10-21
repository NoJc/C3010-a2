

import java.util.ArrayList;
import java.util.*;

public class BreakSchedule {
	int[][] dpTable;
	
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

		if(breakList == null || breakList.isEmpty()){
			return 0;
		}
		if(word.equals("")){
			return 0;
		}	
		//dp table initialisation
		int size = word.length();
		dpTable = new int [size][size];
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				if(i>=j){
					dpTable[i][j] = 0;
				} else {
					dpTable[i][j] = j-i+1;
				}
				System.out.print(dpTable[i][j] + " ");
			}
			System.out.println();
		}
		int[] boundaries = new int[2];
		boundaries[0] = 0;
		boundaries[1] = size-1;
		System.out.println();
		int result = costHelper(boundaries,breakList);
		System.out.println();
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				System.out.print(dpTable[i][j] + " ");
			}
			System.out.println();
		}
		return result;
	}

	//if there exists more than one then we iterate through each one and assume that we pick that one
	//annoying thing is calculating the new boundary points. Two cases really exist, one: the thing that
	//we are trying is in between split points, and two: the point we are trying is on an edge.
	//the boundaries given in this case are the coordinates for the initial value that we add to the
	//minimum choice.
	//
	//when to update table? -> when costHelper has resolved a minimum, update the table coordinates with
	//currentVal + minimum.
	
	//since the amount of items we can have in the breakList is an arbitrary amount, what we could do is
	//have all resolved values put into a large list where we can then take the minimum to add to our boundary point
		

	int costHelper(int[] boundaries, ArrayList<Integer> breakList){
		if(breakList.isEmpty()){
			return 0;
		}
		int size = breakList.size();
		int topBorder = boundaries[1];
		int botBorder = boundaries[0];
		
		//if the table has been altered, then we have solved this sub problem in the past
		if(dpTable[botBorder][topBorder] != topBorder-botBorder+1) {
			System.out.println("sub problem already done -> ["+botBorder+"]["+topBorder+"]");
			return dpTable[botBorder][topBorder];
		}
		
		//if there exists a single split point then return the boundary calculation
		if(size == 1 && breakList.get(0)<topBorder && breakList.get(0)>=botBorder){
			return dpTable[botBorder][topBorder];
		} else if(size == 1 && breakList.get(0) == topBorder) {
			return 0;
		}
		
		ArrayList<Integer> resolutions = new ArrayList<>();
		
		for(int i = 0; i<breakList.size(); i++){
			int curr = breakList.get(i);
			int[] newBound = new int[2];
			ArrayList<Integer> newPoints = new ArrayList<>(breakList);
			//I think we disregard out of bounds slip points for now
			if(curr > topBorder || curr < botBorder){
				continue;
			}
			if(i == 0){
				//if we are the left then we move the boundaries up to split point + 1
				newBound[0] = curr+1;
				newBound[1] = topBorder;
				newPoints.remove(i);
				resolutions.add(costHelper(newBound,newPoints));
				continue;
			}
			if(i == breakList.size()-1){
				newBound[0] = botBorder;
				newBound[1] = curr;
				//if we are the right most then we should be the last in the list really
				newPoints.remove(i);
				resolutions.add(costHelper(newBound,newPoints));
				continue;
			}
			//else we are in the middle of the breakPoints
			
			//handle left side
			newBound[0] = botBorder;
			newBound[1] = curr;
			for(int j = newPoints.size()-1; j>=i; j--){
				newPoints.remove(j);
			}
			int leftSide = costHelper(newBound,newPoints);
			
			//handle right side
			newBound[0] = curr+1;
			newBound[1] = topBorder;
			newPoints = new ArrayList<>(breakList);
			for(int j = i; j>=0; j--){
				newPoints.remove(j);
			}
			int rightSide = costHelper(newBound,newPoints);
			
			resolutions.add((rightSide+leftSide));
		}
		int min = 0;
		if(!resolutions.isEmpty()){
			min = resolutions.get(0);
		}
		for(int i = 0; i<resolutions.size(); i++){
			if(min>resolutions.get(i)){
				min = resolutions.get(i);
			}
		}
		dpTable[botBorder][topBorder]+=min;
		System.out.println("table coords " + botBorder + " " + topBorder + " altered to " + dpTable[botBorder][topBorder]);
		return dpTable[botBorder][topBorder];
	}

	// Precondition: word is a string and breakList is an array of integers in strictly increasing order
	//               the last element of breakList is no more than the number of characters in word.
	// Postcondition: Return the schedule of breaks so that word is broken according to the list of
	// 					breaks specified in breakList which yields the minimum total cost.
	 
	 ArrayList<Integer> breakSchedule (String word, ArrayList<Integer> breakList) { // TODO Complete for Task 3
		if(breakList == null || breakList.isEmpty()){
			return null;
		}
		if(word.equals("")){
			return null;
		}	
		//dp table initialisation
		int size = word.length();
		dpTable = new int [size][size];
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				if(i>=j){
					dpTable[i][j] = 0;
				} else {
					dpTable[i][j] = j-i+1;
				}
				System.out.print(dpTable[i][j]);
			}
			System.out.println();
		}
		int[] boundaries = new int[2];
		boundaries[0] = 0;
		boundaries[1] = size-1;
		return scheduleHelper(boundaries,breakList);
	 }
	 
	 ArrayList<Integer> scheduleHelper(int[] boundaries, ArrayList<Integer> breakList){
		 return null;
	 }
	 
}
