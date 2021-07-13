from converter.component_writer import *
from converter.html_processor import html_processor
def writeFile(filename,data):
    processFile = open(filename, 'w')
    processFile.writelines(data)
    processFile.close()

filename="Week 15"
parser = html_processor()
file = open("/Users/nsl-intern/Downloads/{}.html".format(filename))
proccessedFile = parser.replace(file)
file.close()
writeFile("{}p.html".format(filename),proccessedFile)
wr = component_writer()
component=wr.convertToExpandable(filename)
writeFile('{}_component.html'.format(filename),component)
