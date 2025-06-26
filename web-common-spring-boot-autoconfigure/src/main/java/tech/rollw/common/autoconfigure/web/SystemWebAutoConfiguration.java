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

package tech.rollw.common.autoconfigure.web;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import tech.rollw.common.web.ErrorCodeMessageProvider;
import tech.rollw.common.web.MatchBestStatusCodeProvider;
import tech.rollw.common.web.StatusCodeProvider;
import tech.rollw.common.web.components.ControllerResponseBodyAdvice;
import tech.rollw.common.web.system.ContextThreadAware;
import tech.rollw.common.web.system.paged.PageableContext;

/**
 * @author RollW
 */
@AutoConfiguration
public class SystemWebAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "web-common", name = "controller-response-advise", havingValue = "true")
    public ControllerResponseBodyAdvice controllerResponseBodyAdvice(
            ErrorCodeMessageProvider errorCodeMessageProvider,
            MessageSource messageSource,
            ContextThreadAware<PageableContext> pageableContextFactory,
            StatusCodeProvider statusCodeProvider
    ) {
        return new ControllerResponseBodyAdvice(errorCodeMessageProvider, messageSource, pageableContextFactory, statusCodeProvider);
    }

    @Bean
    @ConditionalOnMissingBean(StatusCodeProvider.class)
    public StatusCodeProvider statusCodeProvider() {
        return new MatchBestStatusCodeProvider();
    }


    @AutoConfiguration
    @ConditionalOnProperty(prefix = "web-common", name = "controller-response-advise", havingValue = "true")
    @Import(ControllerResponseBodyAdvice.class)
    public static class ControllerAdviceAutoConfiguration {
    }
}
