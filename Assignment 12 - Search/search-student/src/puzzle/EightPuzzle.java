/*
 * Copyright 2023 Marc Liberatore.
 */

package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.SearchProblem;
import search.Searcher;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	List<Integer> startingValues;
	public EightPuzzle(List<Integer> startingValues) 
	{
		for (int i = 0; i < 9; i++)
		{
			if (startingValues.get(i) > 8 || startingValues.get(i) < 0)
			{
				throw new IllegalArgumentException();
			}
			else if (startingValues.size() != 9)
			{
				throw new IllegalArgumentException();
			}
		}
		this.startingValues = startingValues;
	}	
	@Override
	public List<Integer> getInitialState() 
	{
		return startingValues;
	}

	static void swap(List<Integer> l, int i1, int i2)
	{
		int temp = l.get(i1);
		l.set(i1, l.get(i2));
		l.set(i2, temp);
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) 
	{
		List<List<Integer>> ll = new ArrayList<>(); 
		if (currentState.get(0) == 0) //when 0 is at index 0
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			swap(l1, 0, 1);
			swap(l2, 0, 3);
			ll.add(l1);
			ll.add(l2);
		}
		if (currentState.get(1) == 0) //when 0 is at index 1
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			List<Integer> l3 = new ArrayList<>(currentState);
			swap(l1, 1, 0);
			swap(l2, 1, 2);
			swap(l3, 1, 4);
			ll.add(l1);
			ll.add(l2);
			ll.add(l3);
		}
		if (currentState.get(2) == 0) // when 0 is at index 2
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			swap(l1, 2, 1);
			swap(l2, 2, 5);
			ll.add(l1);
			ll.add(l2);
		}
		if (currentState.get(3) == 0) //when 0 is at index 3
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			List<Integer> l3 = new ArrayList<>(currentState);
			swap(l1, 3, 0);
			swap(l2, 3, 4);
			swap(l3, 3, 6);
			ll.add(l1);
			ll.add(l2);
			ll.add(l3);
		}
		if (currentState.get(4) == 0) //when 0 is at index 4
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			List<Integer> l3 = new ArrayList<>(currentState);
			List<Integer> l4 = new ArrayList<>(currentState);
			swap(l1, 4, 1);
			swap(l2, 4, 3);
			swap(l3, 4, 5);
			swap(l4, 4, 7);
			ll.add(l1);
			ll.add(l2);
			ll.add(l3);
			ll.add(l4);
		}
		if (currentState.get(5) == 0) //when 0 is at index 5
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			List<Integer> l3 = new ArrayList<>(currentState);
			swap(l1, 5, 2);
			swap(l2, 5, 4);
			swap(l3, 5, 8);
			ll.add(l1);
			ll.add(l2);
			ll.add(l3);
		}
		if (currentState.get(6) == 0) //when 0 is at index 6
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			swap(l1, 6, 3);
			swap(l2, 6, 7);
			ll.add(l1);
			ll.add(l2);
		}
		if (currentState.get(7) == 0) //when 0 is at index 7
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			List<Integer> l3 = new ArrayList<>(currentState);
			swap(l1, 7, 4);
			swap(l2, 7, 6);
			swap(l3, 7, 8);
			ll.add(l1);
			ll.add(l2);
			ll.add(l3);
		}
		if (currentState.get(8) == 0) //when 0 is at index 8
		{
			List<Integer> l1 = new ArrayList<>(currentState);
			List<Integer> l2 = new ArrayList<>(currentState);
			swap(l1, 8, 5);
			swap(l2, 8, 7);
			ll.add(l1);
			ll.add(l2);
		}
		return ll;
	}


	@Override
	public boolean isGoal(List<Integer> state) 
	{

		for (int i = 1; i < 9; i++)
		{
			if (!state.get(i - 1).equals(i))
			{
				return false;
			}
		}
		return state.get(state.size() - 1) == (0);
	}

	public static void main(String[] args) {
		EightPuzzle eightPuzzle = new EightPuzzle(Arrays.asList(new Integer[] {1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> solution = new Searcher<List<Integer>>(eightPuzzle).findSolution();
		for (List<Integer> state : solution) {
			System.out.println(state);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
