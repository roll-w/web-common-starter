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

package tech.rollw.common.web.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.rollw.common.web.system.*;
import tech.rollw.common.web.system.defaults.DefaultSystemResourceAuthenticationProviderFactory;
import tech.rollw.common.web.system.defaults.DefaultSystemResourceFactory;
import tech.rollw.common.web.system.defaults.DefaultSystemResourceOperatorProvider;

import java.util.List;

/**
 * System resource autoconfiguration.
 * <p>
 * You can override the default implementation by
 * implementing the corresponding interface and registering it as a bean.
 * <p>
 * Import beans in your code like:
 * <pre>{@code
 *    @Autowired
 *    SystemResourceAuthenticationProviderFactory&lt;String&gt; systemResourceAuthenticationProviderFactory;
 * }</pre>
 *
 * @author RollW
 * @see SystemResourceAuthenticationProviderFactory
 * @see SystemResourceOperatorProvider
 * @see SystemResourceProvider
 */
@AutoConfiguration
@Configuration
public class SystemResourceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SystemResourceAuthenticationProviderFactory<?> defaultSystemResourceAuthenticationProviderFactory(
            List<SystemResourceAuthenticationProvider<?>> systemResourceAuthenticationProviders
    ) {
        return new DefaultSystemResourceAuthenticationProviderFactory<>(
                systemResourceAuthenticationProviders
        );
    }

    @Bean
    @ConditionalOnMissingBean
    public SystemResourceOperatorProvider<?> defaultSystemResourceOperatorProvider(
            List<SystemResourceOperatorFactory<?>> systemResourceOperatorFactories
    ) {
        return new DefaultSystemResourceOperatorProvider<>(systemResourceOperatorFactories);
    }

    @Bean
    @ConditionalOnMissingBean
    public SystemResourceFactory<?> defaultSystemResourceFactory(
            List<SystemResourceProvider<?>> systemResourceProviders
    ) {
        return new DefaultSystemResourceFactory<>(systemResourceProviders);
    }
}
