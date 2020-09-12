package com.eny.taskComposite.task.composite;

import com.eny.taskComposite.task.composite.type.AtomicType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CompositeAtomic extends CompositeBasis {
    private AtomicType atomicType;
    private String element;

    public CompositeAtomic(AtomicType atomicType, String element) {
        this.atomicType = atomicType;
        this.element = element;
    }

    public AtomicType getAtomicType() {
        return atomicType;
    }

    @Override
    public List<CompositeBasis> getChilds() {
        return Collections.emptyList();
    }

    @Override
    public Optional<CompositeBasis> getChild(int id) {
        return Optional.empty();
    }

    @Override
    public void addChild(CompositeBasis child) { }

    @Override
    public void removeChild(int id) { }

    @Override
    public void removeChild(CompositeBasis entity) { }

    @Override
    public String toString() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeBasis that = (CompositeBasis) o;

        return compositeType == that.compositeType;
    }

    @Override
    public int hashCode() {
        int result = 31 * compositeType.hashCode();
        return result;
    }
}
