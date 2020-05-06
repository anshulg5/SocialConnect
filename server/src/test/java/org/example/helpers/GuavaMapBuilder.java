package org.example.helpers;

import com.google.common.collect.ImmutableMap;

public class GuavaMapBuilder<K,V>{
    public ImmutableMap.Builder<K,V> get(){
        return new ImmutableMap.Builder<K,V>();
    }
}
