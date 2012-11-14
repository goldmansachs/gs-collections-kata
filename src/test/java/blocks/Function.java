package blocks;

public interface Function<R>
{
    interface ToObject<R>
    {
        R apply();
    }

    interface ToInt<T>
    {
        int apply();
    }

    interface ToLong<T>
    {
        long apply();
    }

    interface ToFloat<T>
    {
        float apply();
    }

    interface ToDouble<T>
    {
        double apply();
    }

    interface Object
    {
        interface ToObject<T, R>
        {
            R apply(T each);
        }

        interface ToInt<T>
        {
            int apply(T each);
        }

        interface ToLong<T>
        {
            long apply(T each);
        }

        interface ToFloat<T>
        {
            float apply(T each);
        }

        interface ToDouble<T>
        {
            double apply(T each);
        }
    }

    interface ObjectObject
    {
        interface ToObject<T1, T2, R>
        {
            R apply(T1 one, T2 two);
        }

        interface ToInt<T1, T2>
        {
            int apply(T1 one, T2 two);
        }

        interface ToLong<T1, T2>
        {
            long apply(T1 one, T2 two);
        }

        interface ToFloat<T1, T2>
        {
            float apply(T1 one, T2 two);
        }

        interface ToDouble<T1, T2>
        {
            double apply(T1 one, T2 two);
        }
    }

    interface ObjectObjectObject<T1, T2, T3, R>
    {
        interface ToObject<T1, T2, T3, R>
        {
            R apply(T1 one, T2 two, T3 three);
        }

        interface ToInt<T1, T2, T3>
        {
            int apply(T1 one, T2 two, T3 three);
        }

        interface ToLong<T1, T2, T3>
        {
            long apply(T1 one, T2 two, T3 three);
        }

        interface ToFloat<T1, T2, T3>
        {
            float apply(T1 one, T2 two, T3 three);
        }

        interface ToDouble<T1, T2, T3>
        {
            double apply(T1 one, T2 two, T3 three);
        }
    }

    interface ObjectInt
    {
        interface ToObject<T, R>
        {
            R apply(T one, int two);
        }

        interface ToInt<T>
        {
            int apply(T one, int two);
        }

        interface ToLong<T>
        {
            long apply(T one, int two);
        }

        interface ToFloat<T>
        {
            float apply(T one, int two);
        }

        interface ToDouble<T>
        {
            double apply(T one, int two);
        }
    }

    interface ObjectDouble
    {
        interface ToObject<T, R>
        {
            R apply(T one, double two);
        }

        interface ToInt<T>
        {
            int apply(T one, double two);
        }

        interface ToLong<T>
        {
            long apply(T one, double two);
        }

        interface ToFloat<T>
        {
            float apply(T one, double two);
        }

        interface ToDouble<T>
        {
            double apply(T one, double two);
        }
    }

    interface ObjectLong
    {
        interface ToObject<T, R>
        {
            R apply(T one, long two);
        }

        interface ToInt<T>
        {
            int apply(T one, long two);
        }

        interface ToLong<T>
        {
            long apply(T one, long two);
        }

        interface ToFloat<T>
        {
            float apply(T one, long two);
        }

        interface ToDouble<T>
        {
            double apply(T one, long two);
        }
    }

    interface ObjectFloat
    {
        interface ToObject<T, R>
        {
            R apply(T one, float two);
        }

        interface ToInt<T>
        {
            int apply(T one, float two);
        }

        interface ToLong<T>
        {
            long apply(T one, float two);
        }

        interface ToFloat<T>
        {
            float apply(T one, float two);
        }

        interface ToDouble<T>
        {
            double apply(T one, float two);
        }
    }

    interface Int
    {
        interface ToObject<R>
        {
            R apply(int each);
        }

        interface ToInt
        {
            int apply(int each);
        }

        interface ToLong
        {
            long apply(int each);
        }

        interface ToFloat
        {
            float apply(int each);
        }

        interface ToDouble
        {
            double apply(int each);
        }
    }

    interface IntInt
    {
        interface ToObject<R>
        {
            R apply(int one, int two);
        }

