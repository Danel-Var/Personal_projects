#include "ip.h"




bool IP::match(const GenericString& packet) const{
	//bool matching = false;
	//assumming the packet is valid,
	// we dont need to check the split size
	
		const String& str_c= packet.as_string();
		String newPacket = String(str_c);
		
		// maybe we need to do it with " " if we do sed s/,/ /g
		StringArray segments = (newPacket.as_string()).split(","); 
		//(segments[SRC_IP]->as_string()).trim();
		//(segments[DES_IP]->as_string()).trim();
		//either the 1st or 3rd cell
		if(segments.size()<4 ){
			return false;
		}
		
		StringArray ip = (segments[type]->as_string()).split("=");
		(ip[1]->as_string()).trim();
		
		StringArray only_ip= (ip[1]->as_string()).split(".");
		unsigned int extracted = ((only_ip[0]->as_string()).to_integer())<<24
								 |((only_ip[1]->as_string()).to_integer())<<16
								 |((only_ip[2]->as_string()).to_integer())<<8
								 | ((only_ip[3]->as_string()).to_integer());
								 // do we need to remove the dots?
		return ((extracted & this->mask) == this->maskedIP);
		
}

// recieves src-ip=XXX.XXX.XXX.XXX/XX 

IP::IP(const GenericString& rule) { 
	 const String& str_c= rule.as_string();
	 String newRule = String(str_c);
	
	// the number in the name refers to the wanted cell
	
	StringArray mask1 = newRule.split("/");
	
	mask1[1]->as_string().trim();
	
	StringArray ip1 = mask1[0]->as_string().split("=");
	ip1[1]->as_string().trim();
	StringArray type0 = ip1[0]->as_string().split("-");
	type0[0]->as_string().trim();
	
	if(type0[0]->as_string()=="src")
		this->type = SRC_IP;
	else if(type0[0]->as_string() == "dst")
		this->type = DES_IP;

	

	this->mask = -1u;
	StringArray only_ip= ip1[1]->as_string().split(".");
	only_ip[0]->as_string().trim();
	only_ip[1]->as_string().trim();
	only_ip[2]->as_string().trim();
	only_ip[3]->as_string().trim();
	unsigned int extracted = (only_ip[0]->as_string().to_integer())<<24
							 |(only_ip[1]->as_string().to_integer())<<16
							 |(only_ip[2]->as_string().to_integer())<<8
							 | (only_ip[3]->as_string().to_integer());
							 // do we need to remove the dots?
	this->ip = extracted;
	int maskint = mask1[1]->as_string().to_integer();

	this->mask = this->mask <<(32-maskint);
		
	
	
	this->maskedIP = this->mask & this->ip;

}

