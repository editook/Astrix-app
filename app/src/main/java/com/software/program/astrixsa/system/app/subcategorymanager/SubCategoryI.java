package com.software.program.astrixsa.system.app.subcategorymanager;

import java.util.List;

public interface SubCategoryI {
    void addElement(ElementSC elementSC);
    List<ElementSC> getElements();
    void setURl(String url, int index);
    ElementSC getElement(int index);
}
