# JAVA FUNCIONAL Pt2

## Lambda
Lambda: Es una funcion anónima que te permite generar un comportamiento sin
necesidad de declarar dicha función, para que sea utilizado a nivel global.
Pequeña porción de código reutilizable en un pequeño espacio de tiempo.

## Metodo por referencia
Método Por Referencia: Si tengo el mismo parámetro en la derecha que en la izquierda
lo puedo resumir, Ejemplo: products.forEach(p -> System.out.println(p));
                           products.forEach(System.out::println);

## Stream
stream(): Método que nos permite trabajar de manera declarativa con las colecciones

## Tipo Generico
- <?>: Genérico que representa cualquier tipo

## Filter
- Filter (param: Predicate)
   * Permite filtrar los elementos de una coleccion según el predicado que enviemos como argumento

## Map
- Map (param: Function)
   * Permite transformar cualquier elemento que se esté procesando y devuelve un valor deseado

## Sorted (param: Comparator)
- Nos permite organizar los elementos de nuestro flujo siguiendo los criterios establecidos por el Comparator
  que enviemos como argumento.


## Match
- Verifica coincidencias en los elementos según el criterio establecido como predicado.
 * .anyMatch(param: Predicate): Verifica si al menos uno coincide.
 * .allMatch(param: Predicate): Verifica si todos coinciden.
 * .noneMatch(param: Predicate): Verifica si ninguno coincide

## Limit/skip
- Limit: Nos permite limitar la cantidad de elmentos
- Skip: Nos permite saltar/ignorar elementos

## Collect
- Provee métodos para manipular colecciones
  * .collect(Collectors.groupingBy(Product::getPrice));
    Agrupando productos por su precio, devuelve un Map<Double, List<Product>>.

## Reduce
- Reduce los elementos de una coleccion a un solo elemento resultado de una función. 

Los Ejemplos de estos métodos se encuentran el código fuente de este repositorio
