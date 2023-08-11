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
 * Context thread aware is an interface that can assemble a
 * thread by the given context.
 *
 * @author RollW
 */
public interface ContextThreadAware<C extends SystemContext> {
    /**
     * Assemble the context thread by the given context.
     */
    ContextThread<C> assambleContextThread(C context);

    /**
     * Get the context thread.
     */
    ContextThread<C> getContextThread();

    /**
     * Clear the context thread.
     */
    void clearContextThread();
}
