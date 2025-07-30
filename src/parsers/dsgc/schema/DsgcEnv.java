package parsers.dsgc.schema;

import com.google.common.base.Preconditions;
import parsers.fullgc.schema.EnvType;

import java.util.Objects;

// cluster,namespace,aem_service,Blobs,BlobsSize(GB),Candidates,CandidatesSize(GB),References,Duration(Hours)

// cluster,namespace,aem_service,blobs,blobs_size_gb,candidates,candidates_size_gb,references,duration_hours,mark_references,mark_size_gb

public record DsgcEnv(String cluster, String namespace, String aemService, long referencesTotal, long blobs,
                      double blobSize, long candidates, double candidatesSize, long references, double duration,
                      long markReferences, double markSize, EnvType envType) {

    public DsgcEnv(DsgcSize dsgcSize, DsgcSweep dsgcSweep, long markReferences, double markSize) {
        Preconditions.checkNotNull(dsgcSize);
        Preconditions.checkNotNull(dsgcSweep);
        Preconditions.checkArgument(Objects.equals(dsgcSize.aemService(), dsgcSweep.aemService()));
        this(dsgcSize.cluster(), dsgcSize.namespace(), dsgcSize.aemService(), dsgcSize.references(), dsgcSweep.blobs(),
                dsgcSweep.blobSize(), dsgcSweep.candidates(), dsgcSweep.candidatesSize(), dsgcSweep.references(),
                dsgcSweep.durationHours(), markReferences, markSize, EnvType.UNKNOWN);
    }

        @Override
        public boolean equals (Object o){
            return o instanceof DsgcEnv dsgcSweep && Objects.equals(aemService, dsgcSweep.aemService);
        }

        @Override
        public int hashCode () {
            return Objects.hashCode(aemService);
        }

        @Override
        public String toString () {
            return cluster + " " + namespace + " " + aemService + " " + candidates + " " + blobs + " " + referencesTotal + " " + references;
        }
}
