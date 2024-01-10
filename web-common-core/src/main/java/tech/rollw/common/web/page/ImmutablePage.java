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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author RollW
 */
@SuppressWarnings({"unused"})
public class ImmutablePage<E> implements Page<E> {
    /**
     * Current page number.
     */
    private final int page;
    /**
     * Current page size.
     */
    private final int size;
    /**
     * Total number of items.
     */
    private final long total;
    private final List<E> data;

    public ImmutablePage(int page, int size, long total, List<E> data) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public List<E> getData() {
        return data;
    }

    @Override
    public Stream<E> stream() {
        return data.stream();
    }

    public <R> Page<R> transform(Function<E, R> mapper) {
        return new ImmutablePage<>(
                page, size,
                total,
                data.stream().map(mapper).toList()
        );
    }


    public static <T> Page<T> of(int page, int size,
                                          int total, List<T> data) {
        return new ImmutablePage<>(page, size, total, data);
    }

    public static <T> Page<T> of(Pageable pageable,
                                          long total, List<T> data) {
        return new ImmutablePage<>(
                pageable.getPage(),
                pageable.getSize(),
                total, data
        );
    }


    public static <T> Page<T> of(ImmutablePage<?> raw,
                                          Stream<T> data) {
        return new ImmutablePage<>(
                raw.getPage(), raw.getSize(),
                raw.getTotal(), data.toList()
        );
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] a) {
        return data.toArray(a);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return new HashSet<>(data).containsAll(c);
    }

    @Override
    public E get(int index) {
        return data.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return data.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return data.lastIndexOf(o);
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator() {
        return new PageIterator<>(data.listIterator());
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return new PageIterator<>(data.listIterator(index));
    }

    @NonNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return data.subList(fromIndex, toIndex);
    }

    public static <T> ImmutablePage<T> of() {
        return (ImmutablePage<T>) PAGE;
    }

    private static final ImmutablePage<?> PAGE = new ImmutablePage<>(0, 0, 0, List.of());
}
