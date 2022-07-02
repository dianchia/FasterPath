package FasterPath;

import necesse.engine.modLoader.annotations.ModEntry;

@ModEntry
public class FasterPath {
    public static Config config;
    public void preInit() {
        System.out.println("Initializing FasterPath");
        config = new Config("settings.cfg");
        System.out.println("Initialization completed! Path is now faster by " + (config.getSpeedModifier() + 1) + "x!");
    }
}
