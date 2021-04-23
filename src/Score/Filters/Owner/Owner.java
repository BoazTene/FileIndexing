package Score.Filters.Owner;

import Score.Filters.ScoreFilter;

import java.io.IOException;
import java.nio.file.*;

/**
 * This class is a Score Filter, it will give the file a score bonus if the current user created it.
 *
 * @author Boaz Tene
 */
public class Owner implements ScoreFilter {
    @Override
    public int getScore(String path) throws IOException {
        if (getUser().equals(getFileOwner(Paths.get(path)))) return 30; else return 0;
    }

    private String getUser(){
        return System.getProperty("user.name");
    }

    private String getFileOwner(Path path) throws IOException {
        try {
            return Files.getOwner(path).getName().split("\\\\")[1];
        } catch (NoSuchFileException | AccessDeniedException e) {
          return "";
        } catch (Exception e) {
            return Files.getOwner(path).getName();
        }
    }
}
