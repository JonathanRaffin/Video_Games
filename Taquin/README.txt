#################### Projet réalisé par ####################

HILL Tom
RAFFIN Jonathan
TAUPIN Erwann

########## Execution via le script de compilation ##########

- Avant toute chose, avoir l'utilitaire ANT d'installé sur sa machine.

- Pour nettoyer les répertoires 'build' et 'dist' :
	$ ant clean

- Pour compiler les fichiers, et les placer dans le répertoire 'build' (dépend de clean) : 
	$ ant compile 

- Pour créer l'archive jar, et la placer dans le répertoire 'dist' (dépend de compile) :
	$ ant packaging

- Pour executer le Main (dépend de compile) :
	$ ant run (demandera les arguments lors de l'exécution du script)
	ou
	$ ant run -Dwidth_arg='my_argument' -Dheight_arg='my_argument' -Dinterface_arg='my_argument' -Dimages_arg='my_argument'

- Pour générer la javadoc, et la placer dans le répertoire 'doc' :
	$ ant javadoc

############### Execution via l'archive JAR ###############

java -jar dist/taquin.jar width_argument height_argument GUI_argument image_argument

	width_argument 	: pour la largeur du jeu (doit être un nombre entier)
					  facultatif si vous n'utilisez pas d'interface graphique (par défaut 3)

	height_argument : pour la hauteur du jeu (doit être un nombre entier)
					  facultatif si vous n'utilisez pas d'interface graphique (par défaut 3)

	GUI_argument 	: pour l'utilisation (true) ou non (false) d'une interface graphique (doit être un booléen)
					  facultatif (par défaut false)

	image_argument 	: pour l'utilisation (true) ou non (false) des images dans l'interface graphique (doit être un booléen)
					  facultatif (par défaut false) et inutile si vous n'utilisez pas d'interface graphique