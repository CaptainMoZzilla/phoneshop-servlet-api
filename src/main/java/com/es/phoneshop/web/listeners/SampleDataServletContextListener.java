package com.es.phoneshop.web.listeners;

import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class SampleDataServletContextListener implements ServletContextListener {
    private static final String ADD_SAMPLE_DATA = "ADD_SAMPLE_DATA";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ProductDao dao = ArrayListProductDao.getInstance();

        if (!Boolean.valueOf(servletContextEvent.getServletContext()
                .getInitParameter(ADD_SAMPLE_DATA))) {
            return;
        }

        for (int i = 1; i <= 12; ++i) {
            dao.save(new Product((long) i, "A1" + i, "desc" + i, new BigDecimal(i * 100),
                    Currency.getInstance(Locale.UK), i));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
