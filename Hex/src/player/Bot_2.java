package player;

import java.util.ArrayList;
import java.util.HashMap;

import hex.*;
import util.*;

public class Bot_2 extends Player {

	int budget;
	Pair pair;
	boolean toTake = true;
	
	public Bot_2(int id, int budget) {
		super(id);
		this.budget = budget;
	}
	
	@Override
	public Pair play(Game game) {
		Hex h = game.getHex();
		game.displayGrid();
		ArrayList<Pair> possibilities = h.getCaseOfValue(0);
		Pair r = new Pair(0,0);
		if(possibilities.size()==h.getGridSize()*h.getGridSize()-1 && game.getCurrentPlayer().getId()==2) {
			Pair p = h.getCaseOfValue(1).get(0);
			if(getGreatest(h).contains(p)) {
				return p;
			}
		}
		if(possibilities.size()>0) {
			r = getBestCase(possibilities,new Game(game),budget);
		}
		return r;
	}

	private ArrayList<Pair> getGreatest(Hex h) {
		ArrayList<Pair> toReturn = new ArrayList<Pair>();
		for(int i = h.getGridSize()/3; i < h.getGridSize()-h.getGridSize()/3; i++)
			for(int j = h.getGridSize()/3; j < h.getGridSize()-h.getGridSize()/3; j++)
				toReturn.add(new Pair(i,j));
		return toReturn;
	}

	private Pair getBestCase(ArrayList<Pair> possibilities, Game game, int cost) {
		HashMap<Pair,WinData> hm = new HashMap<Pair, WinData>();
		for(Pair p : possibilities) {
			hm.put(p, new WinData());
		}

		for(int i = 0; i<cost;i++) {
			Game testGame = playUntilWin(new Game(game));
			if(testGame.getWinner()!=null) {
				if(testGame.getWinner().getId() == game.getCurrentPlayer().getId()) {												
						hm.get(this.pair).addSucce();			
				} else {
					hm.get(this.pair).addDefaite();			
				}
			}
			
				
		}
		double bestRatio = -1.00;
		Pair bestPair = new Pair(0,0);
		for(Pair p : hm.keySet()) {
			if(hm.get(p).getValue()>bestRatio) {
				bestRatio = hm.get(p).getValue();
				bestPair = p;
			}
		}
		return bestPair;
	}

	public Game playUntilWin(Game game) {
		Hex hex = game.getHex();
		this.toTake = true;
		ArrayList<Pair> possibilities = hex.getCaseOfValue(0);
		while(game.getWinner()==null && possibilities.size()>0) {
			Pair r = possibilities.get((int) (Math.random()*possibilities.size()));
			possibilities.remove(r);
			game.select(r.getP1(),r.getP2());
			if(toTake) {
				toTake = !toTake;
				this.pair = r;
			}
			game.nextPlayer();

		}
		return game;
	}

}
