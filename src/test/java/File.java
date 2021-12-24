import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FileConfiguration {
    public void FileInit() throws IOException {
        File config = new File("config.txt");
        if(!config.exists())config.createNewFile();
        FileWriter writer = new FileWriter(config.getName(),true);

    }

}
