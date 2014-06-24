package org.bundler;

import com.google.common.collect.ImmutableMap;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpression;
import org.androidtransfuse.adapter.PackageClass;
import org.androidtransfuse.gen.AbstractRepositoryGenerator;
import org.androidtransfuse.gen.ClassGenerationUtil;
import org.androidtransfuse.gen.UniqueVariableNamer;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author John Ericksen
 */
public class BundlersTransactionWorker extends AbstractRepositoryGenerator<BundlerDescriptor> {

    public static final PackageClass BUNDLES_NAME = new PackageClass(Bundles.BUNDLER_PACKAGE, Bundles.BUNDLER_NAME);
    public static final PackageClass REPOSITORY_NAME = new PackageClass(Bundles.BUNDLER_PACKAGE, Bundles.BUNDLER_REPOSITORY_NAME);

    @Inject
    public BundlersTransactionWorker(ClassGenerationUtil generationUtil, UniqueVariableNamer namer) {
        super(Repository.class, generationUtil, namer, REPOSITORY_NAME, Bundles.SilverImplementation.class);
    }

    @Override
    protected Map<? extends JExpression, ? extends JExpression> generateMapping(JDefinedClass factoryRepositoryClass, JClass interfaceClass, BundlerDescriptor concreteType) throws JClassAlreadyExistsException {
        return ImmutableMap.of();
    }
}
