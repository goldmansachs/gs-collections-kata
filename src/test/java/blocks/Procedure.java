package blocks;

public interface Procedure<T>
{
    void apply(T each);

    interface Object<T> extends Procedure<T>
    {
    }

    interface ObjectObject<T1, T2>
    {
        void apply(T1 one, T2 two);
    }

    interface ObjectObjectObject<T1, T2, T3>
    {
        void apply(T1 one, T2 two, T3 three);
    }

    interface ObjectInt<T>
    {
        void apply(T one, int two);
    }

    interface ObjectFloat<T>
    {
        void apply(T one, float two);
    }

    interface ObjectDouble<T>
    {
        void apply(T one, double two);
    }

    interface ObjectLong<T>
    {
        void apply(T one, long two);
    }

    interface Int
    {
        void apply(int each);
    }

    interface IntInt
    {
        void apply(int one, int two);
    }

    interface IntObject<T>
    {
        void apply(int one, T two);
    }

    interface Float
    {
        void apply(float each);
    }

    interface FloatFloat
    {
        void apply(float one, float two);
    }

    interface FloatObject<T>
    {
        void apply(float one, T two);
    }

    interface Double
    {
        void apply(double each);
    }

    interface DoubleDouble
    {
        void apply(double one, double two);
    }

    interface DoubleObject<T>
    {
        void apply(double one, T two);
    }

    interface Long
    {
        void apply(long each);
    }

    interface LongLong
    {
        void apply(long one, long two);
    }

    interface LongObject<T>
    {
        void apply(long one, T two);
    }
}
