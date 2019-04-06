import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by mtumilowicz on 2019-04-06.
 */
final class StreamUtil {
    static <T, R> Stream<R> map(Stream<T> stream, Function<T, R> mapper) {
        return stream.flatMap(mapper.andThen(Stream::of));
    }

    static <T> Stream<T> filter(Stream<T> stream, Predicate<T> predicate) {
        return stream.flatMap(x -> predicate.test(x) ? Stream.of(x) : Stream.empty());
    }
}
