package Score;

import Score.Filters.ScoreFilter;

import java.io.IOException;

/**
 * @author Boaz Tene
 *
 */
public class Score {
	private final String path;
	private final ScoreFilter[] scoreFilters;

	public Score(ScoreFilter[] scoreFilters, String path) {
		this.path = path;
		this.scoreFilters = scoreFilters;
	}

	private int getFilterScore(ScoreFilter filter) throws IOException {
		return filter.getScore(this.path);
	}

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
