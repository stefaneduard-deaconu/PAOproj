package first;

import java.util.Scanner;

public class Utility {
    // for now, it is more of a tester function
    // 10 queries or actions
    private String menu;
    public Utility() {
        StringBuilder sb = new StringBuilder(); // prints the list of options
        sb.append(" 1. Add a new reader\n");
        sb.append(" 2. Delete a reader\n");
        sb.append(" 3. Merge two readers\n");
        sb.append(" 4. How many readers\n");
        sb.append(" 5. How many unhelped readers\n");
        sb.append(" 6. Find\n");
        sb.append(" 7. Delete TypeWriters\n");
        sb.append(" 8. Best 3 writers\n");
        sb.append(" 9. Previous Action\n");
        sb.append("10. close program (different to exit/quit/stop)\n");
        menu = sb.toString();
    }
    public void start(Object startingObjects) {
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
            switch(cleanAnswer)
            {
                case "1" :
                    System.out.println("Executing action " + cleanAnswer);
                break;
                case "2" :
                    System.out.println("Executing action " + cleanAnswer);
                    break;
                case "3" :
                    System.out.println("Executing action " + cleanAnswer);
                    break;
                case "4" :
                    System.out.println("Executing action " + cleanAnswer);
                    break;
                case "5" :
                    System.out.println("Executing action " + cleanAnswer);
                    break;
                case "6" :
                    System.out.println("Executing action " + cleanAnswer);
                    break;
                case "7" :
                    System.out.println("Executing action " + cleanAnswer);
                    break;
                case "8" :
                    System.out.println("Executing action " + cleanAnswer);
                    break;
                case "9" :
                    System.out.println("Executing action " + cleanAnswer);
                    break;
                case "10" :
                    System.out.println("Executing action " + cleanAnswer); // replace with status function
                    break;
            }
        }
    }

    // to do:
    // Inserare, stergere, sortare... Si cam aici se termina ideile mele
    public static void main(String args[]) {
        // a list with 8 types of Objects:

        Object []array = new Object[20];
        Utility app = new Utility();
        app.start(array);
    }
}
