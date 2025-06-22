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

/**
 * Operator of the system. For example, the user who created the data, the user
 * who modified the data, etc.
 *
 * @author RollW
 */
public interface Operator {
    /**
     * Get the id of the operator.
     *
     * @return the id of the operator
     */
    long getOperatorId();

    default boolean equals(Operator other) {
        if (other == null) {
            return false;
        }
        return this.getOperatorId() == other.getOperatorId();
    }

    static boolean equals(Operator operator1, Operator operator2) {
        if (operator1 == null || operator2 == null) {
            return false;
        }
        return operator1.equals(operator2);
    }

    static Operator of(long operatorId) {
        return new SimpleOperator(operatorId);
    }
}
