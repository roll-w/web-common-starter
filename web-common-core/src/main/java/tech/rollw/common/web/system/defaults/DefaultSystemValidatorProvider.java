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

package tech.rollw.common.web.system.defaults;

import space.lingu.NonNull;
import tech.rollw.common.web.system.SystemResourceKind;
import tech.rollw.common.web.system.UnsupportedKindException;
import tech.rollw.common.web.system.validate.Validator;
import tech.rollw.common.web.system.validate.ValidatorProvider;

import java.util.List;

/**
 * @author RollW
 */
public class DefaultSystemValidatorProvider implements ValidatorProvider {
    private final List<Validator> validators;

    public DefaultSystemValidatorProvider(List<Validator> validators) {
        this.validators = validators;
    }

    @Override
    @NonNull
    public Validator getValidator(@NonNull SystemResourceKind systemResourceKind)
            throws UnsupportedKindException {
        return getValidatorByKind(systemResourceKind);
    }

    private Validator getValidatorByKind(SystemResourceKind systemResourceKind) {
        return validators.stream()
                .filter(validator -> validator.supports(systemResourceKind))
                .findFirst()
                .orElseThrow(() -> new UnsupportedKindException(systemResourceKind));
    }
}
