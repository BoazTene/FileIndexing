package Score.Filters;

import java.io.IOException;
// an interface for score filter
public interface ScoreFilter {
	// this abstract function gets path and return the score in the specified filter
    int getScore(String path) throws IOException;
}
