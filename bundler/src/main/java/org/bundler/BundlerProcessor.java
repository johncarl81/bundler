package org.bundler;

import org.androidtransfuse.TransfuseAnalysisException;
import org.androidtransfuse.adapter.ASTType;
import org.androidtransfuse.transaction.ScopedTransactionBuilder;
import org.androidtransfuse.transaction.TransactionProcessor;
import org.androidtransfuse.transaction.TransactionProcessorPool;

import javax.inject.Provider;
import java.util.Collection;

/**
 * @author John Ericksen
 */
public class BundlerProcessor {

    private final TransactionProcessor processor;
    private final TransactionProcessorPool<Provider<ASTType>, BundlerDescriptor> bundlerProcessor;
    private final Provider<BundlerTransactionWorker> bundlerWorkerProvider;
    private final ScopedTransactionBuilder scopedTransactionBuilder;

    public BundlerProcessor(TransactionProcessor processor,
                            TransactionProcessorPool<Provider<ASTType>,
                                    BundlerDescriptor> bundlerProcessor,
                            Provider<BundlerTransactionWorker> bundlerWorkerProvider,
                            ScopedTransactionBuilder scopedTransactionBuilder) {
        this.processor = processor;
        this.bundlerProcessor = bundlerProcessor;
        this.bundlerWorkerProvider = bundlerWorkerProvider;
        this.scopedTransactionBuilder = scopedTransactionBuilder;
    }

    public void submit(Collection<Provider<ASTType>> parcelProviders) {
        for (Provider<ASTType> parcelProvider : parcelProviders) {
            bundlerProcessor.submit(scopedTransactionBuilder.build(parcelProvider, bundlerWorkerProvider));
        }
    }

    public void execute() {
        processor.execute();
    }

    public void checkForErrors() {
        if (!processor.isComplete()) {
            throw new TransfuseAnalysisException("@Bundled code generation did not complete successfully.", processor.getErrors());
        }
    }
}
