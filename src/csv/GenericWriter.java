package csv;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.List;

public class GenericWriter<T> {
    private BufferedWriter writer;
    private FileWriter fwriter;

    public GenericWriter(String fileName) {
        try {
            fwriter = new FileWriter(fileName, true);
            writer = new BufferedWriter(fwriter);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public void writeRow(List<T> list) {
        for (T elem : list) {
            try {
                writer.write(elem.toString()); // we inquire the user of this generic class to implement toString
                // to each part of the lists that get transformed into the rows of the output file
                if (list.indexOf(elem) < list.size())
                    writer.write(",");
                else
                    writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeRows(List<List<T>> lists) {
        for (List<T> list : lists) {
            writeRow(list);
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
