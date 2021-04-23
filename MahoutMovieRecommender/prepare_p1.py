
import time

filename = "/Users/nsl-intern/Downloads/movies.txt"
#time at the start of program is noted
start = time.time()
#keeps a track of number of lines in the file
count = 0
pId ="product/productId:"
uId = "review/userId:"
sId = "review/score:"
#Output file number
short = open("shorter.txt", "w")

with open(filename,errors='replace') as infile:
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