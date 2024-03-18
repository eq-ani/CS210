/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

public class Fragment {

	/**
	 * Creates a new Fragment based upon a String representing a sequence of
	 * nucleotides, containing only the uppercase characters G, C, A and T.
	 * 
	 * @param nucleotides
	 * @throws IllegalArgumentException if invalid characters are in the sequence of
	 *                                  nucleotides
	 */
	protected String seq;
	public Fragment(String nucleotides) throws IllegalArgumentException {
		for (int i = 0; i < nucleotides.length();i++)
		{
			if (nucleotides.charAt(i) == 'A' || nucleotides.charAt(i) == 'C' || nucleotides.charAt(i) == 'G' || nucleotides.charAt(i) == 'T')
			{
				seq = nucleotides;
			}
			else
			{
				throw new IllegalArgumentException("Not a valid nucleotide");
			}
			
		}
		
	}

	/**
	 * Returns the length of this fragment.
	 * 
	 * @return the length of this fragment
	 */
	public int length() {
		return seq.length();
	}

	/**
	 * Returns a String representation of this fragment, exactly as was passed to
	 * the constructor.
	 * 
	 * @return a String representation of this fragment
	 */
	@Override
	public String toString() {
		return seq;
	}

	/**
	 * Return true if and only if this fragment contains the same sequence of
	 * nucleotides as another sequence.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Fragment)) {
			return false;
		}

		Fragment f = (Fragment) o;

		// Don't unconditionally return false; check that
		// the relevant instances variables in this and f 
		// are semantically equal
		if (f.toString().equals(seq))
		{
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of nucleotides of overlap between the end of this fragment
	 * and the start of another fragment, f.
	 * 
	 * The largest overlap is found, for example, CAA and AAG have an overlap of 2,
	 * not 1.
	 * 
	 * @param f the other fragment
	 * @return the number of nucleotides of overlap
	 */
	public int calculateOverlap(Fragment f) {
		int num1 = 0;
		int num2 = 0;
		String str = f.toString();
		for (int i = 0; i < seq.length(); i++)
		{	
			if (str.startsWith(seq.substring(i, seq.length())))
			{
				num1 = seq.length() - i;
				break;
			}
		}
		for (int i = 0; i < seq.length(); i++) {
			if (str.startsWith(seq.substring(i, seq.length()))) {
				num2 = seq.length() - i;
				break;
			}
		}
		if (num1 > num2)
		{
			return num1;
		}
		else 
		{
			return num2;
		}
	}


	/**
	 * Returns a new fragment based upon merging this fragment with another fragment
	 * f.
	 * 
	 * This fragment will be on the left, the other fragment will be on the right;
	 * the fragments will be overlapped as much as possible during the merge.
	 * 
	 * @param f the other fragment
	 * @return a new fragment based upon merging this fragment with another fragment
	 */
	public Fragment mergedWith(Fragment f) {
		String str = f.toString();
		int num = calculateOverlap(f);
		String mergestr = "";
		mergestr = seq.substring(0, seq.length()) + str.substring(0 + num, str.length());
		return new Fragment(mergestr);
	}
}
