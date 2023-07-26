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

import java.util.List;

/**
 * Like {@link tech.rollw.common.web.ErrorCode}, needs to define an enum
 * class to implement this interface (suggest using {@link Enum}, but can
 * also define a class that implements this interface, its depend on your
 * needs).
 *
 * @author RollW
 */
public interface SystemResourceKind {
    String getName();

    List<String> getAliases();

    interface Kind {
        SystemResourceKind getSystemResourceKind();
    }
}
