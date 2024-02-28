import pygame as py
import var

posMoyen = py.Rect(433,299, var.dimensionModeW, var.dimensionModeH)

McardW = 100
McardH = 104

fond_cardM = py.image.load("img/card/Moyen/fond_cardM.png")
fondM = py.image.load("img/card/fondM.png")

aa = py.image.load("img/card/Moyen/aa.png")
ba = py.image.load("img/card/Moyen/ba.png")
ca = py.image.load("img/card/Moyen/ca.png")
da = py.image.load("img/card/Moyen/da.png")
ga = py.image.load("img/card/Moyen/ga.png")
ma = py.image.load("img/card/Moyen/ma.png")
na = py.image.load("img/card/Moyen/na.png")
pa = py.image.load("img/card/Moyen/pa.png")
ag = py.image.load("img/card/Moyen/ag.png")
bg = py.image.load("img/card/Moyen/bg.png")
cg = py.image.load("img/card/Moyen/cg.png")
dg = py.image.load("img/card/Moyen/dg.png")
gg = py.image.load("img/card/Moyen/gg.png")
mg = py.image.load("img/card/Moyen/mg.png")
ng = py.image.load("img/card/Moyen/ng.png")
pg = py.image.load("img/card/Moyen/pg.png")

images = [aa,ba,ca,da,ga,ma,na,pa,ag,bg,cg,dg,gg,mg,ng,pg]
dim = [8,4]
position = [[1,9,17,25],[2,10,18,26],[3,11,19,27],[4,12,20,28],[5,13,21,29],[6,14,22,30],[7,15,23,31],[8,16,24,32]]
vals = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32]

MstartW = 148
MstartH = 80

Mpos1 = py.Rect(MstartW,MstartH,McardW,McardH)
Mpos2 = py.Rect(MstartW,MstartH+130,McardW,McardH)
Mpos3 = py.Rect(MstartW,MstartH+260,McardW,McardH)
Mpos4 = py.Rect(MstartW,MstartH+390,McardW,McardH)
Mpos5 = py.Rect(MstartW+120,MstartH,McardW,McardH)
Mpos6 = py.Rect(MstartW+120,MstartH+130,McardW,McardH)
Mpos7 = py.Rect(MstartW+120,MstartH+260,McardW,McardH)
Mpos8 = py.Rect(MstartW+120,MstartH+390,McardW,McardH)
Mpos9 = py.Rect(MstartW+240,MstartH,McardW,McardH)
Mpos10 = py.Rect(MstartW+240,MstartH+130,McardW,McardH)
Mpos11 = py.Rect(MstartW+240,MstartH+260,McardW,McardH)
Mpos12 = py.Rect(MstartW+240,MstartH+390,McardW,McardH)
Mpos13 = py.Rect(MstartW+360,MstartH,McardW,McardH)
Mpos14 = py.Rect(MstartW+360,MstartH+130,McardW,McardH)
Mpos15 = py.Rect(MstartW+360,MstartH+260,McardW,McardH)
Mpos16 = py.Rect(MstartW+360,MstartH+390,McardW,McardH)
Mpos17 = py.Rect(MstartW+480,MstartH,McardW,McardH)
Mpos18 = py.Rect(MstartW+480,MstartH+130,McardW,McardH)
Mpos19 = py.Rect(MstartW+480,MstartH+260,McardW,McardH)
Mpos20 = py.Rect(MstartW+480,MstartH+390,McardW,McardH)
Mpos21 = py.Rect(MstartW+600,MstartH,McardW,McardH)
Mpos22 = py.Rect(MstartW+600,MstartH+130,McardW,McardH)
Mpos23 = py.Rect(MstartW+600,MstartH+260,McardW,McardH)
Mpos24 = py.Rect(MstartW+600,MstartH+390,McardW,McardH)
Mpos25 = py.Rect(MstartW+720,MstartH,McardW,McardH)
Mpos26 = py.Rect(MstartW+720,MstartH+130,McardW,McardH)
Mpos27 = py.Rect(MstartW+720,MstartH+260,McardW,McardH)
Mpos28 = py.Rect(MstartW+720,MstartH+390,McardW,McardH)
Mpos29 = py.Rect(MstartW+840,MstartH,McardW,McardH)
Mpos30 = py.Rect(MstartW+840,MstartH+130,McardW,McardH)
Mpos31 = py.Rect(MstartW+840,MstartH+260,McardW,McardH)
Mpos32 = py.Rect(MstartW+840,MstartH+390,McardW,McardH)