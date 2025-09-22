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

package tech.rollw.common.web;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpMethod;

/**
 * A status code provider that converts a given status to the best matching HTTP status code
 * based on the HTTP method used.
 * <p>
 * Only converts if the status code is 200, otherwise returns the original status code.
 *
 * @author RollW
 */
public class MatchBestStatusCodeProvider implements StatusCodeProvider {
    @Override
    public int getStatusCode(@NonNull Status status, @NonNull HttpMethod httpMethod) {
        int code = status.getErrorCode().getStatus();
        if (code != 200) {
            return code;
        }
        if (httpMethod.equals(HttpMethod.POST) || httpMethod.equals(HttpMethod.PUT) ||
                httpMethod.equals(HttpMethod.PATCH)) {
            return 201;
        }
        if (httpMethod.equals(HttpMethod.DELETE)) {
            return 204;
        }
        return 200;
    }
}
