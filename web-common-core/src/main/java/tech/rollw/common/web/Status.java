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

import java.util.Objects;

/**
 * @author RollW
 */
public final class Status {
    private final ErrorCode errorCode;
    private final String message;

    public static final Status SUCCESS = Status.from(CommonErrorCode.SUCCESS);

    public Status(@NonNull ErrorCode errorCode,@Nullable String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @NonNull
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this);
    }

    @NonNull
    public Status withMessage(@Nullable String message) {
        return new Status(this.errorCode, message);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Status status)) return false;
        return Objects.equals(errorCode, status.errorCode) && Objects.equals(message, status.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, message);
    }

    @Override
    public String toString() {
        return "Status{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                '}';
    }

    @NonNull
    public static Status from(@NonNull ErrorCode errorCode) {
        return new Status(errorCode, null);
    }

    public static final class Builder {
        private ErrorCode errorCode;
        private String message;

        public Builder() {
        }

        public Builder(Status other) {
            this.errorCode = other.errorCode;
            this.message = other.message;
        }

        public Builder errorCode(@NonNull ErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder message(@Nullable String message) {
            this.message = message;
            return this;
        }

        @NonNull
        public Status build() {
            return new Status(errorCode, message);
        }
    }
}

