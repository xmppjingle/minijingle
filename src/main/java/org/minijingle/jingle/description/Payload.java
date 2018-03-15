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

package org.minijingle.jingle.description;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("payload-type")
public class Payload {

    @XStreamAsAttribute
    private final String id, name;

    @XStreamAsAttribute
    private int clockrate;

    public Payload(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Payload(String id, String name, int clockrate) {
        this.id = id;
        this.name = name;
        this.clockrate = clockrate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getClockrate() {
        return clockrate;
    }
}
