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

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import tech.rollw.common.web.util.ErrorCodeKeyHelper;

import java.util.Locale;

/**
 * @author RollW
 */
public class MessageSourceBasedErrorCodeMessageProvider implements ErrorCodeMessageProvider {
    private final MessageSource messageSource;

    public MessageSourceBasedErrorCodeMessageProvider(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String apply(ErrorCode errorCode, Locale locale, Object... args) {
        if (errorCode == null) {
            return null;
        }
        String key = getI18nKey(errorCode);
        try {
            if (locale == null) {
                return messageSource.getMessage(key, args, Locale.getDefault());
            }
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException e) {
            return errorCode.toString();
        }
    }

    private String getI18nKey(ErrorCode errorCode) {
        return ErrorCodeKeyHelper.getI18nKey(errorCode);
    }
}
