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

import java.util.Objects;
import java.util.function.Function;

/**
 * @author RollW
 */
public final class SimpleAction implements Action {
    private final String name;
    private final Function<SystemResourceKind, Boolean> acceptsFunction;

    public SimpleAction(String name) {
        this.name = name;
        this.acceptsFunction = null;
    }

    public SimpleAction(String name, Function<SystemResourceKind, Boolean> acceptsFunction) {
        this.name = name;
        this.acceptsFunction = acceptsFunction;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean accepts(SystemResourceKind systemResourceKind) {
        if (acceptsFunction != null) {
            return acceptsFunction.apply(systemResourceKind);
        }
        return true; // Default behavior is to accept all kinds
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SimpleAction that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "SimpleAction{" +
                "name='" + name + '\'' +
                '}';
    }
}
