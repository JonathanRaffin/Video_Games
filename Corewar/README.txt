#################### Projet réalisé par ####################

ELAGGOUN Aref
HILL Tom
RAFFIN Jonathan
TAUPIN Erwann

############################################################
########### Utilisation du script de compilation ###########
############################################################
########### Pas nécessaire car tous les fichiers ###########
###########        sont déjà présents 			 ###########
############################################################

- Avant toute chose, avoir l'utilitaire ANT d'installé sur sa machine.

- Pour nettoyer les répertoires 'build' et 'jar' :
	$ ant clean

- Pour compiler les fichiers, et les placer dans le répertoire 'build' (dépend de clean) : 
	$ ant compile 

- Pour créer les archives jar, et les placer dans le répertoire 'jar' (dépend de compile) :
	$ ant packaging

- Pour executer le CoreWar (dépend de compile) :
	$ ant run-corewar
	ou
	$ ant (car l'action par défaut est l'exécution du CoreWar)

- Pour executer la génération de Warriors (dépend de compile) :
	$ ant run-generation

- Pour générer la javadoc, et la placer dans le répertoire 'doc' :
	$ ant javadoc

###########################################################
############### Execution via l'archive JAR ###############
###########################################################
###############     Méthode recommandée     ###############
###########################################################

- Pour executer le CoreWar :
	$ java -jar jar/corewar.jar

- Pour executer la génération de Warriors :
	$ java -jar jar/generation.jar
