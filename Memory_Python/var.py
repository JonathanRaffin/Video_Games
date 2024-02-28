import pygame as py
from pygame.draw import aaline

largeur = 1240
hauteur = 680
dimensionBase = (largeur, hauteur)
dimensionFenetre = py.Rect(0,0,largeur,hauteur)

reglesZone = py.Rect(1140,25,60,65)

dimensionModeW = 374
dimensionModeH = 82

# images

logo = py.image.load("img/memory.png")

menu = py.image.load("img/menu.png")
mode = py.image.load("img/mode.png")
fin = py.image.load("img/finGame.png")
regles = py.image.load("img/regles.png")
imgReg = py.image.load("img/bouttons/reg.png")

facile = py.image.load("img/Bouttons/facile.png")
moyen = py.image.load("img/Bouttons/moyen.png")
difficile = py.image.load("img/Bouttons/difficile.png")