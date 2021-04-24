package Score.Filters;

import java.io.IOException;

/**
 * This is an interface that represents ScoreFilter.
 *
 * @author Boaz Tene
 */
public interface ScoreFilter {

    /**
     * This method gets a String path of a file and returns a score for the file.
     *
     * @param path
     * @return
     * @throws IOException
     */
    int getScore(String path) throws IOException;
}
