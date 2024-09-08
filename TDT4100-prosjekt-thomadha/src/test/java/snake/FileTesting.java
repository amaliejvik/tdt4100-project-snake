package snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import snake.Highscore;

public class FileTesting {
    private Highscore test_highscore;
    private List<String> names = new ArrayList<>();
    private List<Integer> scores = new ArrayList<>();

    @Test
    @DisplayName("Write to file, and that scores are sorted")
    public void testWriteToFile() throws FileNotFoundException{
        names.clear();
        scores.clear();

        test_highscore = new Highscore();
        test_highscore.addScore("Thomas",9);
        test_highscore.addScore("Amalie", 10);
        test_highscore.addScore("Katrine", 8);
        test_highscore.addScore("Anine", 7);
       
    
        test_highscore.writeScoresToFile("TestHighscore.txt");

        Scanner scanner = new Scanner(new File("TestHighscore.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineInfo = line.split(",");

            String name = lineInfo[0];
            String score = lineInfo[1];

            names.add(name);
            scores.add(Integer.parseInt(score));
        }

        assertEquals("Amalie", names.get(0));
        assertEquals("Thomas", names.get(1));
        assertEquals("Katrine", names.get(2));
        assertEquals("Anine", names.get(3));

        assertEquals(10, scores.get(0));
        assertEquals(9, scores.get(1));
        assertEquals(8, scores.get(2));
        assertEquals(7, scores.get(3));
        
    }

    @Test 
    @DisplayName("HighscoreList not full: New score from already existing user is not added because user already has a better score")
    public void testNewWorseScore() throws FileNotFoundException{
        
        names.clear();
        scores.clear();

        test_highscore = new Highscore();
        test_highscore.addScore("Thomas",9);
        test_highscore.addScore("Amalie", 10);
        test_highscore.addScore("Katrine", 8);
        test_highscore.addScore("Anine", 7);
        test_highscore.addScore("Thomas",3);
    
        test_highscore.writeScoresToFile("TestHighscore.txt");

        Scanner scanner = new Scanner(new File("TestHighscore.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineInfo = line.split(",");

            String name = lineInfo[0];
            String score = lineInfo[1];

            names.add(name);
            scores.add(Integer.parseInt(score));
        }


        assertEquals("Thomas", names.get(1));
        assertEquals(9, scores.get(1));
    }

    @Test 
    @DisplayName("HighscoreList not full: New score from already existing user is added, because old score is worse")
    public void testNewBetterScore() throws FileNotFoundException{

        names.clear();
        scores.clear();

        test_highscore = new Highscore();
        test_highscore.addScore("Thomas",9);
        test_highscore.addScore("Amalie", 10);
        test_highscore.addScore("Katrine", 8);
        test_highscore.addScore("Anine", 7);
        test_highscore.addScore("Thomas",100);
    
        test_highscore.writeScoresToFile("TestHighscore.txt");

        Scanner scanner = new Scanner(new File("TestHighscore.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineInfo = line.split(",");

            String name = lineInfo[0];
            String score = lineInfo[1];

            names.add(name);
            scores.add(Integer.parseInt(score));
        }

        assertEquals("Thomas", names.get(0));
        assertEquals(100, scores.get(0));
    }

    @Test 
    @DisplayName("HighscoreList full: Test new BETTER score from new user, supposed to be added")
    public void testFullScorelistWhithNewBetterScore() throws FileNotFoundException{

        names.clear();
        scores.clear();

        test_highscore = new Highscore();
        test_highscore.addScore("Thomas",9);
        test_highscore.addScore("Amalie", 10);
        test_highscore.addScore("Katrine", 8);
        test_highscore.addScore("Anine", 7);
        test_highscore.addScore("Jennina", 21);
        test_highscore.addScore("Sara", 5);
        test_highscore.addScore("Jenny", 4);
        test_highscore.addScore("Siri", 3);
        test_highscore.addScore("Marius", 2);
        test_highscore.addScore("Jorunn", 8);
        test_highscore.addScore("Hedda", 22);
    
        test_highscore.writeScoresToFile("TestHighscore.txt");

        Scanner scanner = new Scanner(new File("TestHighscore.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineInfo = line.split(",");

            String name = lineInfo[0];
            String score = lineInfo[1];

            names.add(name);
            scores.add(Integer.parseInt(score));
        }
        assertEquals(10, names.size());
        assertEquals("Hedda", names.get(0));
        assertEquals(22, scores.get(0));
    }

    @Test
    @DisplayName("HighscoreList full: Test new WORSE score from new user, NOT to be added")
    public void testFullScorelistWhithNewWorseScore() throws FileNotFoundException{
        
        names.clear();
        scores.clear();

        test_highscore = new Highscore();
        test_highscore.addScore("Thomas",9);
        test_highscore.addScore("Amalie", 10);
        test_highscore.addScore("Katrine", 8);
        test_highscore.addScore("Anine", 7);
        test_highscore.addScore("Jennina", 21);
        test_highscore.addScore("Sara", 5);
        test_highscore.addScore("Jenny", 4);
        test_highscore.addScore("Siri", 3);
        test_highscore.addScore("Marius", 2);
        test_highscore.addScore("Jorunn", 8);
        
        test_highscore.addScore("Hedda", 1);
    
        test_highscore.writeScoresToFile("TestHighscore.txt");

        Scanner scanner = new Scanner(new File("TestHighscore.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineInfo = line.split(",");

            String name = lineInfo[0];
            String score = lineInfo[1];

            names.add(name);
            scores.add(Integer.parseInt(score));
        }

        assertEquals(10, names.size());
        assertEquals(10, scores.size());

        for (String name : names) {
            assertTrue(!(name.equals("Hedda")));
        }
        for (Integer score : scores) {
            assertTrue(!(score.equals(1)));
        }
    }

}
