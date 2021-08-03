#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'activityNotifications' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER_ARRAY expenditure
#  2. INTEGER d
#
class Manager:
    ExpenditureCounter={}
    ExpenditureHistory=[]
    AliveExpenditures=[]
    def __init__(self,size):
        self.odd = size%2==0
        if(self.odd): self.ind1 = int(size/2)
        else: self.ind1= int(size/2)+1
        self.ind2 = self.ind1+1
        self.trailingDays = size
        self.notifications = 0
    def findAliveIndex(self,expenditure):
        windowMax = len(self.AliveExpenditures)-1
        windowMin = 0
        while(windowMax!=windowMin+1):
            if self.AliveExpenditures[windowMax]==expenditure: return windowMax
            if self.AliveExpenditures[windowMin]==expenditure: return windowMin
            inter = int((windowMax+windowMin)/2)
            interValue = self.AliveExpenditures[inter]
            if(interValue==expenditure): return inter
            if(expenditure>interValue):
                windowMin=inter
            else:
                windowMax=inter;
        return -1,windowMax
    def addByMiddle(self,expenditure):
        _,windowMax = self.findAliveIndex(expenditure)
        self.AliveExpenditures.insert(windowMax,expenditure)
        self.ExpenditureCounter[expenditure]=1
    def addNew(self,expenditure):
        Max = len(self.AliveExpenditures)-1
        if(expenditure>self.AliveExpenditures[Max]):
            self.ExpenditureCounter[expenditure]=1
            self.AliveExpenditures.append(expenditure)
            return
        if(expenditure<self.AliveExpenditures[0]):
            self.ExpenditureCounter[expenditure]=1
            self.AliveExpenditures.insert(0,expenditure)
            return
        self.addByMiddle(expenditure)
    def moveWindow(self,expenditure):
        self.ExpenditureHistory.append(expenditure)
        toRemove = self.ExpenditureHistory.pop(0);
        if(expenditure in self.ExpenditureCounter):self.ExpenditureCounter[expenditure]+=1
        else: self.addNew(expenditure)
        self.ExpenditureCounter[toRemove]-=1
        if(self.ExpenditureCounter[toRemove]<=0):
            indToRemove = self.findAliveIndex(toRemove)
            del(self.ExpenditureCounter[toRemove])
            self.AliveExpenditures.pop(indToRemove)
    def firstAdding(self,expenditure):
        self.trailingDays-=1
        self.ExpenditureHistory.append(expenditure)
        if (len(self.AliveExpenditures)==0):
            self.ExpenditureCounter[expenditure]=1
            self.AliveExpenditures.append(expenditure)
            return
        if (expenditure in self.ExpenditureCounter):
            self.ExpenditureCounter[expenditure]+=1
            return
        self.addNew(expenditure)
    def calculateNotification(self,expenditure):
        aliveInd = 0
        helper = 0
        number=self.AliveExpenditures[aliveInd]
        helper += self.ExpenditureCounter[number]
        while(helper<self.ind1):
            aliveInd+=1
            number = self.AliveExpenditures[aliveInd]
            helper += self.ExpenditureCounter[number]
        if(self.odd and self.ind2>helper): number+=self.AliveExpenditures[aliveInd+1]
        else: number*=2
        if(expenditure>=number): self.notifications+=1
    def add(self,expenditure):
        if(self.trailingDays>0): self.firstAdding(expenditure)
        else:
            self.calculateNotification(expenditure)
            self.moveWindow(expenditure)
def activityNotifications(expenditure, d):
    workingObject = Manager(d)
    for ex in expenditure:
        workingObject.add(ex)
    return workingObject.notifications
    
if __name__ == '__main__':
    fptr = open("result", 'w')

    first_multiple_input = input().rstrip().split()

    n = int(first_multiple_input[0])

    d = int(first_multiple_input[1])

    expenditure = list(map(int, input().rstrip().split()))

    result = activityNotifications(expenditure, d)

    fptr.write(str(result) + '\n')

    fptr.close()