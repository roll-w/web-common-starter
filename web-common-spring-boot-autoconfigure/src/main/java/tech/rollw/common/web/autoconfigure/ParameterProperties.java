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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Activate while the {@link WebCommonProperties#isContextInitializeFilter()}
 * is {@code true}, and will be used to auto set the {@link tech.rollw.common.web.page.Pageable}
 * parameter in the context from the request.
 *
 * @author RollW
 */
@ConfigurationProperties(prefix = "web-common.params")
public class ParameterProperties {
    /**
     * The name of the page parameter.
     */
    private String page = "page";

    /**
     * The name of the size parameter. Put empty string
     * if you don't want to use size parameter.
     */
    private String size = "size";

    private int defaultSize = 10;

    public ParameterProperties(String page, String size) {
        this.page = page;
        this.size = size;
    }

    public ParameterProperties() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }

    @Override
    public String toString() {
        return "ParameterProperties{" +
                "page='" + page + '\'' +
                ", size='" + size + '\'' +
                ", defaultSize=" + defaultSize +
                '}';
    }

}
