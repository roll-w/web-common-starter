/*
 * Copyright (C) 2023 RollW
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.rollw.common.event;

/**
 * @author RollW
 */
public interface EventRegistry<R, M> {
    /**
     * Register an event callback with a message pattern.
     * <p>
     * When an event is published, the event registry will call the event callback
     * if the message pattern matches the event.
     *
     * @param eventCallback  the event callback
     * @param messagePattern the message pattern, used to filter events
     * @return a unique event callback ID (can be used to unregister the event callback),
     * or the same if the event callback is already registered.
     */
    String register(EventCallback<R> eventCallback,
                    M messagePattern);

    /**
     * Unregister an event callback by its event ID.
     *
     * @param eventId the event ID
     */
    void unregister(String eventId);
}
