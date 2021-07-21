def reverseWords(s):
    word = []
    r=""
    for letter in s:
        if(not letter.isalpha()):
            rword=""
            for l in word:
                rword+=l
            r+=rword[::-1]+letter
            word=[]
            continue
        word.append(letter)
    rword=""
    for l in word:
        rword+=l
    r+=rword[::-1]
    return r
print(reverseWords(input("word :")))