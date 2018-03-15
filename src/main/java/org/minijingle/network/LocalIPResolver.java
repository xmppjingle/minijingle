/*
 * Copyright 2009 Jingle Nodes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.minijingle.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class LocalIPResolver {

    public static String getLocalIP() {

        Enumeration ifaces;

        try {
            ifaces = NetworkInterface.getNetworkInterfaces();

            while (ifaces.hasMoreElements()) {

                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                Enumeration iaddresses = iface.getInetAddresses();

                while (iaddresses.hasMoreElements()) {
                    InetAddress iaddress = (InetAddress) iaddresses.nextElement();
                    if (!iaddress.isLoopbackAddress() && !iaddress.isLinkLocalAddress() && !iaddress.isSiteLocalAddress()) {
                        return iaddress.getHostAddress() != null ? iaddress.getHostAddress() : iaddress.getHostName();
                    }
                }
            }

            ifaces = NetworkInterface.getNetworkInterfaces();

            while (ifaces.hasMoreElements()) {

                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                Enumeration iaddresses = iface.getInetAddresses();

                while (iaddresses.hasMoreElements()) {
                    InetAddress iaddress = (InetAddress) iaddresses.nextElement();
                    if (!iaddress.isLoopbackAddress() && !iaddress.isLinkLocalAddress()) {
                        return iaddress.getHostAddress() != null ? iaddress.getHostAddress() : iaddress.getHostName();
                    }
                }
            }

            return InetAddress.getLocalHost().getHostAddress() != null ? InetAddress.getLocalHost().getHostAddress() : InetAddress.getLocalHost().getHostName();

        }
        catch (SocketException e) {
            e.printStackTrace();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "127.0.0.1";
    }
}
