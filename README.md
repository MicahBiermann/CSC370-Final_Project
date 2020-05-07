# CSC370-Final_Project
The default main function asks for the number of files to be processed. Then, it loops the following:
  1. Ask for the name of the text file to be processed, with the filepath hard-coded.
  2. Return the number of occurrences of each unique word, sorted by number of occurences and sub-sorted alphabetically.
  3. Return the single longest word (or a list of words, in the case of a tie).

A function also exists for sorting the given text files by relevance of a given search term. This function uses a function through Lucene stated to be based upon term frequency-inverse document frequency methods.
This function makes use of LuceneIndexer to index the files in a way managable by Lucene methods. Then, it uses LuceneFileSearcher to search the files for relevance of the given search term.
