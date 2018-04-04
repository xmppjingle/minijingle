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

package org.minijingle.xmpp.smack

import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.packet.IQ
import org.jivesoftware.smack.packet.PacketExtension
import org.jivesoftware.smack.packet.Presence
import org.jivesoftware.smack.provider.IQProvider
import org.jivesoftware.smack.provider.ProviderManager
import org.minijingle.xmpp.smack.parser.NParser
import org.minijingle.jingle.Jingle
import org.minijingle.jingle.transport.RawUdpTransport
import org.xmlpull.v1.XmlPullParser

internal class JingleProvider : IQProvider {

    /**
     * Parse a iq/jingle element.
     */
    @Throws(Exception::class)
    fun parseIQ(parser: XmlPullParser): IQ {

        val p = NParser(parser)
        return JingleIQ.fromXml(p.getAsString())

    }

    companion object {

        fun enableJingle(connection: XMPPConnection) {

            ProviderManager.getInstance().addIQProvider("jingle", Jingle.XMLNS, JingleProvider())
            JingleIQ.getStream().alias("jingle", Jingle::class.java)
            JingleIQ.getStream().alias("transport", RawUdpTransport::class.java)

            val presence = Presence(Presence.Type.available)
            presence.addExtension(object : PacketExtension() {
                val elementName: String
                    get() = "c"

                val namespace: String
                    get() = "http://jabber.org/protocol/caps"

                fun toXML(): String {
                    return "<c xmlns=\"http://jabber.org/protocol/caps\" node=\"client:caps\" ver=\"0.1\" ext=\"jingle-v1\"></c>"
                }
            })

            for (i in 0..4) {
                connection.sendPacket(presence)
            }
        }
    }

}
