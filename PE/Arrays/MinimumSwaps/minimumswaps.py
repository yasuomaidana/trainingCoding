#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the minimumSwaps function below.
def minimumSwaps(arr):
    movements=0;
    i =1 
    realInd ={}
    actualInd={}
    for v in arr:
        realInd[i] = v
        actualInd[v]=i
        i+=1
    for v in range(1,len(realInd)):
        realV = realInd[v]
        actualV = actualInd[v]
        if realInd[v]!=v:
            realInd[actualV]=realV
            actualInd[realV]=actualV
            movements+=1
        realInd.pop(v)
        actualInd.pop(v)
    return movements

if __name__ == '__main__':
    fptr = open("result.txt", 'w')

    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    res = minimumSwaps(arr)

    fptr.write(str(res) + '\n')

    fptr.close()
