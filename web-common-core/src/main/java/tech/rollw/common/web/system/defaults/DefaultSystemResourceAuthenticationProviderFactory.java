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
import tech.rollw.common.web.AuthErrorCode;
import tech.rollw.common.web.system.*;


import java.util.List;

/**
 * @author RollW
 */
public class DefaultSystemResourceAuthenticationProviderFactory<ID>
        implements SystemResourceAuthenticationProviderFactory<ID> {
    private final List<SystemResourceAuthenticationProvider<? extends ID>> authenticationProviders;
    private SystemResourceAuthenticationProvider<? extends ID> defaultSystemResourceAuthenticationProvider;

    public DefaultSystemResourceAuthenticationProviderFactory(List<SystemResourceAuthenticationProvider<? extends ID>> authenticationProviders) {
        this.authenticationProviders = authenticationProviders;
        setDefaultSystemResourceAuthenticationProvider(getDefaultProvider());
    }

    @Override
    public SystemResourceAuthenticationProvider<ID> getSystemResourceAuthenticationProvider(
            SystemResourceKind resourceKind) {
        SystemResourceAuthenticationProvider<ID> systemResourceAuthenticationProvider =
                findFirstAuthenticationProvider(resourceKind);
        if (systemResourceAuthenticationProvider == null) {
            throw new SystemResourceException(AuthErrorCode.ERROR_NO_HANDLER,
                    "No authentication provider found for resource kind: " + resourceKind +
                            ", or set a default authentication provider.");
        }
        return systemResourceAuthenticationProvider;
    }

    @Override
    public void setDefaultSystemResourceAuthenticationProvider(
            SystemResourceAuthenticationProvider<ID> systemResourceAuthenticationProvider) {
        this.defaultSystemResourceAuthenticationProvider = systemResourceAuthenticationProvider;
    }

    @SuppressWarnings("unchecked")
    private SystemResourceAuthenticationProvider<ID> findFirstAuthenticationProvider(
            SystemResourceKind resourceKind) {
        return (SystemResourceAuthenticationProvider<ID>) authenticationProviders.stream()
                .filter(authenticationProvider -> authenticationProvider.supports(resourceKind))
                .findFirst()
                .orElse(defaultSystemResourceAuthenticationProvider);
    }

    private static class DefaultProvider<ID> implements SystemResourceAuthenticationProvider<ID> {
        private DefaultProvider() {
        }


        @Override
        public boolean supports(@NonNull SystemResourceKind systemResourceKind) {
            return true;
        }

        @NonNull
        @Override
        public SystemAuthentication<ID> authenticate(SystemResource<ID> systemResource,
                                                     Operator operator, Action action,
                                                     SystemAuthenticateCredentials credentials) {
            return new SimpleSystemAuthentication<>(systemResource, operator, credentials, true);
        }

        static final DefaultProvider<Object> INSTANCE = new DefaultProvider<>();
    }

    @SuppressWarnings("unchecked")
    public static <ID> SystemResourceAuthenticationProvider<ID> getDefaultProvider() {
        return (SystemResourceAuthenticationProvider<ID>) DefaultProvider.INSTANCE;
    }
}
