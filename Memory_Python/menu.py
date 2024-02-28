import pygame as py
import var
import varFacile as vf
import varMoyen as vm
import varDifficile as vd
import systeme as sys

class Menu():

    def __init__(self):
        self.screen = py.display.set_mode(var.dimensionBase)
        py.display.set_icon(var.logo)
        py.display.flip()
        self.men = 0
        self.mod = 0
        self.reg = 0
        self.facile = vf.posFacile
        self.moyen = vm.posMoyen
        self.difficile = vd.posDifficile
        self.regles = var.reglesZone

    def menu(self):
        self.men = 1
        self.screen.blit(var.menu, var.dimensionFenetre)
        py.display.set_caption("Menu")
        py.display.flip()

    def mode(self):
        self.men = 0
        self.reg = 0
        self.mod = 1
        self.screen.blit(var.mode, var.dimensionFenetre)
        py.display.set_caption("Choisissez votre mode de Jeu")
        py.display.flip()

    def règles(self):
        self.mod = 0
        self.reg = 1
        self.screen.blit(var.regles, var.dimensionFenetre)
        py.display.set_caption("Règles")
        py.display.flip()

    def events(self):
        continuer = True
        Menu.menu(self)
        while continuer:
            for event in py.event.get():
                if event.type == py.QUIT:
                    continuer = False
                if self.men == 1:
                    if event.type == py.KEYDOWN:
                        if event.key == py.K_ESCAPE:
                            continuer = False
                    if event.type == py.MOUSEBUTTONUP:
                        if var.dimensionFenetre.collidepoint(event.pos):
                            Menu.mode(self)
                elif self.mod == 1:
                    if event.type == py.KEYDOWN:
                        if event.key == py.K_ESCAPE:
                            Menu.menu(self)
                    if event.type == py.MOUSEBUTTONUP:
                        if self.facile.collidepoint(event.pos):
                            sys.Facile()
                        if self.moyen.collidepoint(event.pos):
                            sys.Moyen()
                        if self.difficile.collidepoint(event.pos):
                            sys.Difficile()
                        Menu.mode(self)
                        if self.regles.collidepoint(event.pos):
                            Menu.règles(self)
                    if event.type == py.MOUSEMOTION:
                        if self.facile.collidepoint(event.pos):
                            self.screen.blit(var.facile, vf.posFacile)
                        elif self.moyen.collidepoint(event.pos):
                            self.screen.blit(var.moyen, vm.posMoyen)
                        elif self.difficile.collidepoint(event.pos):
                            self.screen.blit(var.difficile, vd.posDifficile)
                        elif self.regles.collidepoint(event.pos):
                            self.screen.blit(var.imgReg, var.reglesZone)
                        else:
                            self.screen.blit(var.mode, var.dimensionFenetre)
                        py.display.flip()
                elif self.reg == 1:
                    if event.type == py.KEYDOWN:
                        if event.key == py.K_ESCAPE:
                            Menu.mode(self)
        py.quit()

memory = Menu()
memory.events()