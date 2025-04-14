/*
 * Copyright (C) 2023-2025 RollW
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

import space.lingu.NonNull;

import java.util.List;

/**
 * Like {@link tech.rollw.common.web.ErrorCode}, needs to define an enum
 * class to implement this interface (suggest using {@link Enum}, but can
 * also define a class that implements this interface, its depend on your
 * needs).
 *
 * @author RollW
 */
@FunctionalInterface
public interface SystemResourceKind {
    /**
     * Get the case-insensitive name of this system resource kind.
     *
     * @return the name of this system resource kind
     */
    @NonNull
    String getName();

    /**
     * Get the case-insensitive aliases of this kind (may not include the name of this resource kind).
     *
     * @return the aliases of this kind
     */
    @NonNull
    default List<String> getAliases() {
        return List.of();
    }

    /**
     * Check if this kind is equal to the given kind.
     * <p>
     * This method will check the name and aliases of this kind and the
     * given kind. If any of them is equal, return true.
     *
     * @param kind the kind to check
     * @return true if this kind is equal to the given kind, false otherwise
     */
    default boolean equals(SystemResourceKind kind) {
        if (kind == null) {
            return false;
        }
        if (this == kind) {
            return true;
        }
        if (getName().equalsIgnoreCase(kind.getName())) {
            return true;
        }
        for (String alias : getAliases()) {
            if (alias.equalsIgnoreCase(kind.getName())) {
                return true;
            }
            for (String kindAlias : kind.getAliases()) {
                if (alias.equalsIgnoreCase(kindAlias)) {
                    return true;
                }
            }
        }
        return false;
    }

    @FunctionalInterface
    interface Kind {
        SystemResourceKind getSystemResourceKind();
    }

    static SystemResourceKind of(@NonNull String name) {
        return new SimpleSystemResourceKind(name);
    }

    static SystemResourceKind of(@NonNull String name, @NonNull String... aliases) {
        return new SimpleSystemResourceKind(name, aliases);
    }

    /**
     * Check if two {@link SystemResourceKind} are equal.
     * Delegate to {@link #equals(SystemResourceKind)} method.
     *
     * @param kind1 the first kind
     * @param kind2 the second kind
     * @return true if they are equal, false otherwise
     */
    static boolean equals(SystemResourceKind kind1, SystemResourceKind kind2) {
        if (kind1 == null || kind2 == null) {
            return false;
        }
        if (kind1 == kind2) {
            return true;
        }
        return kind1.equals(kind2) || kind2.equals(kind1);
    }
}
