package settopbox.denemeler;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by enbiya on 05.10.2016.
 */
public class ListNets {

    public static void main(String[] args) throws SocketException {

        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

        for (NetworkInterface netint : Collections.list(nets)) {
            displayInterfaceInformation(netint);
        }

    }


    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {

//        if (netint.getName().contains("ASIX") || netint.getDisplayName().contains("ASIX") ){

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("disName: "+netint.getDisplayName());
        System.out.println("name: "+netint.getName());
        System.out.println("isUp: "+netint.isUp());
        System.out.println("isVirtual: "+netint.isVirtual());

        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                System.out.println("InetAddress: "+ inetAddress);
            }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\n\n");


    }

}
