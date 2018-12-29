package com.software.program.astrixsa.date;

import com.software.program.astrixsa.R;
import com.software.program.astrixsa.model.Category;
import com.software.program.astrixsa.model.CategoryI;
import com.software.program.astrixsa.model.ElementProduct;
import com.software.program.astrixsa.model.Product;
import com.software.program.astrixsa.model.ProductI;


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
        ElementProduct elementProduct1= new ElementProduct(url1,images1);
        ElementProduct elementProduct2= new ElementProduct(url2,images2);
        ElementProduct elementProduct3= new ElementProduct(url3,images3);
        ElementProduct elementProduct4= new ElementProduct(url4,images4);
        ElementProduct elementProduct5= new ElementProduct(url5,images5);

        ProductI product1 = new Product(OlaFuturoMaquina,detergentePolvo, producto1);
        product1.addElement(elementProduct1);
        product1.addElement(elementProduct2);
        product1.addElement(elementProduct3);
        product1.addElement(elementProduct4);
        product1.addElement(elementProduct5);

        CategoryI category = new Category(lavadoYCuidadoRopa,categoria1);
        category.addProduct(product1);
        return category;
    }

}
