package org.example.parser;

import java.util.List;

public interface XmlParsable<T> extends AutoCloseable{
    List<T> getNextPart(int recordsCount);
    Boolean hasNext();
}
