#implementing dynamic programming to store minimum number of transformations
#required for each item in the wordlist to equal to end word
#then, see which word in the list the begining word can be changed to with
#minimum number of transformations
#minSteps(wordList[i] => endWord)
#?minSteps(beginWord => wordList[i])

from queue import Queue

def compareChars(first, second):
    count = 0
    for f,s in zip(first,second):
        if f != s:
            count += 1
    return count

def wordLadder(beginWord, endWord, wordList):
    if compareChars(beginWord,endWord) == 1:
        return 2
    if endWord not in wordList:
        return 0
    
    wordQueue = Queue()
    wordQueue.put(endWord)
    shortestPaths = {endWord: 0}
    
    while not wordQueue.empty():
        w = wordQueue.get()     
        for word in wordList:
            if word not in shortestPaths:
                if compareChars(word, w) == 1:
                    wordQueue.put(word)
                    shortestPaths[word] =  shortestPaths[w]+1
   
    min_transforms = 601
    for word in wordList:
        if compareChars(beginWord, word) == 1 and word in shortestPaths:
            num_transforms = shortestPaths[word] + 2
            if num_transforms < min_transforms:
                min_transforms = num_transforms
    if min_transforms == 601:
        min_transforms = 0        
    return min_transforms        

def main():
  print(wordLadder("hot", "cog", ["hot", "dot", "dog", "lot", "cog"]))

main()
