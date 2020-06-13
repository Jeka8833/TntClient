package optifine;

import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequest
{
    private final String host;
    private int port;
    private final Proxy proxy;
    private final String method;
    private final String file;
    private final String http;
    private Map<String, String> headers;
    private final byte[] body;
    private int redirects = 0;

    public HttpRequest(String p_i60_1_, int p_i60_2_, Proxy p_i60_3_, String p_i60_4_, String p_i60_5_, String p_i60_6_, Map<String, String> p_i60_7_, byte[] p_i60_8_)
    {
        this.host = p_i60_1_;
        port = 0;
        this.port = p_i60_2_;
        this.proxy = p_i60_3_;
        this.method = p_i60_4_;
        this.file = p_i60_5_;
        this.http = p_i60_6_;
        this.headers = p_i60_7_;
        this.body = p_i60_8_;
    }

    public String getHost()
    {
        return this.host;
    }

    public int getPort()
    {
        return this.port;
    }

    public String getMethod()
    {
        return this.method;
    }

    public String getFile()
    {
        return this.file;
    }

    public String getHttp()
    {
        return this.http;
    }

    public Map<String, String> getHeaders()
    {
        return this.headers;
    }

    public byte[] getBody()
    {
        return this.body;
    }

    public int getRedirects()
    {
        return this.redirects;
    }

    public void setRedirects(int p_setRedirects_1_)
    {
        this.redirects = p_setRedirects_1_;
    }

    public Proxy getProxy()
    {
        return this.proxy;
    }
}
