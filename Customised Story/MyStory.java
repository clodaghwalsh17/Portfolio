import java.util.Scanner;
public class MyStory{
    public static void main(String[] args){ 
        Story userStory = null;

        Scanner in = new Scanner(System.in); // NOTE: Using a scanner here instead of input stream since few items are read in 
        System.out.println("Welcome, please enter your name");
        String username = in.nextLine();  
        username = Story.formatEntry(username);
        System.out.printf("Hi %s, would you prefer to make \t1)A Superhero Story \t2)An Adventure Story \nEnter your preference:",username);
        int storyChoice = in.nextInt();
        in.close();

        switch(storyChoice){
            case 1:
            userStory = new Superhero(username);
            break;

            case 2:
            userStory = new AdventureStory(username);
            break;

            default:
            System.out.println("INVALID ENTRY - Please restart the program and try again.");
            return;

        }

        String fileName = userStory.getFileName();
        String fileLocation = userStory.createPDF();

        System.out.printf("Your story '%s' is ready. It is available at %s",fileName,fileLocation);
    }
}