package blocks;

import junit.framework.Assert;
import org.junit.Test;

public class LambdaTest
{
    @Test
    public void procedures()
    {
        Procedure.Object<String> a = aString -> {
            System.out.println("apply" + aString);
        };
        Procedure.ObjectObject<String, String> b = (aString1, aString2) -> {
            System.out.println("apply" + aString1 + aString2);
        };
        Procedure.ObjectObjectObject<String, String, String> c = (aString1, aString2, aString3) -> {
            System.out.println("apply" + aString1 + aString2 + aString3);
        };

        Procedure.ObjectInt<String> d = (aString, anInt) -> {
            System.out.println("apply" + aString + anInt);
        };
        Procedure.ObjectFloat<String> e = (aString, aFloat) -> {
            System.out.println("apply" + aString + aFloat);
        };
        Procedure.ObjectLong<String> f = (aString, aLong) -> {
            System.out.println("apply" + aString + aLong);
        };
        Procedure.ObjectDouble<String> g = (aString, aDouble) -> {
            System.out.println("apply" + aString + aDouble);
        };

        Procedure.Int h = anInt -> {
            System.out.println("apply" + anInt);
        };
        Procedure.IntInt i = (anInt1, anInt2) -> {
            System.out.println("apply" + anInt1 + anInt2);
        };
        Procedure.IntObject<String> j = (anInt, aString) -> {
            System.out.println("apply" + anInt + aString);
        };

        Procedure.Float k = aFloat -> {
            System.out.println("apply" + aFloat);
        };
        Procedure.FloatFloat l = (aFloat1, aFloat2) -> {
            System.out.println("apply" + aFloat1 + aFloat2);
        };
        Procedure.FloatObject<String> m = (aFloat, aString) -> {
            System.out.println("apply" + aFloat + aString);
        };

        Procedure.Double n = aDouble -> {
            System.out.println("apply" + aDouble);
        };
        Procedure.DoubleDouble o = (aDouble1, aDouble2) -> {
            System.out.println("apply" + aDouble1 + aDouble2);
        };
        Procedure.DoubleObject<String> p = (aDouble, aString) -> {
            System.out.println("apply" + aDouble + aString);
        };

        Procedure.Long q = aLong -> {
            System.out.println("apply" + aLong);
        };
        Procedure.LongLong r = (aLong1, aLong2) -> {
            System.out.println("apply" + aLong1 + aLong2);
        };
        Procedure.LongObject<String> s = (aLong, aString) -> {
            System.out.println("apply" + aLong + aString);
        };
    }

    @Test
    public void predicates()
    {
        Predicate.Object<String> a = aString -> true;
        Predicate.ObjectObject<String, String> b = (aString1, aString2) -> true;
        Predicate.ObjectObjectObject<String, String, String> c = (aString1, aString2, aString3) -> true;

        Predicate.ObjectInt<String> d = (aString, anInt) -> true;
        Predicate.ObjectFloat<String> e = (aString, aFloat) -> true;
        Predicate.ObjectLong<String> f = (aString, aLong) -> true;
        Predicate.ObjectDouble<String> g = (aString, aDouble) -> true;

        Predicate.Int h = anInt -> true;
        Predicate.IntInt i = (anInt1, anInt2) -> true;
        Predicate.IntObject<String> j = (anInt, aString) -> true;

        Predicate.Float k = aFloat -> true;
        Predicate.FloatFloat l = (aFloat1, aFloat2) -> true;
        Predicate.FloatObject<String> m = (aFloat, aString) -> true;

        Predicate.Double n = aDouble -> true;
        Predicate.DoubleDouble o = (aDouble1, aDouble2) -> true;
        Predicate.DoubleObject<String> p = (aDouble, aString) -> true;

        Predicate.Long q = aLong -> true;
        Predicate.LongLong r = (aLong1, aLong2) -> true;
        Predicate.LongObject<String> s = (aLong, aString) -> true;
    }

