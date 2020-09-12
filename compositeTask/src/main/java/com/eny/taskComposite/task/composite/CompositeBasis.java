package com.eny.taskComposite.task.composite;

import com.eny.taskComposite.task.composite.type.CompositeType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class CompositeBasis {
    CompositeType compositeType;
    List<CompositeBasis> childs;

    public CompositeBasis() {
        childs = new ArrayList<CompositeBasis>();
    }

    public CompositeType getCompositeType() {
        return compositeType;
    }

    public List<CompositeBasis> getChilds() {
        return Collections.unmodifiableList(childs);
    }

    public Optional<CompositeBasis> getChild(int id) {
        Optional<CompositeBasis> child = Optional.empty();

        if (id > 0 && id < childs.size()) {
            child = Optional.of(childs.get(id));
        }

        return child;
    }

    public void addChild(CompositeBasis child) {
        childs.add(child);
    }

    public void removeChild(int id) {
        childs.remove(id);
    }

    public void removeChild(CompositeBasis entity) {
        childs.remove(entity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (CompositeBasis child : childs) {
            sb.append(child.toString());
            sb.append(compositeType.getSeparator());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeBasis that = (CompositeBasis) o;

        if (compositeType != that.compositeType) return false;
        return childs.equals(that.childs);
    }

    @Override
    public int hashCode() {
        int result = compositeType.hashCode();
        result = 31 * result + childs.hashCode();
        return result;
    }
}
