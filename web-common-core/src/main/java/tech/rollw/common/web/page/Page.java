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

package tech.rollw.common.web.page;

import space.lingu.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * Page interface.
 *
 * @author RollW
 */
public interface Page<E> extends Pageable, List<E> {

    long getTotal();

    List<E> getData();

    @Override
    default boolean add(E e) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default boolean remove(Object o) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default boolean addAll(@NonNull Collection<? extends E> c) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default boolean addAll(int index, @NonNull Collection<? extends E> c) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default boolean removeAll(@NonNull Collection<?> c) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default boolean retainAll(@NonNull Collection<?> c) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default void clear() {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default E set(int index, E element) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default void add(int index, E element) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

    @Override
    default E remove(int index) {
        throw new UnsupportedOperationException("Page is immutable.");
    }

}
