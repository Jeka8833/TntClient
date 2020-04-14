package optifine;

public class ReflectorClass
{
    private String targetClassName;
    private boolean checked;
    private Class targetClass;

    public ReflectorClass(String p_i81_1_)
    {
        this(p_i81_1_, false);
    }

    public ReflectorClass(String p_i82_1_, boolean p_i82_2_)
    {
        this.targetClassName = null;
        this.checked = false;
        this.targetClass = null;
        this.targetClassName = p_i82_1_;

        if (!p_i82_2_)
        {
            this.getTargetClass();
        }
    }

    public ReflectorClass(Class p_i83_1_)
    {
        this.targetClassName = null;
        this.checked = false;
        this.targetClass = null;
        this.targetClass = p_i83_1_;
        this.targetClassName = p_i83_1_.getName();
        this.checked = true;
    }

    public Class getTargetClass()
    {
        if (this.checked)
        {
            return this.targetClass;
        }
        else
        {
            this.checked = true;

            try
            {
                this.targetClass = Class.forName(this.targetClassName);
            }
            catch (ClassNotFoundException var2)
            {
                Config.log("(Reflector) Class not present: " + this.targetClassName);
            }
            catch (Throwable throwable)
            {
                throwable.printStackTrace();
            }

            return this.targetClass;
        }
    }

    public boolean exists()
    {
        return this.getTargetClass() != null;
    }

    public boolean isInstance(Object p_isInstance_1_)
    {
        return this.getTargetClass() != null && this.getTargetClass().isInstance(p_isInstance_1_);
    }
}
