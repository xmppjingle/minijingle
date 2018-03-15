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

package org.minijingle.media;

import org.minijingle.jingle.description.Payload;
import org.minijingle.jingle.transport.Candidate;

import java.util.List;

public interface MediaManager {

    public void startMedia(final Candidate local, final Candidate remote, final Payload payload);

    public void stopMedia();

    public List<Payload> getPayloads();
}
