package player;

import util.*;

import java.util.ArrayList;
import java.util.HashMap;

import hex.Game;
import hex.Hex;

public class Bot extends Player {
	int budget;
	
	public Bot(int id, int budget) {
		super(id);
		this.budget = budget;
	}
	
	@Override
	public Pair play(Game game) {
		Hex h = game.getHex();
		ArrayList<Pair> possibilities = h.getCaseOfValue(0);
		Pair r = new Pair(0,0);
		if(possibilities.size()==h.getGridSize()*h.getGridSize()-1 && game.getCurrentPlayer().getId()==2) {
			Pair p = h.getCaseOfValue(1).get(0);
			if(getGreatest(h).contains(p)) {
				return p;
			}
		}
		if(possibilities.size()>0) {
			r = getBestCase(possibilities, new Game(game), this.budget);
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
			Hex testHex = testGame.getHex();
			if(testGame.getWinner()!=null) {
				if(testGame.getWinner().getId()==game.getCurrentPlayer().getId()) {
					for(Pair p : testHex.getCaseOfValue(game.getCurrentPlayer().getId())) {
						for(Pair p2 : hm.keySet()) {
							if(p2.equals(p)) {
								hm.get(p2).addSucce();
							}
						}
					}
				} else {
					for(Pair p : testHex.getCaseOfValue(game.getCurrentPlayer().getId())) {
						for(Pair p2 : hm.keySet()) {
							if(p2.equals(p)) {
								hm.get(p2).addDefaite();
							}
						}
					}
				}
			}else {
				for(Pair p : testHex.getCaseOfValue(game.getCurrentPlayer().getId())) {
					for(Pair p2 : hm.keySet()) {
						if(p2.equals(p)) {
							hm.get(p2).addEssai();
						}
					}
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
