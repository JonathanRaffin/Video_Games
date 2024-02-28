import pygame as py
import time

class Game():

    MENU = py.image.load("img/menu.png")
    MENU_T = MENU.get_rect()
    REG = py.image.load("img/reg.png")
    SYMB = py.image.load("img/symb.png")
    SYMB_T = SYMB.get_rect()
    FOND = py.image.load("img/fond_2.png")
    FOND_T = FOND.get_rect()
    CROIX = py.image.load("img/croix.png")
    CERCLE = py.image.load("img/cercle.png")
    YOU = py.image.load("img/you.png")
    VAINQUEUR = py.image.load("img/vainq.png")
    EGALITE = py.image.load("img/egalite.png")
    P_CERCLE = py.image.load("img/p_cercle.png")
    P_CROIX = py.image.load("img/p_croix.png")

    def __init__(self):
        self.tf = 800
        self.screen = py.display.set_mode((self.tf,self.tf))
        py.display.set_caption("Menu")
        self.screen.blit(Game.MENU, Game.MENU_T)
        py.display.flip()

        self.zone_reg = py.Rect(164,299,473,131)
        self.zone_jeu = py.Rect(164,455,473,131)
        self.zone_qui = py.Rect(164,611,473,131)

        self.go_menu = 1
        self.go_reg = 0
        self.go_symb = 0
        self.go_jeu = 0
        self.go_fin = 0

        self.symb_cercle = py.Rect(150,400,200,200)
        self.symb_croix = py.Rect(400,400,200,200)

        self.first_symb = None
        self.second_symb = None
        self.p_symb1 = None
        self.p_symb2 = None

        self.compteur = 0

        self.case = None

        self.j1 = py.Rect(95,95,200,200)
        self.j2 = py.Rect(300,95,200,200)
        self.j3 = py.Rect(505,95,200,200)
        self.j4 = py.Rect(95,300,200,200)
        self.j5 = py.Rect(300,300,200,200)
        self.j6 = py.Rect(505,300,200,200)
        self.j7 = py.Rect(95,505,200,200)
        self.j8 = py.Rect(300,505,200,200)
        self.j9 = py.Rect(505,505,200,200)

        self.zone_vide = ["self.j1","self.j2","self.j3","self.j4","self.j5","self.j6","self.j7","self.j8","self.j9"]
        self.zone_nonvide = []

        self.vainq = None
        self.nb = 0

        self.fin = [[1,2,3],[4,5,6],[7,8,9],[1,4,7],[2,5,8],[3,6,9],[1,5,9],[3,5,7]]
        self.valid = [[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]

    def event_menu(self):
        continuer = True
        while continuer:
            for event in py.event.get():
                if self.go_menu == 1:
                    if event.type == py.QUIT:
                        continuer = False
                    if event.type == py.KEYDOWN:
                        if event.key == py.K_ESCAPE:
                            continuer = False
                    if event.type == py.MOUSEBUTTONDOWN:
                        if self.zone_reg.collidepoint(event.pos):
                            Game.regles(self)
                        elif self.zone_jeu.collidepoint(event.pos):
                            Game.symb(self)
                        elif self.zone_qui.collidepoint(event.pos):
                            continuer = False
                elif self.go_reg == 1:
                    if event.type == py.QUIT:
                        continuer = False
                    if event.type == py.KEYDOWN:
                        if event.key == py.K_ESCAPE:
                            self.go_menu = 1
                            self.go_reg = 0
                            Game()
                elif self.go_symb == 1:
                    if event.type == py.MOUSEBUTTONDOWN:
                        if self.symb_cercle.collidepoint(event.pos):
                            self.first_symb = Game.CERCLE
                            self.second_symb = Game.CROIX
                            self.p_symb1 = Game.P_CERCLE
                            self.p_symb2 = Game.P_CROIX
                        if self.symb_croix.collidepoint(event.pos):
                            self.first_symb = Game.CROIX
                            self.second_symb = Game.CERCLE
                            self.p_symb1 = Game.P_CROIX
                            self.p_symb2 = Game.P_CERCLE
                        if self.first_symb != "" :
                            Game.jeu(self)
                elif self.go_fin == 1:
                    if event.type == py.KEYDOWN:
                        if event.key == py.K_ESCAPE:
                            self.go_menu = 1
                            self.go_fin = 0
                            self.compteur = 0
                            self.zone_vide = ["self.j1","self.j2","self.j3","self.j4","self.j5","self.j6","self.j7","self.j8","self.j9"]
                            self.zone_nonvide = []
                            self.vainq = None
                            self.valid = [[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]
                            self.nb = 0
                            Game()
                elif self.go_jeu == 1:
                    if event.type == py.KEYDOWN:
                        if event.key == py.K_ESCAPE:
                            self.go_menu = 1
                            self.go_jeu = 0
                            self.compteur = 0
                            self.zone_vide = ["self.j1","self.j2","self.j3","self.j4","self.j5","self.j6","self.j7","self.j8","self.j9"]
                            self.zone_nonvide = []
                            self.vainq = None
                            self.valid = [[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]
                            self.nb = 0
                            Game()
                        if event.key == py.K_v:
                            Game.verif(self)
                    if event.type == py.MOUSEBUTTONDOWN:
                        for zone2 in self.zone_vide:
                            zone = eval(zone2)
                            if zone.collidepoint(event.pos):
                                if self.compteur%2 == 0:
                                    for i in range(0,len(self.fin)):
                                        if int(zone2[-1]) in self.fin[i]:
                                            self.valid[i][self.fin[i].index(int(zone2[-1]))] = 1
                                    self.screen.blit(self.p_symb2,(460,740,50,50))
                                    self.screen.blit(self.first_symb,zone)
                                    self.compteur += 1
                                    del self.zone_vide[self.zone_vide.index(zone2)]
                                    self.zone_nonvide.append(zone)
                                elif self.compteur%2 != 0:
                                    for i in range(0,len(self.fin)):
                                        if int(zone2[-1]) in self.fin[i]:
                                            self.valid[i][self.fin[i].index(int(zone2[-1]))] = 2
                                    self.screen.blit(self.p_symb1,(460,740,50,50))
                                    self.screen.blit(self.second_symb,zone)
                                    self.compteur += 1
                                    del self.zone_vide[self.zone_vide.index(zone2)]
                                    self.zone_nonvide.append(zone)
                        py.display.flip()
                        Game.verif(self)

                        
        py.display.quit()

    def verif(self):
        self.nb = 0
        for i in range(0,len(self.valid)):
            if self.valid[i] == [1,1,1]:
                self.vainq = self.first_symb
            elif self.valid[i] == [2,2,2]:
                self.vainq = self.second_symb
            if 0 in self.valid[i]:
                self.nb += 1
        if self.vainq != None:
            time.sleep(1)
            self.screen.blit(Game.VAINQUEUR,(0,0,800,800))
            self.screen.blit(self.vainq, (300,350,200,200))
            self.vainq = None
            self.go_fin = 1
            self.go_jeu = 0
            py.display.flip()
        elif self.nb == 0:
            time.sleep(1)
            self.go_fin = 1
            self.go_jeu = 0
            self.screen.blit(Game.EGALITE, (0,0,800,800))
            py.display.flip()
            

    def symb(self):
        self.go_menu = 0
        self.go_symb = 1
        self.screen.blit(Game.SYMB, Game.SYMB_T)
        self.screen.blit(Game.CROIX, self.symb_croix)
        self.screen.blit(Game.CERCLE, self.symb_cercle)
        py.display.flip()

    def jeu(self):
        self.go_jeu = 1
        self.go_symb = 0
        py.display.set_caption("Tic Tac Toe")
        self.screen.blit(Game.FOND, Game.FOND_T)
        self.screen.blit(Game.YOU, (300,750,155,35))
        py.display.flip()

    def regles(self):
        self.go_menu = 0
        self.go_reg = 1
        self.screen.blit(Game.REG, (0,0,800,800))
        py.display.flip()

        

prog = Game()
prog.event_menu()

