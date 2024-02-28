import pygame as py
import random
import time
import var
import varFacile as vf

class GameF():

    def __init__(self):
        self.screen = py.display.set_mode(var.dimensionBase)
        self.screen.blit(var.fond, var.dimensionFenetre)
        py.display.set_icon(var.logo)
        py.display.set_caption("Mode de Jeu: Facile")
        py.display.flip()
        self.card1, self.card2, self.card3, self.card4, self.card5, self.card6, self.card7, self.card8 = None, None, None, None, None, None, None, None
        self.card9, self.card10, self.card11, self.card12, self.card13, self.card14, self.card15, self.card16 = None, None, None, None, None, None, None, None
        self.images = vf.images
        self.end = 0
        self.cards = []
        self.paires = []
        self.carteRetourner = []
        self.carteNonRetourner = []

    def démarrage(self):
        """Affichage des cartes non retournés por le début de la partie."""
        self.carteNonRetourner = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
        self.carteRetourner = []
        for i in range(len(self.carteNonRetourner)):
            txtpos = "vf.Fpos" + str(self.carteNonRetourner[i])
            txtname = "self.card" + str(self.carteNonRetourner[i])
            name, pos = eval(txtname), eval(txtpos)
            name = self.screen.blit(vf.fond_cardF, pos)
            self.cards.append(name)
            py.display.flip()

    def attributionPlaceImg(self):
        """Créer un Tableau et un Dico pour chaque position une valeur correspondante à une image."""
        tab = []
        dico = {}
        valeurs = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
        valeurs2 = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
        images = self.images
        for i in range(vf.dim[0]):
            tmp = []
            for i in range(vf.dim[1]):
                nb = random.choice(valeurs)
                tmp.append(nb)
                valeurs.remove(nb)
            tab.append(tmp)
        for i in range(8):
            val1 = random.choice(valeurs2)
            valeurs2.remove(val1)
            val2 = random.choice(valeurs2)
            valeurs2.remove(val2)
            img = random.choice(images)
            images.remove(img)
            dico[img] = (val1, val2)
        return tab, dico

    def affichage(self,tab,dico,ep):
        for i in range(len(self.cards)):
            if self.cards[i].collidepoint(ep):
                for h in range(len(vf.position)):
                    for l in range(len(vf.position[0])):
                        if i+1 == vf.position[h][l]:
                            valCard = tab[h][l]
                if valCard in self.carteNonRetourner:
                    self.carteRetourner.append(valCard)
                    self.carteNonRetourner.remove(valCard)
                    for j,k in dico.items():
                        for nb in k:
                            if nb == valCard:
                               self.cards[i] = self.screen.blit(j, eval("vf.Fpos"+str(i+1)))
                               print("Place:", i+1, "Card:", valCard)
                               py.display.flip()
                               GameF.listeCards(self)
                               return (i+1, valCard)

    def affichageSuite(self, tab, dico):
        self.screen.blit(var.fond, var.dimensionFenetre)
        for i in range(len(self.paires)):
            for h in range(len(tab)):
                for j in range(len(tab[0])):
                    if self.paires[i][0] == tab[h][j]:
                        pos1 = vf.position[h][j]
                    elif self.paires[i][1] == tab[h][j]:
                        pos2 = vf.position[h][j]
            for l,p in dico.items():
                if self.paires[i][0] in p:
                    self.screen.blit(l,eval("vf.Fpos"+str(pos1)))
                    self.screen.blit(l,eval("vf.Fpos"+str(pos2)))
        for i in range(len(self.carteNonRetourner)):
            for h in range(len(tab)):
                for j in range(len(tab[0])):
                    if self.carteNonRetourner[i] == tab[h][j]:
                        pos = vf.position[h][j]
            print(self.carteNonRetourner[i], "Pos:", pos)
            for l,p in dico.items():
                if self.carteNonRetourner[i] in p:
                    self.screen.blit(vf.fond_cardF,eval("vf.Fpos"+str(pos)))
        py.display.flip()
        GameF.vérification(self)

    def vérification(self):
        if len(self.paires) == 8:
            self.end = 1
            time.sleep(0.5)
            self.screen.blit(var.fin, var.dimensionFenetre)
            py.display.flip()

    def compteur(self, tab, dico, nb):
        if len(self.carteRetourner) == 2:
            val1 = self.carteRetourner[0]
            val2 = self.carteRetourner[1]
            tmp = []
            for j in dico.values():
                tmp.append(j)
            if (val1, val2) in tmp or (val2,val1) in tmp:
                    print("j:", (val1,val2), "paire trouvée")
                    self.paires.append((val1,val2))
                    self.carteRetourner = []
            else:
                self.carteNonRetourner.append(val1)
                self.carteNonRetourner.append(val2)
                self.carteRetourner = []
            time.sleep(1)
            GameF.affichageSuite(self, tab, dico)


    def listeCards(self):
        self.carteNonRetourner.sort()
        print("Carte Non Retourner:", self.carteNonRetourner)
        print("Carte Retourner:", self.carteRetourner)
        print("Paires:", self.paires)

    def eventsF(self):
        continuer = True
        GameF.démarrage(self)
        tab, dico = GameF.attributionPlaceImg(self)
        print("Tab:", tab)
        print("Dico:", dico)
        while continuer:
            for event in py.event.get():
                if event.type == py.QUIT:
                    continuer = False
                if event.type == py.KEYDOWN:
                    if event.key == py.K_ESCAPE:
                        continuer = False
                    if self.end == 1:
                        if event.key == py.K_RETURN:
                            print("Fin du jeu.")
                if event.type == py.MOUSEBUTTONUP:
                    nb = GameF.affichage(self,tab,dico,event.pos)
                    GameF.compteur(self, tab, dico,nb)
                    print("---------------------------------")
        py.quit()

facile = GameF()
facile.eventsF()