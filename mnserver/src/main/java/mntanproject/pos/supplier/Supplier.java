package mntanproject.pos.supplier;

public class Supplier {
	public long id;
    public String company,name,contactno,email,street,city,state,country,bank,notes;


    @Override
    public String toString() {
    	
    	return "name:" + name + ", company:" + company + ", contact:" + contactno +
    			"email:" + email + ",street:" + street +"city:" + city +
    			"state:"+state+",country:"+country + "bank:"+bank+",notes:" + notes;
    }

}
