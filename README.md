This program takes in a dictionary of words as a .txt file and divides them into classes of anagrams; each class contains all words in the dictionary that have the same letters 
regardless of order. Following is the initial algorithm description from where the code was derived, as well as the calculation for the program's run time and a proof of
correctness.


Pseudocode and Description:


anagrams <- empty ArrayList of ArrayList of String

copy <- empty string array with length dictionary size

keys <- empty string array with length dictionary size

1) These first three assignments create the anagrams ArrayList that will be returned in the end, an array copy that stores the dictionary in array format for better efficiency, 
and an array of keys. Each key is a string corresponding to a word in the input dictionary, with the number of each letter denoted. For example, the key for “aabc” is 
“a2b1c1d0...z0” where every letter not in the word is denoted with 0.

for k <- 0 to dictionary size-1 do

	copy [k] <- dictionary.get(k)
	
for i <- 0 to dictionary size-1 do

	numA through numZ <- 0
	
	for j <- 0 to length of each word in dictionary -1
	
		if copy[i].charAt[j] == ‘a’
		
			increment numA
			
		else if copy[i].charAt[j] == ‘b’
		
      			increment numB
		. . .
		
		else 
			throw exception for invalid character
			
	keys[i] <- “a” + numA + “b” + 
	
	numB + … + “z” + numZ
	

2) The first k loop here copies every word in the input dictionary over into the copy array. The second i loop creates the keys array. It iterates over every word in the array. 
First, variables computing the counts of each letter are initialized to 0 at every iteration of the loop for each word. The j loop within the i loop iterates over each word 
which is a String. Each conditional statement checks the current j-th letter, and increments the corresponding count. At the end of this loop, the key is created according to 
the counts of each letter. The last statement stops the program if a string in the dictionary is not all letters, i.e. is not a word.


sortedKeys <- keys

doubleMergeSort (sortedKeys, copy, keys.length)

3) First, a new array of Strings called sortedKeys is created to copy keys without editing it. Then, a version of mergeSort called doubleMergeSort is called on the sortedKeys 
and copy arrays. doubleMergeSort sorts both arrays according to the alphabetical/numerical order of sortedKeys; sortedKeys is sorted such that every key that is equal, i.e. 
the keys of anagrams, are next to each other in the array. Likewise, copy is sorted such that every word in copy is at the same index as its corresponding key.


curKey <- sortedKeys[0]

anagrams.add(new ArrayList)

anagrams.get(0).add(copy[0])

anagramCount <- 0


4) The variables needed for the next step, where the anagram groups are created, are initialized. curKey denotes the current key being observed, and is initialized to the first 
key in the array of sorted keys. A new array list is added to anagrams, which will store the first set of anagrams. The first word in the dictionary sorted according to keys is 
added to this first array. Lastly, a variable counting the number of anagrams present is initialized at 0 to denote the first anagram, which will be stored at anagrams index 0.

if keys length > m
	
	for m <- 1 to copy length
	
 		if sortedKeys[m] == curKey
		
			anagrams.get(anagramCount).add(copy[m])
		else
			anagrams.add(new Arraylist)
			
			anagrams.get(anagramCount++).add(copy[m])
			
      curKey <- sortedKeys[m]
      
return anagrams


5) This first if statement checks to see if the dictionary has more than 1 word (if the dictionary only has 1 word, then anagrams is returned as is with a single anagram array 
list that has a single element). The loop iterates over every element in the now-sorted copy array, and if the key of that word matches the key of the previously observed word, 
that means it is an anagram to that word, and it is added to the same anagram group. If the key of the current word does not match, a new array list denoting this word’s anagram
group is created and added to the anagrams array list. This word is added to that array list, and the anagramCount variable is incremented, indicating that another anagram group
has been found so that each word can be added to the correct anagram group. After the anagrams list is created using this loop, it is returned.


Run Time Derivation:

Step 1: This is simply a few initial assignments, which takes 3 steps and runs in constant time Θ(1).
Step 2: The first loop iterates over and does an assignment for every element in the input dictionary, so it runs in n time. The second loop is a double loop, where the outer 
loop iterates over every element in the input dictionary (and 26 assignments happen here) and the inner loop iterates over the characters in each word, does an assignment for 
each character, and lastly does an assignment for the current key. If k is the average length of a word, and therefore the average number of characters for the inner loop to 
iterate over, then this inner loop takes k time for each of the n words, so this entire step runs in Θ(nk) time if the constant number of assignments is ignored.
Step 3: The assignment of sorted keys is a single assignment and runs in constant time. In general mergeSort, where the divide step where the array is divided in two takes n 
time, the combine step where the divided arrays merge takes n time, and when considering the recursive conquer step where each divided array is sorted, the entire algorithm 
takes nlog(n) time. In doubleMergeSort, two arrays are being sorted, but the same number of comparisons are being done since only the key array’s values are being considered 
when sorting both arrays. So, while the input number of elements increases twofold, the conquer step and recursive calls take the same number of time. The doubleMergeSort 
algorithm therefore takes 2nlog(2n) time, which, is Θ(nlogn) asymptotically.
Step 4: This is another set of a few assignments, and since the array list access methods run in constant time, this step runs in constant time Θ(1).
Step 5: This step consists of a single loop that iterates over all elements of the input dictionary. Everything within the loop runs in constant time (assignments, incrementing,
string comparison, and array list access methods), so this entire step takes cn time, where c is the number of assignments being done for each of the n elements. Therefore, this
runs in linear time Θ(n).
Final Run Time: Adding all these steps yields 2Θ(1) + Θ(n) + Θ(nk) + Θ(nlogn), which asymptotically is Θ(nlogn).


Proof of Correctness:

This algorithm yields the correct result if all words within a sub-list are anagrams, and if every word that is an anagram to another word is in the same sub-list. 
To start with the first conjecture, consider that any word within the same sub-list has the same key; they would not have been added to the same sub-list if their keys were 
different as according to Step 5. The key denotes the number of each of the 26 possible letters within a word, and those counts would be the same for anagrams since, by 
definition, anagrams have the same composition of letters, just in a different order. Therefore, any words that are not anagrams have a different key, and if two words were 
added to the same sub-list, they must have the same key, so it follows that they must be anagrams.
For the second statement, consider that the words in the input dictionary were sorted by key. The keys were all generated the same way in the same order, with the number of a’s,
then the number of b’s, etc. The doubleMergeSort algorithm puts these keys in alphanumeric order, and then puts the words corresponding to each key in that same order, so every
word is next to any anagrams it might have within the array; likewise, all equal keys are next to each other as well at the same index as their words within the keys array. The
loop iterating over the keys and words adds all words in the same anagram before moving onto the next one because the words are in order according to their keys, and therefore
according to their anagram class. Because all words that are anagrams to each other are added at once, and they all must be grouped together in the array since their 
keys were alphanumerically sorted, any anagrams must have been added to the same class.
