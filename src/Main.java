import engine.core.Display;
import engine.core.Runtime;
import game.scene.Battlefield;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        new Display("MegaStrat", gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight(), new Battlefield());
    }
}