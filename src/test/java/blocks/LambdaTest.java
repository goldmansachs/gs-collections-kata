package blocks;

import junit.framework.Assert;
import org.junit.Test;

public class LambdaTest
{
    @Test
    public void procedures()
    {
        Procedure empty = () -> {System.out.println("apply");};

        Procedure.T<String> a = aString -> {System.out.println("apply" + aString);};
        Procedure.T2<String, String> b = (aString1, aString2) -> {System.out.println("apply" + aString1 + aString2);};
        Procedure.T3<String, String, String> c = (aString1, aString2, aString3) -> {System.out.println("apply" + aString1 + aString2 + aString3);};

        Procedure.TInt<String> d = (aString, anInt) -> {System.out.println("apply" + aString + anInt);};
        Procedure.TFloat<String> e = (aString, aFloat) -> {System.out.println("apply" + aString + aFloat);};
        Procedure.TLong<String> f = (aString, aLong) -> {System.out.println("apply" + aString + aLong);};
        Procedure.TDouble<String> g = (aString, aDouble) -> {System.out.println("apply" + aString + aDouble);};

        Procedure.Int h = anInt -> {System.out.println("apply" + anInt);};
        Procedure.Int2 i = (anInt1, anInt2) -> {System.out.println("apply" + anInt1 + anInt2);};
        Procedure.IntT<String> j = (anInt, aString) -> {System.out.println("apply" + anInt + aString);};

        Procedure.Float k = aFloat -> {System.out.println("apply" + aFloat);};
        Procedure.Float2 l = (aFloat1, aFloat2) -> {System.out.println("apply" + aFloat1 + aFloat2);};
        Procedure.FloatT<String> m = (aFloat, aString) -> {System.out.println("apply" + aFloat + aString);};

        Procedure.Double n = aDouble -> {System.out.println("apply" + aDouble);};
        Procedure.Double2 o = (aDouble1, aDouble2) -> {System.out.println("apply" + aDouble1 + aDouble2);};
        Procedure.DoubleT<String> p = (aDouble, aString) -> {System.out.println("apply" + aDouble + aString);};

        Procedure.Long q = aLong -> {System.out.println("apply" + aLong);};
        Procedure.Long2 r = (aLong1, aLong2) -> {System.out.println("apply" + aLong1 + aLong2);};
        Procedure.LongT<String> s = (aLong, aString) -> {System.out.println("apply" + aLong + aString);};
    }

    @Test
    public void objectFunctions()
    {
        Function<String> empty = () -> "apply";
        Assert.assertEquals("apply", empty.apply());

        Function.T<String, String> a = aString -> "apply" + aString;
        Assert.assertEquals("apply1", a.apply("1"));

        Function.T2<String, String, String> b = (aString1, aString2) -> "apply" + aString1 + aString2;
        Assert.assertEquals("apply12", b.apply("1", "2"));

        Function.T3<String, String, String, String> c = (aString1, aString2, aString3) -> "apply" + aString1 + aString2 + aString3;
        Assert.assertEquals("apply123", c.apply("1", "2", "3"));

        Function.TInt<String, String> d = (aString, anInt) -> "apply" + aString + anInt;
        Assert.assertEquals("apply12", d.apply("1", 2));

        Function.TFloat<String, String> e = (aString, aFloat) -> "apply" + aString + aFloat;
        Assert.assertEquals("apply12.0", e.apply("1", 2.0f));

        Function.TLong<String, String> f = (aString, aLong) -> "apply" + aString + aLong;
        Assert.assertEquals("apply12", f.apply("1", 2L));

        Function.TDouble<String, String> g = (aString, aDouble) -> "apply" + aString + aDouble;
        Assert.assertEquals("apply12.0", g.apply("1", 2.0d));

        Function.Int<String> h = anInt -> "apply" + anInt;
        Assert.assertEquals("apply1", h.apply(1));

        Function.Int2<String> i = (anInt1, anInt2) -> "apply" + anInt1 + anInt2;
        Assert.assertEquals("apply12", i.apply(1, 2));

        Function.IntT<String, String> j = (anInt, aString) -> "apply" + anInt + aString;
        Assert.assertEquals("apply12", j.apply(1, "2"));

        Function.Float<String> k = aFloat -> "apply" + aFloat;
        Assert.assertEquals("apply1.0", k.apply(1.0f));

        Function.Float2<String> l = (aFloat1, aFloat2) -> "apply" + aFloat1 + aFloat2;
        Assert.assertEquals("apply1.02.0", l.apply(1.0f, 2.0f));

        Function.FloatT<String, String> m = (aFloat, aString) -> "apply" + aFloat + aString;
        Assert.assertEquals("apply1.02", m.apply(1.0f, "2"));

        Function.Double<String> n = aDouble -> "apply" + aDouble;
        Assert.assertEquals("apply1.0", n.apply(1.0d));

        Function.Double2<String> o = (aDouble1, aDouble2) -> "apply" + aDouble1 + aDouble2;
        Assert.assertEquals("apply1.02.0", o.apply(1.0d, 2.0d));

        Function.DoubleT<String, String> p = (aDouble, aString) -> "apply" + aDouble + aString;
        Assert.assertEquals("apply1.02", p.apply(1.0d, "2"));

        Function.Long<String> q = aLong -> "apply" + aLong;
        Assert.assertEquals("apply1", q.apply(1L));

        Function.Long2<String> r = (aLong1, aLong2) -> "apply" + aLong1 + aLong2;
        Assert.assertEquals("apply12", r.apply(1L, 2L));

        Function.LongT<String, String> s =(aLong, aString) -> "apply" + aLong + aString;
        Assert.assertEquals("apply12", s.apply(1L, "2"));
    }

