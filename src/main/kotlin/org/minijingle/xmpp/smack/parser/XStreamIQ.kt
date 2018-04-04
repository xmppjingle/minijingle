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

package org.minijingle.xmpp.smack.parser

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import com.thoughtworks.xstream.mapper.MapperWrapper
import org.jivesoftware.smack.packet.IQ

class XStreamIQ<T>(element: T) : IQ() {

    private val element: Object

    val childElementXML: String
        get() = this.element.toString()

    init {
        this.element = element
    }

    fun getElement(): T {
        return element
    }

    companion object {

        val stream: XStream = object : XStream(DomDriver()) {
            protected fun wrapMapper(next: MapperWrapper): MapperWrapper {
                return object : MapperWrapper(next) {
                    fun shouldSerializeMember(definedIn: Class, fieldName: String): Boolean {
                        return definedIn !== Object::class.java && super.shouldSerializeMember(definedIn, fieldName)
                    }
                }
            }
        }

        init {
            stream.autodetectAnnotations(true)
        }
    }
}
