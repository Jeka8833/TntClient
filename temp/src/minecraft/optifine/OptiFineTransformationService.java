package optifine;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OptiFineTransformationService implements ITransformationService {
   private static final Logger LOGGER = LogManager.getLogger();
   private static URL ofZipFileUrl;
   private static ZipFile ofZipFile;
   private static OptiFineTransformer transformer;

   public String name() {
      return "OptiFine";
   }

   public void initialize(IEnvironment environment) {
      LOGGER.info("OptiFineTransformationService.initialize");
   }

   public void beginScanning(IEnvironment environment) {
   }

   public void onLoad(IEnvironment env, Set<String> otherServices) throws IncompatibleEnvironmentException {
      LOGGER.info("OptiFineTransformationService.onLoad");
      ofZipFileUrl = OptiFineTransformer.class.getProtectionDomain().getCodeSource().getLocation();

      try {
         URI uri = ofZipFileUrl.toURI();
         File file = new File(uri);
         ofZipFile = new ZipFile(file);
         LOGGER.info("OptiFine ZIP file: " + file);
         transformer = new OptiFineTransformer(ofZipFile);
         OptiFineResourceLocator.setResourceLocator(transformer);
      } catch (Exception var5) {
         LOGGER.error((String)("Error loading OptiFine ZIP file: " + ofZipFileUrl), (Throwable)var5);
         throw new IncompatibleEnvironmentException("Error loading OptiFine ZIP file: " + ofZipFileUrl);
      }
   }

   public Entry<Set<String>, Supplier<Function<String, Optional<URL>>>> additionalResourcesLocator() {
      return super.additionalResourcesLocator();
   }

   public Entry<Set<String>, Supplier<Function<String, Optional<URL>>>> additionalClassesLocator() {
      Set<String> key = new HashSet();
      key.add("net.optifine.");
      key.add("optifine.");
      Supplier<Function<String, Optional<URL>>> value = () -> {
         return this::getResourceUrl;
      };
      Entry<Set<String>, Supplier<Function<String, Optional<URL>>>> entry = new SimpleEntry(key, value);
      LOGGER.info("additionalClassesLocator: " + key);
      return entry;
   }

   public Optional<URL> getResourceUrl(String name) {
      if (name.endsWith(".class") && !name.startsWith("optifine/")) {
         name = "srg/" + name;
      }

      if (transformer == null) {
         return Optional.empty();
      } else {
         ZipEntry ze = ofZipFile.getEntry(name);
         if (ze == null) {
            return Optional.empty();
         } else {
            try {
               String ofZipUrlStr = ofZipFileUrl.toExternalForm();
               URL urlJar = new URL("jar:" + ofZipUrlStr + "!/" + name);
               return Optional.of(urlJar);
            } catch (IOException var5) {
               LOGGER.error((Object)var5);
               return Optional.empty();
            }
         }
      }
   }

   public List<ITransformer> transformers() {
      LOGGER.info("OptiFineTransformationService.transformers");
      List<ITransformer> list = new ArrayList();
      if (transformer != null) {
         list.add(transformer);
      }

      return list;
   }

   public static OptiFineTransformer getTransformer() {
      return transformer;
   }
}
