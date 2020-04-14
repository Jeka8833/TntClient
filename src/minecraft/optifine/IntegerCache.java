package optifine;

public class IntegerCache
{
    private static final Integer[] cache = makeCache();

    private static Integer[] makeCache()
    {
        Integer[] ainteger = new Integer[4096];

        for (int i = 0; i < 4096; ++i)
        {
            ainteger[i] = i;
        }

        return ainteger;
    }

    public static Integer valueOf(int p_valueOf_0_)
    {
        return p_valueOf_0_ >= 0 && p_valueOf_0_ < 4096 ? cache[p_valueOf_0_] : Integer.valueOf(p_valueOf_0_);
    }
}
