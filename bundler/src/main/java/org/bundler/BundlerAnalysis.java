package org.bundler;

import org.androidtransfuse.adapter.ASTField;
import org.androidtransfuse.adapter.ASTStringType;
import org.androidtransfuse.adapter.ASTType;

/**
 * @author John Ericksen
 */
public class BundlerAnalysis {

    private static final ASTType OBJECT_TYPE = new ASTStringType(Object.class.getName());

    public BundlerDescriptor analyze(ASTType target){

        BundlerDescriptor descriptor = new BundlerDescriptor(target);

        for(ASTType hierarchyLoop = target; hierarchyLoop != null && !hierarchyLoop.equals(OBJECT_TYPE); hierarchyLoop = hierarchyLoop.getSuperClass()){
            for (ASTField field : hierarchyLoop.getFields()) {
                if(field.isAnnotated(Bundled.class)){
                    descriptor.getFields().add(new FieldReference(hierarchyLoop, field));
                }
            }
        }

        return descriptor;
    }
}
