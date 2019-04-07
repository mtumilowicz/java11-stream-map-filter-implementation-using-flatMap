import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-04-06.
 */
public class StreamUtilTest {

    @Test
    public void map_empty() {
        assertThat(StreamUtil.map(Stream.<Integer>empty(), x -> x * x).count(), is(0L));
    }

    @Test
    public void map_single() {
//        given
        var ints = Stream.of(2);

//        when
        var squared = StreamUtil.map(ints, x -> x * x).collect(Collectors.toList());

//        expect
        assertThat(squared, is(List.of(4)));
    }    
    
    @Test
    public void map_multiple() {
//        given
        var ints = Stream.of(1, 2, 3);
        
//        when
        var squared = StreamUtil.map(ints, x -> x * x).collect(Collectors.toList());
        
//        expect
        assertThat(squared, is(List.of(1, 4, 9)));
    }

    @Test
    public void filter() {
        assertThat(StreamUtil.filter(Stream.of(2), x -> x > 1).findAny(), is(Optional.of(2)));
        assertThat(StreamUtil.filter(Stream.of(2), x -> x > 2).findAny(), is(Optional.empty()));
    }
}