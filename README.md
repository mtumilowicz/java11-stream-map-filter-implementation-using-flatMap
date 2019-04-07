# java11-stream-map-filter-implementation-using-flatMap

# flatMap in a nutshell
* in JDK 11, we have 2 types of `flatMap`
    * in `Optional`
        ```
        Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)
        ```
    * in `Stream`
        ```
        Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        ```
* `flatMap` = `map` + flatten
* flatten
    ```
    // given
    Optional<Optional<Integer>> matrioshka = Optional.of(Optional.of(1));
    Optional<Optional<Integer>> emptyMatrioshka = Optional.of(Optional.empty());
    
    // when
    Optional<Integer> maybeInt = matrioshka.flatMap(Function.identity());
    Optional<Integer> emptyInt = emptyMatrioshka.flatMap(Function.identity());
    
    // then
    assertThat(maybeInt, is(Optional.of(1)));
    assertThat(emptyInt, IsSame.sameInstance(Optional.empty()));
    ```
* contrary to map (which introduces one to one correspondence)
    flatMap could change the number of elements in the stream
    ```
    // given
    Stream<Integer> triple = Stream.of(1, 2, 3);
    
    // when
    Stream<Integer> sextuple = triple.flatMap(i -> Stream.of(i, i));
    
    // then
    assertThat(sextuple.collect(Collectors.toList()), is(List.of(1, 1, 2, 2, 3, 3)));
    ```
# project description
* it may be valuable to take a look at: 
https://github.com/mtumilowicz/java11-stream-map-filter-implementation-using-reduce
* `map` implementation using `flatMap`
    ```
    static <T, R> Stream<R> map(Stream<T> stream, Function<T, R> mapper) {
        return stream.flatMap(mapper.andThen(Stream::of));
    }
    ```
* `filter` implementation using `flatMap`
    ```
    static <T> Stream<T> filter(Stream<T> stream, Predicate<T> predicate) {
        return stream.flatMap(x -> predicate.test(x) ? Stream.of(x) : Stream.empty());
    }
    ```
# tests
* `map`
    ```
    // given
    var ints = Stream.of(1, 2, 3);
    
    // when
    var squared = StreamUtil.map(ints, x -> x * x).collect(Collectors.toList());
    
    // then
    assertThat(squared, is(List.of(1, 4, 9)));
    ```
* `filter`
    ``` 
    // given
    var ints = Stream.of(1, 2, 3);
    
    // when
    var filtered = StreamUtil.filter(ints, x -> x > 2).collect(Collectors.toList());
    
    // then
    assertThat(filtered, is(List.of(3)));
    ```