package Score.Filters.LastModified;

import Score.Filters.ScoreFilter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class LastModified implements ScoreFilter {
    private double getLastModified(Path path) {
        return new BigDecimal(new Date().getTime() - path.toFile().lastModified())
                .divide(new BigDecimal(2629743833L), MathContext.DECIMAL128).doubleValue();
    }

    @Override
    public int getScore(String path) {
        return (int) Math.round(new Function().F(getLastModified(Paths.get(path))));
    }
}
