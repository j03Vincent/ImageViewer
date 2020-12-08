/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.List;

/**
 *
 * @author Vincent
 */
public class NextImageCommand implements Command {
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public NextImageCommand(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
    }
    
    @Override
    public void execute(){
        imageDisplay.display(next());
    }

    private Image next() {
        return images.get(next(getIndex()));
    }
    private int next(int index) {
        return (index+1) % images.size();
    }
    private int getIndex() {
        return images.indexOf(imageDisplay.getImage());
    }
}