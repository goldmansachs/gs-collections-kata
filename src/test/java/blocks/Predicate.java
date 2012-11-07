package blocks;

public interface Predicate
{
    interface T<T>
    {
        boolean apply(T each);
    }

    interface T2<T1, T2>
    {
        boolean apply(T1 one, T2 two);
    }

    interface T3<T1, T2, T3>
    {
        boolean apply(T1 one, T2 two, T3 three);
    }

    interface TInt<T>
    {
        boolean apply(T one, int two);
    }

    interface TDouble<T>
    {
        boolean apply(T one, double two);
    }

    interface TLong<T>
    {
        boolean apply(T one, long two);
    }

    interface TFloat<T>
    {
        boolean apply(T one, float two);
    }

    interface Int
    {
        boolean apply(int each);
    }

    interface Int2
    {
        boolean apply(int one, int two);
    }

    interface IntT<T>
    {
        boolean apply(int one, T two);
    }

    interface Float
    {
        boolean apply(float each);
    }

    interface Float2
    {
        boolean apply(float one, float two);
    }

    interface FloatT<T>
    {
        boolean apply(float one, T two);
    }

    interface Double
    {
        boolean apply(double each);
    }

    interface Double2
    {
        boolean apply(double one, double two);
    }

    interface DoubleT<T>
    {
        boolean apply(double one, T two);
    }

    interface Long
    {
        boolean apply(long each);
    }

    interface Long2
    {
        boolean apply(long one, long two);
    }

    interface LongT<T>
    {
        boolean apply(Long one, T two);
    }
}
