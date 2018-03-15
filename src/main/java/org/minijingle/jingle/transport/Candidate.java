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

package org.minijingle.jingle.transport;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("candidate")
public class Candidate {

    @XStreamAsAttribute
    private final String ip, port, generation;

    public Candidate(String ip, String port, String generation) {
        this.ip = ip;
        this.port = port;
        this.generation = generation;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getGeneration() {
        return generation;
    }
}
