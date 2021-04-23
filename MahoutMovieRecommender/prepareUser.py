import csv
import time
count = 0
filename = "shorter.txt"
uIds = "review/userId: " #user id selector
#Output file
uFname = open("data/users.csv", "w")
uwriter = csv.writer(uFname)
#Work variable row[userId,productId,rating]
users=[]
count=0
print("Starts reading")
start = time.time()
with open(filename,errors='replace') as infile:
    for line in infile:
        if uIds in line:
            user = line.replace(uIds,"").replace("\n","")
            users.append(user)
infile.close()
end = time.time()
print("Execution users time in seconds: ",(end - start))

start = time.time()
setUsers = set(users)
end = time.time()
print("Execution setUsers time in seconds: ",(end - start))
print("No. users printed: ",len(setUsers))


start = time.time()
print("Starts writing")
for user in setUsers:
    uwriter.writerow([user])
end = time.time()
print("Execution write users time in seconds: ",(end - start))
