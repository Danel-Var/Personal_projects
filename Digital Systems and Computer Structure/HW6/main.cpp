#include "ip.h"
#include "port.h"
#include "input.h"
#define SRC_IP_S "src-ip"
#define DST_IP_S "dst-ip"
#define SRC_PORT_S "src-port"
#define DST_PORT_S "dst-port"
/**
 * @brief check if the arguments are valid packets, afterwards parse the stdin
 *        and leave only packets that match the given rule.
 * @param argc the number of arguments
 * @param argv the arguments
 * @return 0 if the arguments are valid, 1 otherwise
*/
int main(int argc, char **argv)
{
   //check if it is ip or port rule
    const char *str = argv[1];
    String rule = String(str);
    StringArray type= rule.split("=");
    type[0]->as_string().trim();

   

    if(type[0]->as_string() == SRC_IP_S || type[0]->as_string() == DST_IP_S){
        IP ip_rule= IP(rule);
        GenericField& send_rule = ip_rule;
        parse_input(send_rule);

    }else{
        //setting the rule
        Port port_rule= Port(rule);        
        GenericField& send_rule = port_rule;
        parse_input(send_rule);
        
    }
    
    /*
    const char* str_rule= "dst-ip=255.255.0.0/16";
    GenericString* rule =make_string(str_rule);
    IP my_port= IP(*rule);
    
    const char* packet_s= "src-ip=255.255.0.0,dst-ip=254.255.0.1,src-port=5,dst-port=22";
    bool flag = true;
    GenericString* packet =make_string(packet_s);
    flag = my_port.match(*packet);
    */
    

   
    return 0;
}
