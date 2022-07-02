package FasterPath;

import necesse.engine.GlobalData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

public class Config {

    private Float speedModifier = 1.0F;

    public Config() {
        this(1.0F);
    }

    public Config (float speedModifier) {
        this.speedModifier = speedModifier;
    }

    public Config (String configFileName) {
        System.out.println("Loading config...");
        String filename = GlobalData.rootPath() + "/settings/fasterpath/" + configFileName;
        try {
            File file = new File(filename);
            if (!file.exists()) createNewFile(file);

            InputStreamReader isr = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            loadConfig(br);
            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadConfig(BufferedReader br) throws IOException {
        String line;
        while((line = br.readLine()) != null) {
            if (line.length() != 0) {
                String[] temp = line.split("=");
                if (Objects.equals(temp[0], "speedModifier")) this.setSpeedModifier(Float.parseFloat(temp[1]));
            }
        }
    }

    private void createNewFile(File file) throws IOException {
        if (!file.getParentFile().mkdirs()) throw new IOException("Error creating directory: " + file.getParentFile().toPath());
        if (!file.createNewFile()) throw new IOException("Error creating file: " + file.toPath());
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8))) {
            writer.write("speedModifier=1.0");
        }
    }

    public Float getSpeedModifier() {
        return this.speedModifier;
    }

    public void setSpeedModifier(Float newValue) {
        if (newValue > 0.0F) this.speedModifier = newValue;
    }
}
