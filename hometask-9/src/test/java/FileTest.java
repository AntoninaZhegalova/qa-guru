import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import domain.PersonalData;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FileTest {
    private final ClassLoader classLoader = FileTest.class.getClassLoader();
    private final String jsonFileName = "example.json";
    private final String zipFileName = "sample-files.zip";

    @Test
    void pdfTest() throws Exception {
        InputStream is = getInputStream(zipFileName);
        try (ZipInputStream zipInputStream = new ZipInputStream(is)) {
            InputStream fromZip = getFromZip(zipInputStream, "example.pdf");
            PDF pdf = new PDF(fromZip);
            assertThat(pdf.text).contains("PDF BOOKMARK SAMPLE");
        }
    }

    @Test
    void xlsxTest() throws Exception {
        InputStream is = getInputStream(zipFileName);
        try (ZipInputStream zipInputStream = new ZipInputStream(is)) {
            InputStream fromZip = getFromZip(zipInputStream, "example.xlsx");
            XLS xls = new XLS(fromZip);
            assertThat(
                    xls.excel
                            .getSheetAt(0)
                            .getRow(2)
                            .getCell(2)
                            .getNumericCellValue()
            ).isEqualTo(25);
        }
    }

    @Test
    void csvTest() throws Exception {
        InputStream is = getInputStream(zipFileName);
        try (ZipInputStream zipInputStream = new ZipInputStream(is)) {
            InputStream fromZip = getFromZip(zipInputStream, "example.csv");
            CSVReader csv = new CSVReader(new InputStreamReader(fromZip));
            List<String[]> content = csv.readAll();
            assertThat(content.get(2)).contains("Мария", "Female");
        }
    }

    @Test
    void jsonJacksonTest() throws Exception {
        InputStream inputStream = getInputStream(jsonFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        assert inputStream != null;
        JsonNode jsonNode = objectMapper.readTree(new InputStreamReader(inputStream, UTF_8));

        assertThat(jsonNode.findValue("name").asText()).isEqualTo("Antonina");
        assertThat(jsonNode.findValue("sex").asText()).isEqualTo("female");
        assertThat(jsonNode.findValue("height").asInt()).isEqualTo(175);
        assertThat(jsonNode.findValue("age").asInt()).isEqualTo(36);
    }

    @Test
    void jsonJacksonTestFromObject() throws Exception {
        InputStream inputStream = getInputStream(jsonFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        assert inputStream != null;

        PersonalData personalData = objectMapper.readValue(inputStream, PersonalData.class);

        assertThat(personalData.name).isEqualTo("Antonina");
        assertThat(personalData.sex).isEqualTo("female");
        assertThat(personalData.height).isEqualTo(175);
        assertThat(personalData.age).isEqualTo(36);
    }

    private InputStream getFromZip(ZipInputStream zis, String fileName) throws IOException {
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            if (entry.getName().contains(fileName)) {
                break;
            }
        }
        return zis;
    }

    private InputStream getInputStream(String fileName) {
        return classLoader.getResourceAsStream(fileName);
    }
}
