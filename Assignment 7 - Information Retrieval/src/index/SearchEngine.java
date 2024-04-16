/*
 * Copyright 2023 Marc Liberatore.
 */

package index;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;
import comparators.TfIdfComparator;
import documents.DocumentId;

/**
 * A simplified document indexer and search engine.
 * 
 * Documents are added to the engine one-by-one, and uniquely identified by a DocumentId.
 *
 * Documents are internally represented as "terms", which are lowercased versions of each word 
 * in the document. 
 * 
 * Queries for terms are also made on the lowercased version of the term. Terms are 
 * therefore case-insensitive.
 * 
 * Lookups for documents can be done by term, and the most relevant document(s) to a specific term 
 * (as computed by tf-idf) can also be retrieved.
 *
 * See:
 * - <https://en.wikipedia.org/wiki/Inverted_index>
 * - <https://en.wikipedia.org/wiki/Search_engine_(computing)> 
 * - <https://en.wikipedia.org/wiki/Tf%E2%80%93idf>
 * 
 * @author Marc Liberatore
 *
 */
public class SearchEngine {
	
	/**
	 * Inserts a document into the search engine for later analysis and retrieval.
	 * 
	 * The document is uniquely identified by a documentId; attempts to re-insert the same 
	 * document are ignored.
	 * 
	 * The document is supplied as a Reader; this method stores the document contents for 
	 * later analysis and retrieval.
	 * 
	 * @param documentId
	 * @param reader
	 * @throws IOException iff the reader throws an exception 
	 */
	Map<String, Set<DocumentId>> docmap;
	Map<DocumentId, Map<String, Integer>> counter;
	public SearchEngine()
	{
		docmap = new HashMap<>();
		counter = new HashMap<>();
	}
	public void addDocument(DocumentId documentId, Reader reader) throws IOException 
	{
		Scanner s = new Scanner(reader);
		s.useDelimiter("\\W+");

		while (s.hasNext())
		{
			String str = s.next().toLowerCase();
			if (docmap.get(str) == null)
			{
				docmap.put(str, new HashSet<>());
			}
			docmap.get(str).add(documentId);
			Map<String, Integer> exmap = counter.get(documentId);
			if (exmap == null)
			{
				exmap = new HashMap<>();
				exmap.put(str, 1);
				counter.put(documentId, exmap);
			}
			else if (!exmap.containsKey(str))
			{
				exmap.put(str, 1);
			}
			else
			{
				exmap.put(str, exmap.get(str)+ 1);
			}
			
		}
		s.close();
	}
	
	/**
	 * Returns the set of DocumentIds contained within the search engine that contain a given term.
	 * 
	 * @param term
	 * @return the set of DocumentIds that contain a given term
	 */
	public Set<DocumentId> indexLookup(String term) 
	{	
		return docmap.getOrDefault(term.toLowerCase(), new HashSet<DocumentId>());
	}
	
	/**
	 * Returns the term frequency of a term in a particular document.
	 * 
	 * The term frequency is number of times the term appears in a document.
	 * 
	 * See 
	 * @param documentId
	 * @param term
	 * @return the term frequency of a term in a particular document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public int termFrequency(DocumentId documentId, String term) throws IllegalArgumentException 
	{
		if (!counter.containsKey(documentId))
		{
			throw new IllegalArgumentException();
		}
		return counter.get(documentId).getOrDefault(term.toLowerCase(), 0);
	}
	
	/**
	 * Returns the inverse document frequency of a term across all documents in the index.
	 * 
	 * For our purposes, IDF is defined as log ((1 + N) / (1 + M)) where 
	 * N is the number of documents in total, and M
	 * is the number of documents where the term appears.
	 * 
	 * @param term
	 * @return the inverse document frequency of term 
	 */
	public double inverseDocumentFrequency(String term) {
		int N = counter.keySet().size();
		int M;
		if (docmap.get(term.toLowerCase()) == null)
		{
			M = 0;
		}
		else
		{
			M = docmap.get(term.toLowerCase()).size();
		}
		
		return Math.log(((double) (1 + N) / (1 + M)));
	}
	
	/**
	 * Returns the tfidf score of a particular term for a particular document.
	 * 
	 * tfidf is the product of term frequency and inverse document frequency for the given term and document.
	 * 
	 * @param documentId
	 * @param term
	 * @return the tfidf of the the term/document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public double tfIdf(DocumentId documentId, String term) throws IllegalArgumentException 
	{
		if (!counter.containsKey(documentId))
		{
			throw new IllegalArgumentException();
		}
		return (double) termFrequency(documentId, term.toLowerCase()) * inverseDocumentFrequency(term.toLowerCase());
	}
	
	/**
	 * Returns a sorted list of documents, most relevant to least relevant, for the given term.
	 * 
	 * A document with a larger tfidf score is more relevant than a document with a lower tfidf score.
	 * 
	 * Each document in the returned list must contain the term.
	 * 
	 * @param term
	 * @return a list of documents sorted in descending order by tfidf
	 */
	public List<DocumentId> relevanceLookup(String term) 
	{
		if (docmap.get(term.toLowerCase()) == null)
		{
			return new ArrayList<DocumentId>();
		}
		List<DocumentId> doclist = new ArrayList<DocumentId>(docmap.get(term.toLowerCase()));
		Collections.sort(doclist, new TfIdfComparator(this, term));
		return doclist;
	}
}
