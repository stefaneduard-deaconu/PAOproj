import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Logger {
    private BufferedWriter writer;
    private FileWriter fwriter;

    public Logger(String fileName) {
        try {
            fwriter = new FileWriter(fileName, true);
            writer = new BufferedWriter(fwriter);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    public Logger(String fileName, Boolean replaceFile) {
        try {
            fwriter = new FileWriter(fileName);
            writer = new BufferedWriter(fwriter);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public void writeLog(String action, String typeOfObject) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        try {
            writer.write(
              timestamp.toString() + "," + action + "," + typeOfObject + "\n"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void close() {
        try {
            if (writer != null)
                writer.close();

            if (fwriter != null)
                fwriter.close();
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }
    }
}
