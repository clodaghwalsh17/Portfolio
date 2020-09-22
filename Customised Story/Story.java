import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Story{
    String name; 
    String fileName; 
    String storyTemplate;   
    String imageSource;
    ArrayList<String> userInput = new ArrayList<String>();  // Arraylist optimised for data storage and access
    ArrayList<String> prompts = new ArrayList<String>();
    BufferedReader buffer;

    public Story(){
        prompts.add("username"); // This prompt is common to all stories
    }

    public void collectData(String[] promptList){
        prompts.addAll((Arrays.asList(promptList)));
        try{
            buffer = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Answer the following to finish making your story");

            for(int i=1;i< prompts.size();i++){ // Starting at 1 since name is already got in main()
                System.out.println("Enter " + prompts.get(i));
                userInput.add(buffer.readLine());
            }
            buffer.close();

            System.out.println("\nPlease wait while your story is being made");
        }
        catch(IOException e){
            System.out.println("Error accepting user input");
        }

    }

    private String[] generateContent(){ 
        String[] content = null;

        String formattedPlaceName = formatEntry(userInput.get(1)); // A place name is listed in this location and may need to be capitalised
        userInput.set(1,formattedPlaceName);
        String formattedAdditionalName = formatEntry(userInput.get(2)); // An addtional name is listed in this location and may need to be capitalised
        userInput.set(2,formattedAdditionalName);

        try{
            buffer = new BufferedReader(new FileReader(storyTemplate));
            StringBuilder str = new StringBuilder(); // StringBuilder is good for concatenation within loops
            String input = buffer.readLine();
            while(input != null){
                str.append(input + "\n");
                input = buffer.readLine();
            }
            buffer.close();

            String text = str.toString();

            for(int i=0;i<userInput.size();i++){
                text = text.replace(prompts.get(i),userInput.get(i));
            }

            content = text.split("\n");

        }
        catch(IOException e){
            System.out.println("An error occured");
        }

        return content;
    }

    public String createPDF(){
        String[] pageContents = generateContent();
        String path = null;

        try{
            PDDocument doc = new PDDocument();
            PDPage titlePage = new PDPage();
            PDPage mainContent = new PDPage();

            doc.addPage(titlePage);
            doc.addPage(mainContent);

            PDPageContentStream pageStream = new PDPageContentStream(doc,mainContent);
            PDPageContentStream coverPageStream = new PDPageContentStream(doc,titlePage);
            PDImageXObject image = PDImageXObject.createFromFile(imageSource,doc);

            pageStream.beginText(); // beginText() and endText() are equivalent to operations in PDF language
            pageStream.setFont(PDType1Font.HELVETICA,16); // Helvetica is one of standard PDF fonts
            pageStream.setLeading(20); // Setting spacing between lines
            pageStream.newLineAtOffset(50,706); // Placing cursor at top of page, method makes use of the td operator - a built in PDF operator

            for(String pageContent:pageContents){
                pageStream.showText(pageContent);
                pageStream.newLine();
            }               
            pageStream.endText();
            pageStream.close();

            coverPageStream.beginText(); 
            coverPageStream.setFont(PDType1Font.HELVETICA,30); 
            coverPageStream.newLineAtOffset(175,706);
            coverPageStream.showText(fileName);
            coverPageStream.endText();
            coverPageStream.drawImage(image,100,200,400,400);
            coverPageStream.close();

            String folder = "UserStory";
            File dir = new File(folder);
            dir.mkdir();
            path = dir.getAbsolutePath();

            /* Must append the .pdf extension, otherwise extensionless file is produced
             * File.seperator chooses correct seperator for OS
             */
            doc.save(path + File.separator + fileName + ".pdf");
            doc.close();

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return path;
    }

    public String getFileName(){
        return fileName;
    }

    // Method capitalises first letter ie names,places - Method is static to allow its use in main before type of story is selected
    public static String formatEntry(String s){
        if(s.contains(" ") || s.contains("-")){
            int space = s.lastIndexOf(" ");
            int hyphen = s.lastIndexOf("-");
            int pos = Math.max(space,hyphen) + 1; // Holds index of character after a space or hyphen
            
            return s.substring(0,1).toUpperCase() + s.substring(1,pos) + s.substring(pos,pos+1).toUpperCase() + s.substring(pos+1);
        }
        else{
            return s.substring(0,1).toUpperCase() + s.substring(1);
        }
        
    }

}
