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

import tech.rollw.common.Castable;
import tech.rollw.common.web.BusinessRuntimeException;

/**
 * Defines common operations for system resources.
 *
 * @author RollW
 */
public interface SystemResourceOperator extends ByStatusProvider, Castable<SystemResourceOperator> {
    /**
     * For some system resources that may not be updated automatically
     * (such as some batch operations), you need to call this method
     * to update.
     */
    SystemResource update() throws BusinessRuntimeException;

    /**
     * Delete the system resource.
     */
    SystemResource delete() throws BusinessRuntimeException;

    /**
     * Rename the system resource.
     */
    SystemResource rename(String newName) throws BusinessRuntimeException,
            UnsupportedOperationException;

    /**
     * Disable auto update for the next operations.
     */
    default SystemResourceOperator disableAutoUpdate() {
        throw new UnsupportedOperationException("The system resource operator "
                + getSystemResource().getSystemResourceKind()
                + " does not support switch auto update.");
    }

    /**
     * Enable auto update for the next operations. (Default)
     */
    default SystemResourceOperator enableAutoUpdate() {
        throw new UnsupportedOperationException("The system resource operator "
                + getSystemResource().getSystemResourceKind()
                + " does not support switch auto update.");
    }

    /**
     * Check if auto update is enabled, default is true.
     */
    default boolean isAutoUpdateEnabled() {
        return true;
    }

    /**
     * Set whether to check if the system resource is deleted
     * before the operation.
     */
    @Override
    void setCheckDeleted(boolean checkDeleted);

    /**
     * Check if the system resource is deleted before the operation.
     */
    @Override
    boolean isCheckDeleted();

    /**
     * Get the system resource that this operator is operating on.
     */
    SystemResource getSystemResource();
}
