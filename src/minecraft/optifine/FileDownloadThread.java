package optifine;

import net.minecraft.client.Minecraft;

public class FileDownloadThread extends Thread
{
    private final String urlString;
    private final IFileDownloadListener listener;

    public FileDownloadThread(String p_i41_1_, IFileDownloadListener p_i41_2_)
    {
        this.urlString = p_i41_1_;
        this.listener = p_i41_2_;
    }

    public void run()
    {
        try
        {
            byte[] abyte = HttpPipeline.get(this.urlString, Minecraft.getMinecraft().getProxy());
            this.listener.fileDownloadFinished(this.urlString, abyte);
        }
        catch (Exception exception)
        {
            this.listener.fileDownloadFinished(this.urlString, null);
        }
    }

    public IFileDownloadListener getListener()
    {
        return this.listener;
    }
}
