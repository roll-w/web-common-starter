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

package tech.rollw.common.web.components;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import space.lingu.NonNull;
import tech.rollw.common.web.ParameterFailedException;
import tech.rollw.common.web.autoconfigure.ParameterProperties;
import tech.rollw.common.web.system.ContextThread;
import tech.rollw.common.web.system.ContextThreadAware;
import tech.rollw.common.web.system.paged.PageableContext;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author RollW
 */
@Order(-10)
public class ContextInitializeFilter extends OncePerRequestFilter {
    private final ContextThreadAware<PageableContext> pageableContextFactory;
    private final ParameterProperties parameterProperties;

    public ContextInitializeFilter(ContextThreadAware<PageableContext> pageableContextFactory,
                                   ParameterProperties parameterProperties) {
        this.pageableContextFactory = pageableContextFactory;
        this.parameterProperties = parameterProperties;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        PageableContext context = fromRequest(request);
        ContextThread<PageableContext> contextThread =
                pageableContextFactory.assambleContextThread(context);
        try {
            filterChain.doFilter(request, response);
        } finally {
            pageableContextFactory.clearContextThread();
        }
    }

    private PageableContext fromRequest(HttpServletRequest request) {
        String page = request.getParameter(parameterProperties.getPage());
        try {
            int pageInt = isNullOrEmpty(page) ? 1 : Integer.parseInt(page);
            int sizeInt = getSize(request);
            return new PageableContext(pageInt, sizeInt);
        } catch (NumberFormatException e) {
            throw new ParameterFailedException(e.getMessage());
        }
    }

    private int getSize(HttpServletRequest request) {
        if (isNullOrEmpty(parameterProperties.getSize())) {
            return parameterProperties.getDefaultSize();
        }
        String size = request.getParameter(parameterProperties.getSize());
        if (isNullOrEmpty(size)) {
            return parameterProperties.getDefaultSize();
        }
        return Integer.parseInt(size);
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
