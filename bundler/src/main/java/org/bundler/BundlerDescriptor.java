package org.bundler;

import org.androidtransfuse.adapter.ASTType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Ericksen
 */
public class BundlerDescriptor {

    private final ASTType target;
    private final List<FieldReference> fields = new ArrayList<FieldReference>();

    public BundlerDescriptor(ASTType target){
        this.target = target;
    }

    public ASTType getTarget() {
        return target;
    }

    public List<FieldReference> getFields() {
        return fields;
    }
}
