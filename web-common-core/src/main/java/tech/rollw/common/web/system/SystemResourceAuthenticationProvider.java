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

import java.util.List;

/**
 * @author RollW
 */
public interface SystemResourceAuthenticationProvider<ID> extends SystemResourceSupportable {
    @Override
    boolean supports(@NonNull SystemResourceKind systemResourceKind);

    @NonNull
    SystemAuthentication<ID> authenticate(SystemResource<ID> systemResource,
                                          Operator operator, Action action,
                                          SystemAuthenticateCredentials credentials);

    @NonNull
    default List<SystemAuthentication<ID>> authenticate(
            @NonNull List<? extends SystemResource<ID>> systemResources,
            Operator operator, Action action, SystemAuthenticateCredentials credentials) {
        return systemResources.stream()
                .map(systemResource -> authenticate(systemResource, operator, action, credentials))
                .toList();
    }
}
