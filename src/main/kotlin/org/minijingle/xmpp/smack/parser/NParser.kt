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

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException

import java.io.IOException

class NParser(private val parser: XmlPullParser) {

    val asString: String
        get() {

            val str = StringBuilder()
            var ll = -1
            var lc = -1

            val name = parser.getName()

            try {
                while (parser.getEventType() !== XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType() === XmlPullParser.START_TAG || parser.getEventType() === XmlPullParser.END_TAG) {
                        val cl = parser.getLineNumber()
                        val cc = parser.getColumnNumber()
                        if (ll != cl || lc != cc) {
                            str.append(parser.getText())
                            if (parser.getEventType() === XmlPullParser.END_TAG && name.equals(parser.getName())) {
                                break
                            } else if (parser.getEventType() !== XmlPullParser.END_TAG && parser.isEmptyElementTag() && name.equals(parser.getName())) {
                                break
                            }
                        }
                    }
                    ll = parser.getLineNumber()
                    lc = parser.getColumnNumber()
                    parser.next()
                }

                System.out.println("Received: " + str.toString())
            } catch (e: XmlPullParserException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return str.toString()
        }
}