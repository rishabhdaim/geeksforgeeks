package dsgc.schema;

import com.google.common.base.Preconditions;

import java.util.Objects;

// cluster,namespace,aem_service,Blobs,BlobsSize(GB),Candidates,CandidatesSize(GB),References,Duration(Hours)

public record DsgcEnv(String cluster, String namespace, String aemService, long referencesTotal, long blobs,
                      double blobSize, long candidates, double candidatesSize, long references, double duration) {

    public DsgcEnv(DsgcSize dsgcSize, DsgcSweep dsgcSweep) {
        Preconditions.checkNotNull(dsgcSize);
        Preconditions.checkNotNull(dsgcSweep);
        Preconditions.checkArgument(Objects.equals(dsgcSize.aemService(), dsgcSweep.aemService()));
        this(dsgcSize.cluster(), dsgcSize.namespace(), dsgcSize.aemService(), dsgcSize.references(), dsgcSweep.blobs(), dsgcSweep.blobSize(),
                dsgcSweep.candidates(), dsgcSweep.candidatesSize(), dsgcSweep.references(), dsgcSweep.durationHours());
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
