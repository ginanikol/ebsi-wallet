package gr.ihu.dw.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.PropertyValueConverter;
import org.springframework.data.convert.ValueConversionContext;

import java.util.Date;

public class LongToDateConverter implements PropertyValueConverter {

    @Override
    public Object read(Object value, ValueConversionContext context) {
        return null;
    }

    @Override
    public Object write(Object value, ValueConversionContext context) {
        return null;
    }
}
