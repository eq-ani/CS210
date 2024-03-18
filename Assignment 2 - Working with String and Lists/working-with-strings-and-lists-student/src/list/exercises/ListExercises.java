/*
 * Copyright 2021 Marc Liberatore.
 */

package list.exercises;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExercises {

	/**
	 * Counts the number of characters in total across all strings in the supplied list;
	 * in other words, the sum of the lengths of the all the strings.
	 * @param l a non-null list of strings
	 * @return the number of characters
	 */
	public static int countCharacters(List<String> l) {
		int num = 0;
		if (l.size() > 0)
		{
			String str;
			for (int i = 0; i < l.size(); i++)
			{
				str = l.get(i);
				num += str.length();
			}
		}
		return num; 
	}
	
	/**
	 * Splits a string into words and returns a list of the words. 
	 * If the string is empty, split returns a list containing an empty string.
	 * 
	 * @param s a non-null string of zero or more words
	 * @return a list of words
	 */
	public static List<String> split(String s) 
	{
		String[] split = s.split("\\s+");
		List<String> l = new ArrayList<String>();
		String str = "";
		for (int i = 0; i < split.length; i++)
		{
			str = split[i];
			l.add(str);
		}
		return l;	
	}

	/**
	 * Returns a copy of the list of strings where each string has been 
	 * uppercased (as by String.toUpperCase).
	 * 
	 * The original list is unchanged.
	 * 
	 * @param l a non-null list of strings
	 * @return a list of uppercased strings
	 */
	public static List<String> uppercased(List<String> l) {
		List<String> upperL = new ArrayList<String>();
		for(int i = 0; i < l.size(); i++)
		{
			String str = l.get(i);
			upperL.add(str.toUpperCase());
		}
		return upperL;
	}

	/**
	 * Returns true if and only if each string in the supplied list of strings
	 * starts with an uppercase letter. If the list is empty, returns false.
	 * 
	 * @param l a non-null list of strings
	 * @return true iff each string starts with an uppercase letter
	 */
	public static boolean allCapitalizedWords(List<String> l) {
		if (l.size() > 0)
		{
			String str;
			for (int i = 0; i < l.size(); i++)
			{
				str = l.get(i);
				if(str.isEmpty())
				{
					return false;
				}
				else if(!Character.isLetter(str.charAt(0)))
				{
					return false;
				}
				else if(Character.isLowerCase(str.charAt(0)))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Returns a list of strings selected from a supplied list, which contain the character c.
	 * 
	 * The returned list is in the same order as the original list, but it omits all strings 
	 * that do not contain c.
	 * 
	 * The original list is unmodified.
	 * 
	 * @param l a non-null list of strings
	 * @param c the character to filter on
	 * @return a list of strings containing the character c, selected from l
	 */
	public static List<String> filterContaining(List<String> l, char c) {
		List<String> filter = new ArrayList<String>();
		String str;
		if (l.size() > 0)
		{
			for (int i = 0; i < l.size(); i++)
			{
				str = l.get(i);
				for (int j = 0; j < str.length(); j++)
				{
					if (str.charAt(j) == c)
					{
						filter.add(str);
					}
				}
			}
		}
		return filter;
	}
	
	/**
	 * Inserts a string into a sorted list of strings, maintaining the sorted property of the list.
	 * 
	 * @param s the string to insert
	 * @param l a non-null, sorted list of strings
	 */
	public static void insertInOrder(String s, List<String> l) 
	{
		String str;
		for (int i = 0; i < l.size(); i++)
		{
			str = l.get(i);
			if (str.compareTo(s) > 0)
			{
				l.add(i, s);
				return;
			}
		}
		l.add(s);
	}	
}
