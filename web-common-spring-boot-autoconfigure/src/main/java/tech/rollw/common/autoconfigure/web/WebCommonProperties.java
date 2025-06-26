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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author RollW
 */
@ConfigurationProperties(prefix = "web-common")
public class WebCommonProperties {
    private boolean pageableInitializeFilter;
    private boolean controllerResponseAdvise;

    public WebCommonProperties(boolean pageableInitializeFilter, boolean controllerResponseAdvise) {
        this.pageableInitializeFilter = pageableInitializeFilter;
        this.controllerResponseAdvise = controllerResponseAdvise;
    }

    public WebCommonProperties() {
        this(true, true);
    }

    public boolean isPageableInitializeFilter() {
        return pageableInitializeFilter;
    }

    public void setPageableInitializeFilter(boolean pageableInitializeFilter) {
        this.pageableInitializeFilter = pageableInitializeFilter;
    }

    public boolean isControllerResponseAdvise() {
        return controllerResponseAdvise;
    }

    public void setControllerResponseAdvise(boolean controllerResponseAdvise) {
        this.controllerResponseAdvise = controllerResponseAdvise;
    }
}
