package csv;

import model.User;
import model.checker.FindEnd;
import model.machine.Reader;
import model.machine.Writer;
import model.paper.Paper;
import model.paper.Papermark;
import model.paper.PertainsTo;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
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
    public GenericWriter(String fileName, Boolean replaceFile) {
        try {
            fwriter = new FileWriter(fileName);
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
                if (list.indexOf(elem) < list.size() - 1)
                    writer.write(",");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
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

    // This main generates a example of csv's for each file, by instantiating objects and converting them to List<List<Strin>> and then writing them down
    //   with the use of the generic function from the generic class GenericWriter
    public static void main(String []args) {

        User[] users = {
                new User(1, 1, 3, "user1", "password2413"),
                new User(10, 2, 2, "user2", "passedtheepassword"),
                new User(11, 3, 1, "user4", "gradeyourself")
        };
        Paper[] papers = {
                new Paper(1, "paragraph 7: asdf sd fu u dh fs d fdfsdf rui jfgh sujd  ch fksie n kadsjf nncvsy ej jr"),
                new Paper(5, "paragraph 7: asdf sd fu u dh fs d fdfsdf rui jfgh sujd  ch fksie n kadsjf nncvsy ej jr hasjldfhasd fjasdf hsuidf fej enf skd;f ajsdf")
        };
        Reader[] readers = {
                new Reader(1, 1, 5),
                new Reader(2, 10, 5),
                new Reader(3, 11, 1),
        };
        Writer[] writers = {
                new Writer(1, 11, 5, 1),
                new Writer(2, 10, 1, 2),
                new Writer(3, 1, 5, 3),
        };
        FindEnd[] finders = {// this is, now that I think about it, an oracle
                new FindEnd(1,1, 10),
                new FindEnd(2,2, 13),
                new FindEnd(3,3, 30)
        };
        Papermark[] papermarks = {
                new Papermark(1, "annotation 1"),
                new Papermark(2, "annotation 2"),
                new Papermark(3, "annotation 3"),
                new Papermark(4, "annotation 4"),
                new Papermark(5, "annotation 5"),
                new Papermark(6, "annotation 6"),
                new Papermark(7, "annotation 7")
        };
        PertainsTo.addRelation(1, 1);
        PertainsTo.addRelation(2, 11);
        PertainsTo.addRelation(3, 10);
        PertainsTo.addRelation(4, 1);
        PertainsTo.addRelation(5, 11);
        PertainsTo.addRelation(6, 1);
        PertainsTo.addRelation(7, 10);
        // add any Users' attributes to a List<List<String>>
        List<List<String>> usersList = new LinkedList<>();
        Arrays.stream(users).forEach(
                (user) -> {
                    List<String> list = new LinkedList<>();
                    list.add(user.getUserId().toString());
                    list.add(user.getReaderId().toString());
                    list.add(user.getWriterId().toString());
                    list.add(user.getName());
                    list.add(user.getPass());
                    usersList.add(list);
                }
        );
        // add any Papers' attributes to a List<List<String>>
        List<List<String>> papersList = new LinkedList<>();
        Arrays.stream(papers).forEach(
                (paper) -> {
                    List<String> list = new LinkedList<>();
                    list.add(paper.getPaperId().toString());
                    list.add(paper.getText());
                    papersList.add(list);
                }
        );
        // add any Readers' attributes to a List<List<String>>
        List<List<String>> readersList = new LinkedList<>();
        Arrays.stream(readers).forEach(
                (reader) -> {
                    List<String> list = new LinkedList<>();
                    list.add(reader.getReaderId().toString());
                    list.add(reader.getUserId().toString());
                    list.add(reader.getPaperId().toString());
                    readersList.add(list);
                }
        );
        // add any Writers' attributes to a List<List<String>>
        List<List<String>> writersList = new LinkedList<>();
        Arrays.stream(writers).forEach(
                (writer) -> {
                    List<String> list = new LinkedList<>();
                    list.add(writer.getWriterId().toString());
                    list.add(writer.getUserId().toString());
                    list.add(writer.getPaperId().toString());
                    list.add(writer.getFindEndId().toString());
                    writersList.add(list);
                }
        );
        // add any Finders' attributes to a List<List<String>>
        List<List<String>> findersList = new LinkedList<>();
        Arrays.stream(finders).forEach(
                (finder) -> {
                    List<String> list = new LinkedList<>();
                    list.add(finder.getFindEndId().toString());
                    list.add(finder.getWriterId().toString());
                    list.add(finder.getEnd().toString());
                    findersList.add(list);
                }
        );
        // add any Papermarks' attributes to a List<List<String>>
        List<List<String>> papermarksList = new LinkedList<>();
        Arrays.stream(papermarks).forEach(
                (papermark) -> {
                    List<String> list = new LinkedList<>();
                    list.add(papermark.getPapermarkId().toString());
                    list.add(papermark.getMark().toString());
                    papermarksList.add(list);
                }
        );
        // add any PertainsTo' attricutes to a List<List<String>>
        List<List<String>>relationsList = new LinkedList<>();
        PertainsTo.getRelations().forEach(
                (papermarkId, userId) -> relationsList.add(
                        Arrays.asList(
                                papermarkId.toString(),
                                userId.toString()
                        )
                )

        );
        // if we read some users before starting, we mey get index conflicts
        // so we presume the 1
        // variant of only reading from a file all the users, or any entities, we're gonna use


        // Instantiate writer:
        GenericWriter writer = new GenericWriter("users.csv", true);
        // write the file
        writer.writeRows(usersList);
        writer.close();
        //
        // Instantiate writer:
        writer = new GenericWriter("papers.csv", true);
        // write the file
        writer.writeRows(papersList);
        writer.close();
        //
        // Instantiate writer:
        writer = new GenericWriter("readers.csv", true);
        // write the file
        writer.writeRows(readersList);
        writer.close();
        //
        // Instantiate writer:
        writer = new GenericWriter("writers.csv", true);
        // write the file
        writer.writeRows(writersList);
        writer.close();
        //
        // Instantiate writer:
        writer = new GenericWriter("finders.csv", true);
        // write the file
        writer.writeRows(findersList);
        writer.close();
        //
        // Instantiate writer:
        writer = new GenericWriter("papermarks.csv", true);
        // write the file
        writer.writeRows(papermarksList);
        writer.close();
        //
        // Instantiate writer:
        writer = new GenericWriter("relations.csv", true);
        // write the file
        writer.writeRows(relationsList);
        writer.close();
        //

    }
}
