import org.hamcrest.core.IsSame;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-04-07.
 */
public class FlatMapBasicsTest {

    @Test
    public void flatten() {
//        given
        Optional<Optional<Integer>> matrioshka = Optional.of(Optional.of(1));
        Optional<Optional<Integer>> emptyMatrioshka = Optional.of(Optional.empty());

//        when
        Optional<Integer> maybeInt = matrioshka.flatMap(Function.identity());
        Optional<Integer> emptyInt = emptyMatrioshka.flatMap(Function.identity());

//        then
        assertThat(maybeInt, is(Optional.of(1)));
        assertThat(emptyInt, IsSame.sameInstance(Optional.empty()));
    }

    @Test
    public void duplicate() {
//        given
        Stream<Integer> triple = Stream.of(1, 2, 3);

//        when
        Stream<Integer> sextuple = triple.flatMap(i -> Stream.of(i, i));

//        then
        assertThat(sextuple.collect(Collectors.toList()), is(List.of(1, 1, 2, 2, 3, 3)));
    }
}
