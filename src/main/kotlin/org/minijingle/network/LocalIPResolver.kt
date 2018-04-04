/*
 *
 *  Copyright 2009 Jingle Nodes
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package org.minijingle.network

import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.net.UnknownHostException
import java.util.Enumeration

object LocalIPResolver {

    val localIP: String
        get() {

            var ifaces: Enumeration

            try {
                ifaces = NetworkInterface.getNetworkInterfaces()

                while (ifaces.hasMoreElements()) {

                    val iface = ifaces.nextElement() as NetworkInterface
                    val iaddresses = iface.getInetAddresses()

                    while (iaddresses.hasMoreElements()) {
                        val iaddress = iaddresses.nextElement() as InetAddress
                        if (!iaddress.isLoopbackAddress() && !iaddress.isLinkLocalAddress() && !iaddress.isSiteLocalAddress()) {
                            return if (iaddress.getHostAddress() != null) iaddress.getHostAddress() else iaddress.getHostName()
                        }
                    }
                }

                ifaces = NetworkInterface.getNetworkInterfaces()

                while (ifaces.hasMoreElements()) {

                    val iface = ifaces.nextElement() as NetworkInterface
                    val iaddresses = iface.getInetAddresses()

                    while (iaddresses.hasMoreElements()) {
                        val iaddress = iaddresses.nextElement() as InetAddress
                        if (!iaddress.isLoopbackAddress() && !iaddress.isLinkLocalAddress()) {
                            return if (iaddress.getHostAddress() != null) iaddress.getHostAddress() else iaddress.getHostName()
                        }
                    }
                }

                return if (InetAddress.getLocalHost().getHostAddress() != null) InetAddress.getLocalHost().getHostAddress() else InetAddress.getLocalHost().getHostName()

            } catch (e: SocketException) {
                e.printStackTrace()
            } catch (e: UnknownHostException) {
                e.printStackTrace()
            }

            return "127.0.0.1"
        }
}
