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

import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.XMPPConnection
import org.jivesoftware.smack.XMPPException
import org.jivesoftware.smack.Roster


abstract class Client(private val username: String, private val password: String, server: String) {

    protected val connection: XMPPConnection

    init {
        val conf = ConnectionConfiguration(server, 5222)
        conf.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
        conf.setSASLAuthenticationEnabled(false)
        connection = XMPPConnection(conf)
    }

    protected fun login() {

        try {
            connection.connect()
            connection.login(username, password)
            connection.getRoster().setSubscriptionMode(Roster.SubscriptionMode.accept_all)

            loggedIn()
        } catch (e: XMPPException) {
            e.printStackTrace()
            loggedOut()
        }

    }

    protected abstract fun loggedIn()

    protected abstract fun loggedOut()
}
