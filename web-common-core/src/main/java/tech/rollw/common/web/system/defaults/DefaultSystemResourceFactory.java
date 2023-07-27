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

import tech.rollw.common.web.BusinessRuntimeException;
import tech.rollw.common.web.system.*;

import java.util.List;

/**
 * @author RollW
 */
public class DefaultSystemResourceFactory implements SystemResourceFactory {
    private final List<SystemResourceProvider> systemResourceProviders;

    public DefaultSystemResourceFactory(List<SystemResourceProvider> systemResourceProviders) {
        this.systemResourceProviders = systemResourceProviders;
    }

    @Override
    public SystemResource getSystemResource(long resourceId,
                                            SystemResourceKind systemResourceKind)
            throws BusinessRuntimeException, UnsupportedKindException {
        SystemResourceProvider systemResourceProvider = findFirstProvider(systemResourceKind);
        return systemResourceProvider.provide(resourceId, systemResourceKind);
    }

    @Override
    public SystemResource getSystemResource(SystemResource systemResource) {
        return getSystemResource(systemResource.getResourceId(), systemResource.getSystemResourceKind());
    }

    private SystemResourceProvider findFirstProvider(SystemResourceKind systemResourceKind) {
        return systemResourceProviders.stream()
                .filter(systemResourceProvider -> systemResourceProvider.supports(systemResourceKind))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No provider found for resource kind: " + systemResourceKind));
    }
}
