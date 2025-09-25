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

/**
 * @author RollW
 */
@SuppressWarnings("unchecked")
public class HttpResponseBody<D> {
    private static final HttpResponseBody<?> SUCCESS = new HttpResponseBody<>(Status.SUCCESS, null);

    protected final Status status;
    protected final D data;

    public HttpResponseBody(ErrorCode errorCode,
                            String message,
                            D data) {
        this.status = new Status(errorCode, message);
        this.data = data;
    }

    public HttpResponseBody(Status status, D data) {
        this.status = status;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public D getData() {
        return data;
    }

    public HttpResponseBody<D> fork() {
        return new HttpResponseBody<>(status, data);
    }

    public HttpResponseBody<D> fork(Status status) {
        return new HttpResponseBody<>(status, data);
    }

    public static <D> Builder<D> builder() {
        return new Builder<>();
    }

    public Builder<D> toBuilder() {
        return HttpResponseBody.<D>builder()
                .status(status)
                .data(data);
    }

    public static <D> Builder<D> builder(D data) {
        return new Builder<D>().data(data);
    }

    public static class Builder<D> {
        private Status status;
        private D data = null;

        public Builder() {
        }

        public Builder<D> status(Status status) {
            this.status = status;
            return this;
        }

        public Builder<D> data(D data) {
            this.data = data;
            return this;
        }

        public HttpResponseBody<D> build() {
            return new HttpResponseBody<>(status, data);
        }
    }

    public static <D> HttpResponseBody<D> success() {
        return (HttpResponseBody<D>) SUCCESS;
    }

    public static <D> HttpResponseBody<D> success(String message) {
        return new HttpResponseBody<>(Status.SUCCESS.withMessage(message), null);
    }

    public static <D> HttpResponseBody<D> success(String message, D data) {
        return HttpResponseBody.<D>success()
                .toBuilder()
                .status(Status.SUCCESS.withMessage(message))
                .build();
    }

    public static <D> HttpResponseBody<D> success(D data) {
        return HttpResponseBody.<D>success()
                .toBuilder()
                .data(data)
                .build();
    }

    public static <D> HttpResponseBody<D> of(ErrorCode errorCode,
                                             String message) {
        return new HttpResponseBody<>(new Status(errorCode, message), null);
    }

    public static <D> HttpResponseBody<D> of(ErrorCode errorCode,
                                             D data) {
        return new HttpResponseBody<>(Status.from(errorCode), data);
    }

    public static <D> HttpResponseBody<D> of(ErrorCode errorCode) {
        return new HttpResponseBody<>(Status.from(errorCode), null);
    }

    public static <D> HttpResponseBody<D> of(ErrorCode errorCode,
                                             String message,
                                             D data) {
        return new HttpResponseBody<>(new Status(errorCode, message), data);
    }
}
