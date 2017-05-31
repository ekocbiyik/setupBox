package settopbox.denemeler.wmi;

/**
 * @author user
 */
public class WMICommandConstant {

    public static final String GET_INTERFACE_LIST = "Select * from Win32_NetworkAdapter";
    public static final String GET_ACTIVE_INTERFACE_LIST = "Select * from Win32_NetworkAdapter Where NetConnectionStatus=2";

    public static final String GET_MAC_ADDRESS(String netInterface) {
        return "Select MACAddress from Win32_NetworkAdapter Where NetConnectionID='" + netInterface + "'";
    }

    public static final String GET_TCP_WINDOW_SIZE(String MACAddress) {
        return "Select TcpWindowSize from Win32_NetworkAdapterConfiguration Where MACAddress='" + MACAddress + "'";
    }

    public static final String PING(String ipAddress) {
        return "Select * From Win32_PingStatus where Address = '" + ipAddress + "'";
    }

    public static final String GET_GATEWAY(String MACAddress) {
        return "Select DHCPServer From Win32_NetworkAdapterConfiguration Where MACAddress = '" + MACAddress + "'";
    }

    public static final String GET_ALL_NetworkAdapterConf(String netInterface) {
        return "Select * from Win32_NetworkAdapter Where NetConnectionID='" + netInterface + "'";
    }

    public static final String GET_PHYSICAL_INTERFACE_LIST = "Select * from Win32_NetworkAdapter where PNPDeviceID Like \"PCI%\" OR PNPDeviceID Like \"USB%\"";

    public static final String GET_INTERFACE_INDEX(String netInterface) {
        return "Select InterfaceIndex from Win32_NetworkAdapter where NetConnectionID=\"" + netInterface + "\"";
    }

    public static final String GET_NETWORK_ADAPTER_CONFIG(String MACAddress) {
        return "Select * from Win32_NetworkAdapterConfiguration where MACAddress=\"" + MACAddress + "\"";
    }

    public static String GET_INTERFACE_INDEX_XP(String netInterface) {
        return "Select Index from Win32_NetworkAdapter where NetConnectionID=\"" + netInterface + "\"";
    }

    public static String GET_OS_INFO() {
        return "Select * from Win32_OperatingSystem";
    }

    public static String GET_GUID(Integer index) {
        return "Select SettingID from Win32_NetworkAdapterConfiguration Where Index=\"" + index + "\"";
    }
}
