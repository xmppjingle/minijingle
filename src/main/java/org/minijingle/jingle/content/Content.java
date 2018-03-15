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
package org.minijingle.jingle.content;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.minijingle.jingle.description.Description;
import org.minijingle.jingle.transport.RawUdpTransport;

@XStreamAlias("content")
public class Content {

    @XStreamAsAttribute
    private String creator, name, senders;

    private Description description;
    private RawUdpTransport transport;

    public Content(String creator, String name, String senders, Description description, RawUdpTransport transport) {
        this.creator = creator;
        this.name = name;
        this.senders= senders;
        this.description = description;
        this.transport = transport;
    }

    public String getCreator() {
        return creator;
    }

    public String getName() {
        return name;
    }

    public String getSenders() {
        return senders;
    }

    public Description getDescription() {
        return description;
    }

    public RawUdpTransport getTransport() {
        return transport;
    }
}
