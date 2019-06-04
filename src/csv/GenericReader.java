package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GenericReader<T> {
    private Boolean lineByLine = false;
    private String filename;

    FileReader freader;
    BufferedReader reader;

    public GenericReader(String filename) {
        // we instantiate a reade that would work line by line
        // but if we ever use getNextLine(), we disable reading all lines,
        //   because we keep that method to be used only for reading the entire file
        this.filename = filename;
        try {
            freader = new FileReader(filename);
            reader = new BufferedReader(freader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (reader != null)
                reader.close();

            if (freader != null)
                freader.close();
        } catch (IOException ex) {
            System.err.format("IOException: %s%n", ex);
        }
    }

    public List<String> getNextLine() {
        lineByLine = true;
        List<String> line = new LinkedList<>();



        return line;
    }

    public List<List<String>> getAllLines() {
        if (lineByLine == true) {
            System.out.println("You already started reading this file line-by-line! You can't read the remainder all at once.");
            return Collections.emptyList(); // we return an empty list, even if it would be better to raise an error
        }
        else {
            List<String> lines = Collections.emptyList();
            try {
                lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<List<String>> returnList = new LinkedList<>();
            for (String line : lines) {
                List<String> keptLine = new LinkedList<>();
                keptLine.addAll(Arrays.asList(line.split(",")));
                returnList.add(keptLine);
            }
            return returnList;
        }
    }

    public static void main(String[] args) {
        GenericReader<String> reader = new GenericReader<>("users.csv");
        List<List<String>> readCSV = reader.getAllLines();
        //
        System.out.println(readCSV);
        //
        System.out.println(readCSV.get(0).get(0) instanceof String);
        //
        GenericWriter writer = new GenericWriter<>("users2.csv", true);
        writer.writeRow(readCSV.get(0));
    }
}
