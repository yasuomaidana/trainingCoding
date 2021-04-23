import csv
import time

count = 0
filename = "shorter.txt"
productIds = []
userIds = []

#Selectors
pIds ="product/productId: " #product id selector
uIds = "review/userId: " #user id selector
ss = "review/score: " #score selector
#Output file
data = open("data/data.csv", "w")
writer = csv.writer(data)
uFname = open("data/users.csv", "w")
uwriter = csv.writer(uFname)
pFname = open("data/product.csv", "w")
pwriter = csv.writer(pFname)
#Work variable row[userId,productId,rating]
products=[]
users=[]
count=0
print("Starts writing")
start = time.time()
with open(filename,errors='replace') as infile:
    for line in infile:
        if pIds in line:
            product = line.replace(pIds,"").replace("\n","")
            if not product in products:
                products.append(product)
                pwriter.writerow([product])
            # pId product Id
            pId = products.index(product)+1
        if uIds in line:
            user = line.replace(uIds,"").replace("\n","")
            if not user in users:
                users.append(user)
                uwriter.writerow([user])
            # pId product Id
            uId = users.index(user)+1
        if ss in line:
            score = line.replace(ss,"").replace("\n","")
            score = float(score)
            row = [uId,pId,score]
            writer.writerow(row)
            count +=1
data.close()
uFname.close()
pFname.close()
end = time.time()
print("Execution time in seconds: ",(end - start))
print("No. of lines printed: ",count)
            