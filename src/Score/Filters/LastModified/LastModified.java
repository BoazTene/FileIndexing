package Score.Filters.LastModified;

import Score.Filters.ScoreFilter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


/**
 * This Class is a Score Filter, it will give a score by the last modified variable.
 *
 * 
 */
public class LastModified implements ScoreFilter {
    /**
     * this function returns the last time the file was modified
     *
     * @param path
     * @return
     */
    private double getLastModified(Path path) {
        return new BigDecimal(new Date().getTime() - path.toFile().lastModified())
                .divide(new BigDecimal(2629743833L), MathContext.DECIMAL128).doubleValue();
    }

    /**
     * this function gets the score by the last time the file was modified
     *
     * @param path
     * @return
     */
    @Override
    public int getScore(String path) {
        return (int) Math.round(LastModifiedFunction.F(getLastModified(Paths.get(path))));
    }
}
