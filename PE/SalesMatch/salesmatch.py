#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'sockMerchant' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER n
#  2. INTEGER_ARRAY ar
#

def sockMerchant(ar):
    pairs = set()
    pair = 0
    for element in ar:
        if element in pairs:
            pair+=1
            pairs.remove(element)
        else: pairs.add(element)
    return pair
if __name__ == '__main__':
    fptr = open('result.text', 'w')

    n = int(input().strip())

    ar = list(map(int, input().rstrip().split()))

    result = sockMerchant(ar)

    fptr.write(str(result) + '\n')

    fptr.close()