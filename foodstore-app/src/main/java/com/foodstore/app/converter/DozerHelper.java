package com.foodstore.app.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

public abstract class DozerHelper {

    private static Mapper mapper = new DozerBeanMapper();

    /**
     * Método responsavel por realizar o "map" do dozer para Listas
     *
     * @param source the source
     * @param destType the destType
     * @param <T> the <T>
     * @param <U> the <U>
     * @return ArrayList<?>
     */
    public static <T, U> ArrayList<U> map(final List<T> source, final Class<U> destType) {

        final ArrayList<U> dest = new ArrayList<U>();

        for (T element : source) {
            if (element == null) {
                continue;
            }
            dest.add(mapper.map(element, destType));
        }

        List<?> s1 = new ArrayList<Object>();
        s1.add(null);
        dest.removeAll(s1);

        return dest;
    }

    /**
     * Método sobrescrito do Mapper para em caso da origem estar vazia retornar nulo.
     *
     * @param source the source
     * @param destType the destType
     * @param <T> the <T>
     * @return T
     */
    public static <T> T map(final Object source, final Class<T> destType) {

        if (source == null) {
            return null;
        }

        return mapper.map(source, destType);
    }

    /**
     * Método para retirar mapeamento do JPA no Dozer com retorno de Lista de Objetos
     *
     * @param list
     * @param aClass
     * @param bClass
     * @param excludeField
     * @return
     */
    public static <A, B> List<B> dozerMapListExcludeField(final List<A> list, final Class<A> aClass,
                    final Class<B> bClass, final String... excludeField) {
        final DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                for (String e : excludeField) {
                    this.mapping(aClass, bClass,
                                    TypeMappingOptions.mapNull(false))
                                    .exclude(e);
                }
            }
        });
        return list.stream().map(entity -> dozer.map(entity, bClass)).collect(Collectors.toList());
    }

    /**
     * Método para retirar mapeamento do JPA no Dozer com retorno de objeto
     *
     * @param obj
     * @param aClass
     * @param bClass
     * @param excludeField
     * @return
     */
    public static <A, B> B dozerMapExcludeField(final A obj, final Class<A> aClass,
                    final Class<B> bClass, final String... excludeField) {
        final DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                for (String e : excludeField) {
                    this.mapping(aClass, bClass,
                                    TypeMappingOptions.mapNull(false))
                                    .exclude(e);
                }
            }
        });
        return dozer.map(obj, bClass);
    }

}
