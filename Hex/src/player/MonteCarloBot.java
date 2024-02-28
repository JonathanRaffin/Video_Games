package player;

import java.util.ArrayList;

import hex.Game;
import hex.Hex;
import util.*;

public class MonteCarloBot extends Player{
	private int budget;

	public MonteCarloBot(int id, int budget) {
		super(id);
		this.budget = budget;
	}
	
	public Pair play(Game g) {
		Hex h = g.getHex();
		ArrayList<Pair> coupsPossibles = h.getCaseOfValue(0);
		Pair bestPair = coupsPossibles.get(0);
		int bestRatio = 0-budget;
		for(Pair p : coupsPossibles) {
			Game ephemereGame = new Game(g);
			ephemereGame.play(p.getP1(), p.getP2());
			int value = simulateForPair(ephemereGame);
			if(value>bestRatio) {
				bestPair = p;
				bestRatio = value;
			}
		}
		return bestPair;
	}

	private int simulateForPair(Game game) {
		int value = 0;
		for(int i = 0; i<budget;i++) {
			Game g = new Game(game);
			g = playUntilWin(g);
			if(g.getWinner()!=null) {
				if(g.getWinner().getId()==this.getId()) {
					value+=1;
				}else {
					value-=1;
				}
			}
			
		}
		return value;
	}

	public Game playUntilWin(Game game) {
		Hex hex = game.getHex();
		ArrayList<Pair> possibilities = hex.getCaseOfValue(0);
		int winner = 0;
		while(winner < 1 && possibilities.size()>0) {
			Pair r = possibilities.get((int) (Math.random()*possibilities.size()));
			possibilities.remove(r);
			winner = game.play(r.getP1(),r.getP2());
		}
		return game;
	}
		
}
	

