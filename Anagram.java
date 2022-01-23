	package com.gradescope.anagram;

	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.Scanner;

	public class Anagram {

	  private ArrayList<String> dictionary;
		
	  // Load dictionary.txt
		public Anagram(String dictionaryFilename) {
	    	this.dictionary = loadDictionary(dictionaryFilename);
		}
		
	    // Read the dictionary file into an ArrayList
		public ArrayList<String> loadDictionary(String filename) {
			ArrayList<String> dictionary = new ArrayList<String>();
			try {
				Scanner scanner = new Scanner(new File(filename));
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					dictionary.add(line.trim());
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return dictionary;
		}

		
		/* This doubleMergeSort function works similarly to standard mergeSort, except it sorts two arrays, arrA and arrB, according to how
		 * the values of arrA would be sorted.
		*/ 
		public static void doubleMergeSort(String[] arrA, String[] arrB, int n) {
			if (n < 2) {
		        return;
		    }
		    int mid = n / 2;
		    String[] leftA = new String[mid];
		    String[] rightA = new String[n - mid];
		    String[] leftB = new String[mid];
		    String[] rightB = new String[n - mid];

		    for (int i = 0; i < mid; i++) {
		        leftA[i] = arrA[i];
		        leftB[i] = arrB[i];
		    }
		    for (int i = mid; i < n; i++) {
		        rightA[i - mid] = arrA[i];
		        rightB[i - mid] = arrB[i];
		    }
		    doubleMergeSort(leftA, leftB, mid);
		    doubleMergeSort(rightA, rightB, n - mid);

		    merge(arrA, leftA, rightA, arrB, leftB, rightB, mid, n - mid);
		}
		
		// As in doubleMergeSort, this merge function merges both arrays arrA and arrB according to the sorting order of array a.
		public static void merge(
			String[] arrA, String[] leftA, String[] rightA, String[] arrB, String[] leftB, String[] rightB, int leftSize, int rightSize) {
			int i = 0, j = 0, k = 0;
			while (i < leftSize && j < rightSize) {
				if (leftA[i].compareTo(rightA[j]) > 0) {
					arrA[k] = leftA[i];
				    arrB[k] = leftB[i];
				    k++; i++;
				}
				else {
					arrA[k] = rightA[j];
				    arrB[k] = rightB[j];
				    k++; j++;
				}
			}
			while (i < leftSize) {
				arrA[k] = leftA[i];
				arrB[k] = leftB[i];
				k++; i++;
			}
			while (j < rightSize) {
				arrA[k] = rightA[j];
				arrB[k] = rightB[j];
				k++; j++;
			}
		}
		
		/**
	   * @throws Exception - Program fails if given a string that is not a word
	   * @returns - List of anagram classes, where each element in the list is a list of all the words in that anagram class.
		 */
		public ArrayList<ArrayList<String>> getAnagrams() throws Exception{
			ArrayList<ArrayList<String>> anagrams = new ArrayList<ArrayList<String>>(); //creates an empty array to store the end result
			String[] keys = new String[dictionary.size()]; //an array where the key of each word is stored
			String[] copy = new String[dictionary.size()]; //creates a version of the dictionary in array form for easier manipulation
			for (int k = 0; k < dictionary.size(); k++) { //copies every word in the dictionary to an array
				copy[k] = dictionary.get(k);
			}
			for (int i = 0; i < dictionary.size(); i++) {
				copy[i].toLowerCase();
				int numA = 0, numB = 0, numC = 0, numD = 0, numE = 0, numF = 0, numG = 0, numH = 0, numI = 0, numJ = 0, numK = 0, numL = 0, 
					numM = 0, numN = 0, numO = 0, numP = 0, numQ = 0, numR = 0, numS = 0, numT = 0, numU = 0, numV = 0, numW = 0, numX = 0, 
					numY = 0, numZ = 0;
				for (int j = 0; j < dictionary.get(i).length(); j++) { //iterates over every character of each word
					switch(copy[i].charAt(j)) { //these switch statements increment the number of appearances of whichever letter is at character j of word i
						case 'a': numA++; break;
						case 'b': numB++; break;
						case 'c': numC++; break;
						case 'd': numD++; break;
						case 'e': numE++; break;
						case 'f': numF++; break;
						case 'g': numG++; break;
						case 'h': numH++; break;
						case 'i': numI++; break;
						case 'j': numJ++; break;
						case 'k': numK++; break;
						case 'l': numL++; break;
						case 'm': numM++; break;
						case 'n': numN++; break;
						case 'o': numO++; break;
						case 'p': numP++; break;
						case 'q': numQ++; break;
						case 'r': numR++; break;
						case 's': numS++; break;
						case 't': numT++; break;
						case 'u': numU++; break;
						case 'v': numV++; break;
						case 'w': numW++; break;
						case 'x': numX++; break;
						case 'y': numY++; break;
						case 'z': numZ++; break;
						default: throw new Exception("Invalid Character");
					}
				}
				keys[i] = "a" + numA + "b" + numB + "c" + numC + "d" + numD + "e" + numE + "f" + numF + "g" + numG + "h" + numH + 
						"i" + numI + "j" + numJ + "k" + numK + "l" + numL + "m" + numM + "n" + numN + "o" + numO + "p" + numP + 
						"q" + numQ + "r" + numR + "s" + numS + "t" + numT + "u" + numU + "v" + numV + "w" + numW + "x" + numX + 
						"y" + numY + "z" + numZ; //the key for each word i is created as a string of the form "letter""count of letter"...
			}

			String[] sortedKeys = keys;
			doubleMergeSort(sortedKeys, copy, keys.length); /*sorts the keys in alphanumeric order, and sorts the words such that they are at the same index as their associated key;
															after being sorted, the keys of anagrams are all next to each other,
															and consequently all anagrams are next to each other within the copy array*/
			String curKey = sortedKeys[0]; //creates a variable to store the current key being observed in the following for loop, and initializes it to the alphanumerically first key
			anagrams.add(new ArrayList<String>());
			anagrams.get(0).add(copy[0]); //the first word in the sorted copy array (corresponding to the alphanumerically first key) is added to the first anagram group
			int anagramCount = 0;
			if (keys.length > 1) { /*checks if the dictionary contains more than a single word; if there is only one word, the final returned list of anagrams consists only of the array 
									list created above containing that word */
				for (int l = 1; l < copy.length; l++) {
					if (sortedKeys[l].equals(curKey)) { //if the key of word l matches that of the current key:
						anagrams.get(anagramCount).add(copy[l]); //add that word to its corresponding anagram class
					}
					else { //if the key of word l does not match the current key (i. e. all of the words in the current anagram group have been added to their array list)
						anagrams.add(new ArrayList<String>());
						anagrams.get(++anagramCount).add(copy[l]); //increment anagramCount to get the index of the new group of anagrams, and add the current word to that group's array list
						curKey = sortedKeys[l]; //set the current key to this new group's key to compare with the next word
					}
				}
			}
			System.out.println(anagrams);
			return anagrams;
		}
		
		/**
	   * A dictionary can be imported, with its title between the quotes, to test
		 * @throws Exception - Program fails if given a string that is not a word
		 * @throws FileNotFoundException 
		 */
		public static void main(String[] args) throws Exception {
	    	Anagram pf = new Anagram("dict1.txt");
	    	pf.getAnagrams();
		}

	}
