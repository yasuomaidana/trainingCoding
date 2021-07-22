#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'minimumBribes' function below.
#
# The function accepts INTEGER_ARRAY q as parameter.
#

def minimumBribes(q):
    oP = []
    last_big = 1
    oP.append(last_big)
    seq = 1
    shifts=0
    for currPos in q:
        diff = currPos-seq
        if diff>2:
            print("Too chaotic")
            return
        if diff>0:
            while last_big<currPos:
                last_big+=1
                oP.append(last_big)
        else:
            if len(oP)<2 and last_big!= len(q):
                last_big+=1
                oP.append(last_big)
        shifts+= oP.index(currPos)
        oP.remove(currPos)
        seq+=1
    print(shifts)

if __name__ == '__main__':
    file = open("/Users/nsl-intern/Documents/ACO/JE/NewYearChaos/input02.txt")
    file.readline()
    file.readline()
    
    t = file.readline().strip()
    q = [int(i) for i in t.split()]
    minimumBribes(q)

        