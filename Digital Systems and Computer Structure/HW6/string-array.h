#ifndef STRING_ARRAY_H
#define STRING__ARRAY_H
#include <vector>
#include "generic-string.h"



//will function as an ADT
class StringArray {
    //PRAVITE FILDES
    std::vector<GenericString*> my_string_array;    
    
    public:
    // Constructor
    StringArray();
    //destructor
    ~StringArray();

    //functions    
    /**
     * @brief push a new element.
     */
    void Push(GenericString* str);
     /**
     * @brief Overloaded operator[] to access elements
     */ 
    GenericString* operator[](int index) const;
     /**
     * @brief Method to get the size of the array
     */  
    int size() const;    
};

#endif