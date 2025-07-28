package dsgc.schema;

import java.util.Objects;

// cluster,namespace,aem_service,Blobs,BlobsSize(GB),Candidates,CandidatesSize(GB),References,Duration(Hours)
public record DsgcSweep(String cluster, String namespace, String aemService, long blobs, double blobSize, long candidates,
                        double candidatesSize, long references, double durationHours) {

    @Override
    public boolean equals(Object o) {
        return o instanceof DsgcSweep dsgcSweep && Objects.equals(aemService, dsgcSweep.aemService);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(aemService);
    }

    @Override
    public String toString() {
        return aemService;
    }
}
