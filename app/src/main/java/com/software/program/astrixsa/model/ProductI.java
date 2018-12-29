package com.software.program.astrixsa.model;

import java.util.List;

public interface ProductI {
    void addElement(ElementProduct elementProduct);
    List<ElementProduct> getElements();
    void setURl(String url, int index);
    ElementProduct getElement(int index);
}
