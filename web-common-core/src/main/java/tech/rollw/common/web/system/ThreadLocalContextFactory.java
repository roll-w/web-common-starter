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

/**
 * A thread local implementation of {@link ContextThreadAware}.
 *
 * @author RollW
 */
public class ThreadLocalContextFactory<C extends SystemContext> implements ContextThreadAware<C> {
    private final ThreadLocal<ContextThread<C>> contextThreadLocal = new ThreadLocal<>();

    @Override
    public ContextThread<C> assambleContextThread(C context) {
        ContextThread<C> contextThread = contextThreadLocal.get();
        if (contextThread == null) {
            contextThread = new DefaultContextThread<>(context);
            contextThreadLocal.set(contextThread);
        }
        contextThread.setContext(context);
        return contextThread;
    }

    @Override
    public ContextThread<C> getContextThread() {
        ContextThread<C> thread =
                contextThreadLocal.get();
        if (thread != null) {
            return thread;
        }
        return assambleContextThread(null);
    }

    @Override
    public void clearContextThread() {
        contextThreadLocal.remove();
    }

}
