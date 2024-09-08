package snake;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;





public class Highscore {
    //FELTER
    private LinkedHashMap<String, Integer> highscore;
    private final int max_size;

    //KONSTRUKTØR
    public Highscore(){
        this.highscore = new LinkedHashMap<String, Integer>();
        this.max_size = 10;
    }

    
    //STANDARDMETODER

    //Sorterer listen i synkende rekkefølge før den returneres
    public LinkedHashMap<String, Integer> getHighscore(){
        LinkedHashMap<String, Integer> sorted_highscoreList = highscore.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
            Map.Entry :: getKey,
            Map.Entry :: getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap :: new
        ));

        return sorted_highscoreList; //synkende rekkefølge!
    }


    //METODER

    //Sorterer mapet i stigende rekkefølge, og henter ut det første elementet
    public Integer getWorstScore(){
        LinkedHashMap<String, Integer> asscendingHighscoreList = highscore.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(
            Map.Entry :: getKey,
            Map.Entry :: getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap :: new
        ));

        //Henter ut første elment (det minste)
        Entry <String, Integer> entryWithSmallestValue = asscendingHighscoreList
        .entrySet()
        .stream()
        .findFirst()
        .get();


        return entryWithSmallestValue.getValue();
    }
    

    public void addScore(String name, Integer new_score){
        //Hvis max_size ikke er nådd på highscoren, så skal alle nye brukere legges til
        //Hvis bruker allerede er på listen, skal ny score erstatte den gamle om den er bedre
        if (highscore.size() < max_size){
            if(highscore.containsKey(name)&& highscore.get(name) < new_score){
                highscore.remove(name);
                highscore.put(name, new_score);
                
            }
            if(!highscore.containsKey(name)){
                highscore.put(name, new_score);
            }
        }
        //Hvis max_size er nådd på highscoren, sjekke om den nye scoren er god nok
        else{
            //Hvis new_score er god nok til å komme inn på lista, legges den til, og den andre fjernes
            if(new_score >= this.getWorstScore()){
                for (Entry<String, Integer> entry : this.getHighscore().entrySet()){
                    if(entry.getValue().equals(this.getWorstScore())){
                        highscore.remove(String.valueOf(entry.getKey()));
                        break;

                    }
                }
                highscore.put(name, new_score);
            }
        }
    }
    
    public void printHighscore() {
        System.out.println("HIGHSCORE:");
        for(String i: this.getHighscore().keySet()){
            System.out.println("\n Navn: " + i +  "            Score: " + this.getHighscore().get(i));
        }
    }

    

    //FILHÅNDTERING

    //SKRIVE TIL FIL
    public void writeScoresToFile(String filename){
        try{
            PrintWriter writer = new PrintWriter(filename);
            for(Entry<String, Integer> result : this.getHighscore().entrySet()){
                writer.println(result.getKey() + "," + result.getValue());
                
            }
            writer.flush();
            writer.close();
        } catch(FileNotFoundException e){
            System.out.println("Noe gikk galt!");
        }

    }



    public static void main(String[] args) {
        Highscore highscore = new Highscore();

        highscore.addScore("Thomas",9);
        highscore.addScore("Amalie", 10);
        highscore.addScore("Katrine", 8);
        highscore.addScore("Anine", 7);
        highscore.addScore("Jennina", 21);
        highscore.addScore("Sara", 5);
        highscore.addScore("Jenny", 4);
        //highscore.addScore("Siri", 3);
        //highscore.addScore("Marius", 2);
        //highscore.addScore("Jorunn", 8);
        highscore.addScore("Thomas", 40);


   
        System.out.println(highscore.getHighscore().entrySet());
        highscore.printHighscore();

        highscore.writeScoresToFile("Highscore.txt");

      
        }


    
    
    
}