package org.bundler;

import org.androidtransfuse.adapter.ASTField;
import org.androidtransfuse.adapter.ASTType;

/**
 * @author John Ericksen
 */
public class FieldReference {

    private final ASTType owner;
    private final ASTField field;

    public FieldReference(ASTType owner, ASTField field) {
        this.owner = owner;
        this.field = field;
    }

    public ASTType getOwner() {
        return owner;
    }

    public ASTField getField() {
        return field;
    }
}
