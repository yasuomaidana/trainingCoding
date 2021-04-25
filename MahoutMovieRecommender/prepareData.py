##########################################################
# This program Creates an csv file with the unique members 
##########################################################
import csv
import time
import os
import sys


#Name of the shortest version of information
filename = "shorter.txt"
#Selectors
pIds ="product/productId: " #product id selector
uIds = "review/userId: " #user id selector
ss = "review/score: " #score selector
#Used to measure how long it takes
totalTime =0

#Filter the information using Ids as reference and store the
#information into a name.csv file
def filterId(name,Ids):
    global totalTime
    #Validates if the file exists
    name = "data/"+name+".csv"
    if os.path.exists(name):
        return
    #Open the file and creates its csv writer
    uFname = open(name, "w")
    uwriter = csv.writer(uFname)
    #Initialize the list
    rawInfo = []
    print("Start "+name+" raw filtering")
    start = time.time()
    #Extracts the information
    with open(filename,errors='replace') as infile:
        for line in infile:
            if Ids in line:
                Info = line.replace(Ids,"").replace("\n","")
                rawInfo.append(Info)
    infile.close()
    end = time.time()
    print("Execution"+name+" raw filtering time in seconds: ",(end - start))
    totalTime +=start-end
    start = time.time()
    #Creates an array of the unique members 
    setInfo = set(rawInfo)
    setInfo = list(setInfo)
    setInfo.sort()
    end = time.time()
    print("Execution set"+name+" time in seconds: ",(end - start))
    print("No."+name+ " saved: ",len(setInfo))
    
    totalTime +=start-end
    #Writes the information founded
    start = time.time()
    print("Starts writing in"+name)
    for info in setInfo:
        uwriter.writerow([info])
    uFname.close()
    end = time.time()
    print("Execution write users time in seconds: ",(end - start))
    totalTime +=start-end

#Filters the information from a large text obtained from
#http://snap.stanford.edu/data/web-Movies.html
#and extracts what we need
def bigFilter():
    global totalTime
    filen = input("Insert the path of your data: ")
    #time at the start of program is noted
    start = time.time()
    #keeps a track of number of lines in the file
    count = 0
    #References to extract
    pId ="product/productId:"
    uId = "review/userId:"
    sId = "review/score:"
    #Output file number
    short = open("shorter.txt", "w")
    #Extracts the information
    print("Starting bigFilter")
    with open(filen,errors='replace') as infile:
        for line in infile:
            count = count + 1
            if pId in line or uId in line or sId in line:
                short.write(line)
    #time at the end of program execution is noted
    end = time.time()
    infile.close()
    short.close()
    #total time taken to print the file
    print("Execution time in seconds: ",(end - start))
    print("No. of lines saved: ",count)
    totalTime +=start-end

#Read the CSV and return a dictionary of whatever it reads
def readToDict(name):
    rList = {}
    data = open("data/"+name+".csv", "r")
    count = 0
    for line in data.readlines():
        d=line.replace("\n", "")
        rList[d]=count
        count+=1
    data.close()
    return rList

#Read the CSV and return a list of whatever it reads
def readToList(name):
    rList = []
    data = open("data/"+name+".csv", "r")
    for line in data.readlines():
        d=line.replace("\n", "")
        rList.append(d)
    data.close()
    return rList

