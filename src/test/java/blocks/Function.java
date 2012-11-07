package blocks;

public interface Function<R>
{
    R apply();

    interface T<T, R>
    {
        R apply(T each);
    }

    interface T2<T1, T2, R>
    {
        R apply(T1 one, T2 two);
    }

    interface T3<T1, T2, T3, R>
    {
        R apply(T1 one, T2 two, T3 three);
    }

    interface TInt<T, R>
    {
        R apply(T one, int two);
    }

    interface TDouble<T, R>
    {
        R apply(T one, double two);
    }

    interface TLong<T, R>
    {
        R apply(T one, long two);
    }

    interface TFloat<T, R>
    {
        R apply(T one, float two);
    }

    interface Int<R>
    {
        R apply(int each);
    }

    interface Int2<R>
    {
        R apply(int one, int two);
    }

    interface IntT<T, R>
    {
        R apply(int one, T two);
    }

    interface Float<R>
    {
        R apply(float each);
    }

    interface Float2<R>
    {
        R apply(float one, float two);
    }

    interface FloatT<T, R>
    {
        R apply(float one, T two);
    }

    interface Double<R>
    {
        R apply(double each);
    }

    interface Double2<R>
    {
        R apply(double one, double two);
    }

    interface DoubleT<T, R>
    {
        R apply(double one, T two);
    }

    interface Long<R>
    {
        R apply(long each);
    }

    interface Long2<R>
    {
        R apply(long one, long two);
    }

    interface LongT<T, R>
    {
        R apply(long one, T two);
    }
}
