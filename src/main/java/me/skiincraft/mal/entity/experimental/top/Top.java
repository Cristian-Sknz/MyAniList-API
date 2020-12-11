package me.skiincraft.mal.entity.experimental.top;

import java.util.List;

public interface Top<T> {

    T get(int index);
    List<T> toList();
    String getURL();
    TopType getType();

    default int size(){
        return toList().size();
    }
}
