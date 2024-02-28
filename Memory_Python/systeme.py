import pygame as py
import random
import time
import var
import varFacile as vf
import varMoyen as vm
import varDifficile as vd

class Systeme():

    def __init__(self, niveau, fond, imgs, dosCard, valeurs, posImg, dim, position, nb):
        self.fond = fond
        self.screen = py.display.set_mode(var.dimensionBase)
        self.screen.blit(self.fond, var.dimensionFenetre)
        py.display.set_icon(var.logo)
        py.display.set_caption("Mode de jeu: " + niveau)
        py.display.flip()
        self.card1, self.card2, self.card3, self.card4, self.card5, self.card6, self.card7, self.card8 = None, None, None, None, None, None, None, None
        self.card9, self.card10, self.card11, self.card12, self.card13, self.card14, self.card15, self.card16 = None, None, None, None, None, None, None, None
        self.card17, self.card18, self.card19, self.card20, self.card21, self.card22, self.card23, self.card24 = None, None, None, None, None, None, None, None
        self.card25, self.card26, self.card27, self.card28, self.card29, self.card30, self.card31, self.card32 = None, None, None, None, None, None, None, None
        self.card33, self.card34, self.card35, self.card36, self.card37, self.card38, self.card39, self.card40 = None, None, None, None, None, None, None, None
        self.card41, self.card42, self.card43, self.card44, self.card45, self.card46, self.card47, self.card48 = None, None, None, None, None, None, None, None
        self.end = 0
        self.nb = nb
        self.dim = dim
        self.position = position
        self.images = imgs
        self.dosCard = dosCard
        self.posImg = posImg
        self.valeurs = valeurs.copy()
        self.cards = []
        self.paires = []
        self.carteNonRetourner = valeurs.copy()
        self.carteRetourner = []

    def démarrage(self):
        """Affichage des cartes non retournés por le début de la partie."""
        for i in range(len(self.carteNonRetourner)):
            txtpos = self.posImg + str(self.carteNonRetourner[i])
            txtname = "self.card" + str(self.carteNonRetourner[i])
            name, pos = eval(txtname), eval(txtpos)
            name = self.screen.blit(self.dosCard, pos)
            self.cards.append(name)
            py.display.flip()
    
    def attributionPlaceImg(self):
        """Créer un Tableau et un Dico pour chaque position une valeur correspondante à une image."""
        tab = []
        dico = {}
        valeurs = self.valeurs.copy()
        valeurs2 = self.valeurs.copy()
        images = self.images.copy()
        for i in range(self.dim[0]):
            tmp = []
            for i in range(self.dim[1]):
                nb = random.choice(valeurs)
                tmp.append(nb)
                valeurs.remove(nb)
            tab.append(tmp)
        for i in range(self.nb):
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
                for h in range(len(self.position)):
                    for l in range(len(self.position[0])):
                        if i+1 == self.position[h][l]:
                            valCard = tab[h][l]
                if valCard in self.carteNonRetourner:
                    self.carteRetourner.append(valCard)
                    self.carteNonRetourner.remove(valCard)
                    for j,k in dico.items():
                        for nb in k:
                            if nb == valCard:
                               self.cards[i] = self.screen.blit(j, eval(self.posImg+str(i+1)))
                               py.display.flip()

    def compteur(self, tab, dico):
        if len(self.carteRetourner) == 2:
            val1 = self.carteRetourner[0]
            val2 = self.carteRetourner[1]
            tmp = []
            for j in dico.values():
                tmp.append(j)
            if (val1, val2) in tmp or (val2,val1) in tmp:
                    self.paires.append((val1,val2))
                    self.carteRetourner = []
            else:
                self.carteNonRetourner.append(val1)
                self.carteNonRetourner.append(val2)
                self.carteRetourner = []
            time.sleep(1)
            Systeme.affichageSuite(self, tab, dico)

    def affichageSuite(self, tab, dico):
        self.screen.blit(self.fond, var.dimensionFenetre)
        for i in range(len(self.paires)):
            for h in range(len(tab)):
                for j in range(len(tab[0])):
                    if self.paires[i][0] == tab[h][j]:
                        pos1 = self.position[h][j]
                    elif self.paires[i][1] == tab[h][j]:
                        pos2 = self.position[h][j]
            for l,p in dico.items():
                if self.paires[i][0] in p:
                    self.screen.blit(l,eval(self.posImg+str(pos1)))
                    self.screen.blit(l,eval(self.posImg+str(pos2)))
        for i in range(len(self.carteNonRetourner)):
            for h in range(len(tab)):
                for j in range(len(tab[0])):
                    if self.carteNonRetourner[i] == tab[h][j]:
                        pos = self.position[h][j]
            for l,p in dico.items():
                if self.carteNonRetourner[i] in p:
                    self.screen.blit(self.dosCard,eval(self.posImg+str(pos)))
        py.display.flip()
        Systeme.vérification(self)
    
    def vérification(self):
        if len(self.paires) == self.nb:
            self.end = 1
            time.sleep(0.5)
            self.screen.blit(var.fin, var.dimensionFenetre)
            py.display.flip()

    def events(self):
        continuer = True
        Systeme.démarrage(self)
        tab, dico = Systeme.attributionPlaceImg(self)
        while continuer:
            for event in py.event.get():
                if event.type == py.QUIT:
                    continuer = False
                if event.type == py.KEYDOWN:
                    if event.key == py.K_ESCAPE:
                        continuer = False
                if self.end == 1:
                    if event.type == py.MOUSEBUTTONUP:
                        print("Fin du jeu.")
                        continuer = False
                if event.type == py.MOUSEBUTTONUP:
                    Systeme.affichage(self,tab,dico,event.pos)
                    Systeme.compteur(self, tab, dico)


class Facile():

    def __init__(self):
        main = Systeme("Facile", vf.fondF, vf.images, vf.fond_cardF, vf.vals, "vf.Fpos", vf.dim, vf.position, 8)
        main.events()

class Moyen():

    def __init__(self):
        main = Systeme("Moyen", vm.fondM, vm.images, vm.fond_cardM, vm.vals, "vm.Mpos", vm.dim, vm.position, 16)
        main.events()

class Difficile():

    def __init__(self):
        main =  Systeme("Difficile", vd.fondD, vd.images, vd.fond_cardD, vd.vals, "vd.Dpos", vd.dim, vd.position, 24)
        main.events()