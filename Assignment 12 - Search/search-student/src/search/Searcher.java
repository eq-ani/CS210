/*
 * Copyright 2023 Marc Liberatore.
 */

package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 * @author liberato
 *
 * @param <T> the type for each vertex in the search graph
 */
public class Searcher<T> {
	private final SearchProblem<T> searchProblem;
	
	/**
	 * Instantiates a searcher.
	 * 
	 * @param searchProblem
	 *            the search problem for which this searcher will find and
	 *            validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) 
	{
		this.searchProblem = searchProblem;
	}

	/**
	 * Finds and return a solution to the problem, consisting of a list of
	 * states.
	 * 
	 * The list should start with the initial state of the underlying problem.
	 * Then, it should have one or more additional states. Each state should be
	 * a successor of its predecessor. The last state should be a goal state of
	 * the underlying problem.
	 * 
	 * If there is no solution, then this method should return an empty list.
	 * 
	 * @return a solution to the problem (or an empty list)
	 */
	public List<T> findSolution() 
	{	
		Queue<T> frontier = new LinkedList<>();
		frontier.add(searchProblem.getInitialState());

		HashMap<T, T> predecessor = new HashMap<>();
		predecessor.put(searchProblem.getInitialState(), null);

		List<T> path = new ArrayList<>();

		while (!frontier.isEmpty())
		{
			T current = frontier.remove();
			if (searchProblem.isGoal(current))
			{
				path.add(current);
				T previous = predecessor.get(current);
				while(previous != null)
				{
					path.add(0, previous);
					previous = predecessor.get(previous);
				}
				return path;
			}
			for (T next : searchProblem.getSuccessors(current))
			{
				if (!predecessor.containsKey(next))
				{
					frontier.add(next);
					predecessor.put(next, current);
				}
			}
		}
		return path;
	}

	/**
	 * Checks that a solution is valid.
	 * 
	 * THIS METHOD DOES NOT PERFORM SEARCH! It only checks if a provided solution
	 * is valid!
	 * 
	 * A valid solution consists of a list of states. The list should start with
	 * the initial state of the underlying problem. Then, it should have one or
	 * more additional states. Each state should be a successor of its
	 * predecessor. The last state should be a goal state of the underlying
	 * problem.
	 * 
	 * @param solution
	 * @return true iff this solution is a valid solution
	 * @throws NullPointerException
	 *             if solution is null
	 */
	public final boolean isValidSolution(List<T> solution) 
	{
		if (solution == null)
		{
			throw new NullPointerException();
		}
		
		if (solution.size() <= 1)
		{
			return false;
		}

		T curr = searchProblem.getInitialState();

		if (!curr.equals(solution.get(0)))
		{
			return false;
		}
		if (!searchProblem.isGoal(solution.get(solution.size() - 1)))
		{
			return false;
		}


		for (int i = 1; i < solution.size(); i++)
		{
			List<T> succ = searchProblem.getSuccessors(curr);
			if (!succ.contains(solution.get(i)))
			{
				return false;
			}
			else
			{
				for (int j = 0; j < succ.size(); j++)
				{
					if (succ.get(j).equals(solution.get(i)))
					{
						curr = succ.get(j);
						break;
					}
				}
			}
		}
		return true;
	}
}
