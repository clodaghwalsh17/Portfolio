public class AdventureStory extends Story{
    String[] adventurePrompts = {"your dream destination","your friend's name","a mode of transport","a number","your favourite food","a colour","a noun"};
    // These exact terms are in the file read in
    public AdventureStory(String name){
        this.name = name;
        userInput.add(name);
        fileName = this.name +"'s Adventure";
        storyTemplate = "AdventureTemplate.txt";
        imageSource = "globe.jpg";
        super.collectData(adventurePrompts);
    }

}
