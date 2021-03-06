package seedu.ui;

import seedu.exception.WhereGotTimeException;
import seedu.task.Event;
import seedu.user.User;
import seedu.user.UserList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Prints relevant messages to user based on user interaction.
 */
public class Ui {
    private static final String MESSAGE_LINE = "____________________________________________________________";
    private static final String MESSAGE_SAD_FACE = ":(";
    private static final String MESSAGE_LOGO = " _    _ _                   _____       _ _____ _                \n"
            + "| |  | | |                 |  __ \\     | |_   _(_)               \n"
            + "| |  | | |__   ___ _ __ ___| |  \\/ ___ | |_| |  _ _ __ ___   ___ \n"
            + "| |/\\| | '_ \\ / _ \\ '__/ _ \\ | __ / _ \\| __| | | | '_ ` _ \\ / _ \\\n"
            + "\\  /\\  / | | |  __/ | |  __/ |_\\ \\ (_) | |_| | | | | | | | |  __/\n"
            + " \\/  \\/|_| |_|\\___|_|  \\___|\\____/\\___/ \\__\\_/ |_|_| |_| |_|\\___|\n"
            + "                                                                 \n";
    private static final String MESSAGE_GREETINGS = "\n" + MESSAGE_LOGO + "Hello! Welcome to WhereGotTime, a program "
            + "that helps you and \nyour friend find common unoccupied slots in the timetable!"
            + "\n\nYou're currently not logged in."
            + "\n\nTip: type 'help' for a list of commands.\n";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a horizontal divider line.
     */
    public void showLine() {
        System.out.println(MESSAGE_LINE);
    }

    /**
     * Reads user inputs.
     *
     * @return String the inputs provided by users, where leading and trailing spaces are trimmed.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println(MESSAGE_LINE + MESSAGE_GREETINGS + MESSAGE_LINE);
    }

    /**
     * Prints out all tasks saved in the array list.
     *
     * @param users the array list to print.
     * @param userIndex the index of the current user
     * @param day the day in the timetable to print
     */
    public void printList(UserList users, int userIndex, String day) throws WhereGotTimeException {
        ArrayList<Event> timetable = (users.getUser(userIndex).getTimetable()).getTimetable(day);
        if (!timetable.isEmpty()) {
            int count = 1;
            System.out.println("Here are the classes in your timetable for " + day
                    + ", sorted according to time:");
            for (Object c : timetable) {
                System.out.println(count + ". " + c);
                count++;
            }
        } else {
            System.out.println("There is no class in your timetable for " + day + "!");
        }
    }

    /**
     * Prints out the event task given by the user.
     *
     * @param event the task to be added to the array list.
     */
    public void printEvent(Event event, String date) {
        System.out.println("Got it! I've added the following event on " + date + "\n" + event);
        //System.out.println("Now now have " + taskList.getTotalTaskCount() + " tasks in the list.");
    }


    /**
     * Prints out the task that has been deleted.
     *
     * @param deletedClass the class that has been deleted.
     * @param day          the day on which the task that has been deleted is on.
     * @param size         the number of classes left on that day.
     */
    public void printDelete(String deletedClass, String day, int size) {
        System.out.println("Noted. I have removed this class from your timetable:\n" + deletedClass);
        System.out.println("Now you have " + size + " class(es) for " + day + " in the timetable.");
    }

    /**
     * Prints out the day of the timetable that has been cleared.
     *
     * @param timetable the timetable of the day to be cleared.
     */
    public void printClear(ArrayList<Object> timetable, String day) {
        int count = 1;
        int size = timetable.size();
        if (size == 1) {
            System.out.println("Noted. I have removed this class from your " + day + " timetable:");
        } else {
            System.out.println("Noted. I have removed these classes from your " + day + " timetable:");
        }
        for (Object c : timetable) {
            System.out.println(count + ". " + c);
            count++;
        }
        System.out.println("Your " + day + " timetable has been cleared.");
    }

