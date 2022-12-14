package es.upm.dit.apsv.traceconsumer1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trace {
    @Id
    private String traceId;
    private String truck;
    private long lastSeen;
    private double lat;
    private double lng;
    
    public Trace() {
    }

    public Trace(String traceId, String truck, long lastSeen, double lat, double lng) {
        this.traceId = traceId;
        this.truck = truck;
        this.lastSeen = lastSeen;
        this.lat = lat;
        this.lng = lng;
    }
    @Override
    public String toString() {
        return "Trace [traceId=" + traceId + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((traceId == null) ? 0 : traceId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trace other = (Trace) obj;
        if (traceId == null) {
            if (other.traceId != null)
                return false;
        } else if (!traceId.equals(other.traceId))
            return false;
        return true;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    

    
   
}
