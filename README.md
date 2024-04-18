# Spelling

Background
Implement a combination spell checker and word prediction utility in American English. A spell checker is
a software feature often built into word processors like Google Docs, in browser input boxes, etc. The
spell checker detects misspelled words and has the ability to suggest alternatives. Most spell-checking
software is built on a lot of knowledge of how misspellings occur — for example, the word “bizarre” is
frequently misspelled with one “r” (“bizare”) — and is often guided by word frequency counts. Similarly,
word prediction anticipates the word being typed based on the first letters in the word.

## Part 1 - Trie
Use a trie (pronounced either like “tree” or “try”) to implement a spell checker. Also known as a prefix tree,
a trie is a tree-based data structure. There are implementations of a trie in which each node contains only the previous letter of the prefix. In this implementation, the path from root to leaf for a type like “inn” would contain nodes () —> (i) —> (n) —>(n). 
Either implementation is acceptable for this assignment.

## Part 2 - Data
Fill the trie with data from the file unigram_freq.csv. This file will be supplied to your implementation as the
first argument. This file is from Rachael Tatman’s English Word Frequency. It contains the 333,333 most
frequently used types from Google’s Trillion Word Corpus, along with the frequencies of those types, in
CSV format. Each type, including proper names like “Michelle”, is converted to lowercase, and there are
no repeated entries in the file.

The first 5 lines of this file are as follows:
    word, count
    the,23135851162
    of,13151942776
    and,12997637966
    to,12136980858
    
The first line of this file describes each column. (This is common in CSV data files.) This line may be
ignored. All subsequent lines contain a type, a comma, and an integer representing the frequency of the
type in the corpus (data set). The second line shows the most frequent type in the corpus is “the”, with
more than 23 billion tokens (i.e. used more than 23 billion times in the corpus).

## Part 3 - Mechanism for Check Spelling & Word Prediction
Assuming the parameter token contains n characters, for each character (1 .. n), the suggest(...)
the function adds count types for the token. The suggested types should be the most frequent which share
the prefix with the input, up to and including the ith character. Where no prefix can be found, the
implementation must assume the parameter token is incorrectly spelled, and the most frequent prefix to
the point of misspelling should be used.

## Part 4 - Improvements
Test your implementation with all the data from the file misspelling.csv, which contains some of the most
frequently misspelled types in English in mixed case, according to the Oxford English Corpus. Observe
the number of times the correct spelling appears in the List<List<String>> returned from the
suggest(...) function, varying the value of the count variable between 3 and 7 .
What, if anything, could be changed in the implementation to get the correct spelling of the input tokens?
