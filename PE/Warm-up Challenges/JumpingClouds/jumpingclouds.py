#!/bin/python3

import os

#
# Complete the 'jumpingOnClouds' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER_ARRAY c as parameter.
#

def jumpingOnClouds(c):
    r=0
    i=0
    s =len(c)-1
    while(i<s):
        if i+2<=s: 
            if c[i+2]!=1: 
                i+=1
        i+=1
        r+=1
    return r
if __name__ == '__main__':
    fptr = open("result.txt", 'w')

    n = int(input().strip())

    c = list(map(int, input().rstrip().split()))

    result = jumpingOnClouds(c)

    fptr.write(str(result) + '\n')

    fptr.close()