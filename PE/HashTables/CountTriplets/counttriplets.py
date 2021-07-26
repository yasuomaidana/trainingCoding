#!/bin/python3
import math
import os
import random
import re
import sys
# Complete the countTriplets function below.
def toDict(arr):
    re = {}
    for i in arr:
        re[i]=re.get(i,0)+1
    return re
def countTriplets(arr, r):
    futureDict = toDict(arr)
    pastDict = {}
    triplets=0
    for i in arr:
        past = i/r
        future = i*r
        futureDict[i]-=1
        passed = pastDict.get(past,0)
        tofuture =  futureDict.get(future,0)
        if pastDict.get(past,0)>0 and futureDict.get(future,0)>0 and i%r==0:
            triplets += pastDict[past]*futureDict[future]
        pastDict[i] = pastDict.get(i,0)+1
    return triplets
if __name__ == '__main__':
    fptr = open("result.txt", 'w')

    nr = input().rstrip().split()

    n = int(nr[0])

    r = int(nr[1])

    arr = list(map(int, input().rstrip().split()))

    ans = countTriplets(arr, r)

    fptr.write(str(ans) + '\n')

    fptr.close()