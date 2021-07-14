from converter.component_writer import *
from converter.html_processor import html_processor
def writeFile(filename,data):
    processFile = open(filename, 'w')
    processFile.writelines(data)
    processFile.close()
if __name__ == "__main__":
    import sys
    l=len(sys.argv)    
    if(l==1):
        filename=input("Html filename located at downloads : \n: ")
    elif(l==2):
        filename=(str(sys.argv[1]))
    else:
        filename=(str(sys.argv[1]))
        for word in range(2,l):
            filename+=" "+(str(sys.argv[word]))
parser = html_processor()
file = open("/Users/nsl-intern/Downloads/{}.html".format(filename))
proccessedFile = parser.replace(file)
file.close()
writeFile("{}p.html".format(filename),proccessedFile)
wr = component_writer()
component=wr.convertToExpandable(filename)
writeFile('{}_component.html'.format(filename),component)
