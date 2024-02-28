import pygame as py
import var

posFacile = py.Rect(433,186, var.dimensionModeW, var.dimensionModeH)

FcardW = 130
FcardH = 135

fond_cardF = py.image.load("img/card/Facile/fond_cardF.png")
fondF = py.image.load("img/card/fondF.png")

aa = py.image.load("img/card/Facile/aa.png")
ba = py.image.load("img/card/Facile/ba.png")
ca = py.image.load("img/card/Facile/ca.png")
da = py.image.load("img/card/Facile/da.png")
ga = py.image.load("img/card/Facile/ga.png")
ma = py.image.load("img/card/Facile/ma.png")
na = py.image.load("img/card/Facile/na.png")
pa = py.image.load("img/card/Facile/pa.png")

images = [aa,ba,ca,da,ga,ma,na,pa]
dim = [4,4]
position = [[1,5,9,13],[2,6,10,14],[3,7,11,15],[4,8,12,16]]
vals = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]

FstartW = 297.5
FstartH = 50

Fpos1 = py.Rect(FstartW,FstartH,FcardW,FcardH)
Fpos2 = py.Rect(FstartW,FstartH+150,FcardW,FcardH)
Fpos3 = py.Rect(FstartW,FstartH+300,FcardW,FcardH)
Fpos4 = py.Rect(FstartW,FstartH+450,FcardW,FcardH)
Fpos5 = py.Rect(FstartW+165,FstartH,FcardW,FcardH)
Fpos6 = py.Rect(FstartW+165,FstartH+150,FcardW,FcardH)
Fpos7 = py.Rect(FstartW+165,FstartH+300,FcardW,FcardH)
Fpos8 = py.Rect(FstartW+165,FstartH+450,FcardW,FcardH)
Fpos9 = py.Rect(FstartW+330,FstartH,FcardW,FcardH)
Fpos10 = py.Rect(FstartW+330,FstartH+150,FcardW,FcardH)
Fpos11 = py.Rect(FstartW+330,FstartH+300,FcardW,FcardH)
Fpos12 = py.Rect(FstartW+330,FstartH+450,FcardW,FcardH)
Fpos13 = py.Rect(FstartW+495,FstartH,FcardW,FcardH)
Fpos14 = py.Rect(FstartW+495,FstartH+150,FcardW,FcardH)
Fpos15 = py.Rect(FstartW+495,FstartH+300,FcardW,FcardH)
Fpos16 = py.Rect(FstartW+495,FstartH+450,FcardW,FcardH)