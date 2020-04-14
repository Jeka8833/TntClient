package net.optifine.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;

public class ResUtils {
   public static String[] collectFiles(String prefix, String suffix) {
      return collectFiles(new String[]{prefix}, new String[]{suffix});
   }

   public static String[] collectFiles(String[] prefixes, String[] suffixes) {
      Set<String> setPaths = new LinkedHashSet();
      IResourcePack[] rps = Config.getResourcePacks();

      for(int i = 0; i < rps.length; ++i) {
         IResourcePack rp = rps[i];
         String[] ps = collectFiles(rp, (String[])prefixes, (String[])suffixes, (String[])null);
         setPaths.addAll(Arrays.asList(ps));
      }

      String[] paths = (String[])setPaths.toArray(new String[setPaths.size()]);
      return paths;
   }

   public static String[] collectFiles(IResourcePack rp, String prefix, String suffix, String[] defaultPaths) {
      return collectFiles(rp, new String[]{prefix}, new String[]{suffix}, defaultPaths);
   }

   public static String[] collectFiles(IResourcePack rp, String[] prefixes, String[] suffixes) {
      return collectFiles(rp, (String[])prefixes, (String[])suffixes, (String[])null);
   }

   public static String[] collectFiles(IResourcePack rp, String[] prefixes, String[] suffixes, String[] defaultPaths) {
      if (rp instanceof DefaultResourcePack) {
         return collectFilesFixed(rp, defaultPaths);
      } else if (!(rp instanceof AbstractResourcePack)) {
         Config.warn("Unknown resource pack type: " + rp);
         return new String[0];
      } else {
         AbstractResourcePack arp = (AbstractResourcePack)rp;
         File tpFile = arp.field_110597_b;
         if (tpFile == null) {
            return new String[0];
         } else if (tpFile.isDirectory()) {
            return collectFilesFolder(tpFile, "", prefixes, suffixes);
         } else if (tpFile.isFile()) {
            return collectFilesZIP(tpFile, prefixes, suffixes);
         } else {
            Config.warn("Unknown resource pack file: " + tpFile);
            return new String[0];
         }
      }
   }

   private static String[] collectFilesFixed(IResourcePack rp, String[] paths) {
      if (paths == null) {
         return new String[0];
      } else {
         List list = new ArrayList();

         for(int i = 0; i < paths.length; ++i) {
            String path = paths[i];
            ResourceLocation loc = new ResourceLocation(path);
            if (rp.func_110589_b(loc)) {
               list.add(path);
            }
         }

         String[] pathArr = (String[])((String[])list.toArray(new String[list.size()]));
         return pathArr;
      }
   }

   private static String[] collectFilesFolder(File tpFile, String basePath, String[] prefixes, String[] suffixes) {
      List list = new ArrayList();
      String prefixAssets = "assets/minecraft/";
      File[] files = tpFile.listFiles();
      if (files == null) {
         return new String[0];
      } else {
         for(int i = 0; i < files.length; ++i) {
            File file = files[i];
            String name;
            if (file.isFile()) {
               name = basePath + file.getName();
               if (name.startsWith(prefixAssets)) {
                  name = name.substring(prefixAssets.length());
                  if (StrUtils.startsWith(name, prefixes) && StrUtils.endsWith(name, suffixes)) {
                     list.add(name);
                  }
               }
            } else if (file.isDirectory()) {
               name = basePath + file.getName() + "/";
               String[] names = collectFilesFolder(file, name, prefixes, suffixes);

               for(int n = 0; n < names.length; ++n) {
                  String name = names[n];
                  list.add(name);
               }
            }
         }

         String[] names = (String[])((String[])list.toArray(new String[list.size()]));
         return names;
      }
   }

   private static String[] collectFilesZIP(File tpFile, String[] prefixes, String[] suffixes) {
      List list = new ArrayList();
      String prefixAssets = "assets/minecraft/";

      try {
         ZipFile zf = new ZipFile(tpFile);
         Enumeration en = zf.entries();

         while(en.hasMoreElements()) {
            ZipEntry ze = (ZipEntry)en.nextElement();
            String name = ze.getName();
            if (name.startsWith(prefixAssets)) {
               name = name.substring(prefixAssets.length());
               if (StrUtils.startsWith(name, prefixes) && StrUtils.endsWith(name, suffixes)) {
                  list.add(name);
               }
            }
         }

         zf.close();
         String[] names = (String[])((String[])list.toArray(new String[list.size()]));
         return names;
      } catch (IOException var9) {
         var9.printStackTrace();
         return new String[0];
      }
   }

   private static boolean isLowercase(String str) {
      return str.equals(str.toLowerCase(Locale.ROOT));
   }

   public static Properties readProperties(String path, String module) {
      ResourceLocation loc = new ResourceLocation(path);

      try {
         InputStream in = Config.getResourceStream(loc);
         if (in == null) {
            return null;
         } else {
            Properties props = new PropertiesOrdered();
            props.load(in);
            in.close();
            Config.dbg("" + module + ": Loading " + path);
            return props;
         }
      } catch (FileNotFoundException var5) {
         return null;
      } catch (IOException var6) {
         Config.warn("" + module + ": Error reading " + path);
         return null;
      }
   }

   public static Properties readProperties(InputStream in, String module) {
      if (in == null) {
         return null;
      } else {
         try {
            Properties props = new PropertiesOrdered();
            props.load(in);
            in.close();
            return props;
         } catch (FileNotFoundException var3) {
            return null;
         } catch (IOException var4) {
            return null;
         }
      }
   }
}
