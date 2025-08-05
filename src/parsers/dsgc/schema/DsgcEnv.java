package parsers.dsgc.schema;

import com.google.common.base.Preconditions;
import parsers.fullgc.schema.EnvType;

import java.util.Objects;

// cluster,namespace,aem_service,Blobs,BlobsSize(GB),Candidates,CandidatesSize(GB),References,Duration(Hours)

// cluster,namespace,aem_service,blobs,blobs_size_gb,candidates,candidates_size_gb,references,duration_hours,mark_references,mark_size_gb

// [cluster, namespace, aem_service, blobs, blobs_size_gb, candidates, candidates_size_gb, references, duration_hours, mark_references,
// mark_size_gb, env_type, namespace_max_candidates, batch]
// ethos13-prod-can2,ns-team-aem-cm-prd-n150266,cm-p157981-e1674101,16,0.0,0.0,0.0,16.0,0.0,16,0.0009399335831403,dev,0.0,1

public record DsgcEnv(String cluster, String namespace, String aemService, long referencesTotal, long blobs,
                      double blobSize, long candidates, double candidatesSize, long references, double duration,
                      long markReferences, double markSize, EnvType envType, long nameSpaceMaxCandidates, int batch) {

    public DsgcEnv(DsgcSize dsgcSize, DsgcSweep dsgcSweep, long markReferences, double markSize,
                   long nameSpaceMaxCandidates, int batch) {
        Preconditions.checkNotNull(dsgcSize);
        Preconditions.checkNotNull(dsgcSweep);
        Preconditions.checkArgument(Objects.equals(dsgcSize.aemService(), dsgcSweep.aemService()));
        this(dsgcSize.cluster(), dsgcSize.namespace(), dsgcSize.aemService(), dsgcSize.references(), dsgcSweep.blobs(),
                dsgcSweep.blobSize(), dsgcSweep.candidates(), dsgcSweep.candidatesSize(), dsgcSweep.references(),
                dsgcSweep.durationHours(), markReferences, markSize, EnvType.UNKNOWN, nameSpaceMaxCandidates, batch);
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
