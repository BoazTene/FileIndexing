package Score;

import Score.Filters.ScoreFilter;

import java.io.IOException;

/**
 * this class sets the score of each file in the data base
 * In the data base the tables will be organized score
 */
public class Score {
	private final String path;
	private final ScoreFilter[] scoreFilters;

	// constructor - gets the filter which the score is based on, and a file path
	public Score(ScoreFilter[] scoreFilters, String path) {
		this.path = path;
		this.scoreFilters = scoreFilters;
	}

	// returns the score in the given filter
	private int getFilterScore(ScoreFilter filter) throws IOException {
		return filter.getScore(this.path);
	}

	// this function calculate the score (with consideration in the calculated score from each filter) , and returns it
	public int getScore() throws IOException {
		int scoreAverage = 0;

		for (ScoreFilter filter : this.scoreFilters) {
			scoreAverage += getFilterScore(filter);
			System.out.println(scoreAverage);
		}

		scoreAverage /= this.scoreFilters.length;

		return scoreAverage;
	}
}
