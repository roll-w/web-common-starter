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

package tech.rollw.common.web.system.defaults;

import space.lingu.NonNull;
import tech.rollw.common.web.CommonRuntimeException;
import tech.rollw.common.web.system.SystemResource;
import tech.rollw.common.web.system.SystemResourceFactory;
import tech.rollw.common.web.system.SystemResourceKind;
import tech.rollw.common.web.system.SystemResourceProvider;
import tech.rollw.common.web.system.UnsupportedKindException;

import java.util.List;

/**
 * @author RollW
 */
public class DefaultSystemResourceFactory<ID> implements SystemResourceFactory<ID> {
    private final List<SystemResourceProvider<? extends ID>> systemResourceProviders;

    public DefaultSystemResourceFactory(List<SystemResourceProvider<? extends ID>> systemResourceProviders) {
        this.systemResourceProviders = systemResourceProviders;
    }

    @NonNull
    @Override
    public SystemResource<ID> getSystemResource(@NonNull ID resourceId,
                                                @NonNull SystemResourceKind systemResourceKind)
            throws CommonRuntimeException, UnsupportedKindException {
        SystemResourceProvider<ID> systemResourceProvider = findFirstProvider(systemResourceKind);
        return systemResourceProvider.provide(resourceId, systemResourceKind);
    }

    @NonNull
    @Override
    public SystemResource<ID> getSystemResource(@NonNull SystemResource<ID> systemResource) {
        SystemResourceProvider<ID> systemResourceProvider = findFirstProvider(
                systemResource.getSystemResourceKind());
        return systemResourceProvider.provide(systemResource);
    }

    @SuppressWarnings("unchecked")
    private SystemResourceProvider<ID> findFirstProvider(SystemResourceKind systemResourceKind) {
        return (SystemResourceProvider<ID>) systemResourceProviders.stream()
                .filter(systemResourceProvider -> systemResourceProvider.supports(systemResourceKind))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No provider found for resource kind: " + systemResourceKind));
    }
}
