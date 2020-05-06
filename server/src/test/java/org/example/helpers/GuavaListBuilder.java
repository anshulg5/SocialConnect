package org.example.helpers;

import com.google.common.collect.ImmutableList;

public class GuavaListBuilder<E>{
    public ImmutableList.Builder<E> get() {
        return new ImmutableList.Builder<>();
    }
}
