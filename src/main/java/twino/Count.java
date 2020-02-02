package twino;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Count {

    public Count() {
        this.ip = null;
        this.count = 0;
    }

    public Count(String ip, int count) {
        this.ip = ip;
        this.count = count;
    }

    @Id
    private String ip;
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    private int count;
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}
