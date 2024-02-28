#################### Projet réalisé par ####################

  DELAMARE Clement
  GOUBARD Paul
  RAFFIN Jonathan
  TAUPIN Erwann


############################################################
########### Utilisation du script de compilation ###########
############################################################

  - Avant toute chose, avoir l'utilitaire ANT d'installé sur sa machine.

  - Pour nettoyer les répertoires 'build' et 'dist' :
	    $ ant clean

  - Pour compiler les fichiers, et les placer dans le répertoire 'bin' (dépend de clean) : 
	    $ ant compile 

  - Pour créer une archive jar, et la placer dans le répertoire 'dist' (dépend de compile) :
	    $ ant dist

  - Pour executer l'application (dépend de compile) :
	    $ ant run
	    ou
	    $ ant (car l'action par défaut est l'exécution de l'application)

  - Pour générer la javadoc, et la placer dans le répertoire 'doc' :
	    $ ant javadoc

