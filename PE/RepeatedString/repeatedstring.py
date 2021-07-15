#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'repeatedString' function below.
#
# The function is expected to return a LONG_INTEGER.
# The function accepts following parameters:
#  1. STRING s
#  2. LONG_INTEGER n
#

def repeatedString(s, n):
    size = len(s)
    reminder = n%size
    complete = (n-reminder)/size
    ind=0
    reminder_s=0
    complete_s=0
    for letter in s:
        if letter == "a":
            if ind<reminder:
                reminder_s+=1
            complete_s+=1
        ind+=1
    return complete_s*complete+reminder_s

if __name__ == '__main__':
    fptr = open("result.txt", 'w')

    s = input()

    n = int(input().strip())

    result = repeatedString(s, n)

    fptr.write(str(result) + '\n')

    fptr.close()