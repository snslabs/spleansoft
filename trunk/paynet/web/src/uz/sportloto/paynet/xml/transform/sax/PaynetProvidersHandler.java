package uz.sportloto.paynet.xml.transform.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import uz.sportloto.paynet.model.response.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PaynetProvidersHandler extends PaynetSAXHandler {
    private Map<Integer, Provider> providersMap = new HashMap<Integer, Provider>();
    private Map<Integer, Service> serviceMap = new HashMap<Integer, Service>();
    private Map<Integer, ServiceType> serviceTypeMap = new HashMap<Integer, ServiceType>();
    private Map<Integer, ChequeInfo> chequeInfoMap = new HashMap<Integer, ChequeInfo>();
    private Map<Integer, Category> categoryMap = new HashMap<Integer, Category>();
    private ServiceType currentServiceType;
    private Field currentField;
    private String currentFieldAttribute;
    private static final String FIELD_NAME = "field_name";
    private static final String FIELD_LABEL = "field_label";
    private static final String FIELD_TYPE = "field_type";
    private static final String FIELD_SIZE = "field_size";
    private static final String IS_REQUIRED = "is_required";
    private static final String DETAILS = "details";
    private static final String SERVICE_TYPE = "service_type";
    private static final String PROVIDER = "provider";
    private static final String SERVICE = "service";
    private static final String CHEQUE_INFO = "cheque_info";
    private static final String CATEGORY = "category";
    private static final String ATTR_ID = "id";
    private static final String ATTR_NAME = "name";
    private static final String ATTR_CATEGORY_ID = "category_id";
    private static final String ATTR_PROVIDER_ID = "provider_id";
    private static final String ATTR_SERVICE_TYPE_ID = "service_type_id";
    private static final String ATTR_SERVICE_ID = "service_id";
    private static final String ATTR_SERVICE_PRICE = "service_price";

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (PROVIDER.equals(qName)) {
            providersMap.put(new Integer(attributes.getValue(ATTR_ID)),
                    new Provider(
                            new Integer(attributes.getValue(ATTR_ID)),
                            attributes.getValue(ATTR_NAME),
                            new Integer(attributes.getValue(ATTR_CATEGORY_ID))
                    )
            );
        } else if (SERVICE.equals(qName)) {
            final Service service = new Service(new Integer(attributes.getValue(ATTR_ID)),
                    attributes.getValue(ATTR_NAME),
                    new Integer(attributes.getValue(ATTR_PROVIDER_ID)),
                    new Integer(attributes.getValue(ATTR_CATEGORY_ID)),
                    new Integer(attributes.getValue(ATTR_SERVICE_TYPE_ID)));
            final String servicePriceValue = attributes.getValue(ATTR_SERVICE_PRICE);
            if(servicePriceValue !=null){
                service.setServicePrice(new BigDecimal(servicePriceValue));
            }

            serviceMap.put(new Integer(attributes.getValue(ATTR_ID)), service);
        } else if (SERVICE_TYPE.equals(qName)) {
            currentServiceType = new ServiceType(new Integer(attributes.getValue(ATTR_ID)));
        } else if (DETAILS.equals(qName)) {
            currentField = new Field();
            currentField.setServiceType(currentServiceType);
            currentServiceType.getFields().add(currentField);
        } else if (
                FIELD_NAME.equals(qName) ||
                        FIELD_LABEL.equals(qName) ||
                        FIELD_TYPE.equals(qName) ||
                        FIELD_SIZE.equals(qName) ||
                        IS_REQUIRED.equals(qName)
                ) {
            currentFieldAttribute = qName;
        }
        else if (CHEQUE_INFO.equals(qName)){
            chequeInfoMap.put(new Integer(attributes.getValue(ATTR_SERVICE_ID)),
                    new ChequeInfo(new Integer(attributes.getValue(ATTR_SERVICE_ID)), attributes.getValue("info")));

        }
        else if (CATEGORY.equals(qName)){
            categoryMap.put(
                    new Integer(attributes.getValue(ATTR_ID)),
                    new Category(attributes.getValue(ATTR_NAME), new Integer(attributes.getValue(ATTR_ID)))
            );
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (SERVICE_TYPE.equals(qName)) {
            serviceTypeMap.put(currentServiceType.getServiceTypeId(), currentServiceType);
        } else if (DETAILS.equals(qName)) {
        } else if (
                FIELD_NAME.equals(qName) ||
                        FIELD_LABEL.equals(qName) ||
                        FIELD_TYPE.equals(qName) ||
                        FIELD_SIZE.equals(qName) ||
                        IS_REQUIRED.equals(qName)
                ) {
            currentFieldAttribute = null;
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if(currentFieldAttribute != null){
            final String value = new String(ch, start, length);
            if(currentFieldAttribute.equals(FIELD_NAME)){
                currentField.setName(value);
            }
            else if(currentFieldAttribute.equals(FIELD_LABEL)){
                currentField.setLabel(value);
            }
            else if(currentFieldAttribute.equals(FIELD_TYPE)){
                currentField.setType(value);
            }
            else if(currentFieldAttribute.equals(FIELD_SIZE)){
                currentField.setSize(new Integer(value));
            }
            else if(currentFieldAttribute.equals(IS_REQUIRED)){
                currentField.setRequired(value);
            }
        }
    }

    public void endDocument() throws SAXException {
        super.endDocument();
        linkMaps();
    }

    public Map<Integer, Provider> getProvidersMap() {
        return providersMap;
    }

    public Map<Integer, Service> getServiceMap() {
        return serviceMap;
    }

    public Map getServiceTypeMap() {
        return serviceTypeMap;
    }

    private void linkMaps(){
        for (Integer integer : serviceMap.keySet()) {
            Service service = serviceMap.get(integer);
            final Provider provider = providersMap.get(service.getProviderId());
            if (provider == null) {
                System.out.println("Warning! No provider for service_id=" + service.getServiceId());
            } else {
                provider.getServices().add(service);
                provider.setCategory(categoryMap.get(provider.getCategoryId()));
                service.setProvider(provider);
            }
            service.setServiceType(serviceTypeMap.get(service.getServiceTypeId()));
            final ChequeInfo chequeInfo = chequeInfoMap.get(service.getServiceId());
            service.setChequeInfo(chequeInfo!=null?chequeInfo.getInfo():null);
            service.setCategory(categoryMap.get(service.getCategoryId()));
        }
    }

    public Map getChequeInfoMap() {
        return chequeInfoMap;
    }

    public Map getCategoryMap() {
        return categoryMap;
    }

    public String toTextMode() {
        return null;
    }
}
