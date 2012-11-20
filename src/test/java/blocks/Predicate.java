package blocks;

public interface Predicate<T>
{
    interface Object<T>
    {
        boolean apply(T each);
    }

    interface ObjectObject<T1, T2>
    {
        boolean apply(T1 one, T2 two);
    }

    interface ObjectObjectObject<T1, T2, T3>
    {
        boolean apply(T1 one, T2 two, T3 three);
    }

    interface ObjectInt<T>
    {
        boolean apply(T one, int two);
    }

    interface ObjectDouble<T>
    {
        boolean apply(T one, double two);
    }

    interface ObjectLong<T>
    {
        boolean apply(T one, long two);
    }

    interface ObjectFloat<T>
    {
        boolean apply(T one, float two);
    }

    interface Int
    {
        boolean apply(int each);
    }

    interface IntInt
    {
        boolean apply(int one, int two);
    }

    interface IntObject<T>
    {
        boolean apply(int one, T two);
    }

    interface Float
    {
        boolean apply(float each);
    }

    interface FloatFloat
    {
        boolean apply(float one, float two);
    }

    interface FloatObject<T>
    {
        boolean apply(float one, T two);
    }

    interface Double
    {
        boolean apply(double each);
    }

    interface DoubleDouble
    {
        boolean apply(double one, double two);
    }

    interface DoubleObject<T>
    {
        boolean apply(double one, T two);
    }

    interface Long
    {
        boolean apply(long each);
    }

    interface LongLong
    {
        boolean apply(long one, long two);
    }

    interface LongObject<T>
    {
        boolean apply(Long one, T two);
    }
}
