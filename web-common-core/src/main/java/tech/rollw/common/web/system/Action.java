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

package tech.rollw.common.web.system;

import java.util.function.Function;

/**
 * Define the action of the system, such as create, update, delete, etc.
 *
 * @author RollW
 */
public interface Action {
    String getName();

    default boolean equals(Action other) {
        if (other == null) {
            return false;
        }
        return this.getName().equals(other.getName());
    }

    static boolean equals(Action action1, Action action2) {
        if (action1 == null || action2 == null) {
            return false;
        }
        return action1.equals(action2);
    }

    /**
     * Check if the action accepts the given system resource kind.
     * The default implementation returns true, meaning all actions accept all kinds.
     * <p>
     * This can be overridden by specific actions to restrict the kinds they accept.
     * For example, a "delete" action may only accept "file" and "directory" kinds,
     * while a "view" action may accept all kinds.
     *
     * @param systemResourceKind the kind of system resource
     * @return true if the action accepts the kind, false otherwise
     */
    default boolean accepts(SystemResourceKind systemResourceKind) {
        return true;
    }

    static Action of(String name) {
        return new SimpleAction(name);
    }

    static Action of(String name, Function<SystemResourceKind, Boolean> acceptsFunction) {
        return new SimpleAction(name, acceptsFunction);
    }
}
