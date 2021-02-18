package advisor;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        CompositeOutput output = new CompositeOutput();
        PrintWriter out = new PrintWriter("filename.txt");
        System.out.println("Provide access for the application by typing in: auth");
        Scanner scanner = new Scanner(System.in);
        boolean authorized = false;
        int limit = 20;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-page")) {
                limit = Integer.parseInt(args[i + 1]);
            }
        }
        while (true) {
            String input = scanner.nextLine();
            String command = input.contains(" ") ? input.substring(0, input.indexOf(" ")) : input;
            if ("auth".equals(command)) {
                if (!authorized) {
                    authorized = Controller.authorize();
                    System.out.println(authorized ? "Success!" : "Failed");
                    System.out.println("Options: new, featured, categories, playlists C_NAME, count, save, unit_test, exit:");
                } else {
                    System.out.println("Already authorized");
                }
                continue;
            }
            if ("exit".equals(command)) {
                out.println(output.returnOutput());
                System.out.println("---GOODBYE!---");
                out.close();
                return;
            }
            if (authorized) {
                switch (command) {
                    case "new":
                        var newReleases = Controller.getNewReleases(limit);
                        View.print(newReleases);
                        break;
                    case "featured":
                        var featured = Controller.getFeatured(limit);
                        View.print(featured);
                        break;
                    case "categories":
                        var categories = Controller.getCategories(limit);
                        View.print(categories);
                        break;
                    case "playlists":
                        String categoryName = input.substring(input.indexOf(" ") + 1);
                        var playlists = Controller.getPlaylists(categoryName, limit);
                        View.print(playlists);
                        break;
                    case "next":
                        var nextPage = Controller.getPage("next");
                        if (nextPage == null) {
                            System.out.println("All pages reached.");
                        } else {
                            View.print(nextPage);
                        }
                        break;
                    case "previous":
                        var previousPage = Controller.getPage("previous");
                        if (previousPage == null) {
                            System.out.println("All pages reached.");
                        } else {
                            View.print(previousPage);
                        }
                        break;
                    case "count": {
                        System.out.println("Counting songs in current output text file.");
                        Visitor visit = new Visitor();
                        output.accept(visit);
                        break;
                    }
                    case "unit_test": {
                        UnitTests.tests(limit);
                        break;
                    }
                    case "save": {
                        System.out.println("What type of songs do you want to save in a text file?");
                        System.out.println("Choose from new, featured, playlists C_NAME:");
                        boolean stopSaving = false;
                        command = scanner.nextLine();
                                if (command.equals("new")){
                                    String choice = "";
                                    SaveMusic m = new NewSave();
                                    System.out.println("Would you like to add a title, message or both to the output? Type title, message, both, or none.");
                                    choice = scanner.nextLine();
                                        if (choice.equals("title")){
                                            AddTitle t = new AddTitle(m);
                                            var newOutput = Controller.getNewReleases(limit);
                                            t.writeToText(newOutput);
                                            output.addToList(t);
                                            System.out.println("Saved with title!");
                                        }
                                        else if (choice.equals("message")) {
                                            AddMessage message = new AddMessage(m);
                                            var newOutput = Controller.getNewReleases(limit);
                                            message.writeToText(newOutput);
                                            output.addToList(message);
                                            System.out.println("Saved with message!");
                                        }
                                        else if (choice.equals("both")) {
                                            AddTitle t = new AddTitle(m);
                                            AddMessage message = new AddMessage(t);
                                            var newOutput = Controller.getNewReleases(limit);
                                            message.writeToText(newOutput);
                                            output.addToList(message);
                                            System.out.println("Saved both title and message!");
                                        }
                                        else if (choice.equals("none")) {
                                            var newOutput = Controller.getNewReleases(limit);
                                            m.writeToText(newOutput);
                                            output.addToList(m);
                                            System.out.println("Saved new releases!");
                                        }
                                        else {
                                            System.out.println("Wrong input, going back to normal choices.");
                                        }
                                        break;
                                    }
                                else if (command.equals("featured")) {
                                    String choice2 = "";
                                    SaveMusic n = new FeaturedSave();
                                    System.out.println("Would you like to add a title, message or both to the output? Type title, message, both, or none.");
                                    choice2 = scanner.nextLine();
                                        if (choice2.equals("title")){
                                            AddTitle t = new AddTitle(n);
                                            var newOutput = Controller.getFeatured(limit);
                                            t.writeToText(newOutput);
                                            output.addToList(t);
                                            System.out.println("Saved with title!");
                                        }
                                        else if (choice2.equals("message")){
                                            AddMessage message = new AddMessage(n);
                                            var newOutput = Controller.getFeatured(limit);
                                            message.writeToText(newOutput);
                                            output.addToList(message);
                                            System.out.println("Saved with message!");
                                        } else if (choice2.equals("both")) {
                                            AddTitle t = new AddTitle(n);
                                            AddMessage message = new AddMessage(t);
                                            var newOutput = Controller.getFeatured(limit);
                                            message.writeToText(newOutput);
                                            output.addToList(message);
                                            System.out.println("Saved both title and message!");
                                        }
                                        else if (choice2.equals("none")) {
                                            var featuredOutput = Controller.getFeatured(limit);
                                            n.writeToText(featuredOutput);
                                            output.addToList(n);
                                            System.out.println("Saved featured releases!");
                                        }
                                        else {
                                            System.out.println("Wrong input, going back to normal choices.");
                                            choice2 = scanner.nextLine();
                                        }
                                    break;
                                }
                                else {
                                    System.out.println("Booting back to normal choices!:");
                                }
                        }
                    default:
                        System.out.println("Type a command.");
                }
            } else {
                System.out.println("Type auth to get access to this app!");
            }
        }
    }
}