        interface ToInt
        {
            int apply(int one, int two);
        }

        interface ToLong
        {
            long apply(int one, int two);
        }

        interface ToFloat
        {
            float apply(int one, int two);
        }

        interface ToDouble
        {
            double apply(int one, int two);
        }
    }

    interface IntObject
    {
        interface ToObject<T, R>
        {
            R apply(int one, T two);
        }

        interface ToInt<T>
        {
            int apply(int one, T two);
        }

        interface ToLong<T>
        {
            long apply(int one, T two);
        }

        interface ToFloat<T>
        {
            float apply(int one, T two);
        }

        interface ToDouble<T>
        {
            double apply(int one, T two);
        }
    }

    interface Float
    {
        interface ToObject<R>
        {
            R apply(float each);
        }

        interface ToInt
        {
            int apply(float each);
        }

        interface ToLong
        {
            long apply(float each);
        }

        interface ToFloat
        {
            float apply(float each);
        }

        interface ToDouble
        {
            double apply(float each);
        }
    }

    interface FloatFloat
    {
        interface ToObject<R>
        {
            R apply(float one, float two);
        }

        interface ToInt
        {
            int apply(float one, float two);
        }

        interface ToLong
        {
            long apply(float one, float two);
        }

        interface ToFloat
        {
            float apply(float one, float two);
        }

        interface ToDouble
        {
            double apply(float one, float two);
        }
    }

    interface FloatObject
    {
        interface ToObject<T, R>
        {
            R apply(float one, T two);
        }

        interface ToInt<T>
        {
            int apply(float one, T two);
        }

        interface ToLong<T>
        {
            long apply(float one, T two);
        }

        interface ToFloat<T>
        {
            float apply(float one, T two);
        }

        interface ToDouble<T>
        {
            double apply(float one, T two);
        }
    }

    interface Double
    {
        interface ToObject<R>
        {
            R apply(double each);
        }

        interface ToInt
        {
            int apply(double each);
        }

        interface ToLong
        {
            long apply(double each);
        }

        interface ToFloat
        {
            float apply(double each);
        }

        interface ToDouble
        {
            double apply(double each);
        }
    }

    interface DoubleDouble
    {
        interface ToObject<R>
        {
            R apply(double one, double two);
        }

        interface ToInt
        {
            int apply(double one, double two);
        }

        interface ToLong
        {
            long apply(double one, double two);
        }

        interface ToFloat
        {
            float apply(double one, double two);
        }

        interface ToDouble
        {
            double apply(double one, double two);
        }
    }

    interface DoubleObject
    {
        interface ToObject<T, R>
        {
            R apply(double one, T two);
        }

        interface ToInt<T>
        {
            int apply(double one, T two);
        }

        interface ToLong<T>
        {
            long apply(double one, T two);
        }

        interface ToFloat<T>
        {
            float apply(double one, T two);
        }

        interface ToDouble<T>
        {
            double apply(double one, T two);
        }
    }

    interface Long
    {
        interface ToObject<R>
        {
            R apply(long each);
        }

        interface ToInt
        {
            int apply(double each);
        }

        interface ToLong
        {
            long apply(double each);
        }

        interface ToFloat
        {
            float apply(double each);
        }

        interface ToDouble
        {
            double apply(double each);
        }
    }

    interface LongLong<R>
    {
        interface ToObject<R>
        {
            R apply(long one, long two);
        }

        interface ToInt
        {
            int apply(long one, long two);
        }

        interface ToLong
        {
            long apply(long one, long two);
        }

        interface ToFloat
        {
            float apply(long one, long two);
        }

        interface ToDouble
        {
            double apply(long one, long two);
        }
    }

    interface LongObject
    {
        interface ToObject<T, R>
        {
            R apply(long one, T two);
        }

        interface ToInt<T>
        {
            int apply(long one, T two);
        }

        interface ToLong<T>
        {
            long apply(long one, T two);
        }

        interface ToFloat<T>
        {
            float apply(long one, T two);
        }

        interface ToDouble<T>
        {
            double apply(long one, T two);
        }
    }
}
