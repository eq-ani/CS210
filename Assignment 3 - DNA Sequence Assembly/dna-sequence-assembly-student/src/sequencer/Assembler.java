/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import java.util.List;
import java.util.ArrayList;

public class Assembler 
{

	/**
	 * Creates a new Assembler containing a list of fragments.
	 * 
	 * The list is copied into this assembler so that the original list will not be
	 * modified by the actions of this assembler.
	 * 
	 * @param fragments
	 */
	protected ArrayList<Fragment> fragments;

	public Assembler(List<Fragment> fragments) 
	{
		this.fragments = new ArrayList<Fragment>(fragments);
	}

	/**
	 * Returns the current list of fragments this assembler contains.
	 * 
	 * @return the current list of fragments
	 */
	public List<Fragment> getFragments() 
	{
		return fragments;
	}

	/**
	 * Attempts to perform a single assembly, returning true iff an assembly was
	 * performed.
	 * 
	 * This method chooses the best assembly possible, that is, it merges the two
	 * fragments with the largest overlap, breaking ties between merged fragments by
	 * choosing the shorter merged fragment.
	 * 
	 * Merges must have an overlap of at least 1.
	 * 
	 * After merging two fragments into a new fragment, the new fragment is inserted
	 * into the list of fragments in this assembler, and the two original fragments
	 * are removed from the list.
	 * 
	 * @return true iff an assembly was performed
	 */
	public boolean assembleOnce() 
	{

		int num = 0;
		int max = -1;
		Fragment fgm1 = null;
		Fragment fgm2 = null;
		
		for (int i = 0; i < fragments.size(); i++) 
		{
			for (int j = 0; j < fragments.size(); j++) 
			{
				if (fragments.get(i) == null || fragments.get(j) == null) 
				{
					return false;
				}
				if (fragments.get(i) != fragments.get(j)) 
				{
					num = fragments.get(i).calculateOverlap(fragments.get(j));
					if (num > max) 
					{
						max = num;
						fgm1 = fragments.get(i);
						fgm2 = fragments.get(j);
					}
				}

			}

		}

		if (max > 0) 
		{
			fragments.remove(fgm1);
			fragments.remove(fgm2);
			Fragment merge = fgm1.mergedWith(fgm2);
			fragments.add(merge);
			return true;
		}
		return false;

	}

	/**
	 * Repeatedly assembles fragments until no more assembly can occur.
	 */
	public void assembleAll() 
	{
		while (assembleOnce()) 
		{
			assembleOnce();
		}
	}
}
