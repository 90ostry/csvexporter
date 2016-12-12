package exporter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

//@ContextConfiguration(classes = CSVExporter.class)
//@RunWith(SpringJUnit4ClassRunner.class)

@RunWith(SpringRunner.class)
@SpringBootTest
public class CSVExporterTest {

    @Autowired
    CSVExporter exporter;

    @Autowired
    ApplicationContext ctx;

    @Test
    public void testExporterOutput() throws IOException {
        List<Element> elements = elements();
        OutputStream stream = simpleOutputStream();
        String expected = expectedOutput();

        exporter.exportToCSV(elements,stream);
        assertEquals(expected, stream.toString());
    }

    @Test
    public void testContextLoads() throws Exception {
        assertThat(this.ctx).isNotNull();
        assertThat(this.ctx.containsBean("CSVExporter")).isTrue();
        assertThat(this.ctx.containsBean("exporterApplication")).isTrue();
    }

    private List<Element> elements() {
        return  Arrays.asList(
                new Element(1L, "test content 1" ),
                new Element(2L, "test content 2" ),
                new Element(3L, "test content 3" ),
                new Element(666L, "no content" )
        );
    }

    private String expectedOutput() {
        return "\"id\",\"content\"\n" +
                "\"1\",\"test content 1\"\n" +
                "\"2\",\"test content 2\"\n" +
                "\"3\",\"test content 3\"\n" +
                "\"666\",\"no content\"\n";
    }

    private OutputStream simpleOutputStream() {
        return new OutputStream() {
            StringBuilder builder = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                builder.append((char) b);
            }

            @Override
            public String toString() {
                return builder.toString();
            }
        };
    }



}
