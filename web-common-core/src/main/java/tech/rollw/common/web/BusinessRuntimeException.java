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

package tech.rollw.common.web;

/**
 * Business Runtime Exception. Can be used to pass user prompts into responses.
 * If it is an internal system error, use other Exception classes.
 * <p>
 * Avoid using this class directly to throw exception
 * and try to use inherited classes instead.
 *
 * @deprecated use {@link CommonRuntimeException} instead
 * @author RollW
 */
@Deprecated
public class BusinessRuntimeException extends CommonRuntimeException {
    public BusinessRuntimeException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BusinessRuntimeException(ErrorCode errorCode, String message, Object... args) {
        super(errorCode, message, args);
    }

    public BusinessRuntimeException(ErrorCode errorCode, String message, Throwable cause, Object... args) {
        super(errorCode, message, cause, args);
    }

    public BusinessRuntimeException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
