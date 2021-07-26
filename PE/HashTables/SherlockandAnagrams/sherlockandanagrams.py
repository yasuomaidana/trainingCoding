#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'sherlockAndAnagrams' function below.
#
# The function is expected to return an INTEGER.
# The function accepts STRING s as parameter.
#
def hasRepeat(word):
    letters = set()
    for letter in word:
        if letter not in letters:
            letters.add(letter)
        else:
            return True
    return False

def sherlockAndAnagrams(s):
    anagrams = 0
    anagramsMaps ={}
    helper = set()
    helperDup = set()
    for i in range(1,len(s)):
        for j in range(len(s)-i+1):
            substring = "".join(sorted(s[j:j+i]))
            if anagramsMaps.get(substring,-1)<0 and hasRepeat(substring):
                if substring in helperDup:
                    anagramsMaps[substring] = 1
                    continue
                else:
                    helperDup.add(substring)
            if anagramsMaps.get(substring,-1)>=0:
                anagramsMaps[substring]+=1
            if substring in helper and anagramsMaps.get(substring,-1)==-1:
                anagramsMaps[substring]=1
            if substring not in helper:
                helper.add(substring)
    for i in anagramsMaps:
        n = anagramsMaps[i]
        anagrams+=n*(n+1)/2
    return int(anagrams)

if __name__ == '__main__':
    fptr = open("result.txt", 'w')

    q = int(input().strip())

    for q_itr in range(q):
        s = input()
        result = sherlockAndAnagrams(s)

        fptr.write(str(result) + '\n')

    fptr.close()