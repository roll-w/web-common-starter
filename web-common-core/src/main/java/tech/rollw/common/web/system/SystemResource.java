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

import space.lingu.NonNull;
import tech.rollw.common.Castable;

/**
 * Define the system resource.
 *
 * @param <ID> the type of the system resource id
 * @author RollW
 * @apiNote Should always keep the {@link ID} the same type within
 * the same {@link SystemResourceKind}, or it will cause some
 * unexpected errors like {@link ClassCastException} when trying to
 * cast the given ID to the specific type.
 */
public interface SystemResource<ID> extends SystemResourceKind.Kind, Castable {
    ID getResourceId();

    @Override
    @NonNull
    SystemResourceKind getSystemResourceKind();
}