#Prepares the data, writes a csv that contains userId,productId,score
def prepData():
    global totalTime
    #Validates if the file exists
    name ="data/data.csv"
    if os.path.exists(name):
        return
    print("Start preparing data")
    start = time.time()
    #Read the products and users as lists
    products = readToDict("products")
    users = readToDict("users")
    count = 0
    dats=[]
    #Start extractions
    with open(filename,errors='replace') as infile:
        for line in infile:
            #Extracts the index of the products and users
            if pIds in line:
                product = line.replace(pIds,"").replace("\n","")
                pId = products[product]+1
            if uIds in line:
                user = line.replace(uIds,"").replace("\n","")
                uId = users[user]+1
            #Extracts the score
            if ss in line:
                score = line.replace(ss,"").replace("\n","")
                score = float(score)
                row = [uId,pId,score]
                dats.append(row)
                #writer.writerow(row)
                count +=1
            if count %1e5==0:
                sys.stdout.write('\r')
                # the exact output you're looking for:
                sys.stdout.write("%d%% %d" % (count*100/7911684,count))
                sys.stdout.flush()
    infile.close()
    #Prepares the file and its writer
    with open(name, "w") as data:
        writer = csv.writer(data)
        writer.writerows(dats)
    data.close()
    
    end = time.time()
    print("Execution time in seconds: ",(end - start))
    print("No. of reviews: ",len(dats))
    totalTime +=start-end

#Read a csv and returns how many lines it contains
def getLen(name):
    count=0
    if not os.path.exists(name):
        return 0
    with open(name,errors='replace') as infile:
        for _ in infile:
            count+=1
    infile.close()
    return count

#Validates the length of information stored
def valLen():
    global totalTime
    names=["data","products","users"]
    lens = [7911684,253059,889176]
    i=0
    print("Starting validation")
    start = time.time()
    for name in names:
        pname = "data/"+name+".csv"
        l = getLen(pname)
        print(l)
        if lens[i]!=l and l !=0:
            print("Deleting file "+name)
            os.remove(pname)
        i+=1
    
    end = time.time()
    print("Execution time in seconds: ",(end - start))
    totalTime+=start-end

#Check if all the information is correct
def valData():
    global totalTime
    #Validates if the file exists
    name ="data/data.csv"
    if not os.path.exists(name):
        print("Something goes wrong")
        return
    print("Start validation of data")
    start = time.time()
    #Read the products and users as lists
    products = readToList("products")
    users = readToList("users")
    storedData = readToList("data")
    count = 0
    
    #Start extractions
    with open(filename,errors='replace') as infile:
        for line in infile:
            #Extracts the index of the products and users
            if pIds in line:
                product = line.replace(pIds,"").replace("\n","")
            if uIds in line:
                user = line.replace(uIds,"").replace("\n","")
            #Extracts the score
            if ss in line:
                #score = line.replace(ss,"").replace("\n","")
                #score = float(score)
                lineData = storedData[count].replace("\n","").split(",")
                s_product = products[int(lineData[1])-1]
                s_user = users[int(lineData[0])-1]
                count+=1
                if (s_user!=user or s_product!=product):
                    print()
                    print("Stored bad")
                    print("Expected U:{} P:{}".format(user,product))
                    print("Received U:{} P:{}".format(s_user,s_product))
                    break
                 
            if count %1e5==0:
                sys.stdout.write('\r')
                # the exact output you're looking for:
                sys.stdout.write("Revised %d%% of data" % (count*100/7911684))
                sys.stdout.flush()
    infile.close()
    
    end = time.time()
    print("Execution time in seconds: ",(end - start))
    totalTime +=start-end

#Compress the data stored
def compres():
    import zipfile
    global totalTime
    names=["data","products","users"]
    print("Start compresion of data")
    start = time.time()
    for name in names:
        pname = "data/"+name+".csv"
        ziper = zipfile.ZipFile('data/{}.zip'.format(name), 'w')
        ziper.write(pname, compress_type=zipfile.ZIP_DEFLATED)
        ziper.close()
        os.remove(pname)
    end = time.time()
    totalTime +=start-end

#Check if the shorter txt exists, if not it creates its
#it will ask for the path of the big txt file
if not os.path.exists(filename):
    bigFilter()

#Main part of the code
valLen()
filterId("users",uIds)
filterId("products",pIds)
prepData()
valData()
if input("Compress and delete? (1) yes (0) no")=="1":compres()
print("Total time used to prepare data ",-totalTime)