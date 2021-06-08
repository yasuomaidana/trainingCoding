
# pairList list that contains the relationship between two letters
def findExtr(pairList):
    #Diccionario
    backTr = {}
    #Iterates each pair in the list
    for pair in pairList:
        #Extracts the letters
        upper,lower = pair.split(">")
        if(backTr.get(upper)):
            backTr[upper][0]+=1
        else:
            backTr[upper]=[0,">"]
        if(backTr.get(lower)):
            backTr[lower][0]+=1
        else:
            backTr[lower]=[0,"<"]
        backTr[upper][1]=">"
    print(backTr)
    for letter in backTr:
        if backTr[letter][0]==0:
            if backTr[letter][1]=='>':
                upper = letter
            else:
                lower = letter
    return [lower,letter]
def extracts(extrems,pairList):
    first = extrems[0]
    uP = list((filter(lambda x: first+">" in x, pairList)))[0]
    word =uP.replace(">","")[0]
    while len(pairList)>1:
        word+=uP.replace(">","")[1]
        pairList.pop(pairList.index(uP))
        first = uP[-1]
        uP = list((filter(lambda x: first+">" in x, pairList)))[0]
    word+=extrems[1]
    print(word)
def findWord(letters):
    extrems=findExtr(letters)
    print(extrems)
    #extracts(extrems,letters)

findWord(["P>E", "E>R","R>U"]) # PERU
"""findWord(["I>N", "A>I","P>A","S>P"]) # SPAIN
findWord(["U>N", "G>A", "R>Y", "H>U", "N>G", "A>R"]) # HUNGARY
findWord(["I>F", "W>I", "S>W", "F>T"]) # SWIFT
findWord(["R>T", "A>L", "P>O", "O>R", "G>A", "T>U", "U>G"]) # PORTUGAL
findWord(["W>I", "R>L", "T>Z", "Z>E", "S>W", "E>R", "L>A", "A>N", "N>D", "I>T"]) # SWITZERLAND
"""