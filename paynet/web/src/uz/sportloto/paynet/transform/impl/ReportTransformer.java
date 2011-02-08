package uz.sportloto.paynet.transform.impl;

import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.transform.TransformException;
import uz.sportloto.paynet.xml.transform.sax.PaynetReportHandler;
import uz.sportloto.paynet.xml.transform.sax.PaynetSAXHandler;

/**
 * Трансформер для обработки ответа на запрос дневного отчёта (ACT=4)
 */
public class ReportTransformer extends AbstractResponseTransformer{
    PaynetReportHandler reportHandler = new PaynetReportHandler();
    protected PaynetSAXHandler getSAXHandler() {
        return reportHandler;
    }

    public void postTransform(PaynetRequest paynetRequest) throws TransformException {

    }

    /**
     * Retruns DayReport
     * @return day report object DayReport 
     */
    public Object getAsObject() {
        return reportHandler.getDayReport();
    }
}