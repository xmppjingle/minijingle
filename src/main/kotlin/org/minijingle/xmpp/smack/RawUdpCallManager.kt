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

import org.jivesoftware.smack.PacketListener
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.filter.PacketFilter
import org.jivesoftware.smack.packet.Packet
import org.minijingle.jingle.Jingle
import org.minijingle.jingle.content.Content
import org.minijingle.jingle.description.Description
import org.minijingle.jingle.description.Payload
import org.minijingle.jingle.transport.Candidate
import org.minijingle.jingle.transport.RawUdpTransport
import org.minijingle.media.MediaManager

class RawUdpCallManager(private val connection: XMPPConnection, private val mediaManager: MediaManager, private val transport: RawUdpTransport, private val proxy: String?) : PacketListener {

    private var localCandidate: Candidate? = null
    private val localDescription: Description
    private var remoteCandidate: Candidate? = null
    private var sid: String? = null
    private var inACall = false

    init {
        this.localDescription = Description("audio")
        for (payload in mediaManager.getPayloads()) {
            this.localDescription.addPayload(payload)
        }
        this.connection.addPacketListener(this, object : PacketFilter() {
            fun accept(packet: Packet): Boolean {
                return true
            }
        })
    }

    fun processPacket(packet: Packet) {

        if (packet is JingleIQ) {
            processJingle(packet as JingleIQ)
        }

    }

    private fun processJingle(jingleIQ: JingleIQ) {

        connection.sendPacket(JingleIQ.createResult(jingleIQ))

        val jingle = jingleIQ.getElement()

        // Incomming Call
        if (Jingle.SESSION_INITIATE.equals(jingle.getAction())) {
            if (sid == null && !inACall) {
                //Auto Accept
                acceptCall(jingle)
            }
        } else if (Jingle.SESSION_ACCEPT.equals(jingle.getAction())) {
            if (!inACall) {
                callAccepted(jingle)
            }
        } else if (Jingle.SESSION_TERMINATE.equals(jingle.getAction())) {
            if (sid != null) {
                terminateCall(jingle)
            }
        }// Call Terminated
        // Call Accepted

    }

    private fun callAccepted(jingle: Jingle) {

        if (jingle.getContent().getTransport().getCandidates().size() > 0 && jingle.getContent().getDescription().getPayloads().size() > 0) {

            inACall = true
            remoteCandidate = jingle.getContent().getTransport().getCandidates().get(0)
            mediaManager.startMedia(localCandidate, remoteCandidate, mediaManager.getPayloads().get(0))
        }

    }

    private fun terminateCall(jingle: Jingle) {

        mediaManager.stopMedia()
        inACall = false
        sid = null

    }

    private fun acceptCall(jingle: Jingle) {

        if (jingle.getContent().getTransport().getCandidates().size() > 0 && jingle.getContent().getDescription().getPayloads().size() > 0) {

            inACall = true
            sid = jingle.getSid()
            remoteCandidate = jingle.getContent().getTransport().getCandidates().get(0)
            localCandidate = transport.getCandidates().get(0)

            val localContent = Content(jingle.getContent().getCreator(), connection.getUser().split("/")[0], "both", localDescription, transport)
            val accept = Jingle(sid, jingle.getInitiator(), this.connection.getUser(), Jingle.SESSION_ACCEPT)
            accept.setContent(localContent)

            val acceptIQ = JingleIQ(accept)
            acceptIQ.setFrom(connection.getUser())

            if (proxy != null) {
                acceptIQ.setTo(proxy)
            } else {
                acceptIQ.setTo(jingle.getInitiator())
            }

            System.out.println("Sent: " + acceptIQ.toXML())

            mediaManager.startMedia(localCandidate, remoteCandidate, localDescription.getPayloads().get(0))

            connection.sendPacket(acceptIQ)

        }

    }

}
