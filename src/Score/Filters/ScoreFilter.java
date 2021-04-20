package Score.Filters;

import java.io.IOException;

public interface ScoreFilter {
    int getScore(String path) throws IOException;
}
