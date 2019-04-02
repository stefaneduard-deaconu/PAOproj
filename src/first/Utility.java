package first;

import java.util.Arrays;
import java.util.Scanner;

public class Utility {
    // for now, it is more of a tester function
    // 10 queries or actions
    private String menu;
    private ActionHistory actionHistory;
    public Utility() {
        StringBuilder sb = new StringBuilder(); // prints the list of options
        sb.append(" 1. Add a new reader\n");
        sb.append(" 2. Delete a reader\n");
        sb.append(" 3. Show readers' id's\n");
        sb.append(" 4. How many readers\n");
        sb.append(" 5. How many unhelped readers\n");
        sb.append(" 6. Find\n");
        sb.append(" 7. Delete TypeWriters\n");
        sb.append(" 8. Best 3 writers\n");
        sb.append(" 9. Previous Action\n");
        sb.append("10. Show all actions\n");
        menu = sb.toString();
        //
        actionHistory = new ActionHistory();
    }
    public void start(Object []startingObjects) {
        System.out.println(menu);
        //
        String [] exit = new String[3];
        exit[0] = "exit";
        exit[1] = "quit";
        exit[2] = "stop";

        Boolean running = true;
        while(running) {
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            String cleanAnswer = answer.trim().toLowerCase();
            for(String entry : exit){
                if (answer.trim().toLowerCase().equals(entry))
                    running = false;
            }
            switch(cleanAnswer) {
                case "1":
                    System.out.println("Executing action " + cleanAnswer);
                    System.out.println("New Reader's name: ");
                    String name = input.nextLine().trim().toLowerCase();
                    for (int i = 0; i < 25; i++)
                        if (startingObjects[i] == null) {
                            startingObjects[i] = new Reader(name, new SpeedReader());
                            break;
                        }
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "2":
                    System.out.println("Executing action " + cleanAnswer);
                    System.out.println("Reader's name: ");
                    name = input.nextLine().trim().toLowerCase();
                    for (int i = 0; i < startingObjects.length; i++)
                        if (startingObjects[i] != null && startingObjects[i] instanceof Reader)
                            if (((Reader) startingObjects[i]).getName().trim().toLowerCase().equals(name))
                                startingObjects[i] = null; // here we should eliminate the SpeedReaders from the list? NO!
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "3":
                    System.out.println("Executing action " + cleanAnswer);
                    System.out.println("We have Writers with the assigned id's of: \n");
                    for (int i = 0; i < startingObjects.length; i++)
                        if (startingObjects[i] != null && startingObjects[i] instanceof Writer){
                            System.out.println(((Writer) startingObjects[i]).getId());
                            System.out.println(", from " + ((Writer) startingObjects[i]).getName());
                            System.out.println();
                        }
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "4" :
                    System.out.println("Executing action " + cleanAnswer);
                    Integer howMany;
                    howMany = 0;
                    for (int i = 0; i < startingObjects.length; i++)
                        if (startingObjects[i] != null && startingObjects[i] instanceof Reader)
                            howMany++;
                    System.out.println("  Inside our systems we have " + howMany + " enrolled writers.");
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "5" :
                    System.out.println("Executing action " + cleanAnswer);
                    howMany = 0;
                    for (int i = 0; i < startingObjects.length; i++)
                        if (startingObjects[i] != null &&
                                (startingObjects[i] instanceof Reader && ((Reader)startingObjects[i]).getMentor() == null))
                            howMany++;
                    System.out.println("  Inside our systems we have " + howMany + " unhelped readers.");
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "6" :
                    System.out.println("Executing action " + cleanAnswer);
                    for (int i = 0; i < startingObjects.length; i++)
                        if (startingObjects[i] != null)
                            if (startingObjects[i] instanceof Reader)
                                System.out.println(((Reader) startingObjects[i]).getName());
                            else
                                System.out.println(((Writer) startingObjects[i]).getName());
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "7" :
                    System.out.println("Executing action " + cleanAnswer);
                    for (int i = 0; i < startingObjects.length; i++)
                        if (startingObjects[i] != null && startingObjects[i] instanceof TypeWriter)
                            startingObjects[i] = null;
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "8" :
                    System.out.println("Executing action " + cleanAnswer);
                    Writer []writers;
                    writers = new Writer[25];
                    for(int i = 0; i < startingObjects.length; i++)
                        if (startingObjects[i] != null && startingObjects[i] instanceof Writer)
                            writers[writers.length] = (Writer) startingObjects[i];
                    Arrays.sort(writers);
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "9" :
                    System.out.println("Executing action " + cleanAnswer);
                    System.out.println("Previous action was " + actionHistory.previousAction());
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
                case "10" :
                    System.out.println("Executing action " + cleanAnswer); // replace with status function
                    actionHistory.show();
                    actionHistory.addAction("Action" + cleanAnswer);
                    break;
            }
        }
    }

    public static void main(String args[]) {
        // a list with 8 types of Objects:

        Object []array = new Object[20];
        //initialization
        array[0] = new TypeWriter();
        array[3] = new TypeWriter();
        array[5] = new TypeWriter();
        array[9] = new TypeWriter();
        array[2] = new Reader("Francis", new SpeedReader());
        array[1] = ((Reader)array[2]).getMentor();
        array[4] = new Reader("Desmond", new SpeedReader());
        array[6] = new Writer("Francis");
        ((Writer) array[6]).evaluate(9.71);
        array[7] = new Reader("Matthias", new SpeedReader());
        array[8] = new Writer("Desmond");
        ((Writer) array[8]).evaluate(9.70);
        array[10] = new Writer("Charles");
        ((Writer) array[10]).evaluate(9.72);
        array[11] = new Writer("Anthony");
        ((Writer) array[11]).evaluate(9.73);
        array[12] = new Writer("Richard");
        ((Writer) array[12]).evaluate(9.79);
        array[13] = new Writer("Matthias");
        ((Writer) array[13]).evaluate(4.71);
        array[14] = new Reader("Hopeless");
        //instantiate and "start" utility class
        Utility app = new Utility();
        //
        app.start(array);
    }
}
