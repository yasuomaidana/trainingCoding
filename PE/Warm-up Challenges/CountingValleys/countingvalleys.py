#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'countingValleys' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER steps
#  2. STRING path
#

def countingValleys(steps, path):
    level = 0
    newValley = True
    valleys = 0
    for step in path:
        if step == "D": level -=1
        else: level+=1
        if newValley and level<0:
            valleys+=1
            newValley = False
        if not newValley and level == 0:
            newValley = True
    return valleys

if __name__ == '__main__':
    fptr = open("result.txt", 'w')

    steps = int(input().strip())

    path = input()

    result = countingValleys(steps, path)

    fptr.write(str(result) + '\n')

    fptr.close()