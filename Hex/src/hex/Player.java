package hex;
import java.util.ArrayList;
/**
 * Classe permettant de représenter un joueur.
 */
public class Player {
        /**
         * Identifiant du joueur (sous la forme d'un entier)
         */
        private int id;
        /**
         * Liste des hexagones occupés par le joueur
         */
        private ArrayList<Integer> playerCase;
        /**
         * @param id : l'identifiant qui sera assigné au joueur
         */
        public Player(int id) {
                this.id = id;
                this.playerCase = new ArrayList<Integer>();
        }
        /**
         * Permet de récupérer l'identifiant du joueur.
         * 
         * @return l'identifiant du joueur
         */
        public int getId() {
                return this.id;
        }
        /**
         * Permet de récupérer la liste des hexagones occupés par le joueur
         * 
         * @return la liste des hexagones occupés par le joueur
         */
        public ArrayList<Integer> getPlayerCase() {
                return this.playerCase;
        }
        /**
         * Ajoute le numéro de l'hexagone donné en paramètre à la liste des hexagones occupés par le joueur
         * 
         * @param r : le numéro de l'hexagone
         */
        public void addPlayerCase(int r) {
                this.playerCase.add(r);
        }
        /**
         * Supprime le numéro de l'hexagone donné en paramètre à la liste des hexagones occupés par le joueur
         * 
         * @param r : le numéro de l'hexagone
         */
        public void removePlayerCase(int r) {
                this.playerCase.remove(r);
        }
        @Override
        public String toString() {
                return "joueur " + this.id;
        }
}