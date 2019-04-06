import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
/**
 * Created by mtumilowicz on 2019-04-06.
 */
public class StreamUtilTest {

    @Test
    public void map() {
        assertThat(StreamUtil.map(Stream.of(2), x -> x * x).findAny(), is(Optional.of(4)));
        
    }

    @Test
    public void filter() {
        assertThat(StreamUtil.filter(Stream.of(2), x -> x > 1).findAny(), is(Optional.of(2)));
        assertThat(StreamUtil.filter(Stream.of(2), x -> x > 2).findAny(), is(Optional.empty()));
    }
}