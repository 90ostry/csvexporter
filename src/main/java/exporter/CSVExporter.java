package exporter;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Component
public class CSVExporter {

    public void exportToCSV(List<Element> elements, OutputStream out) throws IOException {
        Assert.notNull(elements, "Elements collection cannot be null");
        Assert.notNull(out, "Output channel cannot be null");

        ColumnPositionMappingStrategy<Element> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(Element.class);
        mappingStrategy.setColumnMapping(new String[] {"id", "content"});
        BeanToCsv<Element> beanConverter = new BeanToCsv<>();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(out));
        beanConverter.write(mappingStrategy, writer, elements);
        writer.close();
    }
}
