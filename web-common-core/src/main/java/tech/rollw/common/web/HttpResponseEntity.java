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
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import tech.rollw.common.web.page.Page;

import java.util.List;

/**
 * Extension of {@link ResponseEntity}. Used in {@code RestTemplate} as well
 * as in {@code @Controller} methods.
 *
 * @author RollW
 */
public class HttpResponseEntity<D> extends ResponseEntity<HttpResponseBody<D>> {

    public HttpResponseEntity(@NonNull HttpStatusCode httpStatus) {
        this(null, httpStatus, null);
    }

    public HttpResponseEntity(@NonNull HttpResponseBody<D> body) {
        this(body, null);
    }

    public HttpResponseEntity(@NonNull HttpResponseBody<D> body,
                              @Nullable MultiValueMap<String, String> headers) {
        this(body, HttpStatusCode.valueOf(body.getStatus().getErrorCode().getStatus()), headers);
    }

    public HttpResponseEntity(@Nullable HttpResponseBody<D> body,
                              @NonNull HttpStatusCode httpStatus,
                              @Nullable MultiValueMap<String, String> headers) {
        super(body, headers, httpStatus);
    }

    @NonNull
    public HttpResponseEntity<D> fork() {
        return new HttpResponseEntity<>(getBody(), getStatusCode(), getHeaders());
    }

    @NonNull
    public HttpResponseEntity<D> fork(@Nullable HttpResponseBody<D> newResponseBody) {
        return new HttpResponseEntity<>(newResponseBody, getStatusCode(), getHeaders());
    }

    public static <D> Builder<D> builder() {
        return new Builder<>();
    }

    public static class Builder<D> {
        private HttpStatusCode httpStatus;
        private MultiValueMap<String, String> headers;
        private Status status;
        private D data;

        public Builder() {
            this.headers = null;
            this.status = null;
            this.data = null;
            this.httpStatus = null;
        }

        public Builder<D> status(Status status) {
            this.status = status;
            return this;
        }

        public Builder<D> httpStatus(int httpStatus) {
            this.httpStatus = HttpStatusCode.valueOf(httpStatus);
            return this;
        }

        public Builder<D> httpStatus(HttpStatusCode httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder<D> data(D data) {
            this.data = data;
            return this;
        }

        public Builder<D> headers(MultiValueMap<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public HttpResponseEntity<D> build() {
            if (status == null) {
                status = Status.SUCCESS;
            }
            if (httpStatus == null) {
                httpStatus = HttpStatusCode.valueOf(status.getErrorCode().getStatus());
            }
            return new HttpResponseEntity<>(
                    HttpResponseBody.<D>builder()
                            .status(status)
                            .data(data)
                            .build(),
                    httpStatus,
                    headers
            );
        }
    }

    public static <D> HttpResponseEntity<D> success() {
        return of(HttpResponseBody.success());
    }

    public static <D> HttpResponseEntity<D> success(D data) {
        return of(HttpResponseBody.success(data));
    }

    public static <D> HttpResponseEntity<D> success(String message, D data) {
        return of(HttpResponseBody.success(message, data));
    }

    public static <D> HttpResponseEntity<List<D>> success(Page<D> page) {
        return of(PageableHttpResponseBody.success(page));
    }

    public static <D> HttpResponseEntity<D> of(ErrorCode errorCode,
                                               String message) {
        return of(HttpResponseBody.of(errorCode, message));
    }

    public static <D> HttpResponseEntity<D> of(ErrorCode errorCode) {
        return of(HttpResponseBody.of(errorCode));
    }

    public static <D> HttpResponseEntity<D> of(HttpResponseBody<D> body) {
        return new HttpResponseEntity<>(body);
    }

    public static <D> HttpResponseEntity<D> of(ErrorCode errorCode,
                                               D data) {
        return of(
                HttpResponseBody.builder(data)
                        .status(Status.from(errorCode))
                        .build()
        );
    }

    public static <D> HttpResponseEntity<List<D>> of(ErrorCode errorCode,
                                                     Page<D> page) {
        return of(
                PageableHttpResponseBody.of(errorCode, page)
        );
    }
}

