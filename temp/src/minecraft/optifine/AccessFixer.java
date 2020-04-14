package optifine;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.MethodNode;

public class AccessFixer {
   public static void fixMemberAccess(ClassNode classOld, ClassNode classNew) {
      List<FieldNode> fieldsOld = classOld.fields;
      List<FieldNode> fieldsNew = classNew.fields;
      Map<String, FieldNode> mapFieldsOld = getMapFields(fieldsOld);
      Iterator it = fieldsNew.iterator();

      while(it.hasNext()) {
         FieldNode fieldNew = (FieldNode)it.next();
         String idNew = fieldNew.name;
         FieldNode fieldOld = (FieldNode)mapFieldsOld.get(idNew);
         if (fieldOld != null && fieldNew.access != fieldOld.access) {
            fieldNew.access = combineAccess(fieldNew.access, fieldOld.access);
         }
      }

      List<MethodNode> methodsOld = classOld.methods;
      List<MethodNode> methodsNew = classNew.methods;
      Map<String, MethodNode> mapMethodsOld = getMapMethods(methodsOld);
      Iterator it = methodsNew.iterator();

      while(it.hasNext()) {
         MethodNode methodNew = (MethodNode)it.next();
         String idNew = methodNew.name + methodNew.desc;
         MethodNode methodOld = (MethodNode)mapMethodsOld.get(idNew);
         if (methodOld != null && methodNew.access != methodOld.access) {
            methodNew.access = combineAccess(methodNew.access, methodOld.access);
         }
      }

      List<InnerClassNode> innerClassesOld = classOld.innerClasses;
      List<InnerClassNode> innerClassesNew = classNew.innerClasses;
      Map<String, InnerClassNode> mapInnerClassesOld = getMapInnerClasses(innerClassesOld);
      Iterator it = innerClassesNew.iterator();

      while(it.hasNext()) {
         InnerClassNode innerClassNew = (InnerClassNode)it.next();
         String idNew = innerClassNew.name;
         InnerClassNode innerClassOld = (InnerClassNode)mapInnerClassesOld.get(idNew);
         if (innerClassOld != null && innerClassNew.access != innerClassOld.access) {
            int accessNew = combineAccess(innerClassNew.access, innerClassOld.access);
            innerClassNew.access = accessNew;
         }
      }

      if (classNew.access != classOld.access) {
         int accessClassNew = combineAccess(classNew.access, classOld.access);
         classNew.access = accessClassNew;
      }

   }

   private static int combineAccess(int access, int access2) {
      if (access == access2) {
         return access;
      } else {
         int MASK_ACCESS = 7;
         int accessClean = access & ~MASK_ACCESS;
         if (!isSet(access, 16) || !isSet(access2, 16)) {
            accessClean &= -17;
         }

         if (!isSet(access, 1) && !isSet(access2, 1)) {
            if (!isSet(access, 4) && !isSet(access2, 4)) {
               return !isSet(access, 2) && !isSet(access2, 2) ? accessClean : accessClean | 2;
            } else {
               return accessClean | 4;
            }
         } else {
            return accessClean | 1;
         }
      }
   }

   private static boolean isSet(int access, int flag) {
      return (access & flag) != 0;
   }

   public static Map<String, FieldNode> getMapFields(List<FieldNode> fields) {
      Map<String, FieldNode> map = new LinkedHashMap();
      Iterator it = fields.iterator();

      while(it.hasNext()) {
         FieldNode fieldNode = (FieldNode)it.next();
         String id = fieldNode.name;
         map.put(id, fieldNode);
      }

      return map;
   }

   public static Map<String, MethodNode> getMapMethods(List<MethodNode> methods) {
      Map<String, MethodNode> map = new LinkedHashMap();
      Iterator it = methods.iterator();

      while(it.hasNext()) {
         MethodNode methodNode = (MethodNode)it.next();
         String id = methodNode.name + methodNode.desc;
         map.put(id, methodNode);
      }

      return map;
   }

   public static Map<String, InnerClassNode> getMapInnerClasses(List<InnerClassNode> innerClasses) {
      Map<String, InnerClassNode> map = new LinkedHashMap();
      Iterator it = innerClasses.iterator();

      while(it.hasNext()) {
         InnerClassNode innerClassNode = (InnerClassNode)it.next();
         String id = innerClassNode.name;
         map.put(id, innerClassNode);
      }

      return map;
   }
}
