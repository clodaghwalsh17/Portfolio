public class Superhero extends Story{
    String[] superheroPrompts = {"a fictional or non fictional place","your side kick's name","an animal","a verb","an adverb","a plural noun","a plural piece of clothing","a colour","a number"}; 
    // These exact terms are in the file read in
    public Superhero(String name){ 
        this.name = name;
        userInput.add(name);
        fileName = this.name + " the Superhero";
        storyTemplate = "SuperheroTemplate.txt";
        imageSource = "superhero_silhouette.jpg";
        super.collectData(superheroPrompts);           
    }

}
