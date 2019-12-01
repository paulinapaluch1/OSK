package pl.pracainz.osk.osk.json;

public class LocationJson {

	 private String nw;
	    private String ns;
	    private String time;
	    private Integer sent;

	    public LocationJson(){}

	    public LocationJson(String GPS_NW, String GPS_NS, String time) {
	        this.nw = GPS_NW;
	        this.ns = GPS_NS;
	        this.time = time;
	    }

	    public String getNw() {
	        return nw;
	    }

	    public void setNw(String nw) {
	        this.nw = nw;
	    }

	    public String getNs() {
	        return ns;
	    }

	    public void setNs(String ns) {
	        this.ns = ns;
	    }

	    public String getTime() {
	        return time;
	    }

	    public void setTime(String time) {
	        this.time = time;
	    }


	    public Integer isSent() {
	        return sent;
	    }

	    public void setSent(Integer sent) {
	        this.sent = sent;
	    }
	
	
}
