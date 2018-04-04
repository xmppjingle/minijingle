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

package org.minijingle.jingle

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute
import org.minijingle.jingle.content.Content

@XStreamAlias("jingle")
class Jingle(@field:XStreamAsAttribute
             val sid: String, @field:XStreamAsAttribute
             val initiator: String, @field:XStreamAsAttribute
             val responder: String, @field:XStreamAsAttribute
             val action: String) {

    @XStreamAsAttribute
    @XStreamAlias("xmlns")
    val NAMESPACE = "urn:xmpp:tmp:jingle"

    var content: Content? = null

    companion object {

        val SESSION_INITIATE = "session-initiate"
        val SESSION_TERMINATE = "session-terminate"
        val SESSION_ACCEPT = "session-accept"
        val XMLNS = "urn:xmpp:tmp:jingle"
    }

}