    @Test
    public void objectFunctions()
    {
        Function.Object.ToObject<String, String> a = aString -> "apply" + aString;
        Assert.assertEquals("applyA", a.apply("A"));

        Function.ObjectObject.ToObject<String, String, String> b = (aString1, aString2) -> "apply" + aString1 + aString2;
        Assert.assertEquals("applyAB", b.apply("A", "B"));

        Function.ObjectObjectObject.ToObject<String, String, String, String> c = (aString1, aString2, aString3) -> "apply" + aString1 + aString2 + aString3;
        Assert.assertEquals("applyABC", c.apply("A", "B", "C"));

        Function.ObjectInt.ToObject<String, String> d = (aString, anInt) -> "apply" + aString + anInt;
        Assert.assertEquals("apply12", d.apply("1", 2));

        Function.ObjectFloat.ToObject<String, String> e = (aString, aFloat) -> "apply" + aString + aFloat;
        Assert.assertEquals("apply12.0", e.apply("1", 2.0f));

        Function.ObjectLong.ToObject<String, String> f = (aString, aLong) -> "apply" + aString + aLong;
        Assert.assertEquals("apply12", f.apply("1", 2L));

        Function.ObjectDouble.ToObject<String, String> g = (aString, aDouble) -> "apply" + aString + aDouble;
        Assert.assertEquals("apply12.0", g.apply("1", 2.0d));

        Function.Int.ToObject<String> h = anInt -> "apply" + anInt;
        Assert.assertEquals("apply1", h.apply(1));

        Function.IntInt.ToObject<String> i = (anInt1, anInt2) -> "apply" + anInt1 + anInt2;
        Assert.assertEquals("apply12", i.apply(1, 2));

        Function.IntObject.ToObject<String, String> j = (anInt, aString) -> "apply" + anInt + aString;
        Assert.assertEquals("apply12", j.apply(1, "2"));

        Function.Float.ToObject<String> k = aFloat -> "apply" + aFloat;
        Assert.assertEquals("apply1.0", k.apply(1.0f));

        Function.FloatFloat.ToObject<String> l = (aFloat1, aFloat2) -> "apply" + aFloat1 + aFloat2;
        Assert.assertEquals("apply1.02.0", l.apply(1.0f, 2.0f));

        Function.FloatObject.ToObject<String, String> m = (aFloat, aString) -> "apply" + aFloat + aString;
        Assert.assertEquals("apply1.02", m.apply(1.0f, "2"));

        Function.Double.ToObject<String> n = aDouble -> "apply" + aDouble;
        Assert.assertEquals("apply1.0", n.apply(1.0d));

        Function.DoubleDouble.ToObject<String> o = (aDouble1, aDouble2) -> "apply" + aDouble1 + aDouble2;
        Assert.assertEquals("apply1.02.0", o.apply(1.0d, 2.0d));

        Function.DoubleObject.ToObject<String, String> p = (aDouble, aString) -> "apply" + aDouble + aString;
        Assert.assertEquals("apply1.02", p.apply(1.0d, "2"));

        Function.Long.ToObject<String> q = aLong -> "apply" + aLong;
        Assert.assertEquals("apply1", q.apply(1L));

        Function.LongLong.ToObject<String> r = (aLong1, aLong2) -> "apply" + aLong1 + aLong2;
        Assert.assertEquals("apply12", r.apply(1L, 2L));

        Function.LongObject.ToObject<String, String> s = (aLong, aString) -> "apply" + aLong + aString;
        Assert.assertEquals("apply12", s.apply(1L, "2"));
    }

    @Test
    public void intFunctions()
    {
        Function.ToObject<String> empty = () -> "empty";
        Function.ToInt<String> emptyInt = () -> 1;
        Function.ToFloat<String> emptyFloat = () -> 1.0f;
        Function.ToLong<String> emptyDouble = () -> 1;

        Function.Object.ToInt<String> a = aString -> Integer.parseInt(aString);
        Assert.assertEquals(1, a.apply("1"));
        Function.ObjectObject.ToInt<String, String> b = (aString1, aString2) -> Integer.parseInt(aString1) + Integer.parseInt(aString2);
        Assert.assertEquals(3, b.apply("1", "2"));
        Function.ObjectObjectObject.ToInt<String, String, String> c = (aString1, aString2, aString3) -> Integer.parseInt(aString1) + Integer.parseInt(aString2) + Integer.parseInt(aString3);
        Assert.assertEquals(6, c.apply("1", "2", "3"));

        Function.ObjectInt.ToInt<String> d = (aString, anInt) -> Integer.parseInt(aString) + anInt;
        Assert.assertEquals(3, d.apply("1", 2));
        Function.ObjectFloat.ToInt<String> e = (aString, aFloat) -> Integer.parseInt(aString) + (int) aFloat;
        Assert.assertEquals(3, e.apply("1", 2.0f));
        Function.ObjectLong.ToInt<String> f = (aString, aLong) -> Integer.parseInt(aString) + (int) aLong;
        Assert.assertEquals(3, f.apply("1", 2L));
        Function.ObjectDouble.ToInt<String> g = (aString, aDouble) -> Integer.parseInt(aString) + (int) aDouble;
        Assert.assertEquals(3, g.apply("1", 2.0d));

        Function.Int.ToInt h = anInt -> anInt;
        Assert.assertEquals(1, h.apply(1));
        Function.IntInt.ToInt i = (anInt1, anInt2) -> anInt1 + anInt2;
        Assert.assertEquals(3, i.apply(1, 2));
        Function.IntObject.ToInt<String> j = (anInt, aString) -> anInt + Integer.parseInt(aString);
        Assert.assertEquals(3, j.apply(1, "2"));

        Function.Float.ToInt k = aFloat -> (int) aFloat;
        Assert.assertEquals(1, k.apply(1.0f));
        Function.FloatFloat.ToInt l = (aFloat1, aFloat2) -> (int) aFloat1 + (int) aFloat2;
        Assert.assertEquals(3, l.apply(1.0f, 2.0f));
        Function.FloatObject.ToInt<String> m = (aFloat, aString) -> (int) aFloat + Integer.parseInt(aString);
        Assert.assertEquals(3, m.apply(1.0f, "2"));

        Function.Double.ToInt n = aDouble -> (int) aDouble;
        Assert.assertEquals(1, n.apply(1.0d));
        Function.DoubleDouble.ToInt o = (aDouble1, aDouble2) -> (int) aDouble1 + (int) aDouble2;
        Assert.assertEquals(3, o.apply(1.0d, 2.0d));
        Function.DoubleObject.ToInt<String> p = (aDouble, aString) -> (int) aDouble + Integer.parseInt(aString);
        Assert.assertEquals(3, p.apply(1.0d, "2"));

        Function.Long.ToInt q = aLong -> (int) aLong;
        Assert.assertEquals(1, q.apply(1L));
        Function.LongLong.ToInt r = (aLong1, aLong2) -> (int) aLong1 + (int) aLong2;
        Assert.assertEquals(3, r.apply(1L, 2L));
        Function.LongObject.ToInt<String> s = (aLong, aString) -> (int) aLong + Integer.parseInt(aString);
        Assert.assertEquals(3, s.apply(1L, "2"));
    }
}