    /**
     * Prints all result(s) that matches the search keyword.
     *
     * @param classesFound string of classes that contains the keyword.
     * @param keyword  the word that the user searches.
     */
    public void printFind(String classesFound, String keyword) {
        if (!classesFound.equals("")) {
            System.out.print("Here are the class(es) in your timetable "
                    + "that matches the keyword \"" + keyword + "\":");
            System.out.println(classesFound);
        } else {
            System.out.println("None of your classes contain the keyword \"" + keyword + "\".");
        }
    }

    public void printHelp(boolean isLoggedIn, User nowUser) {
        if (isLoggedIn) {
            System.out.print("Hi " + nowUser.getName() + ".");
        } else {
            System.out.print("You're currently not logged in.");
        }
        System.out.println("\nHere are the list of commands for WhereGotTime.");

        System.out.println("\t1. Login command\t: login /(username) /(6-digit password)");
        System.out.println("\t2. Add command\t\t: add /(module name with optional descriptions) /(day) "
                + "/(startTime-endTime) /(location)");
        System.out.println("\t3. List command\t\t: list /all OR list /(day)");
        System.out.println("\t4. Edit command\t\t: edit /(day) /(index) /(startTime-endTime)");
        System.out.println("\t5. Delete command\t: delete /(day) /(index)");
        System.out.println("\t6. Clear command\t: clear /(day)");
        System.out.println("\t7. Find command\t\t: find /(keyword)");
        System.out.println("\t8. Compare command\t: compare");
        System.out.println("\nNote:");
        System.out.println("- command and 'day' are not case sensitive, but username and password are.");
        System.out.println("- startTime and endTime should be in 24-hour format (e.g. 0000-2359).");
        System.out.println("- 'day' should be 3-letter, e.g. Mon, TUE, wed, etc.");


    }

    public void printEdit(String[] editedField, String date, int index) throws WhereGotTimeException {
        System.out.println("Got it! I have edited " + date + "'s #" + index + " lesson to "
                + "the following timing: " + editedField[0] + "-" + editedField[1]);
    }

    /**
     * Prints farewell message.
     */
    public void showBye() {
        System.out.println("Thanks for using WhereGotTime. Hope to see you again soon!");
    }

    public void greetUser(User currentUser) {
        System.out.println("Hello " + currentUser.getName() + "!");
    }

    public void greetReturningUser(User currentUser) {
        System.out.println("Hello again " + currentUser.getName() + "!");
    }

    /**
     * Prints error message.
     *
     * @param message the error message to be printed.
     */
    public void showError(String message) {
        System.out.println(MESSAGE_SAD_FACE + " OOPS!!! " + message);
    }

    public void printCompare(ArrayList<Integer> outputArray) {
        ArrayList<Integer> fullArray = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));

        int checkPoint = 0;

        System.out.println("Your common timeslots are: \n");
        for (int availableTime : fullArray) {
            if ((availableTime >= 1) && (!(outputArray.contains(availableTime - 1)))
                    && (!(outputArray.contains(availableTime)))) {
                checkPoint++;
                continue;
            }
            if (!(outputArray.contains(availableTime))) {
                int tempMinusOne = availableTime - 1;
                if (checkPoint < 10) {
                    if (tempMinusOne < 10) {
                        System.out.println("0" + checkPoint + "00HR to 0" + tempMinusOne + "59HR");
                    } else {
                        System.out.println("0" + checkPoint + "00HR to " + tempMinusOne + "59HR");
                    }
                } else {
                    if (tempMinusOne < 10) {
                        System.out.println(checkPoint + "00HR to 0" + tempMinusOne + "59HR");
                    } else {
                        System.out.println(checkPoint + "00HR to " + tempMinusOne + "59HR");
                    }
                }
                if (availableTime != 23) {
                    checkPoint = availableTime + 1;
                }
            }
        }
        if (checkPoint != 23) {
            if (checkPoint < 10) {
                System.out.println("0" + checkPoint + "00HR to " + "2359HR");
            } else {
                System.out.println(checkPoint + "00HR to " + "2359HR");
            }
        }
    }
}
