package blocks;

public interface IntFunction
{
    int apply();

    interface T<T>
    {
        int apply(T each);
    }

    interface T2<T1, T2>
    {
        int apply(T1 one, T2 two);
    }

    interface T3<T1, T2, T3>
    {
        int apply(T1 one, T2 two, T3 three);
    }

    interface TInt<T>
    {
        int apply(T one, int two);
    }

    interface TDouble<T>
    {
        int apply(T one, double two);
    }

    interface TLong<T>
    {
        int apply(T one, long two);
    }

    interface TFloat<T>
    {
        int apply(T one, float two);
    }

    interface Int
    {
        int apply(int each);
    }

    interface Int2
    {
        int apply(int one, int two);
    }

    interface IntT<T>
    {
        int apply(int one, T two);
    }

    interface Float
    {
        int apply(float each);
    }

    interface Float2
    {
        int apply(float one, float two);
    }

    interface FloatT<T>
    {
        int apply(float one, T two);
    }

    interface Double
    {
        int apply(double each);
    }

    interface Double2
    {
        int apply(double one, double two);
    }

    interface DoubleT<T>
    {
        int apply(double one, T two);
    }

    interface Long
    {
        int apply(long each);
    }

    interface Long2
    {
        int apply(long one, long two);
    }

    interface LongT<T>
    {
        int apply(long one, T two);
    }
}
