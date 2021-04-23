package Score;

import Score.Filters.ScoreFilter;

import java.io.IOException;

/**

 * This class is the main score class.
 *
 * The class gets a path to a file and an array of filters and returns the files score.
 *

 */
public class Score {
	private final String path;
	private final ScoreFilter[] scoreFilters;

	/**
	 *  constructor - gets the filter which the score is based on, and a file path
	 *
	 * @param scoreFilters
	 * @param path
	 */
	public Score(ScoreFilter[] scoreFilters, String path) {
		this.path = path;
		this.scoreFilters = scoreFilters;
	}

	/**
	 * returns the score in the given filter
	 *
	 * @param filter
	 * @return
	 * @throws IOException
	 */
	private int getFilterScore(ScoreFilter filter) throws IOException {
		return filter.getScore(this.path);
	}

	/**
	 * this function calculate the score (with consideration in the calculated score from each filter) , and returns it
	 *
	 * @return
	 * @throws IOException
	 */
	public int getScore() throws IOException {
		int scoreAverage = 0;

		for (ScoreFilter filter : this.scoreFilters) {
			scoreAverage += getFilterScore(filter);
		}

		scoreAverage /= this.scoreFilters.length;

		return scoreAverage;
	}
}
