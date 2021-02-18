package advisor;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitTests {
    public static void tests(int limit) throws IOException, InterruptedException {
        String test = "";
        CompositeOutput output = new CompositeOutput();
        System.out.println("------COMPOSITE TEST #1: Adding new songs to list------");
        SaveMusic m = new NewSave();
        var newOutput = Controller.getNewReleases(limit);
        m.writeToText(newOutput);
        output.addToList(m);
        test = output.returnOutput();
        System.out.println(test);

        System.out.println("------COMPOSITE TEST #2: Adding featured songs to list------");
        CompositeOutput output2 = new CompositeOutput();
        SaveMusic n = new FeaturedSave();
        var newOutput2 = Controller.getFeatured(limit);
        n.writeToText(newOutput2);
        output2.addToList(n);
        test = output2.returnOutput();
        System.out.println(test);

        System.out.println("------COMPOSITE && DECORATOR TEST #3: Adding new songs w/title to list------");
        CompositeOutput output3 = new CompositeOutput();
        SaveMusic m1 = new NewSave();
        AddTitle title = new AddTitle(m1);
        var newOutput3 = Controller.getNewReleases(limit);
        title.writeToText(newOutput3);
        output3.addToList(title);
        test = output3.returnOutput();
        System.out.println(test);

        System.out.println("------COMPOSITE && DECORATOR TEST #4: Adding new songs w/message to list------");
        CompositeOutput output4 = new CompositeOutput();
        SaveMusic m2 = new NewSave();
        AddMessage title2 = new AddMessage(m2);
        var newOutput4 = Controller.getNewReleases(limit);
        title2.writeToText(newOutput4);
        output4.addToList(title2);
        test = output4.returnOutput();
        System.out.println(test);

        System.out.println("------COMPOSITE && DECORATOR TEST #5: Adding featured songs w/title to list------");
        CompositeOutput output5 = new CompositeOutput();
        SaveMusic n2 = new FeaturedSave();
        AddTitle titleF = new AddTitle(n2);
        var newOutput5 = Controller.getFeatured(limit);
        titleF.writeToText(newOutput5);
        output5.addToList(titleF);
        test = output5.returnOutput();
        System.out.println(test);

        System.out.println("------COMPOSITE && DECORATOR TEST #6: Adding featured songs w/messages to list------");
        CompositeOutput output6 = new CompositeOutput();
        SaveMusic n3 = new FeaturedSave();
        AddMessage titleM = new AddMessage(n3);
        var newOutput6 = Controller.getFeatured(limit);
        titleM.writeToText(newOutput6);
        output6.addToList(titleM);
        test = output6.returnOutput();
        System.out.println(test);

        System.out.println("------COMPOSITE && DECORATOR TEST #7: Adding new and featured songs------");
        CompositeOutput output7 = new CompositeOutput();
        SaveMusic neww = new NewSave();
        AddTitle t = new AddTitle(neww);
        AddMessage message = new AddMessage(t);
        var newOutput7 = Controller.getNewReleases(limit);
        message.writeToText(newOutput7);
        SaveMusic new2 = new FeaturedSave();
        var newOutput8 = Controller.getFeatured(limit);
        new2.writeToText(newOutput8);
        output7.addToList(message);
        output7.addToList(new2);
        test = output7.returnOutput();
        System.out.println(test);


        System.out.println("------VISITOR TEST #1: Counting songs in list------");
        Visitor visit = new Visitor();
        output7.accept(visit);

        System.out.println("------VISITOR TEST #2: Counting nonexistent list------");
        Visitor visit2 = new Visitor();
        CompositeOutput output12 = new CompositeOutput();
        output12.accept(visit2);

        System.out.println("------VISITOR TEST #3: Counting new songs------");
        Visitor visit3 = new Visitor();
        neww.accept(visit3);

        System.out.println("------VISITOR TEST #4: Counting featured songs------");
        Visitor visit4 = new Visitor();
        n.accept(visit4);

    }
}