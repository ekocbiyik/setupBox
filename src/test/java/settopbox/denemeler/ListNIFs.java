package settopbox.denemeler;

import java.io.*;
import java.net.*;
import java.util.*;
import static java.lang.System.out;

public class ListNIFs 
{
    public static void main(String args[]) throws SocketException {

        display2();

//        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
//
//        for (NetworkInterface netIf : Collections.list(nets)) {
//
//            System.out.println("-------------------------------------------------------------------------");
//            System.out.println("Display name: "+ netIf.getDisplayName());
//            System.out.println("Name: "+ netIf.getName());
//            System.out.println("isUp: "+ netIf.isUp());
//            System.out.println("isVirtual: "+ netIf.isVirtual());
//            displaySubInterfaces(netIf);
//            System.out.println("-------------------------------------------------------------------------");
//
//        }
//        System.out.println("\n");
    }

    static void displaySubInterfaces(NetworkInterface netIf) throws SocketException {


        Enumeration<NetworkInterface> subIfs = netIf.getSubInterfaces();
        for (NetworkInterface subIf : Collections.list(subIfs)) {
            System.out.println("\tSub Interface Display name: "+ subIf.getDisplayName());
            System.out.println("\tSub Interface Name: "+ subIf.getName());

        }

     }

    static void display2() throws SocketException {

        try
        {
            System.out.println("Output of Network Interrogation:");
            System.out.println("********************************\n");

            InetAddress theLocalhost = InetAddress.getLocalHost();
            System.out.println(" LOCALHOST INFO");
            if(theLocalhost != null)
            {
                System.out.println("          host: " + theLocalhost.getHostName());
                System.out.println("         class: " + theLocalhost.getClass().getSimpleName());
                System.out.println("            ip: " + theLocalhost.getHostAddress());
                System.out.println("         chost: " + theLocalhost.getCanonicalHostName());
                System.out.println("      byteaddr: " + toMACAddrString(theLocalhost.getAddress()));
                System.out.println("    sitelocal?: " + theLocalhost.isSiteLocalAddress());
                System.out.println("");
            }
            else
            {
                System.out.println(" localhost was null");
            }

            Enumeration<NetworkInterface> theIntfList = NetworkInterface.getNetworkInterfaces();
            List<InterfaceAddress> theAddrList = null;
            NetworkInterface theIntf = null;
            InetAddress theAddr = null;

            while(theIntfList.hasMoreElements())
            {
                theIntf = theIntfList.nextElement();

                System.out.println("--------------------");
                System.out.println(" " + theIntf.getDisplayName());
                System.out.println("          name: " + theIntf.getName());
                System.out.println("           mac: " + toMACAddrString(theIntf.getHardwareAddress()));
                System.out.println("           mtu: " + theIntf.getMTU());
                System.out.println("        mcast?: " + theIntf.supportsMulticast());
                System.out.println("     loopback?: " + theIntf.isLoopback());
                System.out.println("          ptp?: " + theIntf.isPointToPoint());
                System.out.println("      virtual?: " + theIntf.isVirtual());
                System.out.println("           up?: " + theIntf.isUp());

                theAddrList = theIntf.getInterfaceAddresses();
                System.out.println("     int addrs: " + theAddrList.size() + " total.");
                int addrindex = 0;
                for(InterfaceAddress intAddr : theAddrList)
                {
                    addrindex++;
                    theAddr = intAddr.getAddress();
                    System.out.println("            " + addrindex + ").");
                    System.out.println("            host: " + theAddr.getHostName());
                    System.out.println("           class: " + theAddr.getClass().getSimpleName());
                    System.out.println("              ip: " + theAddr.getHostAddress() + "/" + intAddr.getNetworkPrefixLength());
//                    System.out.println("           bcast: " + intAddr.getBroadcast().getHostAddress());
                    int maskInt = Integer.MIN_VALUE >> (intAddr.getNetworkPrefixLength()-1);
                    System.out.println("            mask: " + toIPAddrString(maskInt));
                    System.out.println("           chost: " + theAddr.getCanonicalHostName());
                    System.out.println("        byteaddr: " + toMACAddrString(theAddr.getAddress()));
                    System.out.println("      sitelocal?: " + theAddr.isSiteLocalAddress());
                    System.out.println("");
                }
            }
        }
        catch (SocketException e)
        {

        }
        catch (UnknownHostException e)
        {

        }


    }

    public static String toMACAddrString(byte[] a)
    {
        if (a == null)
        {
            return "null";
        }
        int iMax = a.length - 1;

        if (iMax == -1)
        {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0;; i++)
        {
            b.append(String.format("%1$02x", a[i]));

            if (i == iMax)
            {
                return b.append(']').toString();
            }
            b.append(":");
        }
    }

    public static String toIPAddrString(int ipa)
    {
        StringBuilder b = new StringBuilder();
        b.append(Integer.toString(0x000000ff & (ipa >> 24)));
        b.append(".");
        b.append(Integer.toString(0x000000ff & (ipa >> 16)));
        b.append(".");
        b.append(Integer.toString(0x000000ff & (ipa >> 8)));
        b.append(".");
        b.append(Integer.toString(0x000000ff & (ipa)));
        return b.toString();
    }




}  