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

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import tech.rollw.common.web.page.Pageable;
import tech.rollw.common.web.system.ContextThread;
import tech.rollw.common.web.system.ContextThreadAware;
import tech.rollw.common.web.system.paged.PageableContext;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author RollW
 */
@ControllerAdvice
@ConditionalOnProperty(prefix = "web-common",
        name = "context-initialize-filter", havingValue = "true")
public class RequestPageableEnhance {
    private final ContextThreadAware<PageableContext> pageableContextThreadAware;

    public RequestPageableEnhance(ContextThreadAware<PageableContext> pageableContextThreadAware) {
        this.pageableContextThreadAware = pageableContextThreadAware;
    }

    @ModelAttribute
    public Pageable fromRequest(HttpServletRequest request) {
        ContextThread<PageableContext> contextThread =
                pageableContextThreadAware.getContextThread();
        return contextThread.getContext();
    }
}
