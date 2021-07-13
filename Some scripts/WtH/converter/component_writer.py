import re
class component_writer:
    paragraph = """
<mat-expansion-panel hideToggle class="ExpansionPanel">
    <mat-expansion-panel-header class="matPanelTitle">
    <mat-panel-title>
        <p> ?T </p>
        </mat-panel-title>
    </mat-expansion-panel-header>
    ?C
</mat-expansion-panel>

"""
    def convertToExpandable(self,filename):
        file = open("{}p.html".format(filename))
        non_empty_lines = [line for line in file if line.strip() != ""]
        file.close()
        out = ""
        content="\t"
        title = re.findall(r'<h1>(.+?)</h1>',non_empty_lines[0])[0]
        non_empty_lines = non_empty_lines[1:]
        img=1
        for line in non_empty_lines:
            if "<h1" not in line:
                if "******IMAGES******" in line:
                    line = "\t<div class='images'><img src='assets/{}/{}.png'></div>".format(filename,img)
                    img+=1
                content+="\t"+line
            else:
                out+=self.paragraph.replace("?T",title).replace("?C",content)
                content = "\t"
                title = re.findall(r'<h1.*?>(.+?)<\/h1>',line)[0]
        out+=self.paragraph.replace("?T",title).replace("?C",content)
        return out
