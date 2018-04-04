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

package org.minijingle.jingle.description

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamAsAttribute
import com.thoughtworks.xstream.annotations.XStreamImplicit

import java.util.ArrayList

@XStreamAlias("description")
class Description(@field:XStreamAsAttribute
                  val media: String) {

    @XStreamAsAttribute
    @XStreamAlias("xmlns")
    val NAMESPACE = "urn:xmpp:tmp:jingle:apps:rtp"

    @XStreamImplicit
    @XStreamAlias("payload-type")
    val payloads: List<Payload> = ArrayList<Payload>()

    fun addPayload(payload: Payload) {
        payloads.add(payload)
    }
}

