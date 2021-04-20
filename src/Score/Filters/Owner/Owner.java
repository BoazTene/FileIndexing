package Score.Filters.Owner;

import Score.Filters.ScoreFilter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        } catch (NoSuchFileException e) {
          return "";
        } catch (Exception e) {
            return Files.getOwner(path).getName();
        }
    }
}
