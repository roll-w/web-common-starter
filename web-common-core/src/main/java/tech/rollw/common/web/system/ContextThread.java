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

import java.util.concurrent.Callable;

/**
 * A context thread is a thread that has a context. It
 * should pass the context to the next thread when it
 * calls the next thread.
 *
 * @author RollW
 */
public interface ContextThread<C extends SystemContext> {
    /**
     * Get the context.
     *
     * @return the context
     */
    C getContext();

    void setContext(C systemThreadContext);

    /**
     * Whether the context is not null.
     *
     * @return true if the context is not null
     */
    boolean hasContext();

    /**
     * Clear the context.
     */
    void clearContext();

    /**
     * Temporarily set the context to the given context,
     * and then call the runnable.
     */
    default void callWithContext(C systemThreadContext, Runnable runnable) {
        C old = getContext();
        setContext(systemThreadContext);
        try {
            call(runnable);
        } finally {
            setContext(old);
        }
    }

    /**
     * Temporarily set the context to the given context,
     * and then call the callable.
     */
    default <T> T callWithContext(C systemThreadContext,
                                  Callable<T> callable) throws Exception {
        C old = getContext();
        setContext(systemThreadContext);
        try {
            return call(callable);
        } finally {
            setContext(old);
        }
    }

    /**
     * Call the callable. If called on a different thread,
     * the context should be passed to the next thread.
     */
    <T> T call(Callable<T> callable) throws Exception;

    /**
     * Call the runnable. If called on a different thread,
     * the context should be passed to the next thread.
     */
    void call(Runnable callable);
}