    @Test
    public void intFunctions()
    {
        IntFunction empty = () -> 0;
        Assert.assertEquals(0, empty.apply());

        IntFunction.T<String> a = aString -> Integer.parseInt(aString);
        Assert.assertEquals(1, a.apply("1"));
        IntFunction.T2<String, String> b = (aString1, aString2) -> Integer.parseInt(aString1) + Integer.parseInt(aString2);
        Assert.assertEquals(3, b.apply("1", "2"));
        IntFunction.T3<String, String, String> c = (aString1, aString2, aString3) -> Integer.parseInt(aString1) + Integer.parseInt(aString2) + Integer.parseInt(aString3);
        Assert.assertEquals(6, c.apply("1", "2", "3"));

        IntFunction.TInt<String> d = (aString, anInt) -> Integer.parseInt(aString) + anInt;
        Assert.assertEquals(3, d.apply("1", 2));
        IntFunction.TFloat<String> e = (aString, aFloat) -> Integer.parseInt(aString) + (int)aFloat;
        Assert.assertEquals(3, e.apply("1", 2.0f));
        IntFunction.TLong<String> f = (aString, aLong) -> Integer.parseInt(aString) + (int)aLong;
        Assert.assertEquals(3, f.apply("1", 2L));
        IntFunction.TDouble<String> g = (aString, aDouble) -> Integer.parseInt(aString) + (int)aDouble;
        Assert.assertEquals(3, g.apply("1", 2.0d));

        IntFunction.Int h = anInt -> anInt;
        Assert.assertEquals(1, h.apply(1));
        IntFunction.Int2 i = (anInt1, anInt2) -> anInt1 + anInt2;
        Assert.assertEquals(3, i.apply(1, 2));
        IntFunction.IntT<String> j = (anInt, aString) -> anInt + Integer.parseInt(aString);
        Assert.assertEquals(3, j.apply(1, "2"));

        IntFunction.Float k = aFloat -> (int)aFloat;
        Assert.assertEquals(1, k.apply(1.0f));
        IntFunction.Float2 l = (aFloat1, aFloat2) -> (int)aFloat1 + (int)aFloat2;
        Assert.assertEquals(3, l.apply(1.0f, 2.0f));
        IntFunction.FloatT<String> m = (aFloat, aString) -> (int)aFloat + Integer.parseInt(aString);
        Assert.assertEquals(3, m.apply(1.0f, "2"));

        IntFunction.Double n = aDouble -> (int)aDouble;
        Assert.assertEquals(1, n.apply(1.0d));
        IntFunction.Double2 o = (aDouble1, aDouble2) -> (int)aDouble1 + (int)aDouble2;
        Assert.assertEquals(3, o.apply(1.0d, 2.0d));
        IntFunction.DoubleT<String> p = (aDouble, aString) -> (int)aDouble + Integer.parseInt(aString);
        Assert.assertEquals(3, p.apply(1.0d, "2"));

        IntFunction.Long q = aLong -> (int)aLong;
        Assert.assertEquals(1, q.apply(1L));
        IntFunction.Long2 r = (aLong1, aLong2) -> (int)aLong1 + (int)aLong2;
        Assert.assertEquals(3, r.apply(1L, 2L));
        IntFunction.LongT<String> s =(aLong, aString) -> (int)aLong + Integer.parseInt(aString);
        Assert.assertEquals(3, s.apply(1L, "2"));
    }
}
