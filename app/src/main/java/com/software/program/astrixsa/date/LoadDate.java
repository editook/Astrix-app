package com.software.program.astrixsa.date;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.system.app.categorymanager.Category;
import com.software.program.astrixsa.system.app.categorymanager.CategoryI;
import com.software.program.astrixsa.system.app.subcategorymanager.ElementSC;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategory;
import com.software.program.astrixsa.system.app.subcategorymanager.SubCategoryI;


public final class LoadDate {

    public static CategoryI getCategory() {
        String lavadoYCuidadoRopa = "LAVADO Y CUIDADO DE LA ROPA";
        Integer categoria1 = R.drawable.categoria;

        String OlaFuturoMaquina = "Ola FUTURO Máquina";
        String detergentePolvo = "Detergente en Polvo";
        Integer producto1 = R.drawable.olafuturo;

        String url1 = "https://www.youtube.com/embed/dRwq9rAZGyA?rel=0;&autoplay=1";
        String url2 = "https://www.youtube.com/embed/5V6h1i-7I4s?rel=0;&autoplay=1";
        String url3 = "https://www.youtube.com/embed/oeYKN6dFOcA?rel=0;&autoplay=1";
        String url4 = "https://www.youtube.com/embed/6Xs6-j2DIdY?rel=0;&autoplay=1";
        String url5 = "https://www.youtube.com/embed/eVzVxS5Pe9A?rel=0;&autoplay=1";
        Integer images1 = R.id.primero;
        Integer images2 = R.id.segundo;
        Integer images3 = R.id.tercero;
        Integer images4 = R.id.cuarto;
        Integer images5 = R.id.quinto;
        ElementSC elementSC1 = new ElementSC(url1,images1);
        elementSC1.setFileName("Detergente OLA FUTURO Polvo - Remoción d");
        ElementSC elementSC2 = new ElementSC(url2,images2);
        elementSC2.setFileName("Detergente OLA FUTURO Polvo - Quitamanch");
        ElementSC elementSC3 = new ElementSC(url3,images3);
        elementSC3.setFileName("Detergente OLA FUTURO Polvo - Sistema Bl");
        ElementSC elementSC4 = new ElementSC(url4,images4);
        elementSC4.setFileName("Detergente OLA FUTURO Polvo - Baja Espum");
        ElementSC elementSC5 = new ElementSC(url5,images5);
        elementSC5.setFileName("Detergente OLA FUTURO - Acción Antibacte");
        SubCategoryI product1 = new SubCategory(OlaFuturoMaquina,detergentePolvo, producto1);
        product1.addElement(elementSC1);
        product1.addElement(elementSC2);
        product1.addElement(elementSC3);
        product1.addElement(elementSC4);
        product1.addElement(elementSC5);

        CategoryI category = new Category(lavadoYCuidadoRopa,categoria1);
        category.addProduct(product1);
        return category;
    }

}
