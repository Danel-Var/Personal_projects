#ifndef IP_H 
#define  IP_H

#include "generic-field.h"
#include "string.h"

enum rule_type_ip { SRC_IP = 0, DES_IP  };

class IP : public GenericField {
	public:
		    /**
         * @brief Check if the network is accepted by the rule
         * @param packet the whole packet
         * @return true if the packet matches the rule (port)
        */
		bool match(const GenericString &packet) const override;
		IP(const GenericString& rule);
		~IP()= default;



	private:
		int type; // 0 = src | 1 = dst
		int ip;
		unsigned int mask;
		unsigned int maskedIP;

};

#endif


/*

src-ip   =     55.255.0.0,dst-ip=255.255.0.1,src-port=5,dst-port=22
 src-ip  =    255.255.0.0

 255.255.0.0
*/
