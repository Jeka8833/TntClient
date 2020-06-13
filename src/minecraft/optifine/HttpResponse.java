package optifine;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse
{
    private final int status;
    private Map<String, String> headers;
    private final byte[] body;

    public HttpResponse(int p_i61_1_, Map p_i61_3_, byte[] p_i61_4_)
    {
        this.status = p_i61_1_;
        this.headers = p_i61_3_;
        this.body = p_i61_4_;
    }

    public int getStatus()
    {
        return this.status;
    }

    public Map getHeaders()
    {
        return this.headers;
    }

    public String getHeader(String p_getHeader_1_)
    {
        return this.headers.get(p_getHeader_1_);
    }

    public byte[] getBody()
    {
        return this.body;
    }
}
