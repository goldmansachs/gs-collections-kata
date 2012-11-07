package blocks;

public interface Procedure
{
    void apply();

    interface T<T>
    {
        void apply(T each);
    }

    interface T2<T1, T2>
    {
        void apply(T1 one, T2 two);
    }

    interface T3<T1, T2, T3>
    {
        void apply(T1 one, T2 two, T3 three);
    }

    interface TInt<T>
    {
        void apply(T one, int two);
    }

    interface TFloat<T>
    {
        void apply(T one, float two);
    }

    interface TDouble<T>
    {
        void apply(T one, double two);
    }

    interface TLong<T>
    {
        void apply(T one, long two);
    }

    interface Int
    {
        void apply(int each);
    }

    interface Int2
    {
        void apply(int one, int two);
    }

    interface IntT<T>
    {
        void apply(int one, T two);
    }

    interface Float
    {
        void apply(float each);
    }

    interface Float2
    {
        void apply(float one, float two);
    }

    interface FloatT<T>
    {
        void apply(float one, T two);
    }

    interface Double
    {
        void apply(double each);
    }

    interface Double2
    {
        void apply(double one, double two);
    }

    interface DoubleT<T>
    {
        void apply(double one, T two);
    }

    interface Long
    {
        void apply(long each);
    }

    interface Long2
    {
        void apply(long one, long two);
    }

    interface LongT<T>
    {
        void apply(long one, T two);
    }
}
