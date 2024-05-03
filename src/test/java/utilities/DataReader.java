package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        // Read JSON to String
        File src = new File(filePath);
        String jsonContent = FileUtils.readFileToString(src, StandardCharsets.UTF_8);

        // String to HashMap - Jackson Databind
        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

        return data;

    }

}
