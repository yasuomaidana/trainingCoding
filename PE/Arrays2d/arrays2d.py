#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'hourglassSum' function below.
#
# The function is expected to return an INTEGER.
# The function accepts 2D_INTEGER_ARRAY arr as parameter.
#

def hourglassSum(arr):
    max_i = len(arr)
    max_j = len(arr[0])
    max =0
    b= range(max_i-2)
    for i in range(max_i-2):
        for j in range(max_j-2):
            sum =0
            for k in range(3):
                fil = arr[i+k]  
                for l in range(3):
                    toSum = fil[j+l]
                    if(k!=1): sum+= toSum
                    if(k==1 and l==1): 
                        sum+=toSum
                        break
            if(i==0 and j==0): max = sum
            if(sum>max): max=sum
    return max;


if __name__ == '__main__':
    fptr = open("result.txt", 'w')

    arr = []

    for _ in range(6):
        arr.append(list(map(int, input().rstrip().split())))

    result = hourglassSum(arr)

    fptr.write(str(result) + '\n')

    fptr.close()
