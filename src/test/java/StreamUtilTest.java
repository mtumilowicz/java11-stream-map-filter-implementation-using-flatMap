import org.junit.Test;

import java.util.List;
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
    public void filter_empty() {
        assertThat(StreamUtil.filter(Stream.<Integer>empty(), x -> x > 2).count(), is(0L));
    }

    @Test
    public void filter_single_true() {
//        given
        var ints = Stream.of(2);

//        when
        var filtered = StreamUtil.filter(ints, x -> x < 3).collect(Collectors.toList());

//        expect
        assertThat(filtered, is(List.of(2)));
    }

    @Test
    public void filter_single_false() {
//        given
        var ints = Stream.of(2);

//        when
        var filtered = StreamUtil.filter(ints, x -> x > 2).collect(Collectors.toList());

//        expect
        assertThat(filtered, is(List.of()));
    }

    @Test
    public void filter_multiple() {
//        given
        var ints = Stream.of(1, 2, 3);

//        when
        var filtered = StreamUtil.filter(ints, x -> x > 2).collect(Collectors.toList());

//        expect
        assertThat(filtered, is(List.of(3)));
    }
}