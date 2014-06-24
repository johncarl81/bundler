package org.bundler;

import org.androidtransfuse.adapter.ASTType;
import org.androidtransfuse.transaction.AbstractCompletionTransactionWorker;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author John Ericksen
 */
public class BundlerTransactionWorker extends AbstractCompletionTransactionWorker<Provider<ASTType>, BundlerDescriptor> {

    private final BundlerAnalysis analysis;
    private final BundlerGenerator generator;

    @Inject
    public BundlerTransactionWorker(BundlerGenerator generator, BundlerAnalysis analysis) {
        this.generator = generator;
        this.analysis = analysis;
    }

    @Override
    public BundlerDescriptor innerRun(Provider<ASTType> typeProvider) {

        ASTType type = typeProvider.get();

        BundlerDescriptor analysis = this.analysis.analyze(type);
        generator.generate(analysis);

        return analysis;
    }
}
