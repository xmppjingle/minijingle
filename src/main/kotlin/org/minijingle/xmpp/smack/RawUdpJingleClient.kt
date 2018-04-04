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

import org.minijingle.jingle.description.Payload
import org.minijingle.jingle.transport.Candidate
import org.minijingle.jingle.transport.RawUdpTransport
import org.minijingle.media.MediaManager
import org.minijingle.network.LocalIPResolver

class RawUdpJingleClient(username: String, password: String, server: String, private val jingleProxy: String) : Client(username, password, server) {

    private var callManager: RawUdpCallManager? = null

    init {
        login()
    }

    protected fun loggedIn() {

        JingleProvider.enableJingle(connection)

        this.callManager = RawUdpCallManager(connection, object : MediaManager() {

            val payloads: List<Payload>?
                get() = null

            fun startMedia(local: Candidate, remote: Candidate, payload: Payload) {

            }

            fun stopMedia() {

            }
        }, RawUdpTransport(Candidate(LocalIPResolver.getLocalIP(), "10002", "0")), jingleProxy)

    }

    protected fun loggedOut() {

    }

    companion object {

        fun main(args: Array<String>) {

            RawUdpJingleClient(args[1], args[2], args[3], args[4])

            for (i in 0..999)
                try {
                    Thread.sleep(10000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }


        }
    }

}
