package uz.sportloto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;
import java.io.IOException;

public class Configuration {
    private static Configuration confInstance = new Configuration();
    private static final Log log = LogFactory.getLog(Configuration.class);
    private Properties props;

    public static Configuration getInstance() {
        return confInstance;
    }

    private Configuration(){
        try{
            props = new Properties();
            props.load(Configuration.class.getClassLoader().getResourceAsStream("paynet.properties"));
        }
        catch(IOException e){
            log.error("Cannot load PAYNET configuration:",e);
        }
    }

    public static String getProvidersUrl(){
        return confInstance.props.getProperty("paynet.providers.url");
    }

    public static String getPayUrl() {
        return confInstance.props.getProperty("paynet.pay.url");
    }

    public static String getReportDateFormat() {
        return confInstance.props.getProperty("paynet.report.dateformat");
    }

    public static String getReportNumberFormat() {
        return confInstance.props.getProperty("paynet.report.numberformat");
    }

    public static String getLastTransactionUrl() {
        return confInstance.props.getProperty("paynet.lastTransaction.url");
    }

    public static String getDayReportUrl() {
        return confInstance.props.getProperty("paynet.dayReport.url");
    }

    public static String getPaynetUsername() {
        return confInstance.props.getProperty("paynet.username");
    }

    public static String getPaynetPassword() {
        return confInstance.props.getProperty("paynet.password");
    }

    public static String getTransactionDateFormat() {
        return confInstance.props.getProperty("paynet.transaction.dateformat");
    }

    public static boolean isBlindTrust() {
        return "true".equalsIgnoreCase(confInstance.props.getProperty("paynet.blindtrust"));
    }

    public static String getBlobEncoding(){
        return confInstance.props.getProperty("paynet.blob.encoding");
    }
    public static boolean getForceProvidersUpdate(){
        return "true".equalsIgnoreCase(confInstance.props.getProperty("paynet.force.providers.update"));
    }

}
