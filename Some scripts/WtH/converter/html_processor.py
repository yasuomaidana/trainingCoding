import re
class html_processor:
    #Tags to eliminate
    toDel=["SPAN","FONT"]
    #Pattern used to get classes
    pat="<([A-Z0-9]+)\s.+>|.*</(.+)>|<([A-Z0-9]+)>"
    #Obtains the tag
    def getTag(self,element):
        tag = list(re.findall(self.pat,element)[0])
        tag = [i for i in tag if i !='']
        tag = tag[0]
        return tag
    #Fills the context depending on the tag
    def fillContent(self,element,tag):
        content = re.findall("(.*)</"+tag+">",element)[0]
        if tag not in self.toDel:
            if tag == "H1":
                content.replace('\n','')
            content+="</"+tag.lower()+">"
        return content
    #Returns the tag with their parameters
    def fillTag(self,tag,element):
        link='';
        newTag='';
        parameters=''
        if(tag=='A'): 
            link=" href="+re.findall(".* HREF=(.*)>",element)[0]
            parameters+=link
        if tag not in self.toDel:
            newTag="<"+tag.lower()+"{}>".format(parameters)
        return newTag

    def validateH1(self,line):
        if "<h1" in line and "</h1" not in line:
            line=line.replace("\n","")
        return line
    #Replace the tag contain
    def repTag(self,line):
        #Line to replace
        torep = [i+">" for i in line.split(">")]
        #To replace string
        r=""
        #For each element in replace
        for el in torep:
            #For empty line
            if el==">":
                continue
            #If it is something that ends in >
            if not "<" in el and ">" in el:
                r +=el.replace(">",'')
                continue
            tag = self.getTag(el)
            if "</" in el:
                r+=self.fillContent(el,tag)
            else:
                r+=self.fillTag(tag,el)
        return self.validateH1(r)
    def replace(self,file):
        non_empty_lines = [line for line in file if line.strip() != ""]
        r=[]
        record = False
        for i in non_empty_lines:
            if "<BODY" in i:
                record = True
                continue
            if "</BODY>" in i:
                break
            if record:
                r.append(self.repTag(i))
        return r
    