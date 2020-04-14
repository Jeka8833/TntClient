package optifine;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import cpw.mods.modlauncher.api.ITransformer.Target;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class OptiFineTransformer implements ITransformer<ClassNode>, IResourceProvider, IOptiFineResourceLocator {
   private static final Logger LOGGER = LogManager.getLogger();
   private ZipFile ofZipFile;
   private Map<String, String> patchMap = null;
   private Pattern[] patterns = null;
   public static final String PREFIX_SRG = "srg/";
   public static final String SUFFIX_CLASS = ".class";
   public static final String PREFIX_PATCH_SRG = "patch/srg/";
   public static final String SUFFIX_CLASS_XDELTA = ".class.xdelta";
   public static final String PREFIX_OPTIFINE = "optifine/";

   public OptiFineTransformer(ZipFile ofZipFile) {
      this.ofZipFile = ofZipFile;

      try {
         this.patchMap = Patcher.getConfigurationMap(ofZipFile);
         this.patterns = Patcher.getConfigurationPatterns(this.patchMap);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public TransformerVoteResult castVote(ITransformerVotingContext context) {
      return TransformerVoteResult.YES;
   }

   public Set<Target> targets() {
      Set<Target> set = new HashSet();
      String[] names = this.getResourceNames("srg/", ".class");
      String[] namesPatch = this.getResourceNames("patch/srg/", ".class.xdelta");
      names = (String[])Utils.addObjectsToArray(names, namesPatch);

      for(int i = 0; i < names.length; ++i) {
         String name = names[i];
         name = Utils.removePrefix(name, new String[]{"srg/", "patch/srg/"});
         name = Utils.removeSuffix(name, new String[]{".class", ".class.xdelta"});
         if (!name.startsWith("net/optifine/")) {
            Target itt = Target.targetClass(name);
            set.add(itt);
         }
      }

      LOGGER.info("Targets: " + set.size());
      return set;
   }

   public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
      ClassNode classNode = input;
      String className = context.getClassName();
      String classNameZip = className.replace('.', '/');
      byte[] bytes = this.getOptiFineResource("srg/" + classNameZip + ".class");
      if (bytes != null) {
         InputStream in = new ByteArrayInputStream(bytes);
         ClassNode classNodeNew = this.loadClass(in);
         if (classNodeNew != null) {
            this.debugClass(classNodeNew);
            AccessFixer.fixMemberAccess(input, classNodeNew);
            classNode = classNodeNew;
         }
      }

      return classNode;
   }

   private void debugClass(ClassNode classNode) {
   }

   private ClassNode loadClass(InputStream in) {
      try {
         ClassReader cr = new ClassReader(in);
         ClassNode cn = new ClassNode(393216);
         cr.accept(cn, 0);
         return cn;
      } catch (IOException var4) {
         var4.printStackTrace();
         return null;
      }
   }

   private String[] getResourceNames(String prefix, String suffix) {
      List<String> list = new ArrayList();
      Enumeration entries = this.ofZipFile.entries();

      while(entries.hasMoreElements()) {
         ZipEntry zipEntry = (ZipEntry)entries.nextElement();
         String name = zipEntry.getName();
         if (name.startsWith(prefix) && name.endsWith(suffix)) {
            list.add(name);
         }
      }

      String[] names = (String[])list.toArray(new String[list.size()]);
      return names;
   }

   private byte[] getOptiFineResource(String name) {
      try {
         InputStream in = this.getOptiFineResourceStream(name);
         if (in == null) {
            return null;
         } else {
            byte[] bytes = Utils.readAll(in);
            in.close();
            return bytes;
         }
      } catch (IOException var4) {
         var4.printStackTrace();
         return null;
      }
   }

   public synchronized InputStream getOptiFineResourceStream(String name) {
      name = Utils.removePrefix(name, "/");
      InputStream in = this.getOptiFineResourceStreamZip(name);
      if (in != null) {
         return in;
      } else {
         in = this.getOptiFineResourceStreamPatched(name);
         return in != null ? in : null;
      }
   }

   public InputStream getResourceStream(String path) {
      path = Utils.ensurePrefix(path, "/");
      return this.getClass().getResourceAsStream(path);
   }

   public synchronized InputStream getOptiFineResourceStreamZip(String name) {
      if (this.ofZipFile == null) {
         return null;
      } else {
         name = Utils.removePrefix(name, "/");
         ZipEntry ze = this.ofZipFile.getEntry(name);
         if (ze == null) {
            return null;
         } else {
            try {
               InputStream in = this.ofZipFile.getInputStream(ze);
               return in;
            } catch (IOException var4) {
               var4.printStackTrace();
               return null;
            }
         }
      }
   }

   public synchronized byte[] getOptiFineResourceZip(String name) {
      InputStream in = this.getOptiFineResourceStreamZip(name);
      if (in == null) {
         return null;
      } else {
         try {
            byte[] bytes = Utils.readAll(in);
            return bytes;
         } catch (IOException var4) {
            return null;
         }
      }
   }

   public synchronized InputStream getOptiFineResourceStreamPatched(String name) {
      byte[] bytes = this.getOptiFineResourcePatched(name);
      return bytes == null ? null : new ByteArrayInputStream(bytes);
   }

   public synchronized byte[] getOptiFineResourcePatched(String name) {
      if (this.patterns != null && this.patchMap != null) {
         name = Utils.removePrefix(name, "/");
         String patchName = "patch/" + name + ".xdelta";
         byte[] bytes = this.getOptiFineResourceZip(patchName);
         if (bytes == null) {
            return null;
         } else {
            try {
               byte[] bytesPatched = Patcher.applyPatch(name, bytes, this.patterns, this.patchMap, this);
               String fullMd5Name = "patch/" + name + ".md5";
               byte[] bytesMd5 = this.getOptiFineResourceZip(fullMd5Name);
               if (bytesMd5 != null) {
                  String md5Str = new String(bytesMd5, "ASCII");
                  byte[] md5Mod = HashUtils.getHashMd5(bytesPatched);
                  String md5ModStr = HashUtils.toHexString(md5Mod);
                  if (!md5Str.equals(md5ModStr)) {
                     throw new IOException("MD5 not matching, name: " + name + ", saved: " + md5Str + ", patched: " + md5ModStr);
                  }
               }

               return bytesPatched;
            } catch (Exception var10) {
               var10.printStackTrace();
               return null;
            }
         }
      } else {
         return null;
      }
   }
}
