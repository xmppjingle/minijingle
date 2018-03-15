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

package org.minijingle.jingle;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.minijingle.jingle.content.Content;

@XStreamAlias("jingle")
public class Jingle {

    public final static String SESSION_INITIATE = "session-initiate";
    public final static String SESSION_TERMINATE = "session-terminate";
    public final static String SESSION_ACCEPT = "session-accept";

    @XStreamAsAttribute
    @XStreamAlias("xmlns")
    public final String NAMESPACE = "urn:xmpp:tmp:jingle";
    public static final String XMLNS = "urn:xmpp:tmp:jingle";

    @XStreamAsAttribute
    private String action, sid, initiator, responder;

    private Content content;

    public Jingle(String sid, String initiator, String responder, String action) {
        this.sid = sid;
        this.initiator = initiator;
        this.responder = responder;
        this.action = action;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }

    public String getSid() {
        return sid;
    }

    public String getInitiator() {
        return initiator;
    }

    public String getResponder() {
        return responder;
    }

    public String getAction() {
        return action;
    }

}
