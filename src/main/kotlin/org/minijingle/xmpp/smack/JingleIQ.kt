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

import org.jivesoftware.smack.packet.IQ
import org.minijingle.jingle.Jingle
import org.minijingle.xmpp.smack.parser.XStreamIQ

class JingleIQ(element: Jingle) : XStreamIQ<Jingle>(element) {

    val element: Jingle
        get() = super.getElement()

    init {
        this.setType(IQ.Type.SET)
    }

    companion object {

        fun fromXml(xml: String): JingleIQ {
            val j = JingleIQ.getStream().fromXML(xml) as Jingle
            return JingleIQ(j)
        }

        fun createResult(request: IQ): IQ {
            val iq = object : IQ() {
                val childElementXML: String
                    get() = ""
            }
            iq.setType(IQ.Type.RESULT)
            iq.setTo(request.getFrom())
            iq.setFrom(request.getTo())
            return iq
        }
    }

}
