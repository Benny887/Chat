package common.Supply;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyGetter {
    private final String pathToProp = getClass().getClassLoader().getResource("serverConfig.properties").getPath();

    public Properties getProps() {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(pathToProp)) {
            properties.load(reader);
        } catch (IOException e) {
            System.out.println("Ошибка при считывании файла конфигурации");
        }
        return properties;
    }
}
