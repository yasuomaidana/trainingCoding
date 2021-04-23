import csv
import time
import os
#Creates an csv file with the unique members 

#Name of the shortest version of information
filename = "shorter.txt"
#Selectors
pIds ="product/productId: " #product id selector
uIds = "review/userId: " #user id selector
ss = "review/score: " #score selector

#Filter the information using Ids as reference and store the
#information into a name.csv file
def filterId(name,Ids):
    #Validates if the file exists
    name = "data/"+name+".csv"
    if os.path.exists(name):
        return
    #Open the file and creates its csv writer
    uFname = open(name, "w")
    uwriter = csv.writer(uFname)
    #Initialize the list
    rawInfo = []
    print("Starts"+name+" raw filtering")
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

    start = time.time()
    #Creates an array of the unique members 
    setInfo = set(rawInfo)
    end = time.time()
    print("Execution set"+name+" time in seconds: ",(end - start))
    print("No. users printed: ",len(setInfo))

    #Writes the information founded
    start = time.time()
    print("Starts writing in"+name)
    for info in setInfo:
        uwriter.writerow([info])
    uFname.close()
    end = time.time()
    print("Execution write users time in seconds: ",(end - start))

#Extracts the information needed for the recommendation
def bigFilter():
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
    print("No. of lines printed: ",count)

#Read the CSV and return a list of whatever it reads
def readToList(name):
    rList = []
    data = open("data/"+name+".csv", "r")
    for line in data.readlines():
        rList.append(line.replace("\n", ""))
    data.close()
    return rList

#Prepares the data, writes a csv that contains userId,productId,score
def prepData():
    #Validates if the file exists
    name ="data/data.csv"
    if os.path.exists(name):
        return
    #Prepares the file and its writer
    data = open(name, "w")
    writer = csv.writer(data)
    #Read the products and users as lists
    products = readToList("products")
    users = readToList("users")
    count = 0
    c2=0
    dats=[]
    #Start extractions
    print("Starts preparing data")
    start = time.time()
    with open(filename,errors='replace') as infile:
        for line in infile:
            #Extracts the index of the products and users
            if pIds in line:
                product = line.replace(pIds,"").replace("\n","")
                pId = products.index(product)+1
            if uIds in line:
                user = line.replace(uIds,"").replace("\n","")
                uId = users.index(user)+1
            #Extracts the score
            if ss in line:
                score = line.replace(ss,"").replace("\n","")
                score = float(score)
                row = [uId,pId,score]
                dats.append(row)
                #writer.writerow(row)
                count +=1
                if count%1e4==0:
                    writer.writerows(dats)
                    dats = []
                    c2+=1
                    print(count/7e6)
                if c2==2:
                    break
    data.close()
    infile.close()
    end = time.time()
    print("Execution time in seconds: ",(end - start))
    print("No. of reviews: ",count)
    print(len(dats))

if not os.path.exists(filename):
    bigFilter()

filterId("users",uIds)
filterId("products",pIds)
prepData()
