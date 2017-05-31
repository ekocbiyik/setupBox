//package settopbox.denemeler.wmi;
//
//import net.egemsoft.matp.caresuite.support.windows.wmi.WMIHelper.VariantVisitor;
//import org.eclipse.swt.ole.win32.OLE;
//import org.eclipse.swt.ole.win32.OleAutomation;
//import org.eclipse.swt.ole.win32.Variant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Logger;
//
//
//public class WMIConnection {
//
//    private Variant service;
//    private OleAutomation automation;
//    private OleAutomation serviceAutomation;
//
//    public WMIConnection() {
//
//    }
//
//    private void init() {
//        this.automation = new OleAutomation("WbemScripting.SWbemLocator");
//
//        this.service = automation.invoke(WMIHelper.getId(automation,
//                "ConnectServer"));
//
//        if (service.getType() == OLE.VT_ERROR) {
//            OLE.error(service.getInt());
//            throw new RuntimeException(String.format(
//                    "Failed to connect to server: %s", service.getString()));
//        }
//
//        serviceAutomation = service.getAutomation();
//    }
//
//    public synchronized List<WMIObjectInformation> executeQuery(String query) {
//
////        if(serviceAutomation==null ) {
//            init();
////        }
//
//        logger.debug("***** WMI query {} is started to execute ", query);
//
//        final List<WMIObjectInformation> result = new LinkedList<WMIObjectInformation>();
//        Variant variantQuery = new Variant(query);
//        Variant variantWQL = new Variant("WQL");
//        Variant variant32 = new Variant(32);
//
//        Variant resultList = null;
//        try {
//
//
//            Variant[] variantList = {variantQuery, variantWQL, variant32};
//
//            resultList = serviceAutomation.invoke(
//                    WMIHelper.getId(serviceAutomation, "ExecQuery"), variantList);
//
//            if (resultList == null) {
//                throw new RuntimeException(serviceAutomation.getLastError());
//            }
//
//            WMIHelper.forEachVariant(resultList, new VariantVisitor() {
//
//                @Override
//                public void visit(Variant variant) {
//
//                    final Map<String, Object> params = new HashMap<String, Object>();
//
//                    Variant properties = WMIHelper.getParameter(variant,
//                            "Properties_");
//
//                    WMIHelper.forEachVariant(properties, new VariantVisitor() {
//
//                        @Override
//                        public void visit(Variant variant) {
//
//                            Variant name = WMIHelper.getParameter(variant, "Name");
//                            Variant value = WMIHelper.getParameter(variant,
//                                    "Value");
//                            Object objectValue = WMIHelper.convertVariant(value);
//
//                            params.put(name.getString(), objectValue);
//
//                            name.dispose();
//                            value.dispose();
//
//                            variant.getUnknown().Release();
//                            variant.dispose();
//                        }
//                    });
//                    properties.getUnknown().Release();
//                    properties.dispose();
//
//                    Variant variantParameter1 = WMIHelper.getParameter(variant, "Path_");
//
//                    Variant variantParameter2 = WMIHelper.getParameter(variantParameter1, "Path");
//
//                    result.add(new WMIObjectInformation(variantParameter2.toString(), params));
//
//                    variant.getUnknown().Release();
//                    variant.dispose();
//
//                    variantParameter1.getUnknown().Release();
//                    variantParameter1.dispose();
//
//                    variantParameter2.dispose();
//                }
//            });
//
//
//        } catch (Exception e) {
//            logger.error("******* An error was occurred while executing WMI query - "+query, e);
//        } finally {
//            if (resultList != null) {
//                resultList.getUnknown().Release();
//                resultList.dispose();
//            }
//
//            variantQuery.dispose();
//            variantWQL.dispose();
//            variant32.dispose();
//
//
//            serviceAutomation.dispose();
//
//            service.getUnknown().Release();
//            service.dispose();
//
//            automation.dispose();
//
//
//        }
//        return result;
//    }
//
////    public void dispose() {
////        this.automation.dispose();
////    }
//}
