package imageviewer.apps.mock;

import imageviewer.control.Command;
import imageviewer.control.InitCommand;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final Command NullCommand = new Command.Null();
    private final Map<String, Command> commands;
   
    
    public static void main(String[] args) {
        new Main().execute();
    }
    
    public Main() {
        InitCommand init = new InitCommand(loadImages(), createImageDisplay());
        init.execute();
        commands = init.getCommands();
    }
    
    private MockImageDisplay createImageDisplay() {
        return new Main.MockImageDisplay();
    }
    
    private List <Image> loadImages() {
        return new MockImageLoader().load();
    }
    
    private void execute() {
        while (true) commands.getOrDefault(input(), NullCommand).execute();
    }
    
    private String input() {
        return scanner.next().toUpperCase();
    }
    
    public static class MockImageDisplay implements ImageDisplay {
        private Image image;
        
        @Override
        public void display(Image image) {
            this.image = image;
            System.out.println(image.getName());
        }
        
        @Override
        public Image getImage() {
            return image;
        }
    }
    
    public static class MockImageLoader implements ImageLoader{
        @Override
        public List <Image> load() {
            List <Image> list = new ArrayList <>();
            list.add(new Image("Hola"));
            list.add(new Image("Mundo"));
            list.add(new Image ("Bienvenido"));
            return list;
        }
    }
}