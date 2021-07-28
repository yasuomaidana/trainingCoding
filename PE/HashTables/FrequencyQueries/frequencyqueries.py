#!/bin/python3

import math
import os
import random
import re
import sys

values = {}
data = {}
def adding(x):
    #Updates data
    data[x] = data.get(x,0)+1
    #Obtain number stored
    number_quantity = data[x]
    #Update count
    values[number_quantity] = values.get(number_quantity,0)+1
    #Update previous count
    prev_number_quantity=number_quantity-1
    if(prev_number_quantity!=0):
        prev_stored_quantity = values.get(prev_number_quantity)-1
        if(prev_stored_quantity<0): prev_stored_quantity=0
        values[prev_number_quantity] = prev_stored_quantity
def delete(y):
    number_quantity = data.get(y,None)
    if(number_quantity==None or number_quantity==0): return
    data[y] = number_quantity-1

    #Update current count
    stored_quantity = values[number_quantity]-1
    if stored_quantity<0: stored_quantity = 0
    values[number_quantity] = stored_quantity

    #Update previous quantity
    prev_number_quantity = number_quantity-1
    if(prev_number_quantity!=0):
        values[prev_number_quantity]+=1
def check(z):
    v = values.get(z,-1) 
    if(values.get(z,-1)>0):
        print("1")
        return 1
    else: print("0")
    return 0

def freqQuery(queries):
    results =[]
    operations = {}
    operations[1] = adding
    operations[2] = delete
    operations[3] = check
    for ope,val in queries:
        if ope == 3: results.append(operations[ope](val))
        else: operations[ope](val)
    return results
if __name__ == '__main__':
    fptr = open("result.txt", 'w')

    q = int(input().strip())

    queries = []

    for _ in range(q):
        queries.append(list(map(int, input().rstrip().split())))

    ans = freqQuery(queries)

    fptr.write('\n'.join(map(str, ans)))
    fptr.write('\n')

    fptr.close()